package com.qdch.portal.littleproject.web;

import com.alibaba.fastjson.JSONObject;
import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.RiskEventModelDao;
import com.qdch.portal.littleproject.entity.RiskEventModel;
import com.qdch.portal.littleproject.service.RiskEventModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 * 个人中心
 */
@Controller
public class PersonalController extends BaseController {


    @Autowired
    private RiskEventModelService riskEventModelService;

    @Autowired
    private RiskEventModelDao riskEventModelDao;


    /**
     * 得到上报给政府官员的风险事件 已上报、已查阅、已处理
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/personal/getRiskEventList")
    @ResponseBody
    public String getRiskEventList(HttpServletRequest request, HttpServletResponse response,RiskEventModel riskEventModel){

        try {
            DynamicDataSource.setInsightDataSource();
            Page<RiskEventModel> page = riskEventModelService.getRiskEventList(
                    new Page<RiskEventModel>(request,response),riskEventModel);

            DynamicDataSource.removeDataSourceKey();
            return this.resultSuccessData(request,response,"",page);
        } catch (Exception e) {
            e.printStackTrace();
            DynamicDataSource.removeDataSourceKey();
            logger.error("服务器发生未知错误");
            return this.resultFaliureData(request,response,"","");
        }
    }

    /**
     * 风险事件详情和历史处理轨迹
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/personal/getDetailsById")
    @ResponseBody
    public String getDetailsById(HttpServletRequest request, HttpServletResponse response,RiskEventModel riskEventModel){

        try {
            DynamicDataSource.setInsightDataSource();
            if(StringUtils.isBlank(request.getParameter("fxsjId"))){
                return this.resultFaliureData(request,response,"请先输入风险事件的id","");
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("basic",riskEventModelDao.getDetailsById(riskEventModel));
            result.put("history",riskEventModelDao.getDetailsHistoryById(riskEventModel));


            DynamicDataSource.removeDataSourceKey();
            JSONObject dto = new JSONObject(true);
            dto.put("data",result);
            dto.put("status", "success");
            dto.put("msg", "");
            return dto.toJSONString();
            //return this.resultSuccessData(re)
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("服务器发生未知错误");
            DynamicDataSource.removeDataSourceKey();
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 保存处理结果
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/personal/save")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response,RiskEventModel riskEventModel){

        try {
            DynamicDataSource.setHubDataSource();
            if(StringUtils.isBlank(request.getParameter("fxsjId"))){
                return this.resultFaliureData(request,response,"请先输入风险事件的id","");
            }
            if(StringUtils.isBlank(request.getParameter("clzt"))){
                return this.resultFaliureData(request,response,"请先输入处理状态","");
            }

            if(StringUtils.isBlank(riskEventModel.getShr())){
                riskEventModel.setShr(request.getSession().getAttribute("loginUser")+"");
            }
            if(StringUtils.isBlank(riskEventModel.getUpdateTime())){
                riskEventModel.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
           riskEventModelService.save(riskEventModel);

            DynamicDataSource.removeDataSourceKey();
            return this.resultSuccessData(request,response,"","");
        } catch (Exception e) {
            e.printStackTrace();
            DynamicDataSource.removeDataSourceKey();
            logger.error("服务器发生未知错误");
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 得到不同状态的个数
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/personal/getCountGroupBY")
    @ResponseBody
    public String getCountGroupBY(HttpServletRequest request, HttpServletResponse response,RiskEventModel riskEventModel){

        try {
            DynamicDataSource.setHubDataSource();

            List<Map<String,Integer>> data = riskEventModelDao.getCountGroupBY(new RiskEventModel());

            DynamicDataSource.removeDataSourceKey();
            return this.resultSuccessData(request,response,"",data);
        } catch (Exception e) {
            e.printStackTrace();
            DynamicDataSource.removeDataSourceKey();
            logger.error("服务器发生未知错误");
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 重置密码
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/personal/resetPassword")
    @ResponseBody
    public String resetPassword(HttpServletRequest request, HttpServletResponse response,RiskEventModel riskEventModel){

        try {
            DynamicDataSource.setHubDataSource();

            List<Map<String,Integer>> data = riskEventModelDao.getCountGroupBY(new RiskEventModel());

            DynamicDataSource.removeDataSourceKey();
            return this.resultSuccessData(request,response,"",data);
        } catch (Exception e) {
            e.printStackTrace();
            DynamicDataSource.removeDataSourceKey();
            logger.error("服务器发生未知错误");
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 更换手机号
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/personal/resetTelphone")
    @ResponseBody
    public String resetTelphone(HttpServletRequest request, HttpServletResponse response,RiskEventModel riskEventModel){

        try {
            DynamicDataSource.setHubDataSource();

            List<Map<String,Integer>> data = riskEventModelDao.getCountGroupBY(new RiskEventModel());

            DynamicDataSource.removeDataSourceKey();
            return this.resultSuccessData(request,response,"",data);
        } catch (Exception e) {
            e.printStackTrace();
            DynamicDataSource.removeDataSourceKey();
            logger.error("服务器发生未知错误");
            return this.resultFaliureData(request,response,"","");
        }
    }

















}


