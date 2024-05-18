package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class PowerSupplyWithBrandWattage {
    private int psuId;
    private String brandName;
    private String productName;
    private int wattage;
    private String cableType;
    private String  energyEfficiency;
    private BigDecimal price;
    private int wattageId;

    public PowerSupplyWithBrandWattage() {
    }

    public PowerSupplyWithBrandWattage(int psuId, String brandName, String productName, int wattage,
                                       String cableType, String energyEfficiency, BigDecimal price, int wattageId) {
        this.psuId = psuId;
        this.brandName = brandName;
        this.productName = productName;
        this.wattage = wattage;
        this.cableType = cableType;
        this.energyEfficiency = energyEfficiency;
        this.price = price;
        this.wattageId = wattageId;
    }

    public int getPsuId() {
        return psuId;
    }

    public void setPsuId(int psuId) {
        this.psuId = psuId;
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

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
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

    public int getWattageId() {
        return wattageId;
    }

    public void setWattageId(int wattageId) {
        this.wattageId = wattageId;
    }

    @Override
    public String toString() {
        if(psuId < 10){
            return psuId + " )     Brand: " + brandName + "    Model: " + productName + "    Wattage: " + wattage
                    + "     Cable Type: " + cableType +  "     Energy Efficiency: " + energyEfficiency +  "     Price: " + price;
        }else{
            return psuId + ")     Brand: " + brandName + "      Model: " + productName + "     Wattage: " + wattage
                    + "     Cable Type: " + cableType + "          Energy Efficiency: " + energyEfficiency +  "             Price: " + price;
        }
    }
}
