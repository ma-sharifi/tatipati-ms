package com.tatipati.file.util.hardware;


import com.tatipati.file.util.serializer.GSONModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

@EqualsAndHashCode(callSuper = true)
@Data
public class MemoryDto extends GSONModel {
    private final MemoryObject heapMemoryUsage;
    private final MemoryObject nonHeapMemoryUsage;
    private MemoryObject runTimeMemory;
    private MemoryObject physicalMemory;
    private MemoryObject swapMemory;
    private double freeRuntimePercent;
    private double freeHeapPercent;//Heap Free Percent:
    private double freePhysicalPercent;
    private double freeSwapPercent;

    //---------------
    public MemoryDto() {
        MemoryUsage usageHeap = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        MemoryUsage usageNonHeap = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        heapMemoryUsage = new MemoryObject(usageHeap.getInit(), usageHeap.getUsed(), usageHeap.getCommitted(), usageHeap.getMax());
        nonHeapMemoryUsage = new MemoryObject(usageNonHeap.getInit(), usageNonHeap.getUsed(), usageNonHeap.getCommitted(), usageNonHeap.getMax());
    }

}
