package SystemMonitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class ServiceHolder {
    public BatteryService batteryService;
    public NetworkService networkService;
    public ProcessorService processorService;
    public MemoryService memoryService;
    public DiskService diskService;

    public ServiceHolder() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        batteryService = new BatteryService(si, hal, os);
        networkService = new NetworkService(si, hal, os);
        processorService = new ProcessorService(si, hal, os);
        memoryService = new MemoryService(si, hal, os);
        diskService = new DiskService();
    }
}
