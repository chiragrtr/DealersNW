package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.BidDAO;
import com.cdk.dealersnetwork.dao.BroadcastDAO;
import com.cdk.dealersnetwork.dao.DealerDAO;
import com.cdk.dealersnetwork.dto.Bid;
import com.cdk.dealersnetwork.dto.Broadcast;
import com.cdk.dealersnetwork.dto.Dealer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by sharmach on 30/8/2016.
 */

@Controller
public class DealerController {

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

    @RequestMapping(value = "/setBid", method = RequestMethod.POST)
    public
    @ResponseBody
    String setBid(HttpServletRequest request, HttpServletResponse response) {
        int broadcastId = Integer.parseInt(request.getParameter("broadcastId"));
        if (broadcastDAO.isOpen(broadcastId)) {
            bidDAO.setBid(Integer.parseInt((String) (request.getSession().getAttribute("dealerId"))), broadcastId, Float.parseFloat(request.getParameter("price")), Integer.parseInt(request.getParameter("deliveryHours")), new Date());
            return "Bid placed, check it in your bids section";
        } else {
            return "Sorry, broadcast just closed";
        }
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
                Dealer dealer = dealerDAO.getDealer(broadcast.getDealerId());
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

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        if(!request.getParameter("sessionId").equals(request.getSession().getId())){
            return new ModelAndView("index");
        }
        Dealer dealer = dealerDAO.login(request.getParameter("email"),request.getParameter("password"));
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("message","invalid");
        if(dealer.getDealerId() == 0 ) return new ModelAndView("index",modelMap);
        HttpSession session = request.getSession();
        session.setAttribute("dealerId","" + dealer.getDealerId());
        session.setAttribute("dealerName","" + dealer.getName());
        return new ModelAndView("home");
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getSession().getId());
        request.getSession().invalidate();
        return new ModelAndView("index");
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
        Dealer dealer = new Dealer(request.getParameter("name"),Long.parseLong(request.getParameter("phone")),new Date(),request.getParameter("email"),request.getParameter("password"));
        dealer = dealerDAO.createDealer(dealer);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("message", "duplicateRegistration");
        if(dealer.getDealerId() == 0) return new ModelAndView("index",modelMap);
        HttpSession session = request.getSession();
        session.setAttribute("dealerId","" + dealer.getDealerId());
        session.setAttribute("dealerName","" + dealer.getName());
        return new ModelAndView("home");
    }


}
