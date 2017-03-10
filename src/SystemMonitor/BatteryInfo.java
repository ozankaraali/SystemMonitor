package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

import java.util.ArrayList;
import java.util.List;

public class BatteryInfo {
    private static SystemInfo systemInfo;
    private static HardwareAbstractionLayer abstractionLayer;
    private static OperatingSystem operatingSystem;

    private static List<Battery> batteries;

    public static void setupOSHI(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        generateBatteries();
    }

    public static void generateBatteries() {
        List<Battery> batteryList = new ArrayList<>();
        for(PowerSource powerSource: abstractionLayer.getPowerSources()) {
            if(!powerSource.getName().equals("Unknown")) {
                batteryList.add(new Battery(powerSource));
            }
        }
    }

    public static boolean batteryExists() {
        return 0 != batteries.size();
    }

    public static List<Battery> getBatteries() {
        return batteries;
    }

    public static void setBatteries(List<Battery> batteries) {
        BatteryInfo.batteries = batteries;
    }
}
