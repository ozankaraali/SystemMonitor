package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

import java.util.ArrayList;
import java.util.List;

public class NetworkInfo {
    private static SystemInfo systemInfo;
    private static HardwareAbstractionLayer abstractionLayer;
    private static OperatingSystem operatingSystem;
    private static List<NetworkInterface> interfaces;
    private static NetworkParams parameters;

    public static void setupOSHI(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        generateNetworkInterface(hal);
        parameters = os.getNetworkParams();
    }

    public static String getHostName() {
        return parameters.getHostName();
    }

    public static String[] getDnsServers() {
        return parameters.getDnsServers();
    }

    public static List<NetworkInterface> getInterfaces() {
        return interfaces;
    }

    public static void updateInterfaces() {
        for(NetworkInterface networkInterface: interfaces) {
            networkInterface.updateNetworkStats();
        }
    }

    public static void generateNetworkInterface(HardwareAbstractionLayer hal) {
        interfaces = new ArrayList<>();

        for(NetworkIF IF: hal.getNetworkIFs()) {
            interfaces.add(new NetworkInterface(IF));
        }
    }
}
