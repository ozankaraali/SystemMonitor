package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;
import java.util.Arrays;
import java.util.List;

public class ProcessorInfo {
    private static SystemInfo systemInfo;
    private static HardwareAbstractionLayer abstractionLayer;
    private static OperatingSystem operatingSystem;

    public static void setupOSHI(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
    }

    public static String getProcessorName(){
        return abstractionLayer.getProcessor().toString();
    }
    public static int getPhysicalProcessorCount(){
        return abstractionLayer.getProcessor().getPhysicalProcessorCount();
    }
    public static int getLogicalProcessorCount(){
        return abstractionLayer.getProcessor().getLogicalProcessorCount();
    }
    public static String getIdentifier(){
        return abstractionLayer.getProcessor().getIdentifier();
    }
    public static String getProcessorID(){
        return abstractionLayer.getProcessor().getProcessorID();
    }
    public static double getSystemCpuLoad(){
        return abstractionLayer.getProcessor().getSystemCpuLoad()*100;
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
