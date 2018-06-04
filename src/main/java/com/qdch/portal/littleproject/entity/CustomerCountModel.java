package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class CustomerCountModel extends DataEntity<CustomerCountModel> {
	private String jys;
	private String jysmc;
	private String jysinfo;
	private int grrzrkhs;// 个人融资客户数
	private int grtzrkhs;// 个人投资人
	private int jgrzrkhs;// 机构融资人
	private int jgrtzrkhs;// 机构投资人
	private int jgkhs;// 机构客户数总数
	private int grkhs;// 个人总数

	public String getJys() {
		return jys;
	}

	public void setJys(String jys) {
		this.jys = jys;
	}

	public String getJysmc() {
		return jysmc;
	}

	public void setJysmc(String jysmc) {
		this.jysmc = jysmc;
	}

	public String getJysinfo() {
		return jysinfo;
	}

	public void setJysinfo(String jysinfo) {
		this.jysinfo = jysinfo;
	}

	public int getGrrzrkhs() {
		return grrzrkhs;
	}

	public void setGrrzrkhs(int grrzrkhs) {
		this.grrzrkhs = grrzrkhs;
	}

	public int getGrtzrkhs() {
		return grtzrkhs;
	}

	public void setGrtzrkhs(int grtzrkhs) {
		this.grtzrkhs = grtzrkhs;
	}

	public int getJgrzrkhs() {
		return jgrzrkhs;
	}

	public void setJgrzrkhs(int jgrzrkhs) {
		this.jgrzrkhs = jgrzrkhs;
	}

	public int getJgrtzrkhs() {
		return jgrtzrkhs;
	}

	public void setJgrtzrkhs(int jgrtzrkhs) {
		this.jgrtzrkhs = jgrtzrkhs;
	}

	public int getJgkhs() {
		return jgkhs;
	}

	public void setJgkhs(int jgkhs) {
		this.jgkhs = jgkhs;
	}

	public int getGrkhs() {
		return grkhs;
	}

	public void setGrkhs(int grkhs) {
		this.grkhs = grkhs;
	}

}
