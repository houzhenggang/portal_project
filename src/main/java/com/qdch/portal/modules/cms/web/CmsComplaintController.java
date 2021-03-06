/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.cms.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.DateUtils;
import com.qdch.portal.common.utils.IdGen;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.utils.UploadUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.cms.entity.CmsComplaint;
import com.qdch.portal.modules.cms.service.CmsComplaintService;
import com.qdch.portal.modules.sys.entity.User;

/**
 * 投诉Controller
 * @author zuoqb
 * @version 2018-03-15
 */
@Controller
public class CmsComplaintController extends BaseController {

	@Autowired
	private CmsComplaintService cmsComplaintService;
	
	@ModelAttribute
	public CmsComplaint get(@RequestParam(required=false) String id) {
		CmsComplaint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsComplaintService.get(id);
		}
		if (entity == null){
			entity = new CmsComplaint();
		}
		if(entity.getFindDate()!=null){
			entity.setFindDateStr(DateUtils.formatDate(entity.getFindDate(), "yyyy-MM-dd"));
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsComplaint:view")
	@RequestMapping(value = {"${adminPath}/cms/cmsComplaint/list"})
	public String list(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsComplaint> page = cmsComplaintService.findPage(new Page<CmsComplaint>(request, response), cmsComplaint); 
		model.addAttribute("page", page);
		return "modules/cms/cmsComplaintList";
	}

	@RequiresPermissions("cms:cmsComplaint:view")
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/form")
	public String form(CmsComplaint cmsComplaint, Model model) {
		model.addAttribute("cmsComplaint", cmsComplaint);
		return "modules/cms/cmsComplaintForm";
	}
	
	@RequiresPermissions("cms:cmsComplaint:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/save")
	public String save(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsComplaint)){
			return form(cmsComplaint, model);
		}
		cmsComplaintService.save(cmsComplaint);
		addMessage(redirectAttributes, "保存投诉成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComplaint/list?repage";
	}
	
	@RequiresPermissions("cms:cmsComplaint:edit")
	@RequestMapping(value = "${adminPath}/cms/cmsComplaint/delete")
	public String delete(CmsComplaint cmsComplaint, RedirectAttributes redirectAttributes) {
		cmsComplaintService.delete(cmsComplaint);
		addMessage(redirectAttributes, "删除投诉成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsComplaint/list?repage";
	}
	
	/**
	 * 
	 * @todo   微信举报
	 * @time   2018年3月27日 下午2:11:45
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/wx/report"})
	public String report(CmsComplaint cmsComplaint,Model model,HttpServletRequest request, HttpServletResponse response){
		String userId=request.getParameter("userId");
		model.addAttribute("cmsComplaint", cmsComplaint);
		model.addAttribute("userId", userId);
		return render(request, "wechat/report");
	}
	/**
	 * @todo   微信公众号用户举报-图片上传
	 * @time   2018年3月29日 下午1:58:30
	 * @author zuoqb
	 * @return_type   void
	 */
	@RequestMapping(value = "${portalPath}/wx/uploadImage")
	@ResponseBody
	public String uploadImage(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		String images=request.getParameter("images");
		UploadUtils util=new UploadUtils();
		if(StringUtils.isNotBlank(images)&&images.indexOf("base64,")!=-1){
			images=util.GenerateImage(images,request);
			cmsComplaint.setImage(images);
		}
		return this.resultSuccessData(request, response, "上传图片成功", cmsComplaint);
	}
	
	
	/**
	 * @todo   微信公众号用户举报
	 * @time   2018年3月29日 下午1:58:30
	 * @author zuoqb
	 * @return_type   void
	 */
	@RequestMapping(value = "${portalPath}/wx/saveReport")
	public String saveReport(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response,@RequestParam("files") MultipartFile[] files) {
		String userId=request.getParameter("userId");
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String images=request.getParameter("images");
		String target=request.getParameter("target");
		String address=request.getParameter("address");
		String date=request.getParameter("date");
		String source=request.getParameter("source");
		String tel=request.getParameter("tel");
		String imagePath = request.getParameter("imageArray");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				// 上传文件路径
				String savePath = "";
				savePath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL.substring(1, Global.USERFILES_BASE_URL.length());
				savePath += "images/wxreport/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				// .../basePath/dirName/yyyyMMdd/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + "/";
				File saveDirFile2 = new File(savePath);
				if (!saveDirFile2.exists()) {
					saveDirFile2.mkdirs();
				}
				// 上传文件名
				String oldFilename = file.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
				String filename = simpleDateFormat.format(new Date()) + "-" + uuid.toString() + oldFilename.substring(oldFilename.lastIndexOf("."));
				File filepath = new File(savePath, filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件当中
				try {
					file.transferTo(new File(savePath + File.separator + filename));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				savePath = "/" + savePath.substring(Global.getUserfilesBaseDir().length(), savePath.length()) + "" + filename;
				imagePath += savePath + ",";
			}
		}
		/*if (!beanValidator(model, cmsComplaint)){
			return form(cmsComplaint, model);
		}
		cmsComplaintService.save(cmsComplaint);*/
		cmsComplaint.setCompanyAddress(address);
		cmsComplaint.setCompanyName(target);
		/*UploadUtils util=new UploadUtils();
		if(StringUtils.isNotBlank(images)&&images.indexOf("base64,")!=-1){
			images=util.GenerateImage(images,request);
			cmsComplaint.setImage(images);
		}*/
		if(StringUtils.isNotBlank(date)){
			cmsComplaint.setFindDate(DateUtils.parseDate(date));
		}
		if(imagePath.length() > 0) {
		    cmsComplaint.setImage(imagePath.substring(0,imagePath.length() - 1));
		}else {
			cmsComplaint.setImage("");
		}
		cmsComplaint.setCreateBy(new User(userId));
		cmsComplaint.setUserId(userId);
		cmsComplaint.setTitle(title);
		cmsComplaint.setContent(description);
		cmsComplaint.setSource(source);
		cmsComplaint.setStatus("0");
		cmsComplaint.setRemarks(tel);//存放手机
		cmsComplaintService.save(cmsComplaint);
		//return this.resultSuccessData(request, response, "举报成功", null);
		return "redirect:"+portalPath+"/cms/cmsComplaint/list"; 
	}
	
	/**
	 * @todo   微信公众号举报列表数据
	 * @time   2018年3月29日 下午1:58:11
	 * @author zuoqb
	 * @return_type   void
	 */
	@RequestMapping(value = {"${portalPath}/cms/cmsComplaint/listData"})
	@ResponseBody
	public String cmsComplaintListData(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		cmsComplaint.setUserId(request.getParameter("userId"));
		Page<CmsComplaint> page = cmsComplaintService.findPage(new Page<CmsComplaint>(request, response), cmsComplaint); 
		return this.resultSuccessData(request,response, "", mapJson(page,"success","获取数据成功"));
	}
	/**
	 * @todo  微信公众号举报列表
	 * @time   2018年3月29日 下午1:57:58
	 * @author zuoqb
	 * @return_type   String
	 */
	@RequestMapping(value = {"${portalPath}/cms/cmsComplaint/list"})
	public String cmsComplaintList(CmsComplaint cmsComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		request.setAttribute("userId", request.getParameter("userId"));
		return "portal/wechat/reportList";
	}
	/**
	 * 
	 * @todo   TODO
	 * @time   2018年3月29日 下午1:55:52
	 * @author zuoqb
	 * @return_type   撤销举报
	 */
	@RequestMapping(value = "${portalPath}/wx/cancleReport")
	@ResponseBody
	public String cancleReport(CmsComplaint cmsComplaint, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		/*if (!beanValidator(model, cmsComplaint)){
			return form(cmsComplaint, model);
		}
		cmsComplaintService.save(cmsComplaint);*/
		//状态 0-未受理 1-已受理 2-驳回 3-处理结束 4-已撤销
		if(cmsComplaint!=null&&"0".equals(cmsComplaint.getStatus())){
			cmsComplaint.setStatus("4");
			cmsComplaintService.save(cmsComplaint);
			return this.resultSuccessData(request, response, "撤销成功", null);
		}else{
			return this.resultFaliureData(request, response, "已处理，不能撤销！", null);
		}
		
	}
}