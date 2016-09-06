package com.cdk.dealersnetwork.dao;

import com.cdk.dealersnetwork.dto.Bid;
import com.cdk.dealersnetwork.dto.Dealer;
import com.google.gson.Gson;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by malir on 8/30/2016.
 */
@Component
public class BidDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        hibernateTemplate.setCheckWriteOperations(false);
        this.hibernateTemplate = hibernateTemplate;
    }

    private BroadcastDAO broadcastDAO = new BroadcastDAO();

    public void selectDeal(int bidId) {
        com.cdk.dealersnetwork.domain.Bid domainBid = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Bid.class, bidId);
        domainBid.setStatus(1);
        domainBid.setNotified(1);
        hibernateTemplate.saveOrUpdate(domainBid);
    }

    public Bid setBid(int dealerId, int broadcastId, float price, int deliveryHours, Date bidDate) {
        com.cdk.dealersnetwork.domain.Bid domainBid = new com.cdk.dealersnetwork.domain.Bid(broadcastId, dealerId, new java.sql.Timestamp(bidDate.getTime()), price, deliveryHours, 0, 0);
        hibernateTemplate.save(domainBid);
        return new Bid(domainBid.getBidId(), domainBid.getBroadcastId(), domainBid.getDealerId(), domainBid.getBidDate(), domainBid.getPrice(), domainBid.getDeliveryHours(), domainBid.getStatus(), domainBid.getNotified());
    }

    public void rejectDeals(int broadcastId, int bidId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            if (domainBid.getBidId() != bidId) {
                domainBid.setStatus(2);
                domainBid.setNotified(1);
                hibernateTemplate.saveOrUpdate(domainBid);
            }
        }
    }

    public String getNumOfBids(int broadcastId) {
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        return "" + bidList.size();
    }

    public String getAllBids(int broadcastId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        String json = "";
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            int dealerId = domainBid.getDealerId();
            com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
            json += new Gson().toJson(domainBid) + "," + new Gson().toJson(new Dealer(dealer.getDealerId(),dealer.getName(),dealer.getPhone(),dealer.getRegDate(),dealer.getEmail(),dealer.getPassword())) + ",";
        }
        return json.substring(0, json.length() - 1);
    }

    public int getBroadcastId(int bidId) {
        com.cdk.dealersnetwork.domain.Bid domainBid = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Bid.class, bidId);
        return domainBid.getBroadcastId();
    }

    public List<Bid> showMyBids(int dealerId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.dealerId=:dealerId", "dealerId", dealerId);
        List<Bid> bidList = new ArrayList<>();
        for(com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList){
            bidList.add(new Bid(domainBid.getBidId(),domainBid.getBroadcastId(),domainBid.getDealerId(),domainBid.getBidDate(),domainBid.getPrice(),domainBid.getDeliveryHours(),domainBid.getStatus(),domainBid.getNotified()));
        }
        return bidList;
    }

    public int getNumOfNewBids(int broadcastId) {
        int newBids = 0;
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId and b.notified=:newBids", new String[]{"broadcastId","newBids"}, new Object[]{broadcastId,newBids});
        newBids += bidList.size();
        return newBids;
    }

    public void setAllBidsViewed(int broadcastId){
        int notified = 0;
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId and b.notified=:notified", new String[]{"broadcastId","notified"}, new Object[]{broadcastId,notified});
        for(com.cdk.dealersnetwork.domain.Bid bid : bidList){
            bid.setNotified(1);
            hibernateTemplate.update(bid);
        }
    }

    public void setAllMyBidsViewed(int dealerId) {
        int status = 0;
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.dealerId=:dealerId and b.status!=:status",new String[]{"dealerId","status"}, new Object[]{dealerId,status});
        for(com.cdk.dealersnetwork.domain.Bid bid : bidList){
            bid.setNotified(2);
            hibernateTemplate.update(bid);
        }
    }
}