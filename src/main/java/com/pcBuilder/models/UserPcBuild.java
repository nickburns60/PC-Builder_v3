package com.pcBuilder.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserPcBuild {
    private int pcId;
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
