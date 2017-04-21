package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

import java.util.ArrayList;
import java.util.List;

public class BatteryService {
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer abstractionLayer;
    private OperatingSystem operatingSystem;

    private List<Battery> batteries;

    public BatteryService(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        generateBatteries();
    }

    public void generateBatteries() {
        batteries = new ArrayList<>();
        for(PowerSource powerSource: abstractionLayer.getPowerSources()) {
            if(!powerSource.getName().equals("Unknown")) {
                batteries.add(new Battery(powerSource));
            }
        }
    }

    public boolean batteryExists() {
        return 0 != batteries.size();
    }

    public List<Battery> getBatteries() {
        return batteries;
    }
}
