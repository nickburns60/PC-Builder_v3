package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class RamWithBrandRamType {
    private int ramId;
    private String brandName;
    private String productName;
    private String ramTypeName;
    private BigDecimal price;
    private int ramTypeId;

    public RamWithBrandRamType() {
    }

    public RamWithBrandRamType(int ramId, String brandName, String productName,
                               String ramTypeName, BigDecimal price, int ramTypeId) {
        this.ramId = ramId;
        this.brandName = brandName;
        this.productName = productName;
        this.ramTypeName = ramTypeName;
        this.price = price;
        this.ramTypeId = ramTypeId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
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

    public int getRamTypeId() {
        return ramTypeId;
    }

    public void setRamTypeId(int ramTypeId) {
        this.ramTypeId = ramTypeId;
    }

    @Override
    public String toString() {
        if(ramId < 10){
            return ramId + " )     Brand: " + brandName + "    Model: " + productName +  "     Ram Type: " + ramTypeName +  "     Price: " + price;
        }else{
            return ramId + ")     Brand: " + brandName + "      Model: " + productName + "          Ram Type: " + ramTypeName +  "             Price: " + price;
        }
    }
}
