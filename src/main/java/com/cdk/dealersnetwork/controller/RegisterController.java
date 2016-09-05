package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.DealerDAO;
import com.cdk.dealersnetwork.dto.Dealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by sharmach on 31/8/2016.
 */
@Controller
public class RegisterController {

    @Autowired
    private DealerDAO dealerDAO = null;

    public DealerDAO getDealerDAO(){
        return dealerDAO;
    }

    public void setDealerDAO(DealerDAO dealerDAO) {
        this.dealerDAO = dealerDAO;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
        Dealer dealer = new Dealer(request.getParameter("name"),Long.parseLong(request.getParameter("phone")),new Date(),request.getParameter("email"),request.getParameter("password"));
        dealer = dealerDAO.createDealer(dealer);
        if(dealer.getDealerId() == 0) return new ModelAndView("index");
        HttpSession session = request.getSession();
        session.setAttribute("dealerId","" + dealer.getDealerId());
        session.setAttribute("dealerName","" + dealer.getName());
        return new ModelAndView("home");
    }

}
