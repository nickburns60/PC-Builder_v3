package com.pcBuilder.viewmodels;

import java.math.BigDecimal;

public class ProcessorWithBrandSocketRam {
    private int processorId;
    private String brandName;
    private String productName;
    private String socketType;
    private String ramTypeName;
    private BigDecimal price;

    public ProcessorWithBrandSocketRam() {
    }

    public ProcessorWithBrandSocketRam(int processorId, String brandName, String productName, String socketType, String ramTypeName, BigDecimal price) {
        this.processorId = processorId;
        this.brandName = brandName;
        this.productName = productName;
        this.socketType = socketType;
        this.ramTypeName = ramTypeName;
        this.price = price;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
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
        if(processorId < 10){
            return processorId + " )     Brand: " + brandName + "    Model: " + productName + "    Socket: " + socketType
                    + "     Ram Type: " + ramTypeName +  "     Price: " + price;
        }else{
            return processorId + ")     Brand: " + brandName + "      Model: " + productName + "     Socket: " + socketType
                    + "          Ram Type: " + ramTypeName +  "             Price: " + price;
        }
    }
}
