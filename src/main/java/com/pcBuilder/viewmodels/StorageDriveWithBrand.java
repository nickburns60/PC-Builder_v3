package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class StorageDriveWithBrand {
    private int storageDriveId;
    private String brandName;
    private String productName;
    private int capacityGb;
    private String formFactor;
    private BigDecimal price;

    public StorageDriveWithBrand() {
    }

    public StorageDriveWithBrand(int storageDriveId, String brandName, String productName,
                                 int capacityGb, String formFactor, BigDecimal price) {
        this.storageDriveId = storageDriveId;
        this.brandName = brandName;
        this.productName = productName;
        this.capacityGb = capacityGb;
        this.formFactor = formFactor;
        this.price = price;
    }

    public int getStorageDriveId() {
        return storageDriveId;
    }

    public void setStorageDriveId(int storageDriveId) {
        this.storageDriveId = storageDriveId;
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

    public int getCapacityGb() {
        return capacityGb;
    }

    public void setCapacityGb(int capacityGb) {
        this.capacityGb = capacityGb;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if (storageDriveId < 10) {
            return storageDriveId + " )     Brand: " + brandName + "    Model: " + productName + "    Capacity: " + capacityGb
                    + "     Form Factor: " + formFactor + "     Price: " + price;
        } else {
            return storageDriveId + ")     Brand: " + brandName + "      Model: " + productName + "     Capacity: " + capacityGb
                    + "     Form Factor: " + formFactor + "             Price: " + price;
        }
    }
}