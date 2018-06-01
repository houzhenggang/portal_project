/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.aitext.entity.SouceDataResult;
import com.qdch.portal.modules.aitext.dao.SouceDataResultDao;

/**
 * 资源数据结果Service
 * @author lixiaoyi
 * @version 2018-05-21
 */
@Service
@Transactional(readOnly = true)
public class SouceDataResultService extends CrudService<SouceDataResultDao, SouceDataResult> {

	public SouceDataResult get(String id) {
		return super.get(id);
	}
	
	public List<SouceDataResult> findList(SouceDataResult souceDataResult) {
		return super.findList(souceDataResult);
	}
	
	public Page<SouceDataResult> findPage(Page<SouceDataResult> page, SouceDataResult souceDataResult) {
		return super.findPage(page, souceDataResult);
	}
	
	@Transactional(readOnly = false)
	public void save(SouceDataResult souceDataResult) {
		super.save(souceDataResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(SouceDataResult souceDataResult) {
		super.delete(souceDataResult);
	}
	
}