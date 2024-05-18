package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class MoboWithSocketFormRamBrand {
    private int motherboardId;
    private String brandName;
    private String productName;
    private String socketType;
    private String formFactorName;
    private String ramTypeName;
    private BigDecimal price;
    private int socketId;

    public MoboWithSocketFormRamBrand() {
    }

    public MoboWithSocketFormRamBrand(int motherboardId, String brandName, String productName,
                                      String socketType, String formFactorName, String ramTypeName,
                                      BigDecimal price, int socketId) {
        this.motherboardId = motherboardId;
        this.brandName = brandName;
        this.productName = productName;
        this.socketType = socketType;
        this.formFactorName = formFactorName;
        this.ramTypeName = ramTypeName;
        this.price = price;
        this.socketId = socketId;
    }

    public int getSocketId() {
        return socketId;
    }

    public void setSocketId(int socketId) {
        this.socketId = socketId;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
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

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }

    public String getFormFactorName() {
        return formFactorName;
    }

    public void setFormFactorName(String formFactorName) {
        this.formFactorName = formFactorName;
    }

    public String getRamTypeName() {
        return ramTypeName;
    }

    public void setRamTypeName(String ramTypeName) {
        this.ramTypeName = ramTypeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if(motherboardId < 10){
            return motherboardId + " )     Brand: " + brandName + "    Model: " + productName + "    Socket: " + socketType
                    + "     Form Factor: " + formFactorName +  "     Ram Type: " + ramTypeName +  "     Price: " + price;
        }else{
            return motherboardId + ")     Brand: " + brandName + "      Model: " + productName + "     Socket: " + socketType
                    + "     Form Factor: " + formFactorName + "          Ram Type: " + ramTypeName +  "             Price: " + price;
        }
    }
}
