package com.pcBuilder.models;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * Represents User built pc
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserPcBuild {
    /**
     * The serial id of the pc
     */
    private int pcId;
    @NotNull(message = "Processor id is required")
    @Min(value = 1, message = "Processor must be in database")
    @Max(value = 15, message = "Processor must be in database")
    private int processorId;
    private int graphicsCardId;
    private int motherboardId;
    private int ramId;
    private int psuId;
    private int storageDriveId;
    private int caseId;
    private int fanId;
    private int cpuCoolerId;
    private BigDecimal totalCost;




    @Override
    public String toString(){
        return pcId + ")    " + "Processor Id: " + processorId + "   Graphics Card Id: " + graphicsCardId + "   Motherboard Id: " + motherboardId +
                "   Ram Id: " + ramId + "   Power Supply Id: " + psuId + "  Storage Drive Id: " + storageDriveId + "    Case Id: " + caseId + "    Total Cost: " + totalCost;
    }
}
