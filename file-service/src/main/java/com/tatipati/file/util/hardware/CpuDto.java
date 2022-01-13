package com.tatipati.file.util.hardware;


import com.tatipati.file.util.serializer.GSONModel;

public class CpuDto extends GSONModel {
    private Double systemCpuLoad;
    private Double processCpuLoad; //= 0.29704659418548773
    private Long processCpuTime;//246606380800

    public CpuDto() {
    }

    public CpuDto(Double systemCpuLoad, Double processCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
        this.processCpuLoad = processCpuLoad;
    }

    public Double getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setSystemCpuLoad(Double systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }

    public Double getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setProcessCpuLoad(Double processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    public Long getProcessCpuTime() {
        return processCpuTime;
    }

    public void setProcessCpuTime(Long processCpuTime) {
        this.processCpuTime = processCpuTime;
    }
}
