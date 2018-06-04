package com.qdch.portal.littleproject.dao;

import com.qdch.portal.common.persistence.CrudDao;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.persistence.annotation.MyBatisDao;
import com.qdch.portal.littleproject.entity.RiskEventModel;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface RiskEventModelDao extends CrudDao<RiskEventModel> {

    /**
     * 已上报列表
     * @param riskEventModel
     * @return
     */
    public List<RiskEventModel> getRiskEventList(RiskEventModel riskEventModel);

    /**
     * 已上报列表
     * @param riskEventModel
     * @return
     */
    public List<RiskEventModel> getRiskEventListAllEnterprise(RiskEventModel riskEventModel);

    /**
     * 风险事件详情加处理轨迹
     * @param riskEventModel
     * @return
     */
    public List<RiskEventModel> getDetailsHistoryById(RiskEventModel riskEventModel);

    public RiskEventModel getDetailsById(RiskEventModel riskEventModel);


    public List<Map<String,Integer>> getCountGroupBY(RiskEventModel riskEventModel);




}
