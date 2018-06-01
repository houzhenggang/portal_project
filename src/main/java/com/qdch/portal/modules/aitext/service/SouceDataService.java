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

	public List<SouceData> getNews(String aspect){
		return sourcedata.getNews(aspect);
		
	}
}