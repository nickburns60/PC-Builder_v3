package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class CpuCoolerWithBrand {
    private int cpuCoolerId;
    private String  brandName;
    private String productName;
    private String coolerType;
    private int sizeMm;
    private String color;
    private boolean rgb;
    private BigDecimal price;

    public CpuCoolerWithBrand() {
    }

    public CpuCoolerWithBrand(int cpuCoolerId, String brandName, String productName, String coolerType, int sizeMm, String color, boolean rgb, BigDecimal price) {
        this.cpuCoolerId = cpuCoolerId;
        this.brandName = brandName;
        this.productName = productName;
        this.coolerType = coolerType;
        this.sizeMm = sizeMm;
        this.color = color;
        this.rgb = rgb;
        this.price = price;
    }

    public int getCpuCoolerId() {
        return cpuCoolerId;
    }

    public void setCpuCoolerId(int cpuCoolerId) {
        this.cpuCoolerId = cpuCoolerId;
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

    public String getCoolerType() {
        return coolerType;
    }

    public void setCoolerType(String coolerType) {
        this.coolerType = coolerType;
    }

    public int getSizeMm() {
        return sizeMm;
    }

    public void setSizeMm(int sizeMm) {
        this.sizeMm = sizeMm;
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
        if(cpuCoolerId < 10){
            return cpuCoolerId + " )     Brand: " + brandName + "    Model: " + productName + "     Cooler Type: " + coolerType
                    + "     Color: " + color + "    RGB: " + rgb + "   Size in mm: " + sizeMm + "     Price: " + price;
        }else{
            return cpuCoolerId + ")     Brand: " + brandName + "      Model: " + productName +  "     Cooler Type: " + coolerType
                    + "     Color: " + color + "    RGB: " + rgb + "   Size in mm: " + sizeMm + "     Price: " + price;
        }
    }
}
