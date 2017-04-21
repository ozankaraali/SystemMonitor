package SystemMonitor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BatteryPanel extends JPanel{
    private List<BatteryDisplay> batteryDisplays;
    private ServiceHolder services;
    private boolean batteryExists;
    public BatteryPanel(ServiceHolder services) {
        super();
        this.services = services;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.batteryDisplays = new ArrayList<>();

        setupPanel();
    }

    private void setupPanel() {
        List<Battery> batteryList = services.batteryService.getBatteries();
        batteryExists = batteryList.size() != 0;

        if(batteryExists) {
            for(Battery b: batteryList) {
                BatteryDisplay bDisplay = new BatteryDisplay(b);
                batteryDisplays.add(bDisplay);
                this.add(bDisplay);
            }
        } else {
            this.setLayout(new BorderLayout());
            this.add(new JLabel("<html><font size+=2>Batteries could not be found in your system!</font>"), BorderLayout.CENTER);
        }
    }

    public void updateStats() {
        if(batteryExists) {
            services.batteryService.generateBatteries();
            this.removeAll();
            setupPanel();
        }
    }
}
