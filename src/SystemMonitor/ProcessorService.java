package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

public class ProcessorService {
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer abstractionLayer;
    private OperatingSystem operatingSystem;
    private CentralProcessor centralProcessor;

    public ProcessorService(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        centralProcessor = hal.getProcessor();
    }

    public String getProcessorName(){
        return centralProcessor.toString();
    }

    public int getPhysicalProcessorCount(){
        return centralProcessor.getPhysicalProcessorCount();
    }

    public int getLogicalProcessorCount(){
        return centralProcessor.getLogicalProcessorCount();
    }

    public String getIdentifier(){
        return centralProcessor.getIdentifier();
    }

    public String getProcessorID(){
        return centralProcessor.getProcessorID();
    }

    public double getSystemCpuLoad(){
        return centralProcessor.getSystemCpuLoad()*100;
    }

    public String getSystemUptime(){
        return FormatUtil.formatElapsedSecs(abstractionLayer.getProcessor().getSystemUptime());
    }

    public int getProcesses(){
        return operatingSystem.getProcessCount();
    }

    public int getThreads(){
        return operatingSystem.getThreadCount();
    }

    public OSProcess[] getProcessesList(){
        return operatingSystem.getProcesses(500, OperatingSystem.ProcessSort.MEMORY);
    }

    public int getProcessCount() {
        return operatingSystem.getProcessCount();
    }
}
