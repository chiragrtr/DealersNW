package com.cdk.dealersnetwork.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */

public class Broadcast {
    private int broadcastId;
    private int dealerId;
    private String make;
    private String model;
    private String color;
    private Date broadcastDate;
    private int status;

    public Broadcast() {
    }

    public Broadcast(int dealerId, String make, String model, String color, Date broadcastDate, int status) {
        this.dealerId = dealerId;
        this.make = make;
        this.model = model;
        this.color = color;
        this.broadcastDate = broadcastDate;
        this.status = status;
    }

    public Broadcast(int broadcastId, int dealerId, String make, String model, String color, Date broadcastDate, int status) {
        this.broadcastId = broadcastId;
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
