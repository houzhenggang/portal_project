
package com.qdch.portal.modules.aitext.web;

import java.util.ArrayList;
import java.util.List;

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
import com.qdch.portal.modules.aitext.entity.SouceData;
import com.qdch.portal.modules.aitext.service.SouceDataService;

/**
 * 资源数据Controller
 * @author lixiaoyi
 * @version 2018-05-21
 */
@Controller
@RequestMapping(value={"${portalPath}/data"})
public class SouceDataController extends BaseController {

	@Autowired
	private SouceDataService souceDataService;
	
	
	@RequestMapping(value={"resource"})
	public  void  getResouce(HttpServletResponse response,String aspect,String num){
		List<SouceData> allDatas=new ArrayList<SouceData>();
		if ("0".equals(num)) {
			allDatas= souceDataService.getResouce(aspect);
	    }if ("1".equals(num)) {
	    	allDatas=souceDataService.getResoucebyone(aspect);
		}if ("3".equals(num)||"7".equals(num))  {
			SouceData souceData=new SouceData();
			souceData.setNums(num);
			souceData.setArea(aspect);
		   allDatas	=	souceDataService.getResouceby(souceData);
		}
		
    	renderString(response, allDatas);
	}
	@RequestMapping(value={"area"})
	public  void  getArea(HttpServletResponse response,String aspect,String num){
		List<SouceData> Datas=new ArrayList<SouceData>();
		if ("0".equals(num)) {
		Datas=	souceDataService.getArea(aspect);
		}if ("1".equals(num)) {
		Datas=souceDataService.getAreabyone(aspect);	
		}if ("3".equals(num)||"7".equals(num)) {
			SouceData souceData=new SouceData();
			souceData.setNums(num);
			souceData.setArea(aspect);
		Datas=	souceDataService.getAreabycon(souceData);
		}
	renderString(response, Datas);
	}
	
	@RequestMapping(value={"allresource"})
	public  void  getResouceall(HttpServletResponse response,String aspect,String num){
	System.out.println(aspect);
	List<SouceData> allDatas=new ArrayList<SouceData>();
	if ("0".equals(num)) {
	allDatas=	souceDataService.getResouceall(aspect);
		
	}if ("1".equals(num)) {
		  allDatas= souceDataService.getResouceday(aspect);
		
	}if ("3".equals(num)||"7".equals(num)) {
		SouceData souceData=new SouceData();
		souceData.setArea(aspect);
		souceData.setNums(num);
	   allDatas=	souceDataService.getResoucebycon(souceData);
	}
		
	renderString(response, allDatas);
	}
	
	/**
	 * 最新消息
	 * @author lixiaoyi
	 * @date 2018年5月22日 下午3:55:22
	 * @TODO
	 */
	public  void getNews(HttpServletResponse response,String aspect){
	List<SouceData> news=	souceDataService.getNews(aspect);
	renderString(response, news);
	}
	
	
	
}