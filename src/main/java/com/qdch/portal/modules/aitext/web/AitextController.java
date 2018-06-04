package com.qdch.portal.modules.aitext.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qdch.portal.common.utils.JsonKit;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.aitext.entity.SouceData;
import com.qdch.portal.modules.aitext.service.SouceDataService;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;

/**
 * 
 * @author lixiaoyi
 * @date 2018年5月21日上午10:53:18
 * @TODO 接口获取controller
 */
@Controller
@RequestMapping(value = "${portalPath}")
public class AitextController extends BaseController{

	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月28日 
	 * @TODO 页面接口
	 */
	@RequestMapping(value={"information"})
	public String information(){
		return "item/information";
	}
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年5月21日 上午11:48:58
	 * @TODO 地名接口
	 */
	@RequestMapping(value = {"land"})
	public  void getLandName(String keyword,HttpServletResponse response){
	    //  String keyword="贵阳大酒店老五星级，服务很亲切，中老年顾客很适合，不适合年青人，位置较优越，设备设施不够现代，早餐很丰富，VX：guiyanghotel。";//getPara("keyword");
	      String url="http://47.95.206.224:8091/place?query="+keyword;
	      String result=HttpClientUtil.sendGetRequest(url, "UTF-8");
	          
	     // System.out.println(result);
	      Map<String,Object>  map =  JsonKit.json2map(result);
	      renderString(response, map);
	          
	      
		}
	

	   /**
	    * 
	    * @author lixiaoyi
	    * @date 2018年5月17日 下午4:50:17
	    * @TODO 联系方式接口
	    */
	   @RequestMapping(value = {"relaction"})
		public void getreLactionWay(String keyword,HttpServletResponse response){
		    //  String keyword=getPara("keyword");
		      String url="http://47.95.206.224:8095/contact?query="+keyword;
		      String result=HttpClientUtil.sendGetRequest(url, "UTF-8");
		      System.out.println(result);
		      Map<String,Object>  map =  JsonKit.json2map(result);
		          
		     renderString(response, map);
			}
		/**
		 * 
		 * @author lixiaoyi
		 * @date 2018年5月17日 下午4:51:35
		 * @TODO 观点提取
		 */
	   @RequestMapping(value = {"viewpoint"})
		public void getViewpoint(String keyword,HttpServletResponse response){
		    //  String keyword=getPara("keyword");
		      String url="http://47.95.206.224:8092/opinion?query="+keyword;
		      String result=HttpClientUtil.sendGetRequest(url, "UTF-8");
		      System.out.println(result);
		      Map<String,Object>  map =  JsonKit.json2map(result);
		          
		     renderString(response, map);
			}
		/**
		 * 
		 * @author lixiaoyi
		 * @date 2018年5月17日 下午4:52:42
		 * @TODO 情感分析接口
		 */
	   @RequestMapping(value = {"emotion"})
		public void getEmotion(String keyword,HttpServletResponse response){
		     // String keyword=getPara("keyword");
		      String url="http://47.95.206.224:8094/emotion?query="+keyword;
		      String result=HttpClientUtil.sendGetRequest(url, "UTF-8");
		      System.out.println(result);
		      Map<String,Object>  map =  JsonKit.json2map(result);
		          
		      renderString(response, map);
			}
		/**
		 * 
		 * @author lixiaoyi
		 * @date 2018年5月17日 下午4:54:18
		 * @TODO 文本分类
		 */
	   @RequestMapping(value = {"text"})
		public void getText(String keyword,HttpServletResponse response){
		     // String keyword=getPara("keyword");
		      String url="http://47.95.206.224:8090/classification?query="+keyword;
		      String result=HttpClientUtil.sendGetRequest(url, "UTF-8");
		      System.out.println(result);
		      Map<String,Object>  map =  JsonKit.json2map(result);
		          
		      renderString(response, map);
			}
		/**
		 * 
		 * @author lixiaoyi
		 * @date 2018年5月17日 下午4:55:04
		 * @TODO 词云
		 */
	   @RequestMapping(value = {"cloud"})
		public void getCloud(String keyword,HttpServletResponse response){
		      //String keyword=getPara("keyword");
		      String url="http://47.95.206.224:8093/cloud?query="+keyword;
		      String result=HttpClientUtil.sendGetRequest(url, "UTF-8");
		      System.out.println(result);
		      Map<String,Object>  map =  JsonKit.json2map(result);
		          
		      renderString(response, map);
			}
}
