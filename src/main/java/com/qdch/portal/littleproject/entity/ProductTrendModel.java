package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class ProductTrendModel extends DataEntity<ProductTrendModel>{
private String vmonth;
private String cplb;
private double cpsl;

public String getCplb() {
	return cplb;
}
public void setCplb(String cplb) {
	this.cplb = cplb;
}
public double getCpsl() {
	return cpsl;
}
public void setCpsl(double cpsl) {
	this.cpsl = cpsl;
}
public String getVmonth() {
	return vmonth;
}
public void setVmonth(String vmonth) {
	this.vmonth = vmonth;
}

}
