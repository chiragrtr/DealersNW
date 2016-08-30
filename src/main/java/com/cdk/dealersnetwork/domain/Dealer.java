package com.cdk.dealersnetwork.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */
@Entity
@Table(name = "dealer")
public class Dealer {
    @Id
    @GeneratedValue
    @Column(name = "d_id")
    private int dealerId;
    @Column
    private String name;
    @Column
    private int phone;
    @Column(name = "reg_date")
    private Date regDate;

    public Dealer() {
    }

    public Dealer(String name, int phone, Date regDate) {
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
