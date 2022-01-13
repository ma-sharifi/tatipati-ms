package com.tatipati.file.util.hardware;

import lombok.Data;

@Data
public class MemoryObject {
    private Long init;
    private Long total;
    private Long used;
    private Long free;
    private Long max;
    private Long committed;
    //-----------------

    private static final long ACCURACY=(1024*1024);

    public MemoryObject(Long init, Long used, Long committed, Long max) {
        this.init = init/ACCURACY;
        this.used = used/ACCURACY;
        this.max = max/ACCURACY;
        this.committed = committed/ACCURACY;
    }

    public MemoryObject(Long total, Long free, Long max) {
        this.total = total/ACCURACY;
        this.free = free/ACCURACY;
        this.max = max/ACCURACY;
    }

    public MemoryObject(Long total, Long free) {
        this.total = total/ACCURACY;
        this.free = free/ACCURACY;
    }

    public MemoryObject() {
    }

}
