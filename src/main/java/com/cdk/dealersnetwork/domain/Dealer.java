package com.cdk.dealersnetwork.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by sharmach on 30/8/2016.
 */
@Entity
@Table(name = "dealers")
public class Dealer {
    private int dealerId;
    private String name;
    private int phone;
    private Date regDate;


}
