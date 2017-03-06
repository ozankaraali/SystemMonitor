import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ozan on 3/6/2017.
 */
public class CPUInfo {
    public CPUInfo(){
    }
    public static String getProcessorName(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor().toString();
    }
    public static int getPhysicalProcessorCount(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor().getPhysicalProcessorCount();
    }
    public static int getLogicalProcessorCount(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor().getLogicalProcessorCount();
    }
    public static String getIdentifier(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor().getIdentifier();
    }
    public static String getProcessorID(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor().getProcessorID();
    }
    public static double getSystemCpuLoad(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor().getSystemCpuLoad()*100;
    }
    public static String getSystemUptime(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        return FormatUtil.formatElapsedSecs(hal.getProcessor().getSystemUptime());
    }
    public static int getProcesses(){
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        return os.getProcessCount();
    }
    public static int getThreads(){
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        return os.getThreadCount();
    }
    public static void getProcessesList(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
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