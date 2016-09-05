package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Model;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharmach on 5/9/2016.
 */
public class ModelDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        hibernateTemplate.setCheckWriteOperations(false);
    }

    public List<Model> getModels(int makeId){
        List<Model> modelList = new ArrayList<>();
        List<com.cdk.dealersnetwork.domain.Model> domainModelList = hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.Model m where m.makeId=:makeId","makeId",makeId);
        for(com.cdk.dealersnetwork.domain.Model model : domainModelList){
            modelList.add(new Model(model.getModelId(),model.getModel(),model.getMakeId()));
        }
        return modelList;
    }
}
