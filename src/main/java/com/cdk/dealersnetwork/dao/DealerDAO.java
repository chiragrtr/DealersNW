package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Dealer;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by sharmach on 30/8/2016.
 */
@Component
public class DealerDAO {

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        hibernateTemplate.setCheckWriteOperations(false);
    }

    public Dealer getDealer(int dealerId){
        com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
        return new Dealer(dealerId,dealer.getName(),dealer.getPhone(),dealer.getRegDate(),dealer.getEmail(),dealer.getPassword());
    }

    public Dealer createDealer(Dealer dealer){
        if(hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Dealer d where d.email=:email","email",dealer.getEmail()).size()>0){
            dealer.setDealerId(0);       //duplicate registration
            return dealer;
        }
        com.cdk.dealersnetwork.domain.Dealer domainDealer = new com.cdk.dealersnetwork.domain.Dealer(dealer.getName(),dealer.getPhone(),new Timestamp(dealer.getRegDate().getTime()),dealer.getEmail(),dealer.getPassword());
        hibernateTemplate.save(domainDealer);
        dealer.setDealerId(domainDealer.getDealerId());
        return dealer;
    }

    public Dealer login(String email, String password){
        List<com.cdk.dealersnetwork.domain.Dealer> dealerList = (List<com.cdk.dealersnetwork.domain.Dealer>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Dealer d where d.email=:email and d.password=:password",new String[]{"email","password"}, new Object[]{email,password});
        if(dealerList.size() != 0){
            com.cdk.dealersnetwork.domain.Dealer domainDealer = dealerList.get(0);
            return new Dealer(domainDealer.getDealerId(),domainDealer.getName(),domainDealer.getPhone(),domainDealer.getRegDate(),domainDealer.getEmail(),domainDealer.getPassword());
        }
        return new Dealer(0);           //login failed
    }

    public void deleteDealer(int dealerId){
        com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
        hibernateTemplate.delete(dealer);
    }
}
