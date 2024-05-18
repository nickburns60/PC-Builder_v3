package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class CaseWithBrandFormFactor {
    private int caseId;
    private String brandName;
    private String productName;
    private String formFactorName;
    private String color;
    private boolean rgb;
    private int lengthInMm;
    private int widthInMm;
    private int numFansIncluded;
    private BigDecimal price;
    private int formFactorNum;

    public CaseWithBrandFormFactor() {
    }

    public CaseWithBrandFormFactor(int caseId, String brandName, String productName, String formFactorName, String color, boolean rgb, int lengthInMm, int widthInMm,
                                   int numFansIncluded, BigDecimal price, int formFactorNum) {
        this.caseId = caseId;
        this.brandName = brandName;
        this.productName = productName;
        this.formFactorName = formFactorName;
        this.color = color;
        this.rgb = rgb;
        this.lengthInMm = lengthInMm;
        this.widthInMm = widthInMm;
        this.numFansIncluded = numFansIncluded;
        this.price = price;
        this.formFactorNum = formFactorNum;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
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

    public String getFormFactorName() {
        return formFactorName;
    }

    public void setFormFactorName(String formFactorName) {
        this.formFactorName = formFactorName;
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

    public int getFormFactorNum() {
        return formFactorNum;
    }

    public void setFormFactorNum(int formFactorNum) {
        this.formFactorNum = formFactorNum;
    }

    @Override
    public String toString() {
        if(caseId < 10){
            return caseId + " )     Brand: " + brandName + "    Model: " + productName + "    Form Factor: " + formFactorName
                    + "     Color: " + color + "    RGB: " + rgb + "   Length in mm: " + lengthInMm + "     Width in mm: " + widthInMm +
                    "      Number of fans: " + numFansIncluded + "     Price: " + price;
        }else{
            return caseId + ")     Brand: " + brandName + "      Model: " + productName + "    Form Factor: " + formFactorName
                    + "     Color: " + color + "    RGB: " + rgb + "   Length in mm: " + lengthInMm + "     Width in mm: " + widthInMm +
                    "      Number of fans: " + numFansIncluded + "     Price: " + price;
        }
    }
}
