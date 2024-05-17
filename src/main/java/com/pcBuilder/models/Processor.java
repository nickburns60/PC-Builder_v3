package com.pcBuilder.models;

import java.math.BigDecimal;

public class Processor {
    private int processorId;
    private int brandId;
    private int socketId;
    private int ramTypeId;
    private String productName;
    private String model;
    private BigDecimal price;

    public Processor() {
    }

    public Processor(int processorId, int brandId, int socketId, int ramTypeId, String productName,
                     String model, BigDecimal price) {
        this.processorId = processorId;
        this.brandId = brandId;
        this.socketId = socketId;
        this.ramTypeId = ramTypeId;
        this.productName = productName;
        this.model = model;
        this.price = price;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSocketId() {
        return socketId;
    }

    public void setSocketId(int socketId) {
        this.socketId = socketId;
    }

    public int getRamTypeId() {
        return ramTypeId;
    }

    public void setRamTypeId(int ramTypeId) {
        this.ramTypeId = ramTypeId;
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

