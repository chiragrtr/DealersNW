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
        if (domainBroadcastList != null && domainBroadcastList.size() != 0) {
            dtoBroadcastList = new ArrayList<>();
            for (com.cdk.dealersnetwork.domain.Broadcast e : domainBroadcastList) {
                Broadcast broadcast = new Broadcast();
                broadcast.setBroadcastId(e.getBroadcastId());
                broadcast.setDealerId(e.getDealerId());
                broadcast.setMake(e.getMake());
                broadcast.setModel(e.getModel());
                broadcast.setColor(e.getColor());
                broadcast.setStatus(e.getStatus());
                broadcast.setBroadcastDate(e.getBroadcastDate());
                dtoBroadcastList.add(broadcast);
            }

        }
        return dtoBroadcastList;

    }
}
