package com.cdk.dealersnetwork.dto;

/**
 * Created by sharmach on 4/9/2016.
 */
public class Model {
    private int modelId;
    private String model;
    private int makeId;

    public Model(int modelId, String model, int makeId) {
        this.modelId = modelId;
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
