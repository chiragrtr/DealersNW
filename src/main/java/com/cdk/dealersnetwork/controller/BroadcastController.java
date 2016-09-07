package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.BidDAO;
import com.cdk.dealersnetwork.dao.BroadcastDAO;
import com.cdk.dealersnetwork.dao.DealerDAO;
import com.cdk.dealersnetwork.dto.Bid;
import com.cdk.dealersnetwork.dto.Broadcast;
import com.cdk.dealersnetwork.dto.Dealer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by sharmach on 1/9/2016.
 */
@Controller
public class BroadcastController {

    @Autowired
    BroadcastDAO broadcastDAO = null;

    @Autowired
    BidDAO bidDAO = null;

    @Autowired
    DealerDAO dealerDAO = null;

    public DealerDAO getDealerDAO() {
        return dealerDAO;
    }

    public void setDealerDAO(DealerDAO dealerDAO) {
        this.dealerDAO = dealerDAO;
    }

    public BidDAO getBidDAO() {
        return bidDAO;
    }

    public void setBidDAO(BidDAO bidDAO) {
        this.bidDAO = bidDAO;
    }

    public BroadcastDAO getBroadcastDAO() {
        return broadcastDAO;
    }

    public void setBroadcastDAO(BroadcastDAO broadcastDAO) {
        this.broadcastDAO = broadcastDAO;
    }

    @RequestMapping(value = "/showMyOpenBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    String showMyOpenBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        List<Broadcast> broadcastList = broadcastDAO.showMyOpenBroadcasts(Integer.parseInt(request.getParameter("id")));
        return getMyBroadcastsWithBids(broadcastList);
    }

    private String getMyBroadcastsWithBids(List<Broadcast> broadcastList) {
        String json = "";
        if (broadcastList.size() != 0) {
            json += "[";
            for (int i = broadcastList.size()-1; i >= 0 ; i--) {
                Broadcast broadcast = broadcastList.get(i);
                String numOfBids = bidDAO.getNumOfBids(broadcast.getBroadcastId());
                json += new Gson().toJson(broadcast);
                json = json.substring(0, json.length() - 1) + ",\"totalBids\":" + numOfBids + "},";
                if (!numOfBids.equals("0")) {
                    json += bidDAO.getAllBids(broadcast.getBroadcastId()) + ",";
                }
            }
            json = json.substring(0, json.length() - 1) + "]";
        }
        return json;
    }

    @RequestMapping(value = "/showMyClosedBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    String showMyClosedBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        List<Broadcast> broadcastList = broadcastDAO.showMyClosedBroadcasts(Integer.parseInt(request.getParameter("id")));
        return getMyBroadcastsWithBids(broadcastList);
    }

    @RequestMapping(value = "/showOthersOpenBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    String showOthersOpenBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        int dealerId = Integer.parseInt(request.getParameter("id"));
        String json = "[";
        List<Broadcast> broadcastList = broadcastDAO.showOthersOpenBroadcasts(dealerId);
        for (Broadcast broadcast : broadcastList) {
            if (hasDealerBidOnThisBroadcast(dealerId, broadcast.getBroadcastId())) continue;
            Dealer dealer = dealerDAO.getDealer(broadcast.getDealerId());
            json += new Gson().toJson(broadcast) + "," + new Gson().toJson(dealer) + ",";
        }
        if (json.length() == 1) return "";
        return json.substring(0, json.length() - 1) + "]";
    }

    private boolean hasDealerBidOnThisBroadcast(int dealerId, int broadcastId) {
        for (Bid bid : bidDAO.showMyBids(dealerId)) {
            if (bid.getBroadcastId() == broadcastId) return true;
        }
        return false;
    }

    @RequestMapping(value = "/showOthersClosedBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    String showOthersClosedBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        String json = "";
        List<Broadcast> broadcastList = broadcastDAO.showOthersClosedBroadcasts(Integer.parseInt(request.getParameter("id")));
        if (broadcastList.size() != 0) {
            json += "[";
            for (Broadcast broadcast : broadcastList) {
                Dealer dealer = dealerDAO.getDealer(broadcast.getDealerId());
                json += new Gson().toJson(broadcast) + "," + new Gson().toJson(dealer) + ",";
            }
            json = json.substring(0, json.length() - 1) + "]";
        }
        return json;
    }

    @RequestMapping(value = "/showMyBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broadcast> showMyBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        return broadcastDAO.showMyBroadcasts(Integer.parseInt(request.getParameter("id")));
    }

    @RequestMapping(value = "/showOthersBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broadcast> showOthersBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        return broadcastDAO.showOthersBroadcasts(Integer.parseInt(request.getParameter("id")));
    }

    @RequestMapping(value = "/createBroadcast", method = RequestMethod.POST)
    public
    @ResponseBody
    String createBroadcast(HttpServletRequest request, HttpServletResponse response) {
        Broadcast broadcast = new Broadcast(Integer.parseInt((String) (request.getSession().getAttribute("dealerId"))), request.getParameter("make"), request.getParameter("model"), request.getParameter("color"), new Date(), 0);
        broadcast = broadcastDAO.createBroadcast(broadcast);
        return "" + broadcast.getBroadcastId();
    }

    @RequestMapping(value = "/cancelBroadcast", method = RequestMethod.POST)
    public
    @ResponseBody
    String cancelBroadcast(HttpServletRequest request, HttpServletResponse response) {
        int broadcastId = Integer.parseInt(request.getParameter("broadcastId"));
        bidDAO.rejectDeals(broadcastId, 0);
        broadcastDAO.closeBroadcast(broadcastId);
        return showMyOpenBroadcasts(request, response);
    }
}
