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
}