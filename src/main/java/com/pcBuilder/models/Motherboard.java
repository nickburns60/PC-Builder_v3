package com.pcBuilder.models;

import java.math.BigDecimal;

public class Motherboard {
    private int motherboardId;
    private int brandId;
    private String productName;
    private String model;
    private int socketId;
    private int formFactorId;
    private int ramTypeId;
    private BigDecimal price;

    public Motherboard() {
    }

    public Motherboard(int motherboardId, int brandId, String productName,
                       String model, int socket, int formFactorId, int ramTypeId, BigDecimal price) {
        this.motherboardId = motherboardId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
        this.socketId = socket;
        this.formFactorId = formFactorId;
        this.ramTypeId = ramTypeId;
        this.price = price;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
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

    public int getSocketId() {
        return socketId;
    }

    public void setSocketId(int socket) {
        this.socketId = socket;
    }

    public int getFormFactorId() {
        return formFactorId;
    }

    public void setFormFactorId(int formFactorId) {
        this.formFactorId = formFactorId;
    }

    public int getRamTypeId() {
        return ramTypeId;
    }

    public void setRamTypeId(int ramTypeId) {
        this.ramTypeId = ramTypeId;
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
