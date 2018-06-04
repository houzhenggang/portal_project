/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.aitext.entity.SouceData;
import com.qdch.portal.modules.aitext.dao.SouceDataDao;

/**
 * 资源数据Service
 * @author lixiaoyi
 * @version 2018-05-21
 */
@Service
@Transactional(readOnly = true)
public class SouceDataService extends CrudService<SouceDataDao, SouceData> {
	@Autowired
   private SouceDataDao sourcedata;
	/**
	 * 活跃媒体 全部 一天 三天 7天
	 * @author lixiaoyi
	 * @date 2018年5月22日 下午2:17:22
	 * @TODO
	 */
	public List<SouceData> getResouce(String aspect){
		return sourcedata.getResource(aspect);
		
	}
	public List<SouceData> getResouceby(SouceData souceData){
		return sourcedata.getResourceby(souceData);
		
	}
	public List<SouceData> getResoucebyone(String aspect){
		return sourcedata.getResourcebyone(aspect);
		
	}
	/**
	 * 区域 全部 一天 三天 七天
	 * @author lixiaoyi
	 * @date 2018年5月22日 下午2:18:13
	 * @TODO
	 */
	public List<SouceData> getArea(String aspect){
		return sourcedata.getArea(aspect);
	}
	public List<SouceData> getAreabycon(SouceData souceData){
		return sourcedata.getAreabycon(souceData);
	}
	
	public List<SouceData> getAreabyone(String aspect){
		return sourcedata.getAreabyone(aspect);
	}
	
	
	/**
	 * 声量走势 全部 一天 三天 七天
	 * @author lixiaoyi
	 * @date 2018年5月22日 下午2:19:06
	 * @TODO
	 */
	public List<SouceData> getResouceall(String aspect){
		//查分类
		List<SouceData> resource= sourcedata.getReSoucreall(aspect);
		//循环分类 查天数
		for (SouceData souceData : resource) {
		List<SouceData> data=	sourcedata.getResourceByday(souceData.getSource());
	           souceData.setSouceDatas(data);
		}
		return resource;
	}
	public List<SouceData> getResouceday(String aspect){
	   List<SouceData> resource=sourcedata.getResourceday(aspect);
		for (SouceData souceData : resource) {
			List<SouceData> data=	sourcedata.getResourceByday(souceData.getSource());
		           souceData.setSouceDatas(data);
			}
	  return resource;
		
		
	}
	
	public List<SouceData> getResoucebycon(SouceData souceData){
		return sourcedata.getResourcebycon(souceData);
		
	}
	//最新消息
	public List<SouceData> getNews(String aspect){
		return sourcedata.getNews(aspect);
		
	}
	/**
	 * 词云 
	 * @author gaozhao
	 * @date 2018年5月31日 
	 * @TODO
	 */
	public SouceData getWordCloud(String aspect){
		return sourcedata.getWordCloud(aspect);
	}
	/**
	 * 热度状况 全部 一天 三天 七天
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO
	 */
	public List<SouceData> getHotall(String aspect){
		return sourcedata.getHotall(aspect);
	}
	public List<SouceData> getHotbycon(SouceData souceData){
		return sourcedata.getHotbycon(souceData);
	}
	
	public List<SouceData> getHotday(String aspect){
		return sourcedata.getHotday(aspect);
	}
	/**
	 * 详细数据-数据来源
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO
	 */
	public List<SouceData> getData(String aspect) {
		
		return sourcedata.getData(aspect);
	}
	/**
	 * 详细数据-抽取信息
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 
	 */
	public List<SouceData> getExtract(String aspect, String textLY,
			String textQG, String textXG, String textLB) {
		
		return sourcedata.getExtract(aspect,textLY,textQG,textXG,textLB);
	}
	public List<SouceData> getExtractbyone(String aspect, String textLY,
			String textQG, String textXG, String textLB) {
		
		return sourcedata.getExtractbyone(aspect,textLY,textQG,textXG,textLB);
	}
	public List<SouceData> getExtractby(String aspect, String textLY,
			String textQG, String textXG, String textLB) {
		
		return sourcedata.getExtractby(aspect,textLY,textQG,textXG,textLB);
	}
	/**
	 * 数据分析  总体友好度
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getFrendlyall(String aspect) {
		 return sourcedata.getFrendlyall(aspect);
	}
	public List<SouceData> getFrendlyday(String aspect) {
		 return sourcedata.getFrendlyday(aspect);
	}
	public List<SouceData> getFrendlybycon(SouceData souceData) {
		 return sourcedata.getFrendlybycon(souceData);
	}
	/**
	 * 数据分析  负面评论分布
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getNegativeall(String aspect) {
		return sourcedata.getNegativeall(aspect);
	}
	public List<SouceData> getNegativeday(String aspect) {
		return sourcedata.getNegativeday(aspect);
	}
	public List<SouceData> getNegativebycon(SouceData souceData) {
		return sourcedata.getNegativebycon(souceData);
	}
	/**
	 * 数据分析  全网声量
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getNetworkall(String aspect) {
		return sourcedata.getNetworkall(aspect);
	}
	public List<SouceData> getNetworkday(String aspect) {
		return sourcedata.getNetworkday(aspect);
	}
	public List<SouceData> getNetworkbycon(SouceData souceData) {
		return sourcedata.getNetworkbycon(souceData);
	}
	/**
	 * 数据分析  评价折线词云 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getBrokenall(String aspect) {
		return sourcedata.getBrokenall(aspect);
	}
	public List<SouceData> getBrokenday(String aspect) {
		return sourcedata.getBrokenday(aspect);
	}
	public List<SouceData> getBrokenbycon(SouceData souceData) {
		return sourcedata.getBrokenbycon(souceData);
	}
	/**
	 * 数据分析  评价
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getEvaluateall(String aspect) {
		return sourcedata.getEvaluateall(aspect);
	}
	public List<SouceData> getEvaluateday(String aspect) {
		return sourcedata.getEvaluateday(aspect);
	}
	public List<SouceData> getEvaluatebycon(SouceData souceData) {
		return sourcedata.getEvaluatebycon(souceData);
	}
	/**
	 * 数据分析  评价信息 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getInformationall(String aspect) {
		return sourcedata.getInformationall(aspect);
	}
	public List<SouceData> getInformationday(String aspect) {
		return sourcedata.getInformationday(aspect);
	}
	public List<SouceData> getInformationbycon(SouceData souceData) {
		return sourcedata.getInformationbycon(souceData);
	}
	/**
	 * 数据分析  评价，折线，词云详细 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getBrokenInformationall(String aspect) {
		return sourcedata.getBrokenInformationall(aspect);
	}
	public List<SouceData> getBrokenInformationday(String aspect) {
		return sourcedata.getBrokenInformationday(aspect);
	}
	public List<SouceData> getBrokenInformationbycon(SouceData souceData) {
		return sourcedata.getBrokenInformationbycon(souceData);
	}
	
	
}