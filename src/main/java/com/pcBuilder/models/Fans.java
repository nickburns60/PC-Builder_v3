package com.pcBuilder.models;

import java.math.BigDecimal;

public class Fans {
    private int fanId;
    private int brandId;
    private String productName;
    private String model;
    private int sizeInMm;
    private int numOfFans;
    private String color;
    private boolean rgb;
    private BigDecimal price;

    public Fans() {
    }

    public Fans(int fanId, int brandId, String productName, String model, int sizeInMm, int numOfFans, String color, boolean rgb, BigDecimal price) {
        this.fanId = fanId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
        this.sizeInMm = sizeInMm;
        this.numOfFans = numOfFans;
        this.color = color;
        this.rgb = rgb;
        this.price = price;
    }

    public int getFanId() {
        return fanId;
    }

    public void setFanId(int fanId) {
        this.fanId = fanId;
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

    public int getSizeInMm() {
        return sizeInMm;
    }

    public void setSizeInMm(int sizeInMm) {
        this.sizeInMm = sizeInMm;
    }

    public int getNumOfFans() {
        return numOfFans;
    }

    public void setNumOfFans(int numOfFans) {
        this.numOfFans = numOfFans;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
