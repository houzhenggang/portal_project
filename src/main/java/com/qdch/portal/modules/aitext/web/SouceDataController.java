package com.qdch.portal.modules.aitext.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletResponse;
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

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.modules.aitext.entity.SouceData;
import com.qdch.portal.modules.aitext.entity.SouceDataResult;
import com.qdch.portal.modules.aitext.service.SouceDataResultService;
import com.qdch.portal.modules.aitext.service.SouceDataService;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.sun.tools.javac.code.Source;

/**
 * 资源数据Controller
 * 
 * @author lixiaoyi
 * @version 2018-05-21
 */
@Controller
@RequestMapping(value = { "${portalPath}/data" })
public class SouceDataController extends BaseController {

	@Autowired
	private SouceDataService souceDataService;
	@Autowired
	private SouceDataResultService souceDataResultService;

	@RequestMapping(value = { "index" })
	public String index() {

		return "item/index";
	}

	@RequestMapping(value = { "resource" })
	public void getResouce(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allDatas = new ArrayList<SouceData>();
		if ("0".equals(num)) {
			allDatas = souceDataService.getResouce(aspect);
		}
		if ("1".equals(num)) {
			allDatas = souceDataService.getResoucebyone(aspect);
		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setNums(num);
			souceData.setArea(aspect);
			allDatas = souceDataService.getResouceby(souceData);
		}

		renderString(response, allDatas);

	}

	@RequestMapping(value = { "area" })
	public void getArea(HttpServletResponse response, String aspect, String num) {
		List<SouceData> Datas = new ArrayList<SouceData>();
		if ("0".equals(num)) {
			Datas = souceDataService.getArea(aspect);
		}
		if ("1".equals(num)) {
			Datas = souceDataService.getAreabyone(aspect);
		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setNums(num);
			souceData.setArea(aspect);

			Datas = souceDataService.getAreabycon(souceData);
		}

		renderString(response, Datas);
	}

	@RequestMapping(value = { "allresource" })
	public void getResouceall(HttpServletResponse response, String aspect,
			String num) {
		System.out.println(aspect);
		List<SouceData> allDatas = new ArrayList<SouceData>();
		if ("0".equals(num)) {
			allDatas = souceDataService.getResouceall(aspect);

		}
		if ("1".equals(num)) {
			allDatas = souceDataService.getResouceday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			souceData.setNums(num);
			allDatas = souceDataService.getResoucebycon(souceData);
		}

		renderString(response, allDatas);
	}

	/**
	 * 最新消息
	 * 
	 * @author lixiaoyi
	 * @date 2018年5月22日 下午3:55:22
	 * @TODO
	 */
	@RequestMapping(value = { "newMessage" })
	public void getNews(HttpServletResponse response, String aspect) {
		List<SouceData> news = souceDataService.getNews(aspect);
		renderString(response, news);
	}

	/**
	 * 词云
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO
	 */
	@RequestMapping(value = { "wordCloud" })
	public void getWordCloud(HttpServletResponse response, String aspect) {
		SouceData souceData = souceDataService.getWordCloud(aspect);
		Map<String, Integer> map = new HashMap<String, Integer>();
		JiebaSegmenter segmenter = new JiebaSegmenter();
		String textString =souceData.getComment();
//		textString=textString.trim().replace(",", "").replace(";", "").replace(".", "")
//				.replace("!", "").replace("?", "").replace(":", "")
//				.replace("\"", "").replace("'", "").replace("，", "").replace("。", "");
		textString=textString.replaceAll("[\\pP\\pS\\pZ]", "");
		List<String> wordList = segmenter.sentenceProcess(textString);
		for (String s : wordList) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}
		// 这里将map.entrySet()转换成list
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				map.entrySet());
		// 然后通过比较器来实现排序
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			// 升序排序
			public int compare(Entry<String, Integer> e1,
					Entry<String, Integer> e2) {
				return e2.getValue() - e1.getValue();
			}

		});
		// 输出出现频率最高的10个

		/*for (int i = 0; i < 2; i++) {
			Map.Entry<String, Integer> e = list.get(i);
			System.out.println(e.getKey() + ":" + e.getValue());
		}*/

		renderString(response, list);
	}

	/**
	 * 热度状况
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO
	 */
	@RequestMapping(value = { "hot" })
	public void getHotAll(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getHotall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getHotday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			souceData.setNums(num);
			allHot = souceDataService.getHotbycon(souceData);
		}

		renderString(response, allHot);
	}

	/**
	 * 详细数据 数据来源
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 得到数据的来源
	 */
	@RequestMapping(value = { "dataSource" })
	public void getData(HttpServletResponse response, String aspect) {
		List<SouceData> allData = new ArrayList<SouceData>(100);
		allData = souceDataService.getData(aspect);
		renderString(response, allData);
	}

	/**
	 * 详细数据 数据类别
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 得到数据的来源
	 */
	@RequestMapping(value = { "class" })
	public void getDataClass(HttpServletResponse response, String aspect) {
		List<SouceDataResult> allDataClass = new ArrayList<SouceDataResult>(100);
		allDataClass = souceDataResultService.getDataClass(aspect);
		renderString(response, allDataClass);
	}

	/**
	 * 详细数据 抽取数据
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 带有正向，中性，负向
	 */
	@RequestMapping(value = { "extract" })
	public void getExtract(HttpServletResponse response, String aspect,
			String num, String textLY, String textQG, String textXG,
			String textLB) {
		List<SouceData> allDatas = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allDatas = souceDataService.getExtract(aspect, textLY, textQG,
					textXG, textLB);
		}
		if ("1".equals(num)) {
			allDatas = souceDataService.getExtractbyone(aspect, textLY, textQG,
					textXG, textLB);
		}
		if ("3".equals(num) || "7".equals(num)) {
			allDatas = souceDataService.getExtractby(aspect, textLY, textQG,
					textXG, textLB);
		}

		renderString(response, allDatas);

	}

	/**
	 * 数据分析 总体友好度
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "frendly" })
	public void getFrendly(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getFrendlyall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getFrendlyday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			if("3".equals(num)){
				souceData.setNums("28");
			}else{
				souceData.setNums("24");
			}
			allHot = souceDataService.getFrendlybycon(souceData);
		}

		renderString(response, allHot);

	}

	/**
	 * 数据分析 负面评论分布
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "negative" })
	public void getNegative(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getNegativeall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getNegativeday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			if("3".equals(num)){
				souceData.setNums("28");
			}else{
				souceData.setNums("24");
			}
			
			allHot = souceDataService.getNegativebycon(souceData);
		}

		renderString(response, allHot);

	}

	/**
	 * 数据分析 全网声量
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "network" })
	public void getNetwork(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getNetworkall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getNetworkday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			if("3".equals(num)){
				souceData.setNums("28");
			}else{
				souceData.setNums("24");
			}
			allHot = souceDataService.getNetworkbycon(souceData);
		}

		renderString(response, allHot);

	}

	/**
	 * 数据分析 评价折线词云
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "broken" })
	public void getBroken(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getBrokenall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getBrokenday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			souceData.setNums(num);
			allHot = souceDataService.getBrokenbycon(souceData);
		}

		renderString(response, allHot);

	}

	/**
	 * 数据分析 评价
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "evaluate" })
	public void getEvaluate(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getEvaluateall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getEvaluateday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			souceData.setNums(num);
			allHot = souceDataService.getEvaluatebycon(souceData);
		}

		renderString(response, allHot);

	}

	/**
	 * 数据分析 评价信息
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "information" })
	public void getInformation(HttpServletResponse response, String aspect,
			String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getInformationall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getInformationday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			souceData.setNums(num);
			allHot = souceDataService.getInformationbycon(souceData);
		}

		renderString(response, allHot);

	}

	/**
	 * 数据分析 评价，折线，词云详细
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO
	 */
	@RequestMapping(value = { "brokenInformation" })
	public void getBrokenInformation(HttpServletResponse response,
			String aspect, String num) {
		List<SouceData> allHot = new ArrayList<SouceData>(500);
		if ("0".equals(num)) {
			allHot = souceDataService.getBrokenInformationall(aspect);

		}
		if ("1".equals(num)) {
			allHot = souceDataService.getBrokenInformationday(aspect);

		}
		if ("3".equals(num) || "7".equals(num)) {
			SouceData souceData = new SouceData();
			souceData.setArea(aspect);
			souceData.setNums(num);
			allHot = souceDataService.getBrokenInformationbycon(souceData);
		}

		renderString(response, allHot);

	}

}