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
	 public  List<SouceData> getResourcebyone(String aspect);
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
	 
	 public  List<SouceData> getResourcebycon(SouceData souceData);
	 
	
	 public  List<SouceData> getReSoucreall(String aspect);
	 
   public List<SouceData> getNews(String aspect);
   
   public List<SouceData> getResourceByday(String source);

}