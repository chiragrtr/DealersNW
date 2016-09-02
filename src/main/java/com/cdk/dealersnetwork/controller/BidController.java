package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.BidDAO;
import com.cdk.dealersnetwork.dto.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by malir on 9/2/2016.
 */
@Controller
public class BidController {

    @Autowired
    BidDAO bidDAO = null;

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
        System.out.println("hi " + (String)(request.getSession().getAttribute("dealerId")));
        int dealerId = Integer.parseInt((String)(request.getSession().getAttribute("dealerId")));
        int broadcastId = Integer.parseInt(request.getParameter("broadcastId"));
        float price = Float.parseFloat(request.getParameter("price"));
        int deliveryHours = Integer.parseInt(request.getParameter("deliveryHours"));
        Date bidDate = new Date();
        System.out.println(bidDate);
        System.out.println(new java.sql.Date(bidDate.getTime()).toString());
        System.out.println("here1");
        Bid bid = new Bid();

        System.out.println("here2");
        bid = bidDAO.setBid(dealerId,broadcastId,price,deliveryHours,bidDate);
        System.out.println("here3");
        //ModelMap modelMap = new ModelMap();
        //modelMap.addAttribute("broadcastId",broadcast.getBroadcastId());
        int bidId = bid.getBidId();
        System.out.println(bidId);
        System.out.println("BID PLACED");
        return "" + bidId;
    }
}
