package com.pcBuilder.models;

import java.math.BigDecimal;

public class GraphicsCard {
    private int graphicsCardId;
    private int brandId;
    private String productName;
    private String model;
    private int psuWattageId;
    private BigDecimal price;

    public GraphicsCard() {
    }

    public GraphicsCard(int graphicsCardId, int brandId, String productName, String model, int psuWattageId,
                        BigDecimal price) {
        this.graphicsCardId = graphicsCardId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
        this.psuWattageId = psuWattageId;
        this.price = price;
    }

    public int getGraphicsCardId() {
        return graphicsCardId;
    }

    public void setGraphicsCardId(int graphicsCardId) {
        this.graphicsCardId = graphicsCardId;
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

