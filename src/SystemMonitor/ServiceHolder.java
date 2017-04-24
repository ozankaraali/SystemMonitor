package SystemMonitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class ServiceHolder {
    public BatteryService batteryService;
    public ProcessorService processorService;
    public MemoryService memoryService;
    public DiskService diskService;

    public ServiceHolder() {
        SystemInfo si = new SystemInfo();
        System.out.println("SystemInfo si = new SystemInfo();");
        HardwareAbstractionLayer hal = si.getHardware();
        System.out.println("HardwareAbstractionLayer hal = si.getHardware();");
        OperatingSystem os = si.getOperatingSystem();
        System.out.println("OperatingSystem os = si.getOperatingSystem();");

        batteryService = new BatteryService(si, hal, os);
        System.out.println("batteryService = new BatteryService(si, hal, os);");
        processorService = new ProcessorService(si, hal, os);
        System.out.println("processorService = new ProcessorService(si, hal, os);");
        memoryService = new MemoryService(si, hal, os);
        System.out.println("memoryService = new MemoryService(si, hal, os);");
        diskService = new DiskService();
        System.out.println("diskService = new DiskService();");
    }
}
