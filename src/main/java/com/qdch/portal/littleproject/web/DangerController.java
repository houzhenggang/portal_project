package com.qdch.portal.littleproject.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.BusinessInfoModelDao;
import com.qdch.portal.littleproject.dao.BusinessRelationModelDao;
import com.qdch.portal.littleproject.dao.EvaluateScoreModelDao;
import com.qdch.portal.littleproject.dao.MarketDynamicModelDao;
import com.qdch.portal.littleproject.dao.RadarModelDao;
import com.qdch.portal.littleproject.dao.ShareHolderModelDao;
import com.qdch.portal.littleproject.entity.BusinessInfoModel;
import com.qdch.portal.littleproject.entity.BusinessRelationModel;
import com.qdch.portal.littleproject.entity.CompanyRelation;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.MarketDynamic;
import com.qdch.portal.littleproject.entity.MarketDynamicModel;
import com.qdch.portal.littleproject.entity.Portrait;
import com.qdch.portal.littleproject.entity.RadarModel;
import com.qdch.portal.littleproject.entity.ShareHolderModel;
import com.qdch.portal.littleproject.entity.Single;
import com.qdch.portal.littleproject.entity.ZiJin;

/**
 * 
 * 画像
 * 
 * @author gaozhao
 * @time 2018年4月18日
 */
@Controller
public class DangerController extends BaseController {
	@Autowired
	public RadarModelDao radarModelDao;//画像-风险雷达图
	@Autowired
	public ShareHolderModelDao shareHolderModelDao;//股东信息
	@Autowired
	public EvaluateScoreModelDao evaluateScoreDao;//市场评价分数
	@Autowired
	public MarketDynamicModelDao marketDynamicModelDao;//市场动态
	@Autowired
	public BusinessInfoModelDao businessInfoModelDao;//工商信息
	@Autowired
	public BusinessRelationModelDao businessRelationModelDao;//企业关系



	/**
	 * 画像-风险雷达图
	 *
	 * @time 2018年4月20日
	 * @author gaozhao
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = { "${portalPath}/littleproject/portraitRadar" })
	@ResponseBody
	public String portraitRadar(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//切换数据源
			DynamicDataSource.setInsightDataSource();
			Portrait dto = new Portrait();
			String type = request.getParameter("type");
			String[] risks = { "用户风险", "产品风险", "资金风险", "工商风险", "舆情风险" };
			List<KeHuFenLei> aggregate = new ArrayList<KeHuFenLei>(500);
			List<RadarModel> lists = new ArrayList<RadarModel>(500);
			if ("1".equals(type) || "2".equals(type)) {

				if ("1".equals(type)) {

					dto.setName("青金中心");
					lists = radarModelDao.getRadarModelDao("0014");

				} else {

					dto.setName("联合信产");
					lists = radarModelDao.getRadarModelDao("0012");

				}

			} else if ("3".equals(type)) {
				risks = new String[] { "工商风险", "舆情风险", "客户风险", "资金风险",
						"交易风险", "产品风险" };
				dto.setName("文化产权");
				lists = radarModelDao.getRadarModelDao("0015");

			}
			//切回原来数据源（切回mysql）
			DynamicDataSource.removeDataSourceKey();
			String scpg = "";
			if (lists != null && lists.size() > 0) {
				scpg = lists.get(0).getScpg();
				dto.setScpg(scpg == null ? "" : scpg );
				boolean flag = false; // 判断是否有风险
				for (String risk : risks) {
					for (RadarModel o : lists) {
						if (risk.equals(o.getFxlb())) {
							flag = true;
							KeHuFenLei k = new KeHuFenLei();
							k.setGrs(risk);
							k.setJgs(o.getWrzs() + "");
							aggregate.add(k);
							break;
						}
					}
					if (!flag) {
						KeHuFenLei k = new KeHuFenLei();
						k.setGrs(risk);
						k.setJgs("0");
						aggregate.add(k);
					}
					flag = false; 
				}
			} else {
				dto.setScpg("");
				for (String risk : risks) {
					KeHuFenLei k = new KeHuFenLei();
					k.setGrs(risk);
					k.setJgs("0");
					aggregate.add(k);
				}
			}
			dto.setRadar(aggregate);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			logger.warn("画像-风险雷达图", e);
			return this.resultFaliureData(request, response, "", null);
		}

	}

	/**
	 * 画像-股东信息
	 *
	 * @time 2018年4月20日
	 * @author gaozhao
	 * @param request
	 * @param response
	 * @return
	 */
	

	@RequestMapping(value = { "${portalPath}/littleproject/Shareholder" })
	@ResponseBody
	public String Shareholder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			String type = request.getParameter("type");
			Portrait dto = new Portrait();
			List<ShareHolderModel> shareHolderLists = new ArrayList<ShareHolderModel>(500);
			List<ZiJin> zijiAggregate = new ArrayList<ZiJin>();
			if ("1".equals(type) || "2".equals(type)) {

				if ("1".equals(type)) {

					dto.setName("青金中心");
					shareHolderLists = shareHolderModelDao
							.getShareHolderModelDao("0014");

				} else {

					dto.setName("联合信产");
					shareHolderLists = shareHolderModelDao
							.getShareHolderModelDao("0012");

				}

			} else if ("3".equals(type)) {

				dto.setName("文化产权");
				shareHolderLists = shareHolderModelDao
						.getShareHolderModelDao("0015");

			}
			// 股东信息
			if (shareHolderLists != null && shareHolderLists.size() > 0) {
				for (ShareHolderModel o : shareHolderLists) {
					ZiJin ziJin = new ZiJin();
					List<String> aggregate3 = new ArrayList<String>(100);
					aggregate3.add(o.getName());
					aggregate3.add(o.getPay() + "");
					aggregate3.add(o.getPay_date());
					aggregate3.add(o.getScale() + "%");
					ziJin.setA(aggregate3);
					zijiAggregate.add(ziJin);
				}

			}
			dto.setShareholder(zijiAggregate);
			DynamicDataSource.removeDataSourceKey();
			if (shareHolderLists == null) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}
		} catch (Exception e) {
			logger.warn(" 画像-股东信息", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 画像-市场评价分数
	 *
	 * @time 2018年4月24日
	 * @author gaozhao
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = { "${portalPath}/littleproject/evaluateScore" })
	@ResponseBody
	public String evaluateScore(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			DynamicDataSource.setInsightDataSource();
			String type = request.getParameter("type");
			String tString = "";
			Map<String,Object> data = new HashMap<String,Object>();
			List<Map<String,Object>> lists = null;
			if ("1".equals(type) || "2".equals(type)) {

				if ("1".equals(type)) {
					tString = "0014";
					data.put("jys", tString);
					lists = evaluateScoreDao.evaluateScore(tString);

				} else {
					tString = "0012";
					data.put("jys", tString);
					lists = evaluateScoreDao.evaluateScore(tString);

				}

			} else if ("3".equals(type)) {
				tString = "0015";
				data.put("jys", tString);
				lists = evaluateScoreDao.evaluateScore(tString);

			}
			DynamicDataSource.removeDataSourceKey();
			double sum = 0;
			if (lists != null && lists.size() > 0) {
				for (Map<String,Object> o : lists) {
					sum = sum + formatterNumber(o.get("df"));
				}
			}
			data.put("sum", sum);
			data.put("ability", lists);
			
			Map<String,Object> dto = new HashMap<String,Object>();
			dto.put("status", "success");
			dto.put("msg", "");
			dto.put("data", data);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			logger.warn(" 画像-市场评价分数", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 画像-市场动态
	 *
	 * @time 2018年4月25日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	

	@RequestMapping(value = { "${portalPath}/littleproject/marketDynamic" })
	@ResponseBody
	public String marketDynamic(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			String page = request.getParameter("type");// 前台传入第一页以1开始
			List<MarketDynamicModel> lists = marketDynamicModelDao
					.getMarketDynamicModelDao((Integer.parseInt(page) - 1) * 4);
			List<MarketDynamic> marketList = new ArrayList<MarketDynamic>(500);
			if (lists != null && lists.size() > 0) {
				for (MarketDynamicModel m : lists) {
					MarketDynamic market = new MarketDynamic();
					market.setTitle(m.getTitle());
					market.setData_source(m.getData_source());
					market.setPublish_date(m.getPublish_date());
					marketList.add(market);
				}
			}
			DynamicDataSource.removeDataSourceKey();
			if (lists == null) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", marketList);
			}
		} catch (Exception e) {

			logger.warn("画像-市场动态", e);
			return this.resultFaliureData(request, response, "", null);

		}
	}

	/**
	 * 画像-工商信息
	 *
	 * @time 2018年4月25日
	 * @author gaozhao
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = { "${portalPath}/littleproject/businessInfo" })
	@ResponseBody
	public String businessInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			String type = "";
			type = request.getParameter("type");
			String tString = "";
			Portrait dto = new Portrait();
			Single single = new Single();

			List<BusinessInfoModel> lists = new ArrayList<BusinessInfoModel>(500);

			List<String> aggregate2 = new ArrayList<String>(500);
			if ("1".equals(type) || "2".equals(type)) {

				if ("1".equals(type)) {
					tString = "青金中心";
					dto.setName(tString);
					lists = businessInfoModelDao.getBusinessInfoModelDao();

				} else {
					tString = "联合信产";
					dto.setName(tString);
					lists = businessInfoModelDao.getBusinessInfoModelDao2();

				}

			} else if ("3".equals(type)) {
				tString = "文化产权";
				dto.setName(tString);
				lists = businessInfoModelDao.getBusinessInfoModelDao3();

			}
			if (lists != null && lists.size() > 0) {
				for (BusinessInfoModel o : lists) {

					aggregate2.add(o.getLegal_person());// 法定代表人
					aggregate2.add(o.getCreate_date());// 建立日期
					aggregate2.add(o.getRegister_money() + "");// 注册资本
					aggregate2.add(o.getRegister_code());// 工商注册号
					aggregate2.add(o.getOrganizition_code());// 组织机构代码
					aggregate2.add(o.getCredit_code());// 统一信用代码
					aggregate2.add(o.getTaxpayer_num());// 纳税人识别号
					aggregate2.add(o.getEnglish_name());// 英文名
					aggregate2.add(o.getBusiness_status());// 经营状态
					aggregate2.add(o.getCompany_type());// 企业类型
					aggregate2.add(o.getIndustry());// 行业
					aggregate2.add(o.getBusiness_limit());// 营业期限
					aggregate2.add(o.getPublish_date());// 核准日期
					aggregate2.add(o.getRegister_address());// 企业地址
					aggregate2.add(o.getBusiness_scope());// 经营范围

				}
				single.setS(aggregate2);
			}
			dto.setInfo(single);
			DynamicDataSource.removeDataSourceKey();
			if (lists == null) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}
		} catch (Exception e) {
			logger.warn("画像-工商信息", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 画像-企业关系
	 *
	 * @time 2018年4月27日
	 * @author 高照
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = { "${portalPath}/littleproject/BusinessRelation" })
	@ResponseBody
	public String BusinessRelation(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			String type = "";
			type = request.getParameter("type");
			List<BusinessRelationModel> lists = new ArrayList<BusinessRelationModel>(500);
			lists = businessRelationModelDao.getBusinessRelationModelDao(type);
			CompanyRelation dto = new CompanyRelation();
			if (lists != null && lists.size() > 0) {
				for (BusinessRelationModel b: lists) {
					dto.setCompanyName(b.getCompany_name());
					dto.setLegalPerson(b.getLegal_person());
					String xString = b.getSenior_managers();
					String x0String = xString.substring(2, xString.length() - 2);
					String yString= b.getShareholders();
					String y0String = yString.substring(2, yString.length() - 2);
					String[] x1Array = x0String.split("\",\"");
					String[] y1Array = y0String.split("\",\"");
					List<String> aggeratex = Arrays.asList(x1Array);
					List<String> aggeratey = Arrays.asList(y1Array);
					dto.setManagers(aggeratex);
					dto.setShareholders(aggeratey);
				}
			}
			DynamicDataSource.removeDataSourceKey();
			if (lists == null) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}
		} catch (Exception e) { 
			logger.warn("画像-企业关系", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}


	/**
	 * 以下是小程序画像-工商信息
	 */

	@RequestMapping(value = { "${portalPath}/littleproject/getIndusAndBusInfo" })
	@ResponseBody
	public String getIndusAndBusInfo(HttpServletRequest request,HttpServletResponse response){

		return "";
	}
	
	private String formatterString(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	private double formatterNumber(Object obj) {
		return obj == null ? 0 : Double.parseDouble(formatterString(obj));
	}
}
