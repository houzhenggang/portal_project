package com.qdch.portal.littleproject.web.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;

/**
 * 企业图谱
 * 
 * @author wangsw
 * @time 2018年5月9日
 */
@Controller
public class EnterpriseAtlasController extends BaseController {

	// 除数
	private static BigDecimal divisor = new BigDecimal(100);

	/**
	 * 企业链图
	 * 
	 * @author wangsw
	 * @time 2018年5月9日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/business/chainDiagram" })
	@ResponseBody
	public String chainDiagram(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = Global
					.getBusinessInfoAddress("businessInfo.getChainDiagram");
			String keyword = request.getParameter("keyword");
			String appkey = Global.getBusinessAppKey();
			Map<String, String> createMap = new HashMap<String, String>();
			createMap.put("keyword", keyword);
			createMap.put("appkey", appkey);
			JSONObject jsonObj = getPostJsonObj(url, createMap);
			if (isJsonRight(jsonObj)) {
				JSONObject dto = new JSONObject(true); // 有序
				JSONObject data = jsonObj.getJSONObject("data");
				JSONObject chainRelation = data.getJSONObject("chain_relation");// 企业链图信息
				dto.put("status", "success");
				dto.put("msg", jsonObj.getString("message"));
				dto.put("data", chainRelation);
				return dto.toJSONString();
			} else {
				if (jsonObj == null) {
					return this.resultFaliureData(request, response,
							"第三方接口调用失败", null);
				} else {
					return this.resultFaliureData(request, response,
							jsonObj.getString("message"), null);
				}
			}
		} catch (Exception e) {
			logger.warn("工商监控-企业图谱——企业链图", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 企业关联族谱
	 * 
	 * @author wangsw
	 * @time 2018年5月10日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/business/getRelationData" })
	@ResponseBody
	public String getRelationInfoByName(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = Global
					.getBusinessInfoAddress("businessInfo.getRelationInfoByName");
			String name = request.getParameter("name");
			String appkey = Global.getBusinessAppKey();
			Map<String, String> createMap = new HashMap<String, String>();
			createMap.put("name", name);
			createMap.put("appkey", "b9dad0497e16450f97c6370c85bc582c");// 暂时写死
			JSONObject jsonObj = getPostJsonObj(url, createMap);
			if (isJsonRight(jsonObj)) {
				JSONObject dto = new JSONObject(true); // 有序
				JSONObject data = jsonObj.getJSONObject("data");
				dto.put("status", "success");
				dto.put("msg", jsonObj.getString("message"));
				dto.put("data", data);
				return dto.toJSONString();
			} else {
				if (jsonObj == null) {
					return this.resultFaliureData(request, response,
							"第三方接口调用失败", null);
				} else {
					return this.resultFaliureData(request, response,
							jsonObj.getString("message"), null);
				}
			}
		} catch (Exception e) {
			logger.warn("工商监控-企业图谱——关联族谱", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 企业股权结构
	 * 
	 * @author wangsw
	 * @time 2018年5月11日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/business/getEquityStructureData" })
	@ResponseBody
	public String getEquityStructureData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = Global
					.getBusinessInfoAddress("businessInfo.getEquityStructureData");
			String name = request.getParameter("name");

			String appkey = Global.getBusinessAppKey();
			Map<String, String> createMap = new HashMap<String, String>();
			createMap.put("name", name);
			createMap.put("appkey", "2711d79cc04940509a20231a219fe6a5");// 暂时写死
			JSONObject jsonObj = getPostJsonObj(url, createMap);
			if (isJsonRight(jsonObj)) {
				JSONObject dto = new JSONObject(true); // 有序
				JSONObject data = jsonObj.getJSONObject("data");
				dto.put("status", "success");
				dto.put("msg", jsonObj.getString("message"));
				dto.put("data", data);
				return dto.toJSONString();
			} else {
				if (jsonObj == null) {
					return this.resultFaliureData(request, response,
							"第三方接口调用失败", null);
				} else {
					return this.resultFaliureData(request, response,
							jsonObj.getString("message"), null);
				}
			}
		} catch (Exception e) {
			logger.warn("工商监控-企业图谱——股权结构", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 疑似实际控制人(需要加注释)
	 * 
	 * @author wangsw
	 * @time 2018年5月14日
	 */
	@RequestMapping(value = { "${portalPath}/littleproject/business/getActualOwnerByEid" })
	@ResponseBody
	public String getActualOwnerByEid(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = Global
					.getBusinessInfoAddress("businessInfo.getEquityStructureData");
			String name = request.getParameter("name");
			String appkey = Global.getBusinessAppKey();
			Map<String, String> createMap = new HashMap<String, String>();
			createMap.put("name", name);
			createMap.put("appkey", "2711d79cc04940509a20231a219fe6a5");// 暂时写死
			JSONObject jsonObj = getPostJsonObj(url, createMap);
			if (isJsonRight(jsonObj)) {
				JSONObject dto = new JSONObject(true); // 有序
				JSONArray nodes = new JSONArray(); // 有序
				JSONObject data = jsonObj.getJSONObject("data");
				JSONObject eqStruct = data.getJSONObject("equity_structure");
				JSONArray childrens = eqStruct.getJSONArray("children");
				Map<String, Integer> indexMap = new HashMap<String, Integer>();
				JSONObject rNode = new JSONObject();
				rNode.put("eid", eqStruct.getString("eid"));
				rNode.put("name", eqStruct.getString("name"));
				rNode.put("type", eqStruct.getString("type"));
				rNode.put("is_key", true);
				rNode.put("parent", true);
				rNode.put("percent", BigDecimal.ONE);
				rNode.put("currentPercent", BigDecimal.ONE);
				JSONArray rPath = new JSONArray();
				rPath.add(0);
				rNode.put("paths", rPath);
				indexMap.put(name, 0);
				nodes.add(rNode);
				buildRelationJob(nodes, childrens, indexMap, rNode);
				data = buildMaxNodesLinks(nodes);

				dto.put("status", "success");
				dto.put("msg", jsonObj.getString("message"));
				dto.put("data", data);
				return dto.toJSONString();
			} else {
				if (jsonObj == null) {
					return this.resultFaliureData(request, response,
							"第三方接口调用失败", null);
				} else {
					return this.resultFaliureData(request, response,
							jsonObj.getString("message"), null);
				}
			}
		} catch (Exception e) {
			logger.warn("工商监控-企业图谱——疑似实际控制人", e);
			return this.resultFaliureData(request, response, "", null);
		}
	}

	/**
	 * 通过POST请求获取第三方接口json
	 * 
	 * @param url
	 *            URL地址
	 * @param createMap
	 *            参数
	 * @return JSON对象
	 */
	private JSONObject getPostJsonObj(String url, Map<String, String> createMap) {
		String jsonString = HttpClientUtil.sendPostSSLRequest(url, createMap);
		if (StringUtils.isNotBlank(jsonString)) {
			JSONObject jsonObj = JSONObject.parseObject(jsonString,
					Feature.OrderedField);// 有序遍历获取
			return jsonObj;
		} else {
			return null;
		}
	}

	private boolean isJsonRight(JSONObject jsonObj) {
		if (jsonObj == null) {
			return false;
		}
		String status = jsonObj.getString("status");
		if (!"200".equals(status) && !"201".equals(status)) {
			logger.warn("[" + status + "]" + jsonObj.getString("message"));
			return false;
		}
		return true;
	}

	private void buildRelationJob(JSONArray nodes, JSONArray children,
			Map<String, Integer> indexMap, JSONObject pObj) {
		if (children != null && children.size() > 0) {
			JSONObject jObj = null;
			JSONObject node = null;
			BigDecimal percent = null;
			BigDecimal nodePercent = null;
			String name = "";
			int index = -1;
			for (Object obj : children) {
				jObj = (JSONObject) obj;
				name = jObj.getString("name");
				if (indexMap.containsKey(name)) {
					percent = new BigDecimal(jObj.getString("percent").replace(
							"%", "")).divide(divisor);
					index = indexMap.get(name);
					node = nodes.getJSONObject(index); // 同类索引位置
					nodePercent = percent.multiply(pObj
							.getBigDecimal("percent")); // 对应节点->根节点的百分比
					percent = nodePercent.add(node.getBigDecimal("percent"));
					if (jObj.containsKey("children")
							&& jObj.getJSONArray("children").size() > 0) {
						node.put("parent", true);
						node.put("percent", percent);
						index = indexMap.get(pObj.getString("name"));
						for (Object p : pObj.getJSONArray("paths")) {
							if (p.equals(index)) {
								break;
							}
							node.getJSONArray("paths").add(p);
						}
						node.getJSONArray("paths").add(index);
						node.getJSONArray("paths").add(indexMap.get(name));
						buildRelationJob(nodes, jObj.getJSONArray("children"),
								indexMap, node);
					} else {
						node.put("parent", false);
						node.put("percent", percent);
						index = indexMap.get(pObj.getString("name"));
						for (Object p : pObj.getJSONArray("paths")) {
							if (p.equals(index)) {
								break;
							}
							node.getJSONArray("paths").add(p);
						}
						node.getJSONArray("paths").add(index);
						node.getJSONArray("paths").add(indexMap.get(name));
					}
					continue;
				}
				node = new JSONObject();
				node.put("eid", jObj.getString("eid"));
				node.put("name", name);
				node.put("type", jObj.getString("type"));
				node.put("currentPercent", jObj.getString("percent"));
				percent = pObj.getBigDecimal("percent").multiply(
						new BigDecimal(jObj.getString("percent").replace("%",
								"")).divide(divisor));
				indexMap.put(name, nodes.size());
				if (jObj.containsKey("children")
						&& jObj.getJSONArray("children").size() > 0) {
					node.put("parent", true);
					node.put("percent", percent);
					if (!node.containsKey("paths")) {
						node.put("paths", new JSONArray());
					}
					index = indexMap.get(pObj.getString("name"));
					for (Object p : pObj.getJSONArray("paths")) {
						if (p.equals(index)) {
							break;
						}
						node.getJSONArray("paths").add(p);
					}
					node.getJSONArray("paths").add(index);
					node.getJSONArray("paths").add(indexMap.get(name));
					nodes.add(node);
					buildRelationJob(nodes, jObj.getJSONArray("children"),
							indexMap, node);
				} else {
					node.put("parent", false);
					if (!node.containsKey("paths")) {
						node.put("paths", new JSONArray());
					}
					index = indexMap.get(pObj.getString("name"));
					for (Object p : pObj.getJSONArray("paths")) {
						if (p.equals(index)) {
							break;
						}
						node.getJSONArray("paths").add(p);
					}
					node.getJSONArray("paths").add(index);
					node.getJSONArray("paths").add(indexMap.get(name));
					node.put("percent", percent);
					nodes.add(node);
				}
			}
		}
	}

	private JSONObject buildMaxNodesLinks(JSONArray array) {
		int maxIndex = -1;
		BigDecimal maxPer = BigDecimal.ZERO;
		BigDecimal per = BigDecimal.ZERO;
		int len = array.size();
		JSONObject obj = null;
		for (int i = 0; i < len; i++) {
			obj = array.getJSONObject(i);
			if (obj.getBoolean("parent")) {
				continue;
			}
			per = obj.getBigDecimal("percent");
			if (per.compareTo(maxPer) > -1) {
				maxPer = per;
				maxIndex = i;
			}
		}
		if (maxIndex != -1) {
			obj = array.getJSONObject(maxIndex);
			JSONArray paths = obj.getJSONArray("paths");
			JSONObject data = new JSONObject(true);
			JSONArray nodes = new JSONArray();
			JSONArray links = new JSONArray();
			JSONObject aObj = null;
			JSONObject pObj = null;
			List<Integer> list = new ArrayList<Integer>();
			int index = -1;
			len = paths.size();
			for (int i = 0; i < len; i++) {
				index = paths.getIntValue(i);
				aObj = array.getJSONObject(index);
				if (!list.contains(index)) {
					obj = new JSONObject(true);
					if (0 == index || maxIndex == index) {
						obj.put("is_key", true);
					} else {
						obj.put("is_key", false);
					}
					obj.put("eid", aObj.getString("eid"));
					obj.put("name", aObj.getString("name"));
					obj.put("type", aObj.getString("type"));
					nodes.add(obj);
					list.add(index);
				}
				if (index == 0) { // 每到根节点之后，就继续下一轮循环，去掉分叉树连接点
					pObj = aObj;
					continue;
				}
				obj = new JSONObject(true);
				obj.put("sourcer_id", aObj.getString("name"));
				obj.put("target_id", pObj.getString("name"));
				obj.put("label", aObj.getString("currentPercent"));
				links.add(obj);
				pObj = aObj;
			}
			data.put("nodes", nodes);
			data.put("links", links);
			maxPer = maxPer.multiply(new BigDecimal(100)).setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
			data.put("totalPercent", maxPer + "%");
			data.put("status", 0);
			return data;
		} else {
			return null;
		}
	}

}
