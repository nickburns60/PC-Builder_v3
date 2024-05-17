package com.pcBuilder.models;

import java.math.BigDecimal;

public class StorageDrive {
    private int storageDriveId;
    private int brandId;
    private String productName;
    private String model;
    private int capacityGb;
    private String formFactor;
    private BigDecimal price;

    public StorageDrive() {
    }

    public StorageDrive(int storageDriveId, int brandId, String productName, String model, int capacityGb,
                        String formFactor, BigDecimal price) {
        this.storageDriveId = storageDriveId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
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

    public String toString(){
        return productName + "  $" + price;
    }
}
