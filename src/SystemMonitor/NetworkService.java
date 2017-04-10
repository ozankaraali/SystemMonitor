package SystemMonitor;

import oshi.*;
import oshi.software.os.*;
import oshi.hardware.*;
import oshi.util.*;

import java.util.ArrayList;
import java.util.List;

public class NetworkService {
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer abstractionLayer;
    private OperatingSystem operatingSystem;
    private List<NetworkInterface> interfaces;
    private NetworkParams parameters;

    public NetworkService(SystemInfo si, HardwareAbstractionLayer hal, OperatingSystem os) {
        systemInfo = si;
        abstractionLayer = hal;
        operatingSystem = os;
        generateNetworkInterface(hal);
        parameters = os.getNetworkParams();
    }

    public String getHostName() {
        return parameters.getHostName();
    }

    public String[] getDnsServers() {
        return parameters.getDnsServers();
    }

    public List<NetworkInterface> getInterfaces() {
        return interfaces;
    }

    public void updateInterfaces() {
        for(NetworkInterface networkInterface: interfaces) {
            networkInterface.updateNetworkStats();
        }
    }

    public void generateNetworkInterface(HardwareAbstractionLayer hal) {
        interfaces = new ArrayList<>();

        for(NetworkIF IF: hal.getNetworkIFs()) {
            interfaces.add(new NetworkInterface(IF));
        }
    }
}
