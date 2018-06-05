/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.company.web;


import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.dao.CmsNewsDao;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.service.CmsNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文章Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller

public class NewsController extends BaseController {

    @Autowired
    private CmsNewsService cmsNewsService;

    @Autowired
    private CmsNewsDao cmsNewsDao;

    @RequestMapping(value = {"${portalPath}/newsList"})
    @ResponseBody
    public String newsList(HttpServletRequest request,HttpServletResponse response,CmsNews cmsNews) {

        try {
            Page<CmsNews> page = cmsNewsService.findPage(new Page<CmsNews>(request,response),cmsNews);


            page.setPageCount(page.getTotalPage());

            return  this.resultSuccessData(request,response,"",page);
        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }


    /**
     * 详情页面
     * @param request
     * @param response
     * @param
     * @return
     */

    @RequestMapping(value = {"${portalPath}/newsDetails"})

    public String newsDetails(Model model,HttpServletRequest request,HttpServletResponse response) {


            model.addAttribute("newsid", request.getParameter("id"));
            return "modules/pages/newscontent";
    }


    @RequestMapping(value = {"${portalPath}/newsContent"})
    @ResponseBody
    public String newsContent(HttpServletRequest request,HttpServletResponse response,CmsNews cmsNews) {

        try {
            if(StringUtils.isBlank(cmsNews.getId())){
                return this.resultFaliureData(request,response,"请先输入id","");
            }
            CmsNews news = cmsNewsDao.getContentById(cmsNews);
            return  this.resultSuccessData(request,response,"",news);
        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }
    }

}
