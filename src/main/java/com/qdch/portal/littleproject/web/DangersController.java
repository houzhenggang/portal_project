package com.qdch.portal.littleproject.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.entity.KeHuFenLei;
import com.qdch.portal.littleproject.entity.LittleProjectDto;
import com.qdch.portal.littleproject.entity.LittleProjectEntity;
import com.qdch.portal.littleproject.entity.Risks;
import com.qdch.portal.littleproject.entity.Single;
import com.qdch.portal.littleproject.entity.UnknownIndex;
import com.qdch.portal.littleproject.service.DangerService;

/**
 * 风险
 * 
 * @author wangsw
 * @time 2018年4月24日
 */
@Controller
public class DangersController extends BaseController {

	@Autowired
	public DangerService dangerService;

	/**
	 * 风险——未然指数-未然指数值
	 * 
	 * @author wangsw
	 * @time 2018年5月2日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/weiranindex" })
	@ResponseBody
	public String weiranIndexs(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> riskIndexs = dangerService
					.getMarketRiskIndex();
			DynamicDataSource.removeDataSourceKey();
			if (riskIndexs == null || riskIndexs.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			List<KeHuFenLei> riskList = new ArrayList<KeHuFenLei>();

			String tradeName = "";
			String exValue = "";
			String scpg = "";
			KeHuFenLei ke = null;
			for (Map<String, Object> map : riskIndexs) {
				ke = new KeHuFenLei();
				tradeName = formatterString(map.get("jysinfo"));
				exValue = formatterString(map.get("wrzs"));
				scpg = formatterString(map.get("scpg"));
				ke.setGrs(tradeName);
				ke.setJgs(exValue);
				ke.setScpg(scpg);
				riskList.add(ke);
			}
			// 数据传输对象收集
			dto.setIndexs(riskList);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			logger.warn("风险——未然指数-未然指数值", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 风险——未然指数-未然指数趋势
	 * 
	 * @author wangsw
	 * @time 2018年4月24日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/weiranqushi" })
	@ResponseBody
	public String weiranqushi(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> lists = dangerService.getWeiRanTrend();
			List<Map<String, Object>> tradeLists = dangerService
					.getFinanceMarket();
			DynamicDataSource.removeDataSourceKey();
			if (lists == null || lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			}

			LittleProjectDto dto = new LittleProjectDto();
			// 时间集合
			HashSet<String> times = new LinkedHashSet<String>();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();

			String date = "";
			String jys = "";
			LittleProjectEntity aa = null;
			String tradeName = "";
			String exValue = "";
			List<String> exVList = null;
			String totalTradeV = "总量风险值趋势";
			Map<String, Object> totalMap = new HashMap<String, Object>();
			totalMap.put("jysinfo", totalTradeV);
			tradeLists.add(totalMap);
			for (Map<String, Object> trade : tradeLists) {
				tradeName = trade.get("jysinfo") == null ? "" : trade.get(
						"jysinfo").toString();
				exVList = new ArrayList<String>();
				for (Map<String, Object> map : lists) {
					jys = map.get("jys") == null ? "" : map.get("jys")
							.toString();
					if (tradeName.equals(jys)
							&& StringUtils.isNotEmpty(tradeName)) {
						exValue = map.get("fvalue") == null ? "" : map.get(
								"fvalue").toString();
						if (StringUtils.isEmpty(exValue)) {
							continue;
						}
						date = map.get("date") == null ? "" : map.get("date")
								.toString();
						exVList.add(exValue);
						times.add(date);
					} else {
						continue;
					}
				}
				if (exVList.size() > 0) {
					aa = new LittleProjectEntity();
					aa.setName(tradeName);
					aa.setLists(exVList);
					res.add(aa);
				}
			}
			// 数据传输对象收集
			dto.setTimes(times.toArray());
			dto.setEntities(res);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			logger.warn("风险——未然指数-未然指数趋势", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 风险-未然指数-风险统计
	 *
	 * @time 2018年4月24日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/fengxiantongji" })
	@ResponseBody
	public String fengxiantongji(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			LittleProjectDto dto = new LittleProjectDto();
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> lists = dangerService.getFengxiantongji();
			List<Map<String, Object>> tradelist = dangerService
					.getFinanceMarket();
			DynamicDataSource.removeDataSourceKey();
			// 交易市场集合
			List<LittleProjectEntity> res = new ArrayList<LittleProjectEntity>();
			if (tradelist != null && tradelist.size() > 0) {
				LittleProjectEntity aa = null;
				for (Map<String, Object> o : tradelist) {
					aa = new LittleProjectEntity();
					aa.setName(o.get("jysinfo") + "");
					res.add(aa);
				}
				aa = new LittleProjectEntity();
				aa.setName("总量");
				res.add(aa);
			}
			if (res != null && res.size() > 0) {
				List<String> shiChan = null;
				for (LittleProjectEntity s : res) {
					shiChan = new ArrayList<String>();
					if (lists != null && lists.size() > 0) {
						for (Map<String, Object> o : lists) {
							if (o.get("jysinfo") == null) {
								continue;
							}
							if (o.get("jysinfo").toString().equals(s.getName())) {
								shiChan.add(o.get("bzfxz") + "");
								shiChan.add(o.get("byfxz") + "");
								shiChan.add(o.get("bnfxz") + "");
								shiChan.add(o.get("ljfxz") + "");
							}
						}

					}
					s.setLists(shiChan);
				}
			}
			dto.setEntities(res);
			if (lists == null || lists.size() < 0) {
				return this.resultSuccessData(request, response, "", null);
			} else {
				return this.resultSuccessData(request, response, "", dto);
			}
		} catch (Exception e) {
			logger.warn("风险-未然指数-风险统计", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 风险-金融资产类&商品类风险监测
	 *
	 * @time 2018年4月25日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/riskMonitoring" })
	@ResponseBody
	public String riskMonitoring(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			UnknownIndex dto = null;
			// 2是金融资产类，1是商品类
			if ("2".equals(type) || "1".equals(type)) {
				DynamicDataSource.setInsightDataSource();
				List<Map<String, Object>> riskList = dangerService
						.getMarketRiskQtyAndRatio(type);
				List<Map<String, Object>> marketList = dangerService
						.getMarketByType(type);
				DynamicDataSource.removeDataSourceKey();
				if (riskList == null || riskList.size() < 1
						|| marketList == null || marketList.size() < 1) {
					return this.resultSuccessData(request, response, "", null);
				}
				String riskQty = "";
				String riskRatio = "";
				String riskName = "";
				dto = new UnknownIndex();
				List<Risks> risks = new ArrayList<Risks>();
				Risks risk = null;
				for (Map<String, Object> map : riskList) {
					risk = new Risks();
					riskQty = formatterString(map.get("fxsl"));
					riskRatio = formatterString(map.get("fxzb"));
					riskName = formatterString(map.get("fxlb"));
					risk.setName(riskName);
					risk.setPercentage(riskRatio);
					risk.setCreditRisks(Integer.parseInt(riskQty));
					risks.add(risk);
				}
				List<KeHuFenLei> markets = new ArrayList<KeHuFenLei>();
				KeHuFenLei market = null;
				for (Map<String, Object> map : marketList) {
					market = new KeHuFenLei();
					market.setGrs(formatterString(map.get("jys")));
					market.setJgs(formatterString(map.get("jysinfo")));
					markets.add(market);
				}
				dto.setA(markets);
				dto.setB(risks);
			}
			if (dto != null) {
				return this.resultSuccessData(request, response, "", dto);
			} else {
				return this.resultSuccessData(request, response, "", null);
			}
		} catch (Exception e) {
			logger.warn("风险-金融资产类&商品类风险监测", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 风险-金融资产类风险监测-风险事件列表
	 *
	 * @time 2018年4月25日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/jysRisk" })
	@ResponseBody
	public String jysRisk(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UnknownIndex dto = null;
			String type = request.getParameter("type");
			String risk = request.getParameter("risk");
			String market = request.getParameter("market");
			if (StringUtils.isNotBlank(risk)) {
				risk = URLDecoder.decode(risk, "UTF-8");
			}
			if (StringUtils.isNotBlank(market)) {
				market = URLDecoder.decode(market, "UTF-8");
			}
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> eventList = dangerService
					.getMarketRiskEventList(type, risk, market);
			DynamicDataSource.removeDataSourceKey();
			if (eventList == null || eventList.size() < 1) {
				return this.resultSuccessData(request, response, "", null);
			}
			dto = new UnknownIndex();
			List<Single> aggregate = new ArrayList<Single>();
			List<String> itemList = null;
			Single s = null;
			for (Map<String, Object> map : eventList) {
				itemList = new ArrayList<String>();
				s = new Single();
				itemList.add(formatterString(map.get("fxzb")));
				itemList.add(formatterString(map.get("fxzbz")));
				itemList.add(formatterString(map.get("yz")));
				itemList.add(formatterString(map.get("yjsj")));
				itemList.add(formatterString(map.get("clzt")));
				s.setS(itemList);
				aggregate.add(s);
			}
			dto.setC(aggregate);
			return this.resultSuccessData(request, response, "", dto);
		} catch (Exception e) {
			logger.warn(" 风险-金融资产类风险监测-风险事件列表", e);
			return this.resultFaliureData(request, response, "", null);
		}

	}

	/**
	 * 风险-金融资产类风险监测-交易市场列表
	 *
	 * @time 2018年5月10日
	 * @author wangsw
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/riskMarkets" })
	@ResponseBody
	public String getRiskMarkets(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			String risk = request.getParameter("risk");
			if (StringUtils.isNotBlank(risk)) {
				risk = URLDecoder.decode(risk, "UTF-8");
			}
			DynamicDataSource.setInsightDataSource();
			List<Map<String, Object>> markets = dangerService.getMarketList(
					type, risk);
			DynamicDataSource.removeDataSourceKey();
			if (markets == null || markets.size() < 1) {
				return this.resultSuccessData(request, response, "", null);
			}
			
			return this.resultSuccessData(request, response, "", markets);
		} catch (Exception e) {
			logger.warn(" 风险-金融资产类风险监测-交易市场列表", e);
			return this.resultFaliureData(request, response, "", null);
		}

	}
	private String formatterString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}
