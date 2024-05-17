package com.pcBuilder.models;

import java.math.BigDecimal;

public class PowerSupply {
    private int psuId;
    private int brandId;
    private String productName;
    private String model;
    private int psuWattageId;
    private String cableType;
    private String energyEfficiency;
    private BigDecimal price;

    public PowerSupply() {
    }

    public PowerSupply(int psuId, int brandId, String productName, String model,
                       int psuWattageId, String cableType, String energyEfficiency, BigDecimal price) {
        this.psuId = psuId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
        this.psuWattageId = psuWattageId;
        this.cableType = cableType;
        this.energyEfficiency = energyEfficiency;
        this.price = price;
    }

    public int getPsuId() {
        return psuId;
    }

    public void setPsuId(int psuId) {
        this.psuId = psuId;
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

    public int getPsuWattageId() {
        return psuWattageId;
    }

    public void setPsuWattageId(int psuWattageId) {
        this.psuWattageId = psuWattageId;
    }

    public String getCableType() {
        return cableType;
    }

    public void setCableType(String cableType) {
        this.cableType = cableType;
    }

    public String getEnergyEfficiency() {
        return energyEfficiency;
    }

    public void setEnergyEfficiency(String energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
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
