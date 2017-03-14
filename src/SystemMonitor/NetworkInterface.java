package SystemMonitor;

import oshi.hardware.NetworkIF;

public class NetworkInterface {
    private NetworkIF IF;

    public NetworkInterface(NetworkIF IF) {
        this.IF = IF;
    }

    public void updateNetworkStats() {
        IF.updateNetworkStats();
    }

    public String getName() {
        return IF.getDisplayName();
    }

    public String[] getIPAdress() {
        return IF.getIPv4addr();
    }

    public String getMacAdress() {
        return IF.getMacaddr();
    }

    public long getSpeed() {
        return IF.getSpeed();
    }
}
