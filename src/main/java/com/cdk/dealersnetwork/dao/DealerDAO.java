package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Dealer;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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
        hibernateTemplate.setCheckWriteOperations(false);
    }

    public Dealer createDealer(Dealer dealer){
        System.out.println("here");
        com.cdk.dealersnetwork.domain.Dealer domainDealer = new com.cdk.dealersnetwork.domain.Dealer(dealer.getName(),dealer.getPhone(),new Date(dealer.getRegDate().getTime()),dealer.getEmail(),dealer.getPassword());
        System.out.println("hrer1");
        hibernateTemplate.save(domainDealer);
        System.out.println("hrer2");
        dealer.setDealerId(domainDealer.getDealerId());
        System.out.println("hrer3");

        return dealer;
    }

    public boolean isAuthorized(String email, String password){
        List<com.cdk.dealersnetwork.domain.Dealer> dealerList = (List<com.cdk.dealersnetwork.domain.Dealer>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Dealer d where d.email=: email and d.password=: password",new String[]{"email","password"}, new Object[]{email,password});
        if(dealerList.size() != 0){
            return true;
        }
        return false;
    }

    public void deleteDealer(int dealerId){
        com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
        hibernateTemplate.delete(dealer);
    }
}
