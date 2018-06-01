/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.entity;

import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 资源数据结果Entity
 * @author lixiaoyi
 * @version 2018-05-21
 */
public class SouceDataResult extends DataEntity<SouceDataResult> {
	
	private static final long serialVersionUID = 1L;
	private String comment;		// comment
	private String aspect;		// aspect
	private String keypart;		// keypart
	private String label;		// label
	private String classe;		// classe
	
	public SouceDataResult() {
		super();
	}

	public SouceDataResult(String id){
		super(id);
	}

	@Length(min=0, max=255, message="comment长度必须介于 0 和 255 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Length(min=0, max=50, message="aspect长度必须介于 0 和 50 之间")
	public String getAspect() {
		return aspect;
	}

	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	
	@Length(min=0, max=255, message="keypart长度必须介于 0 和 255 之间")
	public String getKeypart() {
		return keypart;
	}

	public void setKeypart(String keypart) {
		this.keypart = keypart;
	}
	
	@Length(min=0, max=12, message="label长度必须介于 0 和 12 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=0, max=20, message="classe长度必须介于 0 和 20 之间")
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
}