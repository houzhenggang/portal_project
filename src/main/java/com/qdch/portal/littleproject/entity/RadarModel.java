package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class RadarModel extends DataEntity<RadarModel> {
	private String jys;
	private String jysinfo;
	private String fxsj;
	private String fxlb;
	private double wrzs;
	private String scpg;

	public String getJysinfo() {
		return jysinfo;
	}

	public void setJysinfo(String jysinfo) {
		this.jysinfo = jysinfo;
	}

	public String getFxlb() {
		return fxlb;
	}

	public void setFxlb(String fxlb) {
		this.fxlb = fxlb;
	}

	public String getJys() {
		return jys;
	}

	public void setJys(String jys) {
		this.jys = jys;
	}

	public String getFxsj() {
		return fxsj;
	}

	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}

	public double getWrzs() {
		return wrzs;
	}

	public void setWrzs(double wrzs) {
		this.wrzs = wrzs;
	}

	public String getScpg() {
		return scpg;
	}

	public void setScpg(String scpg) {
		this.scpg = scpg;
	}

}
