package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.DealerDAO;
import com.cdk.dealersnetwork.dto.Dealer;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by sharmach on 31/8/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private DealerDAO dealerDAO = null;

    public DealerDAO getDealerDAO(){
        return dealerDAO;
    }

    public void setDealerDAO(DealerDAO dealerDAO) {
        this.dealerDAO = dealerDAO;
    }


    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        Dealer dealer = dealerDAO.login(request.getParameter("email"),request.getParameter("password"));
        if(dealer.getDealerId() == 0 ) return new ModelAndView("index");
        HttpSession session = request.getSession();
        session.setAttribute("dealerId","" + dealer.getDealerId());
        session.setAttribute("dealerName","" + dealer.getName());
        return new ModelAndView("home");
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        return new ModelAndView("index");
    }
}
