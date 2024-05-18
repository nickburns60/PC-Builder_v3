package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class FanWithBrand {
    private int fanId;
    private String brandName;
    private String productName;
    private int sizeInMm;
    private int numOfFans;
    private String color;
    private boolean rgb;
    private BigDecimal price;

    public FanWithBrand() {
    }

    public FanWithBrand(int fanId, String brandName, String productName, int sizeInMm, int numOfFans, String color, boolean rgb, BigDecimal price) {
        this.fanId = fanId;
        this.brandName = brandName;
        this.productName = productName;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public String toString() {
        if(fanId < 10){
            return fanId + " )     Brand: " + brandName + "    Model: " + productName + "     Number of Fans: " + numOfFans
                    + "     Color: " + color + "    RGB: " + rgb + "   Size in mm: " + sizeInMm + "     Price: " + price;
        }else{
            return fanId + ")     Brand: " + brandName + "      Model: " + productName + "     Number of Fans: " + numOfFans
                    + "     Color: " + color + "    RGB: " + rgb + "   Size in mm: " + sizeInMm + "     Price: " + price;
        }
    }
}
