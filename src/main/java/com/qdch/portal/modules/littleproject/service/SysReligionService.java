/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.littleproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.modules.littleproject.entity.SysReligion;
import com.qdch.portal.modules.littleproject.dao.SysReligionDao;

/**
 * 区域信息Service
 * @author wf
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class SysReligionService extends CrudService<SysReligionDao, SysReligion> {

	public SysReligion get(String id) {
		return super.get(id);
	}
	
	public List<SysReligion> findList(SysReligion sysReligion) {
		return super.findList(sysReligion);
	}
	
	public Page<SysReligion> findPage(Page<SysReligion> page, SysReligion sysReligion) {
		return super.findPage(page, sysReligion);
	}
	
	@Transactional(readOnly = false)
	public void save(SysReligion sysReligion) {
		super.save(sysReligion);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysReligion sysReligion) {
		super.delete(sysReligion);
	}
	
}