package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

public class MemoryService {
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer abstractionLayer;
    private OperatingSystem operatingSystem;
    private GlobalMemory memory;

    public MemoryService(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        memory = hal.getMemory();
    }

    public long getTotalMemory() {
        return memory.getTotal();
    }

    public long getUsedMemory() {
        return memory.getTotal() - memory.getAvailable();
    }

    public long getFreeMemory() {
        return memory.getAvailable();
    }

    public long getTotalSwap() {
        return memory.getSwapTotal();
    }

    public long getUsedSwap() {
        return memory.getSwapUsed();
    }

}
