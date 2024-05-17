package com.pcBuilder.models;

import java.math.BigDecimal;

public class PcCase {
    private int caseId;
    private int brandId;
    private String productName;
    private String model;
    private int formFactorId;
    private String color;
    private boolean rgb;
    private int lengthInMm;
    private int widthInMm;
    private int numFansIncluded;
    private BigDecimal price;

    public PcCase() {
    }

    public PcCase(int caseId, int brandId, String productName, String model, int formFactorId,
                  String color, boolean rgb, int lengthInMm, int widthInMm, int numFansIncluded, BigDecimal price) {
        this.caseId = caseId;
        this.brandId = brandId;
        this.productName = productName;
        this.model = model;
        this.formFactorId = formFactorId;
        this.color = color;
        this.rgb = rgb;
        this.lengthInMm = lengthInMm;
        this.widthInMm = widthInMm;
        this.numFansIncluded = numFansIncluded;
        this.price = price;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
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

    public int getFormFactorId() {
        return formFactorId;
    }

    public void setFormFactorId(int formFactorId) {
        this.formFactorId = formFactorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    public int getLengthInMm() {
        return lengthInMm;
    }

    public void setLengthInMm(int lengthInMm) {
        this.lengthInMm = lengthInMm;
    }

    public int getWidthInMm() {
        return widthInMm;
    }

    public void setWidthInMm(int widthInMm) {
        this.widthInMm = widthInMm;
    }

    public int getNumFansIncluded() {
        return numFansIncluded;
    }

    public void setNumFansIncluded(int numFansIncluded) {
        this.numFansIncluded = numFansIncluded;
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