package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Dealer;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * Created by sharmach on 30/8/2016.
 */
public class DealerDAO {

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Dealer createDealer(Dealer dealer){
        com.cdk.dealersnetwork.domain.Dealer domainDealer = new com.cdk.dealersnetwork.domain.Dealer();
        domainDealer.setName(dealer.getName());
        domainDealer.setPhone(dealer.getPhone());
        domainDealer.setRegDate(dealer.getRegDate());
        hibernateTemplate.save(domainDealer);
        dealer.setDealerId(domainDealer.getDealerId());
        return dealer;
    }

    public void deleteDealer(int dealerId){
        com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
        hibernateTemplate.delete(dealer);
    }
}
