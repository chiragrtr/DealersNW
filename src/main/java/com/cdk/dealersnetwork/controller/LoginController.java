package com.cdk.dealersnetwork.controller;

import com.cdk.dealersnetwork.dao.DealerDAO;
import com.cdk.dealersnetwork.dto.Dealer;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Dealer dealer = dealerDAO.login(email,password);
        if(dealer.getDealerId() == 0 ){
            System.out.println("login failed");
            return new ModelAndView("index");
        }
        System.out.println("login successful");
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("dealerId",dealer.getDealerId());
        return new ModelAndView("home",modelMap);
    }

}
