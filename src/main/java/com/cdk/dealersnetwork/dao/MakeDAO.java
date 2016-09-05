package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Make;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharmach on 5/9/2016.
 */
public class MakeDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        hibernateTemplate.setCheckWriteOperations(false);
    }

    public List<Make> getMakes(){
        List<Make> makeList = new ArrayList<>();
        for(com.cdk.dealersnetwork.domain.Make make : hibernateTemplate.loadAll(com.cdk.dealersnetwork.domain.Make.class)){
            makeList.add(new Make(make.getMakeId(), make.getMake()));
        }
        return makeList;
    }
}
