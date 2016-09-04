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
        System.out.println("here " + request.getSession().getAttributeNames().nextElement());
        System.out.println("hi " + (String) (request.getSession().getAttribute("dealerId")));
        int dealerId = Integer.parseInt((String) (request.getSession().getAttribute("dealerId")));
        int broadcastId = Integer.parseInt(request.getParameter("broadcastId"));
        float price = Float.parseFloat(request.getParameter("price"));
        int deliveryHours = Integer.parseInt(request.getParameter("deliveryHours"));
        Date bidDate = new Date();
        System.out.println(bidDate);
        System.out.println(new java.sql.Date(bidDate.getTime()).toString());
        System.out.println("here1");
        Bid bid = new Bid();

        System.out.println("here2");
        bid = bidDAO.setBid(dealerId, broadcastId, price, deliveryHours, bidDate);
        System.out.println("here3");
        //ModelMap modelMap = new ModelMap();
        //modelMap.addAttribute("broadcastId",broadcast.getBroadcastId());
        int bidId = bid.getBidId();
        System.out.println(bidId);
        System.out.println("BID PLACED");
        return "" + bidId;
    }

    @RequestMapping(value = "/selectDeal", method = RequestMethod.POST)
    public
    @ResponseBody
    String selectDeal(HttpServletRequest request, HttpServletResponse response) {
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        bidDAO.selectDeal(bidId);
        int broadcastId = bidDAO.getBroadcastId(bidId);
        broadcastDAO.closeBroadcast(broadcastId);
        bidDAO.rejectDeals(broadcastId, bidId);
        return "Bid selected";
    }

    @RequestMapping(value = "/showMyBids", method = RequestMethod.POST)
    public
    @ResponseBody
    String showMyBids(HttpServletRequest request, HttpServletResponse response) {
        int dealerId = Integer.parseInt(request.getParameter("dealerId"));
        String json = "";
        List<Bid> bidList = bidDAO.showMyBids(dealerId);
        if (bidList.size() != 0) {
            json = "[";
            for (Bid bid : bidList) {
                Broadcast broadcast = broadcastDAO.getBroadcast(bid.getBroadcastId());
                Dealer dealer = broadcastDAO.getDealer(broadcast.getDealerId());
                json += new Gson().toJson(dealer);
                json += ",";
                json += new Gson().toJson(broadcast);
                json += ",";
                json += new Gson().toJson(bid);
                json += ",";
            }
            json = json.substring(0, json.length() - 1);
            json += "]";
        }
        return json;
    }
}
