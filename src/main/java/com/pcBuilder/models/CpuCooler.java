package com.pcBuilder.models;

import java.math.BigDecimal;

public class CpuCooler {
    private int cpuCoolerId;
    private int brandId;
    private String productName;
    private String model;
    private String coolerType;
    private int sizeMm;
    private String color;
    private boolean rgb;
    private BigDecimal price;

    public CpuCooler() {
    }

    public CpuCooler(int cpuCoolerId, int brandId, String productName, String model,
                     String coolerType, int sizeMm, String color, boolean rgb, BigDecimal price) {
        this.cpuCoolerId = cpuCoolerId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
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

    public String toString(){
        return productName + "  $" + price;
    }
}
