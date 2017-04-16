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
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        timeRemaining = new JLabel(battery.toString());
        timeRemaining.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        remainingPercent = new JProgressBar(0, 100);
        int percentage = (int) Math.round(battery.getRemainingPercent() * 100);
        remainingPercent.setValue(percentage);
        JLabel percentageLabel = new JLabel("%" + percentage + " charged.");
        percentageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        this.add(name);
        this.add(timeRemaining);
        this.add(percentageLabel);
        this.add(remainingPercent);
    }
}
