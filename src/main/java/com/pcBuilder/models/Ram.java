package com.pcBuilder.models;

import java.math.BigDecimal;

public class Ram {
    private int ramId;
    private int brandId;
    private String productName;
    private String model;
    private int ramTypeId;
    private int capacityGb;
    private int numOfSticks;
    private boolean rgb;
    private BigDecimal price;

    public Ram() {
    }

    public Ram(int ramId, int brandId, String productName, String model, int ramTypeId, int capacityGb,
               int numOfSticks, boolean rgb, BigDecimal price) {
        this.ramId = ramId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
        this.ramTypeId = ramTypeId;
        this.capacityGb = capacityGb;
        this.numOfSticks = numOfSticks;
        this.rgb = rgb;
        this.price = price;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRamTypeId() {
        return ramTypeId;
    }

    public void setRamTypeId(int ramTypeId) {
        this.ramTypeId = ramTypeId;
    }

    public int getCapacityGb() {
        return capacityGb;
    }

    public void setCapacityGb(int capacityGb) {
        this.capacityGb = capacityGb;
    }

    public int getNumOfSticks() {
        return numOfSticks;
    }

    public void setNumOfSticks(int numOfSticks) {
        this.numOfSticks = numOfSticks;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString(){
        return productName + "  $" + price;
    }
}