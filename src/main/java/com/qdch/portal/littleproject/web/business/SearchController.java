package com.qdch.portal.littleproject.web.business;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.littleproject.dao.SysReligionDao;
import com.qdch.portal.modules.littleproject.entity.SysReligion;
import com.qdch.portal.modules.littleproject.service.SysReligionService;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.*;

/**
 * 搜索
 */
@Controller
public class SearchController extends BaseController {

    @Autowired
    private SysReligionService sysReligionService ;

    @Autowired
    private SysReligionDao sysReligionDao;

    /**
     * 得到自定义标签
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "${portalPath}/littleproject/business/getSelfSearch")
    @ResponseBody
    public String getSelfSearch(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> searchdata = JedisUtils.getList("business_search"); //工商信息自定义搜索
            return this.resultSuccessData(request,response,"",searchdata);
        } catch (Exception e) {
            e.printStackTrace();
            return  this.resultFaliureData(request,response,"","");
        }
    }

    /**
     * 添加、删除、修改自定义标签
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "${portalPath}/littleproject/business/alterSelfSearch")
    @ResponseBody
    public String alterSelfSearch(HttpServletRequest request, HttpServletResponse response){
        try {


            String tagName = request.getParameter("tagName");

            List<String> taglist = Arrays.asList(tagName);
            JedisUtils.setList("business_search",taglist,0);
            return this.resultSuccessData(request,response,"","");
        } catch (Exception e) {
            e.printStackTrace();
            return  this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 高级搜索
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/business/advanceSearch")
    @ResponseBody
    public String advanceSearch(HttpServletRequest request,HttpServletResponse response){

        try {
            String urlparams = request.getQueryString(); //取得问号后所有的值
            String keyword = request.getParameter("keyword");
            String skip = request.getParameter("skip");
            if(keyword == null){
                keyword = "小米科技有限责任公司";
            }
            if(skip == null){
                skip = "0";
            }
            String url = Global.getBusinessInfoAddress("businessInfo.advanceSearch");
            Map<String,String> createMap = new HashMap<String,String>();
            if(Global.getBusinessAppKey().equals("")||Global.getBusinessAppKey() == null){
                createMap.put("appkey","ada44bd0070711e6b8a865678b483fde");
            }else{
                createMap.put("appkey",Global.getBusinessAppKey());
            }
            createMap.put("keyword",keyword);
            createMap.put("skip",skip);
            String data = JedisUtils.get(keyword+"_advanceSearch");
            JSONObject dto = new JSONObject(true);
            if(data == null ||data.equals("")){
                JSONObject jsonObj = getPostJsonObj(url+"?"+urlparams, createMap);
                if (isJsonRight(jsonObj)) {

                    JSONObject dataobj = jsonObj.getJSONObject("data");
                    JedisUtils.set(keyword+"_advanceSearch",dataobj+"",12*60*60); //一天的过期时间
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
     * 省市信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "${portalPath}/littleproject/business/getReligion")
    @ResponseBody
    public String getReligion(HttpServletRequest request, HttpServletResponse response){
        try {
            List<SysReligion> data = sysReligionDao.findAllList(new SysReligion());
            return  this.resultSuccessData(request,response,"","");
        } catch (Exception e) {
            e.printStackTrace();
            return  this.resultFaliureData(request,response,"","");
        }
    }







}
