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
        com.cdk.dealersnetwork.domain.Bid domainBid = (com.cdk.dealersnetwork.domain.Bid) hibernateTemplate.get(com.cdk.dealersnetwork.domain.Bid.class, bidId);
        //Bid bid = new Bid(domainBid.getBidId(),domainBid.getBroadcastId(),domainBid.getDealerId(),domainBid.getBidDate(),domainBid.getPrice(),domainBid.getDeliveryHours(),domainBid.getStatus(),domainBid.getNotified());
        domainBid.setStatus(1);
        domainBid.setNotified(1);
        hibernateTemplate.saveOrUpdate(domainBid);
        System.out.println("bid with bidId=" + bidId + " status, notified set to 1");
    }

    public List<Bid> showBids(int dealerId, int broadcastId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId and b.dealerId=:dealerId", new String[]{"broadcastId", "dealerId"}, new Object[]{broadcastId, dealerId});
        List<Bid> bidList = new ArrayList<>();
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            Bid bid = new Bid(domainBid.getBidId(), domainBid.getBroadcastId(), domainBid.getDealerId(), domainBid.getBidDate(), domainBid.getPrice(), domainBid.getDeliveryHours(), domainBid.getStatus(), domainBid.getNotified());
            bidList.add(bid);
        }
        return bidList;
    }

    public Bid setBid(int dealerId, int broadcastId, float price, int deliveryHours, Date bidDate) {
        com.cdk.dealersnetwork.domain.Bid domainBid = new com.cdk.dealersnetwork.domain.Bid(broadcastId, dealerId, new java.sql.Timestamp(bidDate.getTime()), price, deliveryHours, 0, 0);
        hibernateTemplate.save(domainBid);
        Bid bid = new Bid(domainBid.getBidId(), domainBid.getBroadcastId(), domainBid.getDealerId(), domainBid.getBidDate(), domainBid.getPrice(), domainBid.getDeliveryHours(), domainBid.getStatus(), domainBid.getNotified());
        return bid;
    }

    /*public void rejectDeals(int broadcastId){
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId","broadcastId",broadcastId);
        for(com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList){
            domainBid.setStatus(2);
            hibernateTemplate.update(domainBid);
        }
    }*/

    public void rejectDeals(int broadcastId, int bidId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            if (domainBid.getBidId() != bidId) {
                domainBid.setStatus(2);
                domainBid.setNotified(1);
                System.out.println("bid with bidId=" + domainBid.getBidId() + " status set to 2, notified set to 1");
                hibernateTemplate.saveOrUpdate(domainBid);
            }
        }
    }

    public String getLatestResponse(int broadcastId) {
        String response = "";
        System.out.println("at1");
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        System.out.println("at2");
        if (bidList.size() == 0) {
            System.out.println("at3");
            return "NO BIDS";
        }
        System.out.println("at4");
        com.cdk.dealersnetwork.domain.Bid bid = bidList.get(bidList.size() - 1);
        response += "Dealer " + bid.getDealerId() + " has bid " + bid.getPrice() + "$ and can deliver in " + bid.getDeliveryHours() + "hours";
        return response;
    }

    public String getNumOfBids(int broadcastId) {
        String numOfBids = "";
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        numOfBids += bidList.size();
        System.out.println("size is " + numOfBids);
        return numOfBids;
    }

    public String getAllBids(int broadcastId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId", "broadcastId", broadcastId);
        String json = "";
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            int dealerId = domainBid.getDealerId();

            com.cdk.dealersnetwork.domain.Dealer dealer = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Dealer.class,dealerId);
            System.out.println("NAME IS "+ dealer.getName());
            json += new Gson().toJson(domainBid) + "," + new Gson().toJson(new Dealer(dealer.getDealerId(),dealer.getName(),dealer.getPhone(),dealer.getRegDate(),dealer.getEmail(),dealer.getPassword())) + ",";
        }
        json = json.substring(0, json.length() - 1);
        System.out.println("bids are " + json);
        return json;
    }

    public int getBroadcastId(int bidId) {
        com.cdk.dealersnetwork.domain.Bid domainBid = (com.cdk.dealersnetwork.domain.Bid) hibernateTemplate.get(com.cdk.dealersnetwork.domain.Bid.class, bidId);
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
        System.out.println("size is " + newBids);
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