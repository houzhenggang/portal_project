/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.modules.aitext.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qdch.portal.common.persistence.DataEntity;

/**
 * 资源数据Entity
 * @author lixiaoyi
 * @version 2018-05-21
 */
public class SouceData extends DataEntity<SouceData> {
	
	private static final long serialVersionUID = 1L;
	private String source;		// source
	private Date time;		// time
	private String area;		// area
	private String comment;		// comment
	private String emotion;		// emotion
	private String extract;		// extract
	private String subdivision;		// subdivision
	private String nums;
	private String date;
	private String max;
	
	private List<SouceData> souceDatas;
	
	public List<SouceData> getSouceDatas() {
		return souceDatas;
	}

	public void setSouceDatas(List<SouceData> souceDatas) {
		this.souceDatas = souceDatas;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public SouceData() {
		super();
	}

	public SouceData(String id){
		super(id);
	}

	@Length(min=0, max=50, message="source长度必须介于 0 和 50 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@Length(min=0, max=50, message="area长度必须介于 0 和 50 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="comment长度必须介于 0 和 255 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Length(min=0, max=30, message="emotion长度必须介于 0 和 30 之间")
	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	
	@Length(min=0, max=255, message="extract长度必须介于 0 和 255 之间")
	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}
	
	@Length(min=0, max=30, message="subdivision长度必须介于 0 和 30 之间")
	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	
	
}