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
        String name = request.getParameter("name");
        long phone = Long.parseLong(request.getParameter("phone"));
        Date regDate = new Date();
        System.out.println(regDate);
        System.out.println(new java.sql.Date(regDate.getTime()).toString());
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Dealer dealer = new Dealer(name,phone,regDate,email,password);
        dealer = dealerDAO.createDealer(dealer);
        if(dealer.getDealerId() == 0){
            return new ModelAndView("index");
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("dealerId",dealer.getDealerId());
        request.getSession().setAttribute("dealerId","" + dealer.getDealerId());
        return new ModelAndView("home",modelMap);
    }

}
