/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.aitext.entity.SouceData;

/**
 * 资源数据DAO接口
 * 
 * @author lixiaoyi
 * @version 2018-05-21
 */
@MyBatisDao
public interface SouceDataDao extends CrudDao<SouceData> {
	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年5月21日 下午5:08:54
	 * @TODO 获取所有来源及个数
	 */
	public List<SouceData> getResource(String aspect);

	public List<SouceData> getResourceby(SouceData souceData);

	public List<SouceData> getResourcebyone(String aspect);

	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年5月21日 下午5:09:47
	 * @TODO 获取所有地名及个数
	 */
	public List<SouceData> getArea(String aspect);

	public List<SouceData> getAreabycon(SouceData souceData);

	public List<SouceData> getAreabyone(String aspect);

	/**
	 * 
	 * @author lixiaoyi
	 * @date 2018年5月21日 下午5:15:22
	 * @TODO 条件查询 一天的 一周的 三天的及全部
	 */
	public List<SouceData> getResourceday(String aspect);

	public List<SouceData> getResourcebycon(SouceData souceData);

	public List<SouceData> getReSoucreall(String aspect);

	public List<SouceData> getNews(String aspect);

	public List<SouceData> getResourceByday(String source);

	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 词云
	 */
	public SouceData getWordCloud(String aspect);

	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 查询关键词热度
	 */
	public List<SouceData> getHotall(String aspect);

	public List<SouceData> getHotbycon(SouceData souceData);

	public List<SouceData> getHotday(String aspect);

	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 按关键词查询数据来源
	 */
	public List<SouceData> getData(String aspect);
	/**
	 * 详细数据-抽取信息
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 
	 */
	public List<SouceData> getExtract(String aspect, String textLY,
			String textQG, String textXG, String textLB);

	public List<SouceData> getExtractbyone(String aspect, String textLY,
			String textQG, String textXG, String textLB);

	public List<SouceData> getExtractby(String aspect, String textLY,
			String textQG, String textXG, String textLB);
	/**
	 * 数据分析  总体友好度
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getFrendlyall(String aspect);

	public List<SouceData> getFrendlyday(String aspect);

	public List<SouceData> getFrendlybycon(SouceData souceData);
	/**
	 * 数据分析  负面评论分布
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getNegativeall(String aspect);

	public List<SouceData> getNegativeday(String aspect);

	public List<SouceData> getNegativebycon(SouceData souceData);
	/**
	 * 数据分析  全网声量
	 * 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getNetworkall(String aspect);

	public List<SouceData> getNetworkday(String aspect);

	public List<SouceData> getNetworkbycon(SouceData souceData);
	/**
	 * 数据分析  评价折线词云 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getBrokenall(String aspect);

	public List<SouceData> getBrokenday(String aspect);

	public List<SouceData> getBrokenbycon(SouceData souceData);
	/**
	 * 数据分析  评价信息 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getEvaluateall(String aspect);

	public List<SouceData> getEvaluateday(String aspect);

	public List<SouceData> getEvaluatebycon(SouceData souceData);
	/**
	 * 数据分析  评价信息 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getInformationall(String aspect);

	public List<SouceData> getInformationday(String aspect);

	public List<SouceData> getInformationbycon(SouceData souceData);
	/**
	 * 数据分析  评价，折线，词云详细 
	 * @author gaozhao
	 * @date 2018年6月1日
	 * @TODO 
	 */
	public List<SouceData> getBrokenInformationall(String aspect);

	public List<SouceData> getBrokenInformationday(String aspect);

	public List<SouceData> getBrokenInformationbycon(SouceData souceData);
	
}