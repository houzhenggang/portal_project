/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.littleproject.web;

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
import com.qdch.portal.modules.littleproject.entity.SysReligion;
import com.qdch.portal.modules.littleproject.service.SysReligionService;

/**
 * 区域信息Controller
 * @author wf
 * @version 2018-05-15
 */
@Controller
public class SysReligionController extends BaseController {

	@Autowired
	private SysReligionService sysReligionService;
	
	@ModelAttribute
	public SysReligion get(@RequestParam(required=false) String id) {
		SysReligion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysReligionService.get(id);
		}
		if (entity == null){
			entity = new SysReligion();
		}
		return entity;
	}
	
	@RequiresPermissions("littleproject:sysReligion:view")
	@RequestMapping(value = {"${adminPath}/littleproject/sysReligion/list"})
	public String list(SysReligion sysReligion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysReligion> page = sysReligionService.findPage(new Page<SysReligion>(request, response), sysReligion); 
		model.addAttribute("page", page);
		return "modules/littleproject/sysReligionList";
	}

	@RequiresPermissions("littleproject:sysReligion:view")
	@RequestMapping(value = "${adminPath}/littleproject/sysReligion/form")
	public String form(SysReligion sysReligion, Model model) {
		model.addAttribute("sysReligion", sysReligion);
		return "modules/littleproject/sysReligionForm";
	}

	@RequiresPermissions("littleproject:sysReligion:edit")
	@RequestMapping(value = "${adminPath}/littleproject/sysReligion/save")
	public String save(SysReligion sysReligion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysReligion)){
			return form(sysReligion, model);
		}
		sysReligionService.save(sysReligion);
		addMessage(redirectAttributes, "保存区域成功");
		return "redirect:"+Global.getAdminPath()+"/littleproject/sysReligion/list?repage";
	}
	
	@RequiresPermissions("littleproject:sysReligion:edit")
	@RequestMapping(value = "${adminPath}/littleproject/sysReligion/delete")
	public String delete(SysReligion sysReligion, RedirectAttributes redirectAttributes) {
		sysReligionService.delete(sysReligion);
		addMessage(redirectAttributes, "删除区域成功");
		return "redirect:"+Global.getAdminPath()+"/littleproject/sysReligion/list?repage";
	}

}