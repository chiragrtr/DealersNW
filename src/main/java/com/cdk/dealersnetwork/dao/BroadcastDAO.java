package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Broadcast;
import com.cdk.dealersnetwork.dto.Dealer;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malir on 8/30/2016.
 */
@Component
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
        domainBroadcast.setBroadcastDate(new java.sql.Timestamp(broadcast.getBroadcastDate().getTime()));
        domainBroadcast.setMake(broadcast.getMake());
        domainBroadcast.setModel(broadcast.getModel());
        domainBroadcast.setColor(broadcast.getColor());
        domainBroadcast.setStatus(broadcast.getStatus());
        hibernateTemplate.save(domainBroadcast);
        broadcast.setBroadcastId(domainBroadcast.getBroadcastId());
        return broadcast;
    }

    public List<Broadcast> showMyBroadcasts(int dealerId) {
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId =:dealerId", "dealerId", dealerId);
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    public List<Broadcast> showMyOpenBroadcasts(int dealerId) {
        int status = 0;
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId =:dealerId and b.status=:status", new String[]{"dealerId", "status"}, new Object[]{dealerId, status});
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    public List<Broadcast> showMyClosedBroadcasts(int dealerId) {
        int status = 1;
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId =:dealerId and b.status=:status", new String[]{"dealerId", "status"}, new Object[]{dealerId, status});
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    public List<Broadcast> showOthersBroadcasts(int dealerId) {
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId !=:dealerId", "dealerId", dealerId);
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    /*public List<Broadcast> showOthersOpenBroadcasts(int dealerId) {
        int status = 0;
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId !=:dealerId and b.status=:status", new String[]{"dealerId", "status"}, new Object[]{dealerId, status});
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    public List<Broadcast> showOthersClosedBroadcasts(int dealerId) {
        int status = 1;
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId !=:dealerId and b.status =:status", new String[]{"dealerId", "status"}, new Object[]{dealerId, status});
        List<Broadcast> dtoBroadcastList = null;
        return getBroadcastsList(domainBroadcastList);
    }

    private List<Broadcast> getBroadcastsList(List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList) {
        List<Broadcast> dtoBroadcastList = null;
        if (domainBroadcastList != null && domainBroadcastList.size() != 0) {
            dtoBroadcastList = new ArrayList<>();
            for (com.cdk.dealersnetwork.domain.Broadcast b : domainBroadcastList) {
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
    }*/

    public void closeBroadcast(int broadcastId) {
        com.cdk.dealersnetwork.domain.Broadcast broadcast = (com.cdk.dealersnetwork.domain.Broadcast) hibernateTemplate.get(com.cdk.dealersnetwork.domain.Broadcast.class, broadcastId);
        broadcast.setStatus(1);
        hibernateTemplate.saveOrUpdate(broadcast);
    }

    public List<Broadcast> showOthersOpenBroadcasts(int dealerId){
        int status = 0;
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId !=:dealerId and b.status=:status",new String[]{"dealerId","status"}, new Object[]{dealerId,status});
        return getBroadcastsList(domainBroadcastList);
    }

    public List<Broadcast> showOthersClosedBroadcasts(int dealerId){
        int status = 1;
        List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList = (List<com.cdk.dealersnetwork.domain.Broadcast>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Broadcast b where b.dealerId !=:dealerId and b.status=:status", new String[]{"dealerId","status"}, new Object[]{dealerId,status});
        return getBroadcastsList(domainBroadcastList);

    }

    private List<Broadcast> getBroadcastsList(List<com.cdk.dealersnetwork.domain.Broadcast> domainBroadcastList) {
        List<Broadcast> dtoBroadcastList = new ArrayList<>();
        if(domainBroadcastList != null && domainBroadcastList.size() !=0){
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

    public Broadcast getBroadcast(int broadcastId) {
        com.cdk.dealersnetwork.domain.Broadcast domainBroadcast = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Broadcast.class, broadcastId);
        return new Broadcast(broadcastId,domainBroadcast.getDealerId(),domainBroadcast.getMake(),domainBroadcast.getModel(),domainBroadcast.getColor(),domainBroadcast.getBroadcastDate(),domainBroadcast.getStatus());
    }

    public Dealer getDealer(int dealerId){
        com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
        return new Dealer(dealerId,dealer.getName(),dealer.getPhone(),dealer.getRegDate(),dealer.getEmail(),dealer.getPassword());
    }

    public boolean isOpen(int broadcastId) {
        return (hibernateTemplate.get(com.cdk.dealersnetwork.domain.Broadcast.class, broadcastId).getStatus() == 0);
    }
}
