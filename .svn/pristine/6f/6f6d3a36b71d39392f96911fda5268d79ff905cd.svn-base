package com.qdch.portal.littleproject.service.business;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.service.BaseService;
import com.qdch.portal.common.utils.AESUtil;
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;

/**
 * @author hansz
 * @version 2018年5月10日 下午4:52:22
 * @Description TODO
 */
@Service
public class BusinessInfoService extends BaseService{
	
	private static final String APPKEY = Global.getBusinessAppKey();
	private static final String REDIS_EXPIRATION_TIME = Global.getConfig("redis.expirationTime");
	
	
	/**
	 * @author hansz
	 * @version 2018年5月14日 下午3:37:33
	 * @Description TODO 风险信息-行政处罚
	 * @param companyInfo 企业全名; redisKey redis中存放的key
	 */
	public String getAdminPenaltyMessage(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getAdminPunishByNameUrl = Global.getBusinessInfoAddress("businessInfo.getAdminPunishByName");
			String getEPByNameUrl = Global.getBusinessInfoAddress("businessInfo.getEPByName");
			String getEPDetailByIdUrl = Global.getBusinessInfoAddress("businessInfo.getEPDetailById");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> getSonList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			//根据企业名获取企业工商行政处罚信息
			getResultJson(getAdminPunishByNameUrl, companyInfo, "keyword", 0, getList, APPKEY);
//			getResultJson(getAdminPunishByNameUrl, companyInfo, "keyword", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class); 
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 行政处罚决定书文号
						successMap.put("number", dataJson.get("number"));
						// 违法行为类型
						successMap.put("illegal_type", dataJson.get("illegal_type"));
						// 行政处罚内容
						successMap.put("content", dataJson.get("content"));
						// 作出行政处罚决定机关名称
						successMap.put("department", dataJson.get("department"));
						// 作出行政处罚决定日期
						successMap.put("date", dataJson.get("date"));

						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				}else {
					errorMessage = (String) map.get("message");
				}
			}
			getList.clear();
			//根据企业名获取获取企业环保处罚信息，其中包括处罚id
			getResultJson(getEPByNameUrl, companyInfo, "name", 0, getList, "2711d79cc04940509a20231a219fe6a5");
//			getResultJson(getEPByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(((String) map.get("status")))) {
					String data = (String) map.get("data");
					List<HashMap> dataList =JSON.parseArray(data, HashMap.class); 
					for(HashMap<String, Object> dataJson : dataList) {
						//根据环保处罚id获取企业环保处罚详细信息
						getResultJson(getEPDetailByIdUrl, (String)dataJson.get("id"), "id", 0, getSonList, "2711d79cc04940509a20231a219fe6a5");
//						getResultJson(getEPDetailByIdUrl, (String)dataJson.get("id"), "id", 0, getSonList);
						for (Map<String, Object> sonMap : getSonList) {
							if ("200".equals(((String) sonMap.get("status")))) {
								String sonData = (String) sonMap.get("data");
								List<HashMap> sonDataList = JSON.parseArray(sonData, HashMap.class);
								for (HashMap<String, Object> sonDataJson : sonDataList) {
									Map<String, Object> successMap = new HashMap<String, Object>();
									// 行政处罚决定书文号
									successMap.put("number", dataJson.get("document_no"));
									// 作出行政处罚决定机关名称
									successMap.put("department", dataJson.get("punishment_dept"));
									// 作出行政处罚决定日期
									successMap.put("date", dataJson.get("punishment_date"));
									// 违法行为类型
									successMap.put("illegal_type", sonDataJson.get("punishment_title"));
									// 行政处罚内容
									successMap.put("content", sonDataJson.get("punishment_result"));
									resultList.add(successMap);
								}
							}else if ("201".equals(sonMap.get("status"))) {
								resultNullList.add(sonMap);
							} else {
								errorMessage = (String) sonMap.get("message");
							}
						}
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				//将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				//返回数据条数
				resultMap.put("num", resultListSize);
				//风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "date", -2);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 上午9:33:53
	 * @Description TODO 风险信息-企业欠税  
	 */
	public String getOverDueTax(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getOverDueTaxByNameUrl = Global.getBusinessInfoAddress("businessInfo.getEquityQualitiesByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			BigDecimal totalAmount = new BigDecimal("0");
			String errorMessage = null;
			String result = "";
			//根据企业名获取企业欠税信息
//			getResultJson(getOverDueTaxByNameUrl, companyInfo, "name", 0, getList, "8aa2acad0dd949be85b295397d4ea759");
			getResultJson(getOverDueTaxByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class); 
					for(HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 纳税人识别号
						successMap.put("taxpayer_num", dataJson.get("taxpayer_num"));
						// 欠税税种
						successMap.put("overdue_type", dataJson.get("overdue_type"));
						// 欠税余额
						successMap.put("content", dataJson.get("overdue_amount"));
						// 当前发生的欠税余额
						successMap.put("curr_overdue_amount", dataJson.get("curr_overdue_amount"));
						// 发布日期
						successMap.put("date", dataJson.get("pub_date"));
						
						resultList.add(successMap);
						//企业欠税总额
						BigDecimal content = new BigDecimal((String)(dataJson.get("overdue_amount")));
						totalAmount = totalAmount.add(content);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				}else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				//将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				//返回数据条数
				resultMap.put("num", resultListSize);
				//欠税总金额
				resultMap.put("totalAmount", totalAmount.toString());
				//风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 上午11:46:58
	 * @Description TODO 风险信息-股权出质
	 */
	public String getEquityQualities(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getOverDueTaxByNameUrl = Global.getBusinessInfoAddress("businessInfo.getEquityQualitiesByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			//根据企业名获取企业欠税信息
			getResultJson(getOverDueTaxByNameUrl, companyInfo, "name", 0, getList, "08351BB4D71545AE8F1105374A2DF7B0");
//			getResultJson(getOverDueTaxByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class); 
					for(HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 登记日期
						successMap.put("date", dataJson.get("date"));
						// 登记编号
						successMap.put("number", dataJson.get("number"));
						// 出质人
						successMap.put("pledgor", dataJson.get("pledgor"));
						// 质权人
						successMap.put("pawnee", dataJson.get("pawnee"));
						// 状态
						successMap.put("status", dataJson.get("status"));
						// 出质人证件号码
						String pledgorIdentifyNo = (String) dataJson.get("pledgor_identify_no");
//						if (pledgorIdentifyNo.indexOf("*") == -1) {
//							pledgorIdentifyNo = "（非公示项）";
//						}
						successMap.put("pledgor_identify_no", pledgorIdentifyNo);
						// 质权人证件号码
						String pawneeIdentifyNo = (String) dataJson.get("pawnee_identify_no");
//						if (pawneeIdentifyNo.indexOf("*") == -1) {
//							pawneeIdentifyNo = "（非公示项）";
//						}
						successMap.put("pawnee_identify_no", pawneeIdentifyNo);
						// 出质股权数
						successMap.put("pledgor_amount", dataJson.get("pledgor_amount"));
						// 备注
						successMap.put("remark", dataJson.get("remark"));
						
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				}else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				//将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				//返回数据条数
				resultMap.put("num", resultListSize);
				//风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "date", -2);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}

	/**
	 * @author hansz
	 * @version 2018年5月15日 下午1:53:35
	 * @Description TODO 风险信息-动产抵押列表 
	 */
	public String getMortgages(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getOverDueTaxByNameUrl = Global.getBusinessInfoAddress("businessInfo.getMortgagesByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			//根据企业名获取企业动产抵押列表
			getResultJson(getOverDueTaxByNameUrl, companyInfo, "name", 0, getList, "9760b4bba8cc4cda8f5fc24e2b6db519");
//			getResultJson(getOverDueTaxByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class); 
					for(HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 登记编号
						successMap.put("number", dataJson.get("number"));
						// 登记机关
						successMap.put("department", dataJson.get("department"));
						// 被担保债权种类
						successMap.put("type", dataJson.get("type"));
						// 被担保债权数额
						successMap.put("amount", dataJson.get("amount"));
						// 状态
						successMap.put("status", dataJson.get("status"));
						// 登记日期
						successMap.put("date", dataJson.get("date"));
						// 债务人履行债务的期限/被担保债权债务履行期限
						successMap.put("period", dataJson.get("period"));
						// 担保范围/被担保债权担保范围
						successMap.put("scope", dataJson.get("scope"));
						// 登记信息备注/被担保债权备注
						successMap.put("remarks", dataJson.get("remarks"));
//						List<HashMap> mortgageesList = JSON.parseArray(String.valueOf(dataJson.get("mortgagees")), HashMap.class); 
//						List<HashMap> guaranteesList = JSON.parseArray(String.valueOf(dataJson.get("guarantees")), HashMap.class); 
//						for(HashMap<String, Object> mortgagees : mortgageesList){
//							// 抵押权人名称
//							successMap.put("mortgagees_name", mortgagees.get("name"));
//							// 抵押权人证照/证件类型
//							successMap.put("mortgagees_identify_type", mortgagees.get("identify_type"));
//							// 抵押权人证件/证照号码
//							successMap.put("mortgagees_identify_no", mortgagees.get("identify_no"));
//						}
//						for(HashMap<String, Object> guarantees : guaranteesList){
//							// 抵押物名称
//							successMap.put("guarantees_name", guarantees.get("name"));
//							// 抵押物名称所有权归属
//							successMap.put("guarantees_belong_to", guarantees.get("belong_to"));
//							// 抵押物数量，质量，状况，所在地等情况
//							successMap.put("guarantees_desc", guarantees.get("desc"));
//							// 抵押物备注
//							successMap.put("guarantees_remarks", guarantees.get("remarks"));
//						}
						//抵押权人概况
						successMap.put("mortgagees", dataJson.get("mortgagees"));
						//抵押物概况
						successMap.put("guarantees", dataJson.get("guarantees"));
						
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				}else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				//将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				//返回数据条数
				resultMap.put("num", resultListSize);
				//风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午5:09:56
	 * @Description TODO 风险信息-经营异常
	 */
	public String getAbnormalList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getAbnormalListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getAbnormalListByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			//根据企业名获取企业欠税信息
			getResultJson(getAbnormalListByNameUrl, companyInfo, "name", 0, getList, "59297c9e0ade47f8b87915e3b0f4afaf");
//			getResultJson(getAbnormalListByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class); 
					for(HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 做出决定机关
						successMap.put("department", dataJson.get("department"));
						// 列入经营异常名录原因
						successMap.put("in_reason", dataJson.get("in_reason"));
						// 移出日期	
						successMap.put("out_date", dataJson.get("out_date"));
						// 移出经营异常名录原因
						successMap.put("out_reason", dataJson.get("out_reason"));
						// 列入日期
						successMap.put("in_date", dataJson.get("in_date"));
						
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				}else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				//将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "in_date", "out_date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				//返回数据条数
				resultMap.put("num", resultListSize);
				//风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "in_date", -2);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午4:54:32
	 * @Description TODO 风险信息-抽样信息
	 */
	public String getCheckupList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getCheckupListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getCheckupListByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取被执行人信息
			getResultJson(getCheckupListByNameUrl, companyInfo, "name", 0, getList, "2711d79cc04940509a20231a219fe6a5");
//			getResultJson(getCheckupListByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
//						Map<String, Object> successMap = new HashMap<String, Object>();
//						// 检查抽查编号
//						successMap.put("id", dataJson.get("id"));
//						// 检查实施机关
//						successMap.put("department", dataJson.get("department"));
//						// 日期
//						successMap.put("date", dataJson.get("date"));
//						// 类型
//						successMap.put("type", dataJson.get("type"));
//						// 结果
//						successMap.put("result", dataJson.get("result"));
//						
//						resultList.add(successMap);
						resultList.add(dataJson);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午2:07:26
	 * @Description TODO 基本信息-工商变更
	 */
	public String getDetail(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getDetailByNameUrl = Global.getBusinessInfoAddress("businessInfo.getDetailByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取工商变更信息
			getResultJson(getDetailByNameUrl, companyInfo, "keyword", 0, getList, "ada44bd0070711e6b8a865678b483fde");
//			getResultJson(getDetailByNameUrl, companyInfo, "keyword", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 变更项目
						successMap.put("change_item", dataJson.get("change_item"));
						// 变更日期
						successMap.put("date", dataJson.get("change_date"));
						// 变更前内容
						successMap.put("before_content", dataJson.get("before_content"));
						// 变更后内容
						successMap.put("after_content", dataJson.get("after_content"));
						
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "date", -1);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午5:59:01
	 * @Description TODO 涉诉信息-开庭公告
	 */
	public String getCourtNotice(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getCourtNoticeByNameUrl = Global.getBusinessInfoAddress("businessInfo.getCourtNoticeByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取开庭公告信息
			getResultJson(getCourtNoticeByNameUrl, companyInfo, "name", 0, getList, "6e1e4ce5c2ba402ebcca8f4a75502ff6");
//			getResultJson(getCourtNoticeByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 案号
						successMap.put("case_no", dataJson.get("case_no"));
						// 原告
						successMap.put("plaintiff", dataJson.get("plaintiff"));
						// 被告
						successMap.put("defendant", dataJson.get("defendant"));
						// 开庭日期
						successMap.put("date", dataJson.get("hearing_date"));
						// 案由
						successMap.put("case_reason", dataJson.get("case_reason"));
						
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "date", -1);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午6:56:47
	 * @Description TODO 涉诉信息-法院公告
	 */
	public String getNoticeList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getNoticeListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getNoticeListByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取开庭公告信息
			getResultJson(getNoticeListByNameUrl, companyInfo, "name", 0, getList, "3a255067cbad41b685e985ece93d7965");
//			getResultJson(getNoticeListByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 公告类型	
						successMap.put("type", dataJson.get("type"));
						// 当事人
						successMap.put("content", dataJson.get("content"));
						// 发布时间
						successMap.put("date", dataJson.get("date"));
						// 公告内容
						successMap.put("content", dataJson.get("content"));
						// 当事人
						successMap.put("person", dataJson.get("person"));
						// 公告法院
						successMap.put("court", dataJson.get("court"));
						
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "date", -1);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午6:56:47
	 * @Description TODO 涉诉信息-裁判文书
	 */
	public String getLawsuitList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getLawsuitListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getLawsuitListByName");
			String getLawsuitDetailUrl = Global.getBusinessInfoAddress("businessInfo.getLawsuitDetail");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> getSonList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取裁判文书信息
			getResultJson(getLawsuitListByNameUrl, companyInfo, "name", 0, getList, "a0a01d53a236415699fb5f7f0788927f");
//			getResultJson(getLawsuitListByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 类型
						successMap.put("type", dataJson.get("type"));
						// 提交日期
						successMap.put("date", dataJson.get("date"));
						// 标题
						successMap.put("title", dataJson.get("title"));
						// 身份
						successMap.put("role", dataJson.get("role"));
						// 按裁判文书 id返回详细信息
						getResultJson(getLawsuitDetailUrl, (String) dataJson.get("id"), "id", 0, getSonList, "a0a01d53a236415699fb5f7f0788927f");
//						getResultJson(getLawsuitDetailUrl, (String) dataJson.get("id"), "id", 0, getSonList);
						for (Map<String, Object> mapSon : getSonList) {
							if ("200".equals(mapSon.get("status"))) {
								String dataSon = (String) mapSon.get("data");
								JSONObject dataSonJson = JSON.parseObject(dataSon);
								// 文书内容
								successMap.put("content", dataSonJson.get("content"));
								// 案号
								successMap.put("case_no", dataSonJson.get("case_no"));
								// 案由
								successMap.put("case_cause", dataSonJson.get("case_cause"));
							} else {
								successMap.put("content", "-");
								successMap.put("case_no", "-");
								successMap.put("case_cause", "-");
							}
						}
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				int riskNum = calculatedRiskQuantity(resultList, "date", -2);
				resultMap.put("riskNum", riskNum);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 上午10:54:02
	 * @Description TODO 涉诉信息-司法协助
	 */
	public String getJudicialChange(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getJudicialChangeByNameUrl = Global.getBusinessInfoAddress("businessInfo.getJudicialChangeByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取司法协助信息
			getResultJson(getJudicialChangeByNameUrl, companyInfo, "name", 0, getList, "2711d79cc04940509a20231a219fe6a5");
//			getResultJson(getJudicialChangeByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 被执行人
						successMap.put("be_executed_person", dataJson.get("be_executed_person"));
						// 股权数额
						successMap.put("amount", dataJson.get("amount"));
						// 执行法院
						successMap.put("executive_court", dataJson.get("executive_court"));
						// 协助公示通知书文号
						successMap.put("number", dataJson.get("number"));
						// 类型
						successMap.put("type", dataJson.get("type"));
						// 状态
						successMap.put("status", dataJson.get("status"));

						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午1:51:46
	 * @Description TODO 涉诉信息-被执行人
	 */
	public String getExecutedpersonList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getExecutedpersonListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getExecutedpersonListByName");
			String getExecutedpersonDetailByIdUrl = Global.getBusinessInfoAddress("businessInfo.getExecutedpersonDetailById");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> getSonList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取被执行人信息
			getResultJson(getExecutedpersonListByNameUrl, companyInfo, "name", 0, getList, "2711d79cc04940509a20231a219fe6a5");
//			getResultJson(getExecutedpersonListByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 执行法院
						successMap.put("court", dataJson.get("court"));
						// 立案日期
						successMap.put("date", dataJson.get("case_date"));
						// 根据被执行 id 获取被执行人详情
						getResultJson(getExecutedpersonDetailByIdUrl, (String) dataJson.get("id"), "id", 0, getSonList, "2711d79cc04940509a20231a219fe6a5");
//						getResultJson(getExecutedpersonDetailByIdUrl, (String) dataJson.get("id"), "id", 0, getSonList);
						for (Map<String, Object> mapSon : getSonList) {
							if ("200".equals(mapSon.get("status"))) {
								String dataSon = (String) mapSon.get("data");
								JSONObject dataSonJson = JSON.parseObject(dataSon);
								// 案号/执行依据文号
								successMap.put("case_number", dataSonJson.get("case_number"));
								// 执行金额
								successMap.put("amount", dataSonJson.get("amount"));
								// 案件状态
								String status = (String) dataSonJson.get("status");
								if ("0".equals(status)) {
									successMap.put("status", "执行中");
								} else if ("1".equals(status)) {
									successMap.put("status", "已结案");
								} else {
									successMap.put("status", "-");
								}
							} else {
								successMap.put("case_number", "-");
								successMap.put("status", "-");
								successMap.put("id", "-");
								successMap.put("amount", "-");
							}
						}
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午2:55:17
	 * @Description TODO 涉诉信息-失信信息 
	 */
	public String getExecutionList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getExecutionListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getExecutionListByName");
			String getExecutionDetailByIdUrl = Global.getBusinessInfoAddress("businessInfo.getExecutionDetailById");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> getSonList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取被执行人信息
			getResultJson(getExecutionListByNameUrl, companyInfo, "keyword", 0, getList, "2711d79cc04940509a20231a219fe6a5");
//			getResultJson(getExecutionListByNameUrl, companyInfo, "keyword", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
						Map<String, Object> successMap = new HashMap<String, Object>();
						// 执行法院
						successMap.put("court", dataJson.get("court"));
						// 案号
						successMap.put("case_number", dataJson.get("case_number"));
						// 立案日期
						successMap.put("date", dataJson.get("date"));
						// 做出执行依据单位
						successMap.put("ex_department", dataJson.get("ex_department"));
						// 发布日期
						successMap.put("publish_date", dataJson.get("publish_date"));
						// 根据被执行 id 获取被执行人详情
						getResultJson(getExecutionDetailByIdUrl, (String) dataJson.get("id"), "id", 0, getSonList, "2711d79cc04940509a20231a219fe6a5");
//						getResultJson(getExecutionDetailByIdUrl, (String) dataJson.get("id"), "id", 0, getSonList);
						for (Map<String, Object> mapSon : getSonList) {
							if ("200".equals(mapSon.get("status"))) {
								String dataSon = (String) mapSon.get("data");
								JSONObject dataSonJson = JSON.parseObject(dataSon);
								// 法定代表人
								successMap.put("oper_name", dataSonJson.get("oper_name"));
								// 执行依据文号
								successMap.put("doc_number", dataSonJson.get("doc_number"));
								// 省份
								successMap.put("province", dataSonJson.get("province"));
								// 被执行人履行情况
								successMap.put("execution_status", dataSonJson.get("execution_status"));
								// 失信被执行人行为具体情形
								successMap.put("execution_desc", dataSonJson.get("execution_desc"));
								// 组织机构号
								successMap.put("number", dataSonJson.get("number"));
								// 生效法律文书确定的义务
								successMap.put("final_duty", dataSonJson.get("final_duty"));
								// 执行金额
								successMap.put("amount", "0");
								
							} else {
								successMap.put("oper_name", "-");
								successMap.put("doc_number", "-");
								successMap.put("province", "-");
								successMap.put("execution_status", "-");
								successMap.put("execution_desc", "-");
								successMap.put("number", "-");
								successMap.put("final_duty", "-");
								successMap.put("amount", "0");
							}
						}
						resultList.add(successMap);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午4:01:49
	 * @Description TODO 涉诉信息-司法拍卖
	 */
	public String getAuctionsList(String companyInfo, String redisKey) {
		String returnJson = JedisUtils.get(redisKey);
		if (null != returnJson && !"".equals(returnJson)) {
			logger.info("redis缓存中数据有效，从redis缓存中直接提取数据");
			return returnJson;
		} else {
			String getAuctionsListByNameUrl = Global.getBusinessInfoAddress("businessInfo.getAuctionsListByName");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> getList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultNullList = new ArrayList<Map<String, Object>>();
			String errorMessage = null;
			String result = "";
			// 根据企业名获取被执行人信息
			getResultJson(getAuctionsListByNameUrl, companyInfo, "name", 0, getList, "16de47b84da345b98ec744d138b09f02");
//			getResultJson(getAuctionsListByNameUrl, companyInfo, "name", 0, getList);
			for (Map<String, Object> map : getList) {
				if ("200".equals(map.get("status"))) {
					String data = (String) map.get("data");
					List<HashMap> dataList = JSON.parseArray(data, HashMap.class);
					for (HashMap<String, Object> dataJson : dataList) {
//						Map<String, Object> successMap = new HashMap<String, Object>();
//						// 标题
//						successMap.put("full_name", dataJson.get("full_name"));
//						// 权利限制情况
//						successMap.put("restrict", dataJson.get("restrict"));
//						// 拍卖日期
//						successMap.put("date", dataJson.get("date"));
//						// 起拍价
//						successMap.put("start_price", dataJson.get("start_price"));
//						// 拍品介绍
//						successMap.put("description", dataJson.get("description"));
//						// 权利来源
//						successMap.put("basis", dataJson.get("basis"));
//						// 处置法院
//						successMap.put("court", dataJson.get("court"));
//						
//						resultList.add(successMap);
						resultList.add(dataJson);
					}
				} else if ("201".equals(map.get("status"))) {
					resultNullList.add(map);
				} else {
					errorMessage = (String) map.get("message");
				}
			}
			int resultListSize = resultList.size();
			if (resultListSize > 0) {
				// 将resultList中的数据按照作出行政处罚决定日期进行排序
				sorting(resultList, "date");
				resultMap.put("status", "success");
				resultMap.put("msg", "返回成功");
				// 返回数据条数
				resultMap.put("num", resultListSize);
				// 风险扫描中的数据数
				resultMap.put("riskNum", resultListSize);
				resultMap.put("data", resultList);
				result = encryptedInfo(resultMap, redisKey);
			} else {
				result = getResult(resultMap, resultNullList, errorMessage);
			}
			return result;
		}
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 上午9:22:29
	 * @Description TODO 风险信息-风险扫描
	 */
	public String riskScanning(String companyInfo, String redisKey) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取行政处罚信息
		String adminPenaltyMessage = getAdminPenaltyMessage(companyInfo, companyInfo + "-getAdminPenaltyMessage");
		//获取欠税信息
		String overDueTax = getOverDueTax(companyInfo, redisKey);
		//获取股权出质信息
		String equityQualities = getEquityQualities(companyInfo, companyInfo + "-getEquityQualities");
		//动产抵押
		String mortgages = getMortgages(companyInfo, companyInfo + "-getMortgages");
		//获取经营异常信息
		String abnormalList = getAbnormalList(companyInfo, companyInfo + "-getAbnormalList");
		//获取工商变更信息
		String detail = getDetail(companyInfo, companyInfo + "-getDetail");
		//获取开庭公告信息
		String courtNotice = getCourtNotice(companyInfo, companyInfo + "-getCourtNotice");
		//获取法院公告信息
		String noticeList = getNoticeList(companyInfo, companyInfo + "-getNoticeList");
		//获取裁判文书信息
		String lawsuitList = getLawsuitList(companyInfo, companyInfo + "-getLawsuitList");
		//获取司法协助信息
		String judicialChange = getJudicialChange(companyInfo, companyInfo + "-getJudicialChange");
		//获取被执行人信息
		String executedpersonList = getExecutedpersonList(companyInfo, companyInfo + "-getExecutedpersonList");
		//失信人信息
		String executionList = getExecutionList(companyInfo, companyInfo + "-getExecutionList");
		//获取司法拍卖信息
		String auctionsList = getAuctionsList(companyInfo, companyInfo + "-getAuctionsList");
		
		return null;
	}

	/**
	 * 通过POST请求获取第三方接口json（工具类）
	 * @param url URL地址
	 * @param createMap 参数
	 * @return JSON对象
	 */
	public JSONObject getPostJsonObj(String url, Map<String, String> createMap) {
		String jsonString = HttpClientUtil.sendPostSSLRequest(url, createMap);
		if (StringUtils.isNotBlank(jsonString)) {
			// 有序遍历获取
			JSONObject jsonObject = JSONObject.parseObject(jsonString, Feature.OrderedField);
			return jsonObject;
		} else {
			return null;
		}
	}

	/**
	 * @version 2018年5月14日 下午2:37:06
	 * @Description TODO 判断返回json的信息是否成功（工具类）
	 */
	public boolean isJsonRight(JSONObject jsonObject) {
		if (null != jsonObject && ("200".equals(jsonObject.getString("status")) || "201".equals(jsonObject.getString("status")))) {
			return true;
		}
		return false;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月14日 下午2:27:27
	 * @Description TODO 获取需要的json通用方法（工具类）
	 * @param url 三方接口通讯地址, companyNameOrId 企业名或企业id, companyKeyName 发送给三方企业名或企业id时的key, skip 忽略的条数即页码*20, list 用于皴法女查询出的数据的list
	 */
	public String getResultJson(String url, String companyNameOrId, String companyKeyName, int skip, List<Map<String, Object>> list) {
		logger.info("通过发送第三方接口查询数据");
		Map<String, String> sendMap = new HashMap<String, String>();
		sendMap.put(companyKeyName, companyNameOrId);
		sendMap.put("appkey", APPKEY);
		sendMap.put("skip", String.valueOf(skip * 20));
		JSONObject jsonObject = getPostJsonObj(url, sendMap);
		Map<String, Object> resultJsonMap = new HashMap<String, Object>();
		if (isJsonRight(jsonObject)) {
			resultJsonMap.put("status", jsonObject.getString("status"));
			resultJsonMap.put("message", jsonObject.getString("message"));
			resultJsonMap.put("sign", jsonObject.getString("sign"));
			if ("200".equals(jsonObject.getString("status"))) {
				JSONObject data = jsonObject.getJSONObject("data");
				if(null != data.getString("items")) {
				    resultJsonMap.put("data", data.getString("items"));
				}else if(null != data.getString("punishments")) {
					 resultJsonMap.put("data", data.getString("punishments"));
				}else {
					resultJsonMap.put("data", jsonObject.getString("data"));
				}
   			    // 记录总数
				if(null != data.getString("total")) {
					BigDecimal total = new BigDecimal(data.getString("total"));
					BigDecimal num = new BigDecimal("20");
					int pageNum = total.divide(num, 0, BigDecimal.ROUND_UP).intValue();
					skip = skip + 1;
					if (skip < pageNum) {
						getResultJson(url, companyNameOrId, companyKeyName, skip, list);
					}
				}
			}
		} else {
			if (null == jsonObject) {
				resultJsonMap.put("message", "第三方接口调用失败");
				resultJsonMap.put("status", "000");
				logger.error("第三方接口调用失败，通过" + url + "获取数据失败");
				
			}else {
				resultJsonMap.put("message", jsonObject.getString("message"));
				resultJsonMap.put("status", jsonObject.getString("status"));
				logger.error("返回码：" + jsonObject.getString("message") + "返回信息：" + jsonObject.getString("message") + "，通过" + url + "获取数据失败");
			}
		}
		list.add(resultJsonMap);
		return "success";
	}
	
	
	public String getResultJson(String url, String companyNameOrId, String companyKeyName, int skip, List<Map<String, Object>> list, String appKey) {
		logger.info("通过发送第三方接口查询数据");
		Map<String, String> sendMap = new HashMap<String, String>();
		sendMap.put(companyKeyName, companyNameOrId);
		sendMap.put("appkey", appKey);
		sendMap.put("skip", String.valueOf(skip * 20));
		JSONObject jsonObject = getPostJsonObj(url, sendMap);
		Map<String, Object> resultJsonMap = new HashMap<String, Object>();
		if (isJsonRight(jsonObject)) {
			resultJsonMap.put("status", jsonObject.getString("status"));
			resultJsonMap.put("message", jsonObject.getString("message"));
			resultJsonMap.put("sign", jsonObject.getString("sign"));
			if ("200".equals(jsonObject.getString("status"))) {
				JSONObject data = jsonObject.getJSONObject("data");
				if (null != data.getString("items")) {
					resultJsonMap.put("data", data.getString("items"));
				} else if (null != data.getString("punishments")) {
					resultJsonMap.put("data", data.getString("punishments"));
				} else if (null != data.getString("changerecords")) {
					resultJsonMap.put("data", data.getString("changerecords"));
				} else {
					resultJsonMap.put("data", jsonObject.getString("data"));
				}
				// 记录总数
				if (null != data.getString("total")) {
					BigDecimal total = new BigDecimal(data.getString("total"));
					BigDecimal num = new BigDecimal("20");
					int pageNum = total.divide(num, 0, BigDecimal.ROUND_UP).intValue();
					skip = skip + 1;
					if (skip < pageNum) {
						getResultJson(url, companyNameOrId, companyKeyName, skip, list);
					}
				}
			}
		} else {
			if (null == jsonObject) {
				resultJsonMap.put("message", "第三方接口调用失败");
				resultJsonMap.put("status", "000");
				logger.error("第三方接口调用失败，通过" + url + "获取数据失败");

			} else {
				resultJsonMap.put("message", jsonObject.getString("message"));
				resultJsonMap.put("status", jsonObject.getString("status"));
				logger.error("返回码：" + jsonObject.getString("message") + "返回信息：" + jsonObject.getString("message") + "，通过" + url + "获取数据失败");
			}
		}
		list.add(resultJsonMap);
		return "success";
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午10:49:06
	 * @Description TODO 获取信息总数（工具类）
	 */
	public String getResultNum(String url, String companyNameOrId, String companyKeyName, String keyName, Map<String, Object> map, String appKey) {
		logger.info("通过发送第三方接口查询" + keyName + "信息");
		Map<String, String> sendMap = new HashMap<String, String>();
		sendMap.put(companyKeyName, companyNameOrId);
		sendMap.put("appkey", appKey);
		sendMap.put("skip", "0");
		JSONObject jsonObject = getPostJsonObj(url, sendMap);
		Map<String, Object> resultJsonMap = new HashMap<String, Object>();
		if (isJsonRight(jsonObject)) {
			resultJsonMap.put("status", jsonObject.getString("status"));
			resultJsonMap.put("msg", jsonObject.getString("message"));
			resultJsonMap.put("sign", jsonObject.getString("sign"));
			if ("200".equals(jsonObject.getString("status"))) {
				JSONObject data = jsonObject.getJSONObject("data");
				map.put(keyName, data.getString("total"));
			}
		} else {
			map.put(keyName, "0");
		}
		return "success";
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 上午10:39:49
	 * @Description TODO 对list中的内容根据时间排序,单个参数（工具类）
	 */
	public void sorting(List<Map<String, Object>> list, final String keyName) {
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String o1Time = (String) o1.get(keyName);
				String o2Time = (String) o2.get(keyName);
				return compareTwoDate(o1Time, o2Time);
			}
		});
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 上午10:39:49
	 * @Description TODO 对list中的内容根据时间排序,两个个参数（工具类）
	 */
	public void sorting(List<Map<String, Object>> list, final String keyName1, final String keyName2) {
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String o1Time1 = (String) o1.get(keyName1);
				String o2Time1 = (String) o2.get(keyName1);
				o1Time1 = (null == o1Time1) ? "" : o1Time1;
				o2Time1 = (null == o2Time1) ? "" : o2Time1;
				if (o1Time1.equals(o2Time1)) {
					String o1Time2 = (String) o1.get(keyName2);
					String o2Time2 = (String) o2.get(keyName2);
					return compareTwoDate(o1Time2, o2Time2);
				} else {
					return compareTwoDate(o1Time1, o2Time1);
				}
			}
		});
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午5:53:50
	 * @Description TODO 比较两个时间（工具类）
	 */
	public static int compareTwoDate(String date1, String date2) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTime1 = null;
		Date dateTime2 = null;
		try {
			dateTime1 = dateFormat.parse(date1);
		} catch (ParseException e) {
			date1 = "0000-00-00";
			try {
				dateTime1 = dateFormat.parse(date1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			dateTime2 = dateFormat.parse(date2);
		} catch (ParseException e) {
			date2 = "0000-00-00";
			try {
				dateTime2 = dateFormat.parse(date2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return dateTime1.before(dateTime2) ? 1 : -1;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 上午10:26:34
	 * @Description TODO 统计风险扫描年限范围内的数量（工具类）
	 * @param years 几年之内的数据，为负数
	 */
	public int calculatedRiskQuantity(List<Map<String, Object>> list, String keyName, int years) {
		int riskNum = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(new Date());  
        calendar.add(Calendar.YEAR, years);  
        Date limitDate = calendar.getTime();  
		for (Map<String, Object> map : list) {
			Date dateTime = null;
			try {
				dateTime = dateFormat.parse((String)map.get(keyName));
			} catch (ParseException e) {
				try {
					dateTime = dateFormat.parse("0000-00-00");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			if (limitDate.before(dateTime) || limitDate.equals(dateTime)) {
				riskNum++;
			}
		}
		return riskNum;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午2:30:01
	 * @Description TODO 返回结果工具类（工具类）
	 */
	public String getResult(Map<String, Object> resultMap, List<Map<String, Object>> resultNullList, String errorMessage) {
		String result = "";
		if (resultNullList.size() > 0) {
			resultMap.put("status", "success");
			resultMap.put("msg", "查询无结果");
			resultMap.put("riskNum", "0");
			resultMap.put("num", "0");
			result = JsonMapper.toJsonString(resultMap);
		} else {
			resultMap.put("status", "faliure");
			resultMap.put("riskNum", "0");
			resultMap.put("num", "0");
			if (null != errorMessage && !"".equals(errorMessage)) {
				resultMap.put("msg", errorMessage);
			} else {
				resultMap.put("msg", "第三方接口调用失败");
			}
			result = JsonMapper.toJsonString(resultMap);
		}
		return result;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午6:24:07
	 * @Description TODO 信息加密（工具类）
	 */
	public String encryptedInfo(Map<String, Object> resultMap, String redisKey) {
		String result = null;
		JSONObject resultJSONObj = JSONObject.parseObject(JsonMapper.toJsonString(resultMap));
		try {
			// data中的内容进行加密
//			String encryptData = AESUtil.encryptAES(resultJSONObj.getString("data"));
//			resultJSONObj.put("data", encryptData);
			result = resultJSONObj.toJSONString();
			logger.info("查询成功时将查询出的数据存进redis缓存，有效时间为：" + REDIS_EXPIRATION_TIME + "秒");
			JedisUtils.set(redisKey, result, Integer.parseInt(REDIS_EXPIRATION_TIME));
		} catch (Exception e) {
			logger.warn("加密失败，直接发送未加密数据");
			result = JsonMapper.toJsonString(resultMap);
			e.printStackTrace();
		}
		return result;
	}
}
