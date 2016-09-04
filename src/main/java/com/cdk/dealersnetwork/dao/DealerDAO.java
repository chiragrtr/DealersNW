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

    public Dealer createDealer(Dealer dealer){
        com.cdk.dealersnetwork.domain.Dealer domainDealer = new com.cdk.dealersnetwork.domain.Dealer(dealer.getName(),dealer.getPhone(),new Timestamp(dealer.getRegDate().getTime()),dealer.getEmail(),dealer.getPassword());
        if(hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Dealer d where d.email=:email","email",dealer.getEmail()).size()>0){
            dealer.setDealerId(0);
            System.out.println("Already exists");
            return dealer;
        }
        hibernateTemplate.save(domainDealer);
        System.out.println("Added dealer to database");
        dealer.setDealerId(domainDealer.getDealerId());

        return dealer;
    }

    public Dealer login(String email, String password){
        List<com.cdk.dealersnetwork.domain.Dealer> dealerList = (List<com.cdk.dealersnetwork.domain.Dealer>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Dealer d where d.email=:email and d.password=:password",new String[]{"email","password"}, new Object[]{email,password});
        Dealer dealer = null;
        if(dealerList.size() != 0){
            com.cdk.dealersnetwork.domain.Dealer domainDealer = dealerList.get(0);
            dealer = new Dealer(domainDealer.getDealerId(),domainDealer.getName(),domainDealer.getPhone(),domainDealer.getRegDate(),domainDealer.getEmail(),domainDealer.getPassword());
            System.out.println("dealer found with given email id n password");
            return dealer;
        }
        System.out.println("invalid credentials");
        dealer = new Dealer();
        dealer.setDealerId(0);
        return dealer;
    }

    public void deleteDealer(int dealerId){
        com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
        hibernateTemplate.delete(dealer);
    }
}
