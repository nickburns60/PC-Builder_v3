package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class GPUWithBrandWattage {
    private int graphicsCardId;
    private String brandName;
    private String productName;
    private int wattage;
    private BigDecimal price;

    public GPUWithBrandWattage() {
    }

    public GPUWithBrandWattage(int graphicsCardId, String brandName, String productName, int wattage, BigDecimal price) {
        this.graphicsCardId = graphicsCardId;
        this.brandName = brandName;
        this.productName = productName;
        this.wattage = wattage;
        this.price = price;
    }

    public int getGraphicsCardId() {
        return graphicsCardId;
    }

    public void setGraphicsCardId(int graphicsCardId) {
        this.graphicsCardId = graphicsCardId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {

        if(graphicsCardId < 10){
            return graphicsCardId + " )     Brand: " + brandName + "    Model: " + productName + "    Wattage: " + wattage
                    +  "     Price: " + price;
        }else{
            return graphicsCardId + ")     Brand: " + brandName + "      Model: " + productName + "     Wattage: " + wattage
                    +  "             Price: " + price;
        }
    }
}
