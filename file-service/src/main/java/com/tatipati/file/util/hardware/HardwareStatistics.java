package com.tatipati.file.util.hardware;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class HardwareStatistics {
//    private static final Logger LOGGER_HARDWARE = Logger.getLogger("hardware");
//    private static final Logger LOGGER = Logger.getLogger(HardwareAllocated.class);
//    private static final Logger LOGGER_THREAD_DETAILS = Logger.getLogger("threadDetails");

    public static HardwareDto getHardwareStatistics(){
        HardwareDto hardware=new HardwareDto(getCpuStatistics(),getJvmMemoryStatus(),getThreadStatistics(),new ClassLoadedDto());
        return hardware;

    }

    public static CpuDto getCpuStatistics(){

        CpuDto cpuDto=null;
        try {
            cpuDto=new CpuDto(getSystemCpuLoad(),getProcessCpuLoad());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return cpuDto;
    }
    public static MemoryDto getJvmMemoryStatus()  {
        MemoryDto memoryDto = null;
        try {
            memoryDto =new MemoryDto();

            MemoryObject physicalMemory = new MemoryObject(getTotalPhysicalMemorySize(),getFreePhysicalMemorySize());
            memoryDto.setPhysicalMemory(physicalMemory);
            memoryDto.setFreePhysicalPercent((int) ((physicalMemory.getFree() * 1000) / (physicalMemory.getTotal() * 10.0)));

            MemoryObject swapMemory = new MemoryObject(getTotalSwapSpaceSize(),getFreeSwapSpaceSize());
            memoryDto.setSwapMemory(swapMemory);
            swapMemory.setCommitted(getCommittedVirtualMemorySize()/(1024*1024));
            memoryDto.setFreeSwapPercent((int) ((swapMemory.getFree() * 1000) / (swapMemory.getTotal() * 10.0)));

            double free = memoryDto.getHeapMemoryUsage().getMax() - memoryDto.getHeapMemoryUsage().getUsed();
            double heapMemoryUsagePercent = (int) ((free * 1000) / (memoryDto.getHeapMemoryUsage().getMax() * 10.0)); //Heap Free Percent:
            memoryDto.setFreeHeapPercent(heapMemoryUsagePercent);


            MemoryObject memoryRuntime = new MemoryObject(Runtime.getRuntime().totalMemory(), Runtime.getRuntime().freeMemory());
            memoryRuntime.setMax(Runtime.getRuntime().maxMemory()/(1024*1024));
            memoryDto.setRunTimeMemory(memoryRuntime);

            memoryDto.setFreeRuntimePercent((int) (memoryRuntime.getFree() * 1000 / (memoryRuntime.getTotal()* 10.0)));

        }catch (Exception ex){
//            ex.printStackTrace();
        }
        return memoryDto;
    }
    public static long getTotalSwapSpaceSize() throws Exception {
        return getMemory("TotalSwapSpaceSize");
    }
    public static long getFreeSwapSpaceSize() throws Exception {
        return getMemory("FreeSwapSpaceSize");
    }
    public static long  getTotalPhysicalMemorySize() throws Exception {
        return getMemory("TotalPhysicalMemorySize");
    }
    public static long getFreePhysicalMemorySize() throws Exception {
        return getMemory("FreePhysicalMemorySize");
    }
    public static long getCommittedVirtualMemorySize() throws Exception {
        return getMemory("CommittedVirtualMemorySize");
    }
    public static long getMemory(String methodName) throws Exception {//ProcessCpuLoad ,SystemCpuLoad
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{methodName});
        if (list.isEmpty()) return 0;
        Attribute att = (Attribute) list.get(0);
        return (long) att.getValue();
    }
    //------------
    public static double getProcessCpuLoad() throws Exception {
        return getProcessCpu("ProcessCpuLoad");
    }
    public static double getSystemCpuLoad() throws Exception {
        return getProcessCpu("SystemCpuLoad");
    }
    public static double getProcessCpu(String methodName) throws Exception {//ProcessCpuLoad ,SystemCpuLoad
        MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
        ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{methodName });
        if (list.isEmpty())     return Double.NaN;
        Attribute att = (Attribute)list.get(0);
        Double value  = (Double)att.getValue();
        // usually takes a couple of seconds before we get real values
        if (value == -1.0)      return Double.NaN;
        // returns a percentage value with 1 decimal point precision
        return ((int)(value * 1000) / 10.0);
    }
    //--------------
    public static ThreadDto getThreadStatistics() {
        ThreadDto threadDto=null;
        int iWaiting = 0,iBlocked = 0,iNew = 0,iRunnable = 0,iTerminated = 0,iTimedWaiting=0,iStuck = 0;
        try {
            for (long tId : ManagementFactory.getThreadMXBean().getAllThreadIds()) {
                ThreadInfo threadInfo = ManagementFactory.getThreadMXBean().getThreadInfo(tId);
                HardwareAllocated.toStringThreadInfo(threadInfo);
                if ((threadInfo.getThreadName().indexOf("STUCK")) > 0) {
                    iStuck++;
                }
                switch (threadInfo.getThreadState()) {
                    case BLOCKED:
                         iBlocked++;
                        break;
                    case NEW:
                         iNew++;
                        break;
                    case RUNNABLE:
                         iRunnable++;
                        break;
                    case TERMINATED:
                         iTerminated++;
                        break;
                    case TIMED_WAITING:
                         iTimedWaiting++;
                        break;
                    case WAITING:
                         iWaiting++;
                        break;
                    default:
                        break;
                }
            }
            threadDto=new ThreadDto();
            threadDto.setBlockedCount(iBlocked);
            threadDto.setNewCount(iNew);
            threadDto.setRunnableCount(iRunnable);
            threadDto.setTerminatedCount(iTerminated);
            threadDto.setTimeWaitingCount(iTimedWaiting);
            threadDto.setWaitingCount(iWaiting);
            threadDto.setStuckCount(iStuck);
        } catch (Exception ex) {
        }
        return threadDto;
    }
}
