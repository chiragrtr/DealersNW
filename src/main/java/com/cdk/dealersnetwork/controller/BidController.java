package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.BidDAO;
import com.cdk.dealersnetwork.dao.BroadcastDAO;
import com.cdk.dealersnetwork.dto.Bid;
import com.cdk.dealersnetwork.dto.Broadcast;
import com.cdk.dealersnetwork.dto.Dealer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by malir on 9/2/2016.
 */
@Controller
@Transactional
public class BidController {

    @Autowired
    BidDAO bidDAO = null;

    @Autowired
    BroadcastDAO broadcastDAO = null;

    public BroadcastDAO getBroadcastDAO() {
        return broadcastDAO;
    }

    public void setBroadcastDAO(BroadcastDAO broadcastDAO) {
        this.broadcastDAO = broadcastDAO;
    }

    public BidDAO getBidDAO() {
        return bidDAO;
    }

    public void setBidDAO(BidDAO bidDAO) {
        this.bidDAO = bidDAO;
    }

    @RequestMapping(value = "/setBid", method = RequestMethod.POST)
    public
    @ResponseBody
    String setBid(HttpServletRequest request, HttpServletResponse response) {
        Bid bid = bidDAO.setBid(Integer.parseInt((String) (request.getSession().getAttribute("dealerId"))), Integer.parseInt(request.getParameter("broadcastId")), Float.parseFloat(request.getParameter("price")), Integer.parseInt(request.getParameter("deliveryHours")), new Date());
        return "" + bid.getBidId();
    }

    @RequestMapping(value = "/selectDeal", method = RequestMethod.POST)
    public
    @ResponseBody
    String selectDeal(HttpServletRequest request, HttpServletResponse response) {
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        int broadcastId = bidDAO.getBroadcastId(bidId);
        bidDAO.selectDeal(bidId);
        broadcastDAO.closeBroadcast(broadcastId);
        bidDAO.rejectDeals(broadcastId, bidId);
        return "Bid selected";
    }

    @RequestMapping(value = "/showMyBids", method = RequestMethod.POST)
    public
    @ResponseBody
    String showMyBids(HttpServletRequest request, HttpServletResponse response) {
        String json = "";
        List<Bid> bidList = bidDAO.showMyBids(Integer.parseInt(request.getParameter("dealerId")));
        if (bidList.size() != 0) {
            json = "[";
            for (Bid bid : bidList) {
                Broadcast broadcast = broadcastDAO.getBroadcast(bid.getBroadcastId());
                Dealer dealer = broadcastDAO.getDealer(broadcast.getDealerId());
                json += new Gson().toJson(bid) + "," + new Gson().toJson(broadcast) + "," + new Gson().toJson(dealer) + ",";
            }
            json = json.substring(0, json.length() - 1) + "]";
        }
        return json;
    }

    @RequestMapping(value = "/numOfNewBids", method = RequestMethod.POST)
    public
    @ResponseBody
    String numOfNewBids(HttpServletRequest request, HttpServletResponse response) {
        int newBids = 0;
        for (Broadcast broadcast : broadcastDAO.showMyBroadcasts(Integer.parseInt(request.getParameter("dealerId")))) {
            newBids += bidDAO.getNumOfNewBids(broadcast.getBroadcastId());
        }
        return "" + newBids;
    }

    @RequestMapping(value = "/allBidsViewed", method = RequestMethod.POST)
    public
    @ResponseBody
    String allBidsViewed(HttpServletRequest request, HttpServletResponse response) {
        for (Broadcast broadcast : broadcastDAO.showMyBroadcasts(Integer.parseInt(request.getParameter("dealerId")))) {
            bidDAO.setAllBidsViewed(broadcast.getBroadcastId());
        }
        return numOfNewBids(request, response);
    }

    @RequestMapping(value = "/numOfSelectedBids", method = RequestMethod.POST)
    public
    @ResponseBody
    String numOfSelectedBids(HttpServletRequest request, HttpServletResponse response) {
        int selectedBids = 0;
        int rejectedBids = 0;
        for (Bid bid : bidDAO.showMyBids(Integer.parseInt(request.getParameter("dealerId")))) {
            if (bid.getStatus() == 1 && bid.getNotified() <= 1) selectedBids++;
            else if (bid.getStatus() == 2 && bid.getNotified() <= 1) rejectedBids++;
        }
        return "[{\"selectedBids\":" + selectedBids + ",\"rejectedBids\":" + rejectedBids + "}]";
    }

    @RequestMapping(value = "/allMyBidsViewed", method = RequestMethod.POST)
    public
    @ResponseBody
    String allMyBidsViewed(HttpServletRequest request, HttpServletResponse response) {
        bidDAO.setAllMyBidsViewed(Integer.parseInt(request.getParameter("dealerId")));
        return numOfSelectedBids(request, response);
    }

}
