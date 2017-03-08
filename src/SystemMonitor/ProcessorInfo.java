package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

public class ProcessorInfo {
    private static SystemInfo systemInfo;
    private static HardwareAbstractionLayer abstractionLayer;
    private static OperatingSystem operatingSystem;
    private static CentralProcessor centralProcessor;

    public static void setupOSHI(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        centralProcessor = hal.getProcessor();
    }

    public static String getProcessorName(){
        return centralProcessor.toString();
    }

    public static int getPhysicalProcessorCount(){
        return centralProcessor.getPhysicalProcessorCount();
    }

    public static int getLogicalProcessorCount(){
        return centralProcessor.getLogicalProcessorCount();
    }

    public static String getIdentifier(){
        return centralProcessor.getIdentifier();
    }

    public static String getProcessorID(){
        return centralProcessor.getProcessorID();
    }

    public static double getSystemCpuLoad(){
        return centralProcessor.getSystemCpuLoad()*100;
    }

    public static String getSystemUptime(){
        return FormatUtil.formatElapsedSecs(abstractionLayer.getProcessor().getSystemUptime());
    }

    public static int getProcesses(){
        return operatingSystem.getProcessCount();
    }

    public static int getThreads(){
        return operatingSystem.getThreadCount();
    }

    public static OSProcess[] getProcessesList(){
        return operatingSystem.getProcesses(500, OperatingSystem.ProcessSort.MEMORY);
    }
}
