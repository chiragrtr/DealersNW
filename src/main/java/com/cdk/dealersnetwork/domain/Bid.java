package com.cdk.dealersnetwork.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */
@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @Column(name = "bid_id")
    @GeneratedValue
    private int bidId;
    @Column(name = "b_id")
    private int broadcastId;
    @Column(name = "d_id")
    private int dealerId;
    @Column(name = "bid_date")
    private Timestamp bidDate;
    private float price;
    @Column(name = "delivery_hours")
    private int deliveryHours;
    @Column
    private int status;
    @Column
    private int notified;

    public Bid(int broadcastId, int dealerId, Timestamp bidDate, float price, int deliveryHours, int status, int notified) {
        this.broadcastId = broadcastId;
        this.dealerId = dealerId;
        this.bidDate = bidDate;
        this.price = price;
        this.deliveryHours = deliveryHours;
        this.status = status;
        this.notified = notified;
    }



    public Bid() {
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
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

    public Timestamp getBidDate() {
        return bidDate;
    }

    public void setBidDate(Timestamp bidDate) {
        this.bidDate = bidDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(int deliveryHours) {
        this.deliveryHours = deliveryHours;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNotified() {
        return notified;
    }

    public void setNotified(int notified) {
        this.notified = notified;
    }
}