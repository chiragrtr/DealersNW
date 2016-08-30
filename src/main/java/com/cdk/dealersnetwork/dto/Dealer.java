package com.cdk.dealersnetwork.dto;

import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */
public class Dealer {
    private int dealerId;
    private String name;
    private int phone;
    private Date regDate;

    public Dealer() {
    }

    public Dealer(int dealerId, String name, int phone, Date regDate) {
        this.dealerId = dealerId;
        this.name = name;
        this.phone = phone;
        this.regDate = regDate;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}