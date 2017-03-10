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

    // methods about NetworkIF goes here
}
