package com.cdk.dealersnetwork.dto;

/**
 * Created by sharmach on 4/9/2016.
 */
public class Make {
    private int makeId;
    private String make;

    public Make(int makeId, String make) {
        this.makeId = makeId;
        this.make = make;
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
