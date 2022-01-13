package com.tatipati.file.util.hardware;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tatipati.file.util.serializer.GSONModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.management.ManagementFactory;


@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadDto extends GSONModel {
    private Integer stuckCount;
    @SerializedName("live_count")
    @Expose
    private Integer currentCount;
    private Long count;
    private Integer daemonCount;
    private Integer terminatedCount;
    private Integer peakCount;
    private Integer runnableCount;
    private Integer blockedCount;
    private Integer newCount;
    private Integer timeWaitingCount;
    private Integer waitingCount;

    public ThreadDto() {
        currentCount = (ManagementFactory.getThreadMXBean().getThreadCount());
        count = (ManagementFactory.getThreadMXBean().getTotalStartedThreadCount());
        daemonCount = (ManagementFactory.getThreadMXBean().getDaemonThreadCount());
        peakCount = ManagementFactory.getThreadMXBean().getPeakThreadCount();
    }

}
