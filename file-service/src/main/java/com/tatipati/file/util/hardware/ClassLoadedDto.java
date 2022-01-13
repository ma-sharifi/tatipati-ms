package com.tatipati.file.util.hardware;

import lombok.Data;

import java.lang.management.ManagementFactory;

@Data
public class ClassLoadedDto {
    private final Integer getLoadedClassCount;
    private final Long getTotalLoadedClassCount;
    private final Long getUnloadedClassCount;

    public ClassLoadedDto() {
        getLoadedClassCount= ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();
        getTotalLoadedClassCount= ManagementFactory.getClassLoadingMXBean().getTotalLoadedClassCount();
        getUnloadedClassCount= ManagementFactory.getClassLoadingMXBean().getUnloadedClassCount();
    }
}
