package com.qdch.portal.littleproject.web.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.service.business.BusinessInfoService;
import com.thoughtworks.xstream.mapper.Mapper.Null;

/**
 * @author hansz
 * @version 2018年5月10日 上午9:10:57
 * @Description TODO  工商信息查询————风险信息
 */
@Controller
public class RiskInformationController extends BaseController{

	@Autowired 
	BusinessInfoService businessInfoService;
	
	/**
	 * @author hansz
	 * @version 2018年5月14日 下午3:22:09
	 * @Description TODO 风险信息-行政处罚
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getAdminPenaltyMessage", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAdminPenaltyMessage(String companyInfo) {
		String redisKey = companyInfo + "_" + "getAdminPenaltyMessage";
		String resultJson = businessInfoService.getAdminPenaltyMessage(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午1:24:14
	 * @Description TODO 风险信息-欠税信息
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getOverDueTax", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getOverDueTax(String companyInfo) {
		String redisKey = companyInfo + "_" + "getOverDueTax";
		String resultJson = businessInfoService.getOverDueTax(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午1:24:19
	 * @Description TODO 风险信息-股权出质
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getEquityQualities", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getEquityQualities(String companyInfo) {
		String redisKey = companyInfo + "_" + "getEquityQualities";
		String resultJson = businessInfoService.getEquityQualities(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午3:19:26
	 * @Description TODO 风险信息-动产抵押列表
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getMortgages", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMortgages(String companyInfo) {
		String redisKey = companyInfo + "_" + "getMortgages";
		String resultJson = businessInfoService.getMortgages(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月15日 下午6:02:14
	 * @Description TODO 风险信息-经营异常
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getAbnormalList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAbnormalList(String companyInfo) {
		String redisKey = companyInfo + "_" + "getAbnormalList";
		String resultJson = businessInfoService.getAbnormalList(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午5:14:53
	 * @Description TODO 风险信息-抽查信息
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getCheckupList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCheckupList(String companyInfo) {
		String redisKey = companyInfo + "_" + "getCheckupList";
		String resultJson = businessInfoService.getCheckupList(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午5:22:33
	 * @Description TODO 基本信息-工商变更
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getDetail", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getDetail(String companyInfo) {
		String redisKey = companyInfo + "_" + "getDetail";
		String resultJson = businessInfoService.getDetail(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午5:22:33
	 * @Description TODO 涉诉信息-开庭公告
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getCourtNotice", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCourtNotice(String companyInfo) {
		String redisKey = companyInfo + "_" + "getCourtNotice";
		String resultJson = businessInfoService.getCourtNotice(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午7:10:30
	 * @Description TODO 涉诉信息-法院公告
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getNoticeList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getNoticeList(String companyInfo) {
		String redisKey = companyInfo + "_" + "getNoticeList";
		String resultJson = businessInfoService.getNoticeList(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月16日 下午8:04:20
	 * @Description TODO 涉诉信息-裁判文书
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getLawsuitList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getLawsuitList(String companyInfo) {
		String redisKey = companyInfo + "_" + "getLawsuitList";
		String resultJson = businessInfoService.getLawsuitList(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午12:49:12
	 * @Description TODO 涉诉信息-司法协助
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getJudicialChange", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getJudicialChange(String companyInfo) {
		String redisKey = companyInfo + "_" + "getJudicialChange";
		String resultJson = businessInfoService.getJudicialChange(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午2:26:13
	 * @Description TODO 涉诉信息-被执行人
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getExecutedpersonList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getExecutedpersonList(String companyInfo) {
		String redisKey = companyInfo + "_" + "getExecutedpersonList";
		String resultJson = businessInfoService.getExecutedpersonList(companyInfo, redisKey);
		return resultJson;
	}
	
	/**
	 * @author hansz
	 * @version 2018年5月17日 下午4:37:56
	 * @Description TODO 涉诉信息-司法拍卖
	 */
	@RequestMapping(value = "${portalPath}/littleproject/getAuctionsList", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuctionsList(String companyInfo) {
		String redisKey = companyInfo + "_" + "getAuctionsList";
		String resultJson = businessInfoService.getAuctionsList(companyInfo, redisKey);
		return resultJson;
	}
}
