package com.qdch.portal.littleproject.dto;


/**
 * 工商信息
 */
public class WorkBusinessDto {
//    private String	name	;
//    private String	econ_kind	;
//    private String	regist_capi	;
//    private String	address	;
//    private String	reg_no	;
//    private String	scope	;
//    private String	term_start	;
//    private String	term_end	;
//    private String	belong_org	;
//    private String	oper_name	;
//    private String	check_date	;
//    private String	start_date	;
//    private String	end_date	;
//    private String	status	;
//    private String	org_no	;
//    private String	credit_no	;
//    private String	province	;
//    private String	city	;
//    private String	domains	;
//    private String	websites. web_name	;
//    private String	websites. web_type	;
//    private String	websites. web_url	;
//    private String	websites.source	;
//    private String	websites.seq	;
//    private String	websites.date	;
//    private String	employees.job_title	;
//    private String	employees.name	;
//    private String	branches.name	;
//    private String	changerecords.change_item	;
//    private String	changerecords.change_date	;
//    private String	changerecords.before_content	;
//    private String	changerecords.after_content	;
//    private String	partners.name	;
//    private String	partners.stock_type	;
//    private String	partners.identify_type	;
//    private String	partners.identify_no	;
//    private String	partners.should_capi_items.shoud_capi	;
//    private String	partners.should_capi_items.invest_type	;
//    private String	partners.should_capi_items.should_capi_date	;
//    private String	partners.real_capi_items.real_capi	;
//    private String	partners.real_capi_items.invest_type	;
//    private String	partners.real_capi_items.real_capi_date	;
//    private String	abnormal_items.in_reason	;
//    private String	abnormal_items. in_date	;
//    private String	abnormal_items. out_reason	;
//    private String	abnormal_items. out_date	;
//    private String	contact.address	;
//    private String	contact.telephone	;
//    private String	contact.email	;

    private String creditNO; //信用代码
    private String orgNO;//组织机构代码
    private String regNO;//注册号
    private String aa;//经营状态
    //private StringEscapeUtils
    private String econ_kind;//公司类型
    private String createDate; //成立日期
    private String operName;//法定代表人
    private String termStart; //营业开始日期
    private String termEnd; //营业结束日期
    private String registCapi;//注册资本
    private String regOrg;//登记机关
    private String regAdd; //企业地址
    private String scope;//经营范围

    public String getCreditNO() {
        return creditNO;
    }

    public void setCreditNO(String creditNO) {
        this.creditNO = creditNO;
    }

    public String getOrgNO() {
        return orgNO;
    }

    public void setOrgNO(String orgNO) {
        this.orgNO = orgNO;
    }

    public String getRegNO() {
        return regNO;
    }

    public void setRegNO(String regNO) {
        this.regNO = regNO;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getEcon_kind() {
        return econ_kind;
    }

    public void setEcon_kind(String econ_kind) {
        this.econ_kind = econ_kind;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }

    public String getRegistCapi() {
        return registCapi;
    }

    public void setRegistCapi(String registCapi) {
        this.registCapi = registCapi;
    }

    public String getRegOrg() {
        return regOrg;
    }

    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }

    public String getRegAdd() {
        return regAdd;
    }

    public void setRegAdd(String regAdd) {
        this.regAdd = regAdd;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
