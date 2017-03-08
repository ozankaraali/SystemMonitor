package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

public class MemoryInfo {
    private static SystemInfo systemInfo;
    private static HardwareAbstractionLayer abstractionLayer;
    private static OperatingSystem operatingSystem;
    private static GlobalMemory memory;

    public static void setupOSHI(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        memory = hal.getMemory();
    }

    public static long getTotalMemory() {
        return memory.getTotal();
    }

    public static long getUsedMemory() {
        return memory.getTotal() - memory.getAvailable();
    }

    public static long getFreeMemory() {
        return memory.getAvailable();
    }

}
