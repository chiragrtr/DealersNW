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
        hibernateTemplate.setCheckWriteOperations(false);
        this.hibernateTemplate = hibernateTemplate;
    }

    public void selectDeal(int bidId) {
        com.cdk.dealersnetwork.domain.Bid domainBid = hibernateTemplate.get(com.cdk.dealersnetwork.domain.Bid.class, bidId);
        //Bid bid = new Bid(domainBid.getBidId(),domainBid.getBroadcastId(),domainBid.getDealerId(),domainBid.getBidDate(),domainBid.getPrice(),domainBid.getDeliveryHours(),domainBid.getStatus(),domainBid.getNotified());
        domainBid.setStatus(1);
        hibernateTemplate.update(domainBid);
    }

    public List<Bid> showBids(int dealerId, int broadcastId) {
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId and b.dealerId=:dealerId", new String[]{"broadcastId", "dealerId"}, new Object[]{broadcastId, dealerId});
        List<Bid> bidList = new ArrayList<>();
        for (com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList) {
            Bid bid = new Bid(domainBid.getBidId(),domainBid.getBroadcastId(),domainBid.getDealerId(),domainBid.getBidDate(),domainBid.getPrice(),domainBid.getDeliveryHours(),domainBid.getStatus(),domainBid.getNotified());
            bidList.add(bid);
        }
        return bidList;
    }

    public void setBid(int dealerId, int broadcastId, float price, int deliveryHours, Date bidDate) {
        hibernateTemplate.save(new com.cdk.dealersnetwork.domain.Bid(broadcastId, dealerId, bidDate, price, deliveryHours, 0, 0));
    }

    public void rejectDeals(int broadcastId){
        List<com.cdk.dealersnetwork.domain.Bid> domainBidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId","broadcastId",broadcastId);
        for(com.cdk.dealersnetwork.domain.Bid domainBid : domainBidList){
            domainBid.setStatus(2);
            hibernateTemplate.update(domainBid);
        }
    }

    public String getLatestResponse(int broadcastId) {
        String response = "";
        System.out.println("at1");
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId","broadcastId",broadcastId);
        System.out.println("at2");
        if(bidList.size() == 0){
            System.out.println("at3");
            return "NO BIDS";
        }
        System.out.println("at4");
        com.cdk.dealersnetwork.domain.Bid bid = bidList.get(bidList.size()-1);
        response += "Dealer " + bid.getDealerId() + " has bid " + bid.getPrice() + "$ and can deliver in " + bid.getDeliveryHours() + "hours";
        return response;
    }

    public String getNumOfBids(int broadcastId) {
        String numOfBids = "";
        List<com.cdk.dealersnetwork.domain.Bid> bidList = (List<com.cdk.dealersnetwork.domain.Bid>) hibernateTemplate.findByNamedParam("from com.cdk.dealersnetwork.domain.Bid b where b.broadcastId=:broadcastId","broadcastId",broadcastId);
        numOfBids += bidList.size();
        System.out.println("size is " + numOfBids);
        return numOfBids;
    }
}