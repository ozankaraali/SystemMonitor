package SystemMonitor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BatteryPanel extends JPanel{
    private List<BatteryDisplay> batteryDisplays;
    private ServiceHolder services;
    public BatteryPanel(ServiceHolder services) {
        super();
        this.services = services;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.batteryDisplays = new ArrayList<>();

        setupPanel();
    }

    private void setupPanel() {
        List<Battery> batteryList = services.batteryService.getBatteries();

        for(Battery b: batteryList) {
            BatteryDisplay bDisplay = new BatteryDisplay(b);
            batteryDisplays.add(bDisplay);
            this.add(bDisplay);
        }
    }

    public void updateStats() {
        services.batteryService.generateBatteries();
        this.removeAll();
        setupPanel();
    }
}
