package com.qdch.portal.littleproject.web.business;


import com.alibaba.fastjson.parser.Feature;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.utils.AESUtil;
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工商信息-基本信息
 */
@Controller
public class BaseInfoController extends BaseController {

    /**
     * 工商信息-基本信息
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/getWorkBusinessInfo")
    @ResponseBody
    public String getWorkBusinessInfo(HttpServletRequest request,HttpServletResponse response){

        try {

            String keyword = request.getParameter("keyword");
            if(keyword == null){
                keyword = "博山鲁星环保机械厂";
            }
            String url = Global.getBusinessInfoAddress("businessInfo.getCompanyInfoByName");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","ada44bd0070711e6b8a865678b483fde");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            String data = JedisUtils.get(keyword+"_getCompanyInfoByName");
            JSONObject dto = new JSONObject(true);
            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url, createMap);
                if (isJsonRight(jsonObj)) {

//                    byte[] n2 = keyword.getBytes("gbk");
//                    String gn2 = new String(n2, "utf-8");
                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getCompanyInfoByName",dataobj+"",12*60*60); //一天的过期时间
                    //String ss = JedisUtils.get(keyword+"_getCompanyInfoByName");
                    dto.put("data",dataobj);
                    dto.put("status", "success");
                    dto.put("msg", jsonObj.getString("message"));
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
            }else{
                dto.put("data",data);
                dto.put("status", "success");
                dto.put("msg", "");
                return dto.toJSONString();
            }

            //return  this.resultSuccessData(request,response,"",token);

        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }catch (Error e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }
    /**
     * 企业年报
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/getEnterpriseReport")
    @ResponseBody
    public String getEnterpriseYearReport(HttpServletRequest request,HttpServletResponse response){

        try {
            String keyword = request.getParameter("keyword");
            if(keyword == null){
                keyword = "小米科技有限责任公司";
            }
            String url = Global.getBusinessInfoAddress("businessInfo.getReportListByName");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","c7c41222070711e6b8a865678b483fde");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            String data = JedisUtils.get(keyword+"_getReportListByName");
            JSONObject dto = new JSONObject(true);
            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getReportListByName",dataobj+"",12*60*60); //一天的过期时间
                    dto.put("data",dataobj);
                    dto.put("status", "success");
                    dto.put("msg", jsonObj.getString("message"));
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
            }else{
                dto.put("data",data);
                dto.put("status", "success");
                dto.put("msg", "");
                return dto.toJSONString();
            }



        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 测试加密
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "${portalPath}/littleproject/business/testaes")
    @ResponseBody
    public String testaes(HttpServletRequest request,HttpServletResponse response){

        String keyword = request.getParameter("keyword");
        if(keyword == null){
            keyword = "博山鲁星环保机械厂";
        }
        String url = "http://api.qixin.com/APITestService/nwEnterprise/getCompanyInfoByName";
        //?appkey=ada44bd0070711e6b8a865678b483fde&keyword=博山鲁星环保机械厂";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("appkey","ada44bd0070711e6b8a865678b483fde");
        createMap.put("keyword",keyword);
        String token=HttpClientUtil.sendPostSSLRequest(url, createMap);
        try {
            token  = AESUtil.encryptAES(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.resultSuccessData(request,response,"",token);
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
        if (jsonObj == null || !"200".equals(jsonObj.getString("status"))) {
            return false;
        }
        return true;
    }


    /**
     * 招聘信息
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/getRecruitmentListByName")
    @ResponseBody
    public String getRecruitmentListByName(HttpServletRequest request,HttpServletResponse response){

        try {
            String skip = request.getParameter("skip");
            if(skip == null){
                skip = "0";
            }
            String keyword = request.getParameter("keyword");
            if(keyword == null){
                keyword = "小米科技有限责任公司";
            }
            String url = Global.getBusinessInfoAddress("businessInfo.getRecruitmentListByName");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","da3e4ba0070711e6b8a865678b483fde");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            createMap.put("skip",skip);
            String data = JedisUtils.get(keyword+"_getRecruitmentListByName");
            JSONObject dto = new JSONObject(true);
            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getRecruitmentListByName",dataobj+"",12*60*60); //一天的过期时间
                    dto.put("data",dataobj);
                    dto.put("status", "success");
                    dto.put("msg", jsonObj.getString("message"));
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
            }else{
                dto.put("data",data);
                dto.put("status", "success");
                dto.put("msg", "");
                return dto.toJSONString();
            }



        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }



    /**
     * 商标信息
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/getTrademarkByName")
    @ResponseBody
    public String getTrademarkByName(HttpServletRequest request,HttpServletResponse response){

        try {
            String keyword = request.getParameter("keyword");
            String skip = request.getParameter("skip");
            if(keyword == null){
                keyword = "小米科技有限责任公司";
            }
            if(skip == null){
                skip = "0";
            }
            String url = Global.getBusinessInfoAddress("businessInfo.getTrademarkByName");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","da3e4ba0070711e6b8a865678b483fde");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            createMap.put("skip",skip);
            String data = JedisUtils.get(keyword+"_getTrademarkByName");
            JSONObject dto = new JSONObject(true);
            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getTrademarkByName",dataobj+"",12*60*60); //一天的过期时间
                    dto.put("data",dataobj);
                    dto.put("status", "success");
                    dto.put("msg", jsonObj.getString("message"));
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
            }else{
                dto.put("data",data);
                dto.put("status", "success");
                dto.put("msg", "");
                return dto.toJSONString();
            }



        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 专利信息
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/getPatentListByName")
    @ResponseBody
    public String getPatentListByName(HttpServletRequest request,HttpServletResponse response){

        try {
            String keyword = request.getParameter("keyword");
            String skip = request.getParameter("skip");
            if(keyword == null){
                keyword = "小米科技有限责任公司";
            }
            if(skip == null){
                skip = "0";
            }
            String url = Global.getBusinessInfoAddress("businessInfo.getPatentListByName");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","d4c9f6b0070711e6b8a865678b483fde");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            createMap.put("skip",skip);
            String data = JedisUtils.get(keyword+"_getPatentListByName");
            JSONObject dto = new JSONObject(true);
            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getPatentListByName",dataobj+"",12*60*60); //一天的过期时间
                    dto.put("data",dataobj);
                    dto.put("status", "success");
                    dto.put("msg", jsonObj.getString("message"));
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
            }else{
                dto.put("data",data);
                dto.put("status", "success");
                dto.put("msg", "");
                return dto.toJSONString();
            }



        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 软件著作权和著作权
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/getCopyrightSoftByName")
    @ResponseBody
    public String getCopyrightSoftByName(HttpServletRequest request,HttpServletResponse response){

        try {
            String keyword = request.getParameter("keyword");
            if(keyword == null){
                keyword = "小米科技有限责任公司";
            }

            String urlsoft = Global.getBusinessInfoAddress("businessInfo.getCopyrightSoftByName");
            String url = Global.getBusinessInfoAddress("businessInfo.getCopyrightByName");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","5e1909eb29d64e1884058f044ab1c77f");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            String datasoft = JedisUtils.get(keyword+"_getCopyrightSoftByName");
            String data = JedisUtils.get(keyword+"_getCopyrightByName");
            JSONObject dto = new JSONObject(true);
            if(datasoft == null ||datasoft.equals("")){
                JSONObject jsonObj = getPostJsonObj(urlsoft, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getCopyrightSoftByName",dataobj+"",12*60*60); //一天的过期时间
                    dto.put("datasoft",dataobj);

                    //return dto.toJSONString();
                } else {
                    if (jsonObj == null) {
                        return this.resultFaliureData(request, response,
                                "第三方接口调用失败", null);
                    } else {
                        return this.resultFaliureData(request, response,
                                jsonObj.getString("message"), null);
                    }
                }
            }else{
                dto.put("datasoft",data);
            }



            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_getCopyrightByName",dataobj+"",12*60*60); //一天的过期时间
                    dto.put("data",dataobj);
                    dto.put("status", "success");
                    dto.put("msg", jsonObj.getString("message"));
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
            }else{
                dto.put("data",data);
                dto.put("status", "success");
                dto.put("msg", "");
                return dto.toJSONString();
            }



        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }











}
