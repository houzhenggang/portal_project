/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.company.web;


import com.qdch.portal.common.web.BaseController;
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

public class IndexController extends BaseController {

    @RequestMapping(value = {"/"})

    public String index(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/index";
    }

    @RequestMapping(value = {"about"})

    public String about(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/about";
    }
    @RequestMapping(value = {"solution"})

    public String solution(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/solution";
    }

    @RequestMapping(value = {"solution1"})

    public String solution1(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/solution1";
    }

    @RequestMapping(value = {"solution2"})

    public String solution2(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/solution2";
    }


    @RequestMapping(value = {"solution3"})

    public String solution3(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/solution3";
    }


    @RequestMapping(value = {"solution4"})

    public String solution4(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/solution4";
    }

    @RequestMapping(value = {"news"})

    public String news(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/news";
    }


    @RequestMapping(value = {"newscontent"})

    public String newscontent(HttpServletRequest request,HttpServletResponse response, Model model) {

        return "modules/pages/newscontent";
    }

}
