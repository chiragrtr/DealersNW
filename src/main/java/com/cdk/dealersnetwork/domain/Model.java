package com.cdk.dealersnetwork.domain;

import javax.persistence.*;

/**
 * Created by sharmach on 4/9/2016.
 */
@Entity
@Table(name="model")
public class Model {
    @Id
    @GeneratedValue
    @Column(name="model_id")
    private int modelId;
    @Column
    private String model;
    @Column(name="make_id")
    private int makeId;

    public Model(String model, int makeId) {
        this.model = model;
        this.makeId = makeId;
    }

    public Model() {
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }
}
