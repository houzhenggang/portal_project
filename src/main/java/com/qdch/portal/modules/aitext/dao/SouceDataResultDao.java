/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.dao;

import java.util.List;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.modules.aitext.entity.SouceData;
import com.qdch.portal.modules.aitext.entity.SouceDataResult;

/**
 * 资源数据结果DAO接口
 * @author lixiaoyi
 * @version 2018-05-21
 */
@MyBatisDao
public interface SouceDataResultDao extends CrudDao<SouceDataResult> {
	/**
	 * 
	 * @author gaozhao
	 * @date 2018年5月31日
	 * @TODO 按关键词查询数据类别
	 */
	public List<SouceDataResult> getDataClass(String aspect);
}