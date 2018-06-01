/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.aitext.entity.SouceDataResult;
import com.qdch.portal.modules.aitext.service.SouceDataResultService;

/**
 * 资源数据结果Controller
 * @author lixiaoyi
 * @version 2018-05-21
 */
@Controller
public class SouceDataResultController extends BaseController {

	@Autowired
	private SouceDataResultService souceDataResultService;
	
	@ModelAttribute
	public SouceDataResult get(@RequestParam(required=false) String id) {
		SouceDataResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = souceDataResultService.get(id);
		}
		if (entity == null){
			entity = new SouceDataResult();
		}
		return entity;
	}
	
	@RequiresPermissions("aitext:souceDataResult:view")
	@RequestMapping(value = {"${adminPath}/aitext/souceDataResult/list"})
	public String list(SouceDataResult souceDataResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SouceDataResult> page = souceDataResultService.findPage(new Page<SouceDataResult>(request, response), souceDataResult); 
		model.addAttribute("page", page);
		return "modules/aitext/souceDataResultList";
	}

	@RequiresPermissions("aitext:souceDataResult:view")
	@RequestMapping(value = "${adminPath}/aitext/souceDataResult/form")
	public String form(SouceDataResult souceDataResult, Model model) {
		model.addAttribute("souceDataResult", souceDataResult);
		return "modules/aitext/souceDataResultForm";
	}

	@RequiresPermissions("aitext:souceDataResult:edit")
	@RequestMapping(value = "${adminPath}/aitext/souceDataResult/save")
	public String save(SouceDataResult souceDataResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, souceDataResult)){
			return form(souceDataResult, model);
		}
		souceDataResultService.save(souceDataResult);
		addMessage(redirectAttributes, "保存获取资源数据结果成功");
		return "redirect:"+Global.getAdminPath()+"/aitext/souceDataResult/list?repage";
	}
	
	@RequiresPermissions("aitext:souceDataResult:edit")
	@RequestMapping(value = "${adminPath}/aitext/souceDataResult/delete")
	public String delete(SouceDataResult souceDataResult, RedirectAttributes redirectAttributes) {
		souceDataResultService.delete(souceDataResult);
		addMessage(redirectAttributes, "删除获取资源数据结果成功");
		return "redirect:"+Global.getAdminPath()+"/aitext/souceDataResult/list?repage";
	}

}