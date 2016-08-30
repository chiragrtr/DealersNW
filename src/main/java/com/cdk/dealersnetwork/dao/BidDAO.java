package com.cdk.dealersnetwork.dao;

import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * Created by malir on 8/30/2016.
 */
public class BidDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void selectDeal(){

    }
}
