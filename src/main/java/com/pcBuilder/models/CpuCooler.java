package com.pcBuilder.models;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CpuCooler {
    private int cpuCoolerId;
    @NotNull(message = "Brand Id is required")
    private int brandId;
    private String productName;
    private String model;
    private String coolerType;
    private int sizeMm;
    private String color;
    private boolean rgb;
    private BigDecimal price;

    @Override
    public String toString(){
        return productName + "  $" + price;
    }
}
