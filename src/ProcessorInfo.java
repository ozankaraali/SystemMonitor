import oshi.*;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.util.Arrays;
import java.util.List;

public class ProcessorInfo {
    private static SystemInfo si = new SystemInfo();
    private static HardwareAbstractionLayer hal = si.getHardware();
    private static OperatingSystem os = si.getOperatingSystem();

    public static String getProcessorName(){
        return hal.getProcessor().toString();
    }
    public static int getPhysicalProcessorCount(){
        return hal.getProcessor().getPhysicalProcessorCount();
    }
    public static int getLogicalProcessorCount(){
        return hal.getProcessor().getLogicalProcessorCount();
    }
    public static String getIdentifier(){
        return hal.getProcessor().getIdentifier();
    }
    public static String getProcessorID(){
        return hal.getProcessor().getProcessorID();
    }
    public static double getSystemCpuLoad(){
        return hal.getProcessor().getSystemCpuLoad()*100;
    }
    public static String getSystemUptime(){
        return FormatUtil.formatElapsedSecs(hal.getProcessor().getSystemUptime());
    }
    public static int getProcesses(){
        return os.getProcessCount();
    }
    public static int getThreads(){
        return os.getThreadCount();
    }
    public static void getProcessesList(){
        OperatingSystem os = si.getOperatingSystem();

        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, OperatingSystem.ProcessSort.CPU));

        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
        for (int i = 0; i < procs.size() && i < 5; i++) {
            OSProcess p = procs.get(i);
            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / hal.getMemory().getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
                    FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
        }
    }
}
