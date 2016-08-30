package com.cdk.dealersnetwork.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */
@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @Column(name="bid_id")
    @GeneratedValue
    private int bidId;
    @Column(name="b_id")
    private int broadcastId;
    @Column(name="d_id")
    private int dealerId;
    @Column(name="bid_date")
    private Date bidDate;
    private float price;
    @Column(name="delivery_hours")
    private int deliveryHours;
    @Column
    private int status;
    @Column
    private int notified;
}