package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.BroadcastDAO;
import com.cdk.dealersnetwork.dto.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sharmach on 1/9/2016.
 */
@Controller
public class BroadcastController {
    @Autowired
    BroadcastDAO broadcastDAO = null;

    public BroadcastDAO getBroadcastDAO() {
        return broadcastDAO;
    }

    public void setBroadcastDAO(BroadcastDAO broadcastDAO) {
        this.broadcastDAO = broadcastDAO;
    }

    @RequestMapping(value = "/showMyOpenBroadcasts", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Broadcast> showMyOpenBroadcasts(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        return broadcastDAO.showMyOpenBroadcasts(id);
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
}
