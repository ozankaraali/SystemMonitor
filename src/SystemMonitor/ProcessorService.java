package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;

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

    public double[] getCpuLoads() {
        return centralProcessor.getProcessorCpuLoadBetweenTicks();
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

    public long getSystemUptime(){
        return centralProcessor.getSystemUptime();
    }

    public String getFrequency() {
        return Long.toString(centralProcessor.getVendorFreq());
    }

    public int getThreads(){
        return operatingSystem.getThreadCount();
    }

    public boolean is64Bit() {
        // broken in windows
        return centralProcessor.isCpu64bit();
    }

    public OSProcess[] getProcessesList(OperatingSystem.ProcessSort sort){
        return operatingSystem.getProcesses(1000, sort);
    }

    public int getProcessCount() {
        return operatingSystem.getProcessCount();
    }
}
