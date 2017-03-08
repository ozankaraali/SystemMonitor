/*
package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

public class BatteryInfo {
    private static SystemInfo systemInfo;
    private static HardwareAbstractionLayer abstractionLayer;
    private static OperatingSystem operatingSystem;
    private static PowrProf.SystemBatteryState battery;

    public static void setupOSHI(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        battery = new PowrProf.SystemBatteryState();
        hal.getPowerSources()[0].
    }

    public static byte isCharging() {
        return battery.charging;
    }

    public static int getTotalCapacity() {
        return battery.maxCapacity;
    }

    public static int getRemainingCapacity() {
        return battery.remainingCapacity;
    }

}

*/