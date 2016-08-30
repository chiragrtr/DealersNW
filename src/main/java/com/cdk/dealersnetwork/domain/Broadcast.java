package com.cdk.dealersnetwork.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */
@Entity
@Table(name = "broadcast")
public class Broadcast {
    @Id
    @Column(name = "b_id")
    @GeneratedValue
    private int broadcastId;
    @Column(name = "d_id")
    private int dealerId;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private String color;
    @Column(name = "broadcast_date")
    private Date broadcastDate;
    @Column
    private int status;

    public Broadcast(int dealerId, String make, String model, String color, Date broadcastDate, int status) {
        this.dealerId = dealerId;
        this.make = make;
        this.model = model;
        this.color = color;
        this.broadcastDate = broadcastDate;
        this.status = status;
    }

    public int getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(int broadcastId) {
        this.broadcastId = broadcastId;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBroadcastDate() {
        return broadcastDate;
    }

    public void setBroadcastDate(Date broadcastDate) {
        this.broadcastDate = broadcastDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}