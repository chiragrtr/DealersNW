package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.BidDAO;
import com.cdk.dealersnetwork.dao.BroadcastDAO;
import com.cdk.dealersnetwork.dto.Broadcast;
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

    public BidDAO getBidDAO() {
        return bidDAO;
    }

    public void setBidDAO(BidDAO bidDAO) {
        this.bidDAO = bidDAO;
    }

    public BroadcastDAO getBroadcastDAO(
    ) {
        return broadcastDAO;
    }

    public void setBroadcastDAO(BroadcastDAO broadcastDAO) {
        System.out.println("in controller");
        this.broadcastDAO = broadcastDAO;
    }

    /*@RequestMapping(value = "/showMyOpenBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    String showMyOpenBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("here0");
        int id = Integer.parseInt(request.getParameter("id"));
        String json = "";
        List<Broadcast> broadcastList = broadcastDAO.showMyOpenBroadcasts(id);
        System.out.println("here1");
        if(broadcastList.size() != 0) {
            json += "[";
            System.out.println("here2");
            for (Broadcast broadcast : broadcastDAO.showMyOpenBroadcasts(id)) {
                json += "{";
                json += "\"make\":\"" + broadcast.getMake() + "\",";
                json += "\"model\":\"" + broadcast.getModel() + "\",";
                json += "\"color\":\"" + broadcast.getColor() + "\",";
                json += "\"broadcastDate\":\"" + broadcast.getBroadcastDate() + "\",";
                json += "\"broadcastId\":\"" + broadcast.getBroadcastId() + "\",";
                json += "\"totalBids\":\"" + bidDAO.getNumOfBids(broadcast.getBroadcastId()) + "\",";
                System.out.println("herehere");
                json += "\"latestBid\":\"" + bidDAO.getLatestResponse(broadcast.getBroadcastId())+ "\"";
                System.out.println("herethere");
                json += "},";
            }
            System.out.println("here3");
            json = json.substring(0,json.length()-1);
            json += "]";
        }
        System.out.println("here4");
        return json;
    }*/


    @RequestMapping(value = "/showMyOpenBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    String showMyOpenBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("here0");
        int id = Integer.parseInt(request.getParameter("id"));
        String json = "";
        List<Broadcast> broadcastList = broadcastDAO.showMyOpenBroadcasts(id);
        System.out.println("here1");
        if(broadcastList.size() != 0) {
            json += "[";
            System.out.println("here2");
            for (Broadcast broadcast : broadcastDAO.showMyOpenBroadcasts(id)) {
                json += new Gson().toJson(broadcast);
                json = json.substring(0,json.length()-1) + ",";
                json += "\"totalBids\":" + bidDAO.getNumOfBids(broadcast.getBroadcastId()) + "},";
                System.out.println("herehere");
                if(!bidDAO.getNumOfBids(broadcast.getBroadcastId()).equals("0")) {
                    json += bidDAO.getAllBids(broadcast.getBroadcastId());
                    System.out.println("herethere");
                    json += ",";
                }
            }
            System.out.println("here3");
            json = json.substring(0,json.length()-1);
            json += "]";
            System.out.println("json object is " + json);
        }
        System.out.println("here4");
        return json;
    }

    @RequestMapping(value = "/showOthersOpenBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broadcast> showOthersOpenBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        return broadcastDAO.showOthersOpenBroadcasts(id);
    }

    @RequestMapping(value = "/showMyBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broadcast> showMyBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        return broadcastDAO.showMyBroadcasts(id);
    }

    @RequestMapping(value = "/showOthersBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broadcast> showOthersBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        return broadcastDAO.showOthersBroadcasts(id);
    }

    @RequestMapping(value = "/createBroadcast", method = RequestMethod.POST)
    public
    @ResponseBody
    String createBroadcast(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("h");
        System.out.println("here " + request.getSession().getAttributeNames().nextElement());
        System.out.println("hi " + (String)(request.getSession().getAttribute("dealerId")));
        int dealerId = Integer.parseInt((String)(request.getSession().getAttribute("dealerId")));
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        System.out.println(make + " " + color + " " + model);
        Date broadcastDate = new Date();
        System.out.println(broadcastDate);
        System.out.println(new java.sql.Date(broadcastDate.getTime()).toString());
        System.out.println("here1");
        Broadcast broadcast = new Broadcast(dealerId,make,model,color,broadcastDate,0);
        System.out.println("here2");
        broadcast = broadcastDAO.createBroadcast(broadcast);
        System.out.println("here3");
        //ModelMap modelMap = new ModelMap();
        //modelMap.addAttribute("broadcastId",broadcast.getBroadcastId());
        int broadcastId = broadcast.getBroadcastId();
        System.out.println(broadcastId);
        System.out.println("BROADCAST CREATED");
        return "" + broadcastId;
    }
}
