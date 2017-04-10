package SystemMonitor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BatteryDisplay extends JPanel {
    private Battery battery;
    private JLabel name, timeRemaining;
    private JProgressBar remainingPercent;

    public BatteryDisplay(Battery battery) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.battery = battery;
        this.init();
    }

    private void init() {
        name = new JLabel(battery.getName());
        timeRemaining = new JLabel(battery.toString());
        remainingPercent = new JProgressBar(0, 100);
        int percentage = (int) Math.round(battery.getRemainingPercent() * 100);
        remainingPercent.setValue(percentage);
        JLabel percentageLabel = new JLabel("%" + percentage + " charged.");

        this.add(name);
        this.add(timeRemaining);
        this.add(percentageLabel);
        this.add(remainingPercent);
    }

    public void updateStats() {
        name.setText(battery.getName());
        timeRemaining.setText(Double.toString(battery.getTimeRemaining()));
        remainingPercent.setValue((int) Math.round(battery.getRemainingPercent() * 100));
    }
}
