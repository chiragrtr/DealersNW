package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Bid;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void selectDeal(int bidId) {
        com.cdk.dealersnetwork.domain.Bid domainBid = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Bid.class, bidId);
        //Bid bid = new Bid(domainBid.getBidId(),domainBid.getBroadcastId(),domainBid.getDealerId(),domainBid.getBidDate(),domainBid.getPrice(),domainBid.getDeliveryHours(),domainBid.getStatus(),domainBid.getNotified());
        domainBid.setStatus(1);
        hibernateTemplate.update(domainBid);
    }

    public List<Bid> showBids(int dealerId, int broadcastId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.b_id=:broadcastId and b.d_id=:dealerId", new String[]{"broadcastId", "dealerId"}, new Object[]{broadcastId, dealerId});
        List<Bid> bidList = new ArrayList<>();
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            Bid bid = new Bid();
            bid.setBidId(domainBid.getBidId());
            bid.setDealerId(domainBid.getDealerId());
            bid.setBidDate(domainBid.getBidDate());
            bid.setBroadcastId(domainBid.getBroadcastId());
            bid.setStatus(domainBid.getStatus());
            bid.setNotified(domainBid.getNotified());
            bid.setDeliveryHours(domainBid.getDeliveryHours());
            bid.setPrice(domainBid.getPrice());
            bidList.add(bid);
        }
        return bidList;
    }

    public void setBid(int dealerId, int broadcastId, float price, int deliveryHours, Date bidDate) {
        hibernateTemplate.save(new com.cdk.dealersnetwork.domain.Bid(broadcastId, dealerId, bidDate, price, deliveryHours, 0, 0));
    }

    public void rejectDeals(int broadcastId){
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.b_id=:broadcastId","broadcastId",broadcastId);
    }
}