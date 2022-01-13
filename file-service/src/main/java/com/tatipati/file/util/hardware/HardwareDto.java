package com.tatipati.file.util.hardware;

import com.tatipati.file.util.serializer.GSONModel;

public class HardwareDto extends GSONModel {
    private final String accuracy = "MB";
    private final CpuDto cpu;
    private final MemoryDto memory;
    private final ThreadDto thread;
    private final ClassLoadedDto classLoaded;
    private final ServerDto serverInfo;

    public HardwareDto(CpuDto cpu, MemoryDto memory, ThreadDto thread, ClassLoadedDto classLoaded) {
        this.cpu = cpu;
        this.memory = memory;
        this.thread = thread;
        this.classLoaded = classLoaded;
        this.serverInfo = new ServerDto();
    }


}
