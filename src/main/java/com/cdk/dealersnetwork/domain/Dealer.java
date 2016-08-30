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
}
