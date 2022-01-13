package com.tatipati.file.util.hardware;


import com.tatipati.file.util.covert.ConvertAnything;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.Properties;

/**
 * Created by Mahdii on 12/4/2016.
 */

public class HardwareAllocated {
    private static final  Logger LOGGER = LoggerFactory.getLogger(HardwareAllocated.class);
    private static final Logger LOGGER_HARDWARE = LoggerFactory.getLogger("hardware");
    public static int runnableThreadCount;
    public static int blockedThreadCount;
    public static int newThreadCount;
    public static int terminatedThreadCount;
    public static int timeWaitingThreadCount;
    public static int waitingThreadCount;

    public static void printHardwareInfo() {

        /* Total number of processors or cores available to the JVM */
//        LOGGER_HARDWARE.info("AVAILABLE_PROCESS(CORES): "
//                + Runtime.getRuntime().availableProcessors());
        variableHardwareAlloc();
        threadProperties();//asvadi 970225
    }

    public static void threadProperties(){
        LOGGER_HARDWARE.info("--------------------------- THREADS INFO ---------------------------");
        long[] deadlockedThreads = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        if(deadlockedThreads != null)
        LOGGER_HARDWARE.info("DeadlockedThreads: " + ManagementFactory.getThreadMXBean().findDeadlockedThreads());
        long[] monitorDeadlockedThreads = ManagementFactory.getThreadMXBean().findMonitorDeadlockedThreads();
        if(monitorDeadlockedThreads != null)
        LOGGER_HARDWARE.info("MonitorDeadlockedThreads: " + ManagementFactory.getThreadMXBean().findMonitorDeadlockedThreads());
        int allThreadsIdsLength =  ManagementFactory.getThreadMXBean().getAllThreadIds().length;
        if(allThreadsIdsLength > 0)
        for (long tId: ManagementFactory.getThreadMXBean().getAllThreadIds()){
            LOGGER_HARDWARE.info("TCpuTime("+ String.format("%-3d", tId)+"): "
                    + String.format("%-12d", ManagementFactory.getThreadMXBean().getThreadCpuTime(tId))
                    + " ,TUserTime("+ String.format("%-3d", tId)+"): "
                    + String.format("%-12d", ManagementFactory.getThreadMXBean().getThreadUserTime(tId))
                    + " ,TInfo("+ String.format("%-3d", tId)+"): "
                    + toStringThreadInfo(ManagementFactory.getThreadMXBean().getThreadInfo(tId)));

        }
        int threadCount = (int) ManagementFactory.getThreadMXBean().getTotalStartedThreadCount();
        if(threadCount > 0) {
            LOGGER_HARDWARE.info("ThreadCount: " + threadCount);
            LOGGER_HARDWARE.info(getThreadStateCount());
        }
        int peakThreadCount = ManagementFactory.getThreadMXBean().getPeakThreadCount();
        float peakThreadPercent = ((float)peakThreadCount/(float)threadCount)*100;
        if(peakThreadCount > 0) {
            LOGGER_HARDWARE.info("PeakThreadCount: " + peakThreadCount +"(" + (int)peakThreadPercent + "%)");
        }
        int daemonThreadCount = ManagementFactory.getThreadMXBean().getDaemonThreadCount();
        float daemonThreadPercent = ((float)daemonThreadCount/(float)threadCount)*100;
        if(daemonThreadCount > 0) {
            LOGGER_HARDWARE.info("DaemonThreadCount: " + daemonThreadCount +"(" + (int)daemonThreadPercent + "%)");
        }
        int currentThreadLiveCount = ManagementFactory.getThreadMXBean().getThreadCount();
        if(currentThreadLiveCount > 0)
        LOGGER_HARDWARE.info("currentThreadLiveCount: " + currentThreadLiveCount);

        int currentThreadCpuTime = (int) ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
        if(currentThreadCpuTime > 0)
            LOGGER_HARDWARE.info("CurrentThreadCpuTime: "+currentThreadCpuTime);

        int currentThreadUserTime = (int) ManagementFactory.getThreadMXBean().getCurrentThreadUserTime();
        if(currentThreadUserTime > 0)
            LOGGER_HARDWARE.info("CurrentThreadUserTime: "+currentThreadUserTime);
            LOGGER_HARDWARE.info("--------------------------------------------------------------------");
    }
    public static String toStringThreadInfo(ThreadInfo threadInfo) {
        StringBuilder sb = new StringBuilder(
                "  tState: "     + String.format("%-15s",threadInfo.getThreadState())+
                " ,blockCount: " + String.format("%-5s",threadInfo.getBlockedCount())+
                " ,blockTime: "  + String.format("%-3s",threadInfo.getBlockedTime())+
                " ,waitedCount: "+ String.format("%-3s",threadInfo.getWaitedCount())+
                " ,waitedTime: " + String.format("%-3s",threadInfo.getWaitedTime())+
                " ,tName: "      +threadInfo.getThreadName());

        if (threadInfo.getLockName() != null) {
            sb.append(" on " + threadInfo.getLockName());
        }
        if (threadInfo.getLockOwnerName() != null) {
            sb.append(" owned by \"" + threadInfo.getLockOwnerName() +
                    "\" Id=" + threadInfo.getLockOwnerId());
        }
        if (threadInfo.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (threadInfo.isInNative()) {
            sb.append(" (in native)");
        }
        if("stuck".equalsIgnoreCase(threadInfo.getThreadState()+"")) {
            LOGGER.error("*******THREAD :" + sb.toString() + " *************");
        }
        return sb.toString();
    }

    //---------------------------asvadi 970313------------------------
//    public static Map<Thread.State,Integer> getThreadStateCount() {
    public static String getThreadStateCount() {
        StringBuilder sb = new StringBuilder();
        //Map< Thread.State,Integer> map=new HashMap<>();
            int iWaiting = 0,
                iBlocked = 0,
                iNew = 0,
                iRunnable = 0,
                iTerminated = 0,
                iTimedWaiting = 0;
        try {
            for (long tId : ManagementFactory.getThreadMXBean().getAllThreadIds()) {
                ThreadInfo threadInfo = ManagementFactory.getThreadMXBean().getThreadInfo(tId);
                HardwareAllocated.toStringThreadInfo(threadInfo);
                switch (threadInfo.getThreadState()){
                    case BLOCKED:
                        blockedThreadCount = iBlocked++;break;
                    case NEW:
                        newThreadCount = iNew++;break;
                    case RUNNABLE:
                        runnableThreadCount = iRunnable++;break;
                    case TERMINATED:
                        terminatedThreadCount = iTerminated++;break;
                    case TIMED_WAITING:
                        timeWaitingThreadCount = iTimedWaiting++;break;
                    case WAITING:
                        waitingThreadCount = iWaiting++;break;
                    default:;break;
                }
            }
             sb = new StringBuilder("BLOCKED:" + iBlocked+
                                    " ,NEW:" + iNew+
                                    " ,RUNNABLE:" + runnableThreadCount+
                                    " ,TERMINATED:" + iTerminated+
                                    " ,TIMED_WAITING:" + iTimedWaiting+
                                    " ,WAITING:" + iWaiting);

        }catch(Exception ex){
        }
        return sb.toString();
    }
    public static String variableHardwareAlloc() {
        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        String server= "unkhown";
        String maxMemoryStr="MAX_MEMORY: "
                + (maxMemory == Long.MAX_VALUE ? "no limit" : convBytToString(maxMemory));
        LOGGER_HARDWARE.info(maxMemoryStr);

        long totalMemory = Runtime.getRuntime().totalMemory();
        /* Total memory currently available to the JVM */
        String totalMemoryAvailableOnJvm="TOTAL_MEMORY: "
                + convBytToString(totalMemory);
        /* Maximum amount of memory the JVM will attempt to use */
        LOGGER_HARDWARE.info(totalMemoryAvailableOnJvm);
         /* Total amount of free memory available to the JVM */
        long freeMemory = Runtime.getRuntime().freeMemory();
        float freeMemoryPercent = (float)freeMemory/(float)totalMemory*100;
        String freeMemoryStr="FREE_MEMORY: "+convBytToString(freeMemory);
        LOGGER_HARDWARE.info(freeMemoryStr +"("+(long)freeMemoryPercent+"%)");
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        float usedMemoryPercent = ((float)usedMemory /(float)totalMemory)*100;
//        String usedMemoryStr="*USED_MEMORY: "
//                + convBytToString(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        String usedMemoryStr="USED_MEMORY: "
                + convBytToString(totalMemory - freeMemory);
        LOGGER_HARDWARE.info(usedMemoryStr +"(" + (long)usedMemoryPercent +"%)");
    /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        /* For each filesystem root, print some info */
        StringBuilder sb = new StringBuilder();
        for (File root : roots) {
            if(root.getTotalSpace()!=0){
            String str=/*"File system root: " +*/root.getAbsolutePath() + " TOTAL_SPACE: " +
                    String.format("%-8s",convBytToString(root.getTotalSpace()))
                    + " ,FREE_SPACE: "   + String.format("%-8s", convBytToString(root.getFreeSpace()))
                    + " ,USABLE_SPACE: " + String.format("%-8s",convBytToString(root.getUsableSpace()));
            sb.append(str+"\n");
            LOGGER_HARDWARE.info(str);}
        }
        return (server+"\n"+maxMemoryStr+"\n"+totalMemoryAvailableOnJvm+"\n"+freeMemoryStr
                +"\n"+usedMemoryStr+"\n"+sb.toString());
    }

    public static void printSystemProperties() {
        Properties properties = System.getProperties();
        for(String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            LOGGER_HARDWARE.info(key + ": " + value);
        }
    }
    public static String convBytToString(long l) {
        return ConvertAnything.byteToString(l);
    }

}
