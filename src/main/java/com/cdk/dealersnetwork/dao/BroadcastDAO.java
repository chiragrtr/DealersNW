package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Broadcast;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malir on 8/30/2016.
 */
public class BroadcastDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Broadcast createBroadcast(Broadcast broadcast) {
        com.cdk.dealersnetwork.domain.Broadcast domainBroadcast = new com.cdk.dealersnetwork.domain.Broadcast();
        domainBroadcast.setDealerId(broadcast.getDealerId());
        domainBroadcast.setBroadcastDate(broadcast.getBroadcastDate());
        domainBroadcast.setMake(broadcast.getMake());
        domainBroadcast.setModel(broadcast.getModel());
        domainBroadcast.setColor(broadcast.getColor());
        domainBroadcast.setStatus(broadcast.getStatus());
        hibernateTemplate.save(domainBroadcast);
        broadcast.setBroadcastId(domainBroadcast.getBroadcastId());
        return broadcast;
    }

    public List<Broadcast> showMyBroadcasts(int dealerId) {
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId =:bdid", "bdid", dealerId);
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    public List<Broadcast> showOthersBroadcasts(int dealerId){
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId !=:bdid", "bdid", dealerId);
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    private List<Broadcast> getBroadcastsList(List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList) {
        List<Broadcast> dtoBroadcastList = null;
        if(domainBroadcastList != null && domainBroadcastList.size() !=0){
            dtoBroadcastList = new ArrayList<>();
            for(com.cdk.dealersnetwork.domain.Broadcast b: domainBroadcastList){
                Broadcast broadcast = new Broadcast();
                broadcast.setBroadcastId(b.getBroadcastId());
                broadcast.setDealerId(b.getDealerId());
                broadcast.setMake(b.getMake());
                broadcast.setModel(b.getModel());
                broadcast.setColor(b.getColor());
                broadcast.setStatus(b.getStatus());
                broadcast.setBroadcastDate(b.getBroadcastDate());
                dtoBroadcastList.add(broadcast);
            }
        }
        return dtoBroadcastList;
    }
}
