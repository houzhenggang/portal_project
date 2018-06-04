package com.qdch.portal.littleproject.service;

import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.service.CrudService;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.littleproject.dao.RiskEventModelDao;
import com.qdch.portal.littleproject.entity.RiskEventModel;
import com.qdch.portal.modules.cms.dao.CmsNewsDao;
import com.qdch.portal.modules.cms.entity.CmsNews;
import com.qdch.portal.modules.cms.entity.CmsNewsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 风险事件
 * @author wangfeng
 * @version
 */
@Service
@Transactional(readOnly = true)
public class RiskEventModelService extends CrudService<RiskEventModelDao, RiskEventModel> {


    @Autowired
    private RiskEventModelDao riskEventModelDao;

    public RiskEventModel get(String id) {
        return super.get(id);
    }


    @Transactional(readOnly = false)
    public void save(RiskEventModel riskEventModel) {
        //super.save(riskEventModel);

        riskEventModelDao.insert(riskEventModel);
        riskEventModelDao.update(riskEventModel);

    }



    @Transactional(readOnly = false)
    public Page<RiskEventModel> getRiskEventList(Page<RiskEventModel> page, RiskEventModel riskEventModel) {
        if(StringUtils.isBlank(riskEventModel.getClzt())){
            riskEventModel.setClzt("已上报");
        }

        riskEventModel.setPage(page);
        if(riskEventModel.getClzt().equals("全部")){
            page.setList(dao.getRiskEventListAllEnterprise(riskEventModel));
        }else{
            page.setList(dao.getRiskEventList(riskEventModel));
        }

        return page;
    }
}
