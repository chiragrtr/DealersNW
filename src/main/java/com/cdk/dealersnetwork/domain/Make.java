package com.cdk.dealersnetwork.domain;

import javax.persistence.*;

/**
 * Created by sharmach on 4/9/2016.
 */
@Entity
@Table(name="make")
public class Make {
    @Id
    @GeneratedValue
    @Column(name="make_id")
    private int makeId;
    @Column
    private String make;

    public Make(String make) {
        this.make = make;
    }

    public Make() {
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
