/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.littleproject.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 区域信息Entity
 * @author wf
 * @version 2018-05-15
 */
public class SysReligion extends DataEntity<SysReligion> {
	
	private static final long serialVersionUID = 1L;
	private String parentid;		// parentid
	private String parentids;		// parentids
	private String name;		// name
	private String sort;		// sort
	
	public SysReligion() {
		super();
	}

	public SysReligion(String id){
		super(id);
	}

	@Length(min=0, max=11, message="parentid长度必须介于 0 和 11 之间")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=255, message="parentids长度必须介于 0 和 255 之间")
	public String getParentids() {
		return parentids;
	}

	public void setParentids(String parentids) {
		this.parentids = parentids;
	}
	
	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="sort长度必须介于 0 和 1 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}