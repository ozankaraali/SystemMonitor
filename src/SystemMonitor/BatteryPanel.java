package SystemMonitor;

import javax.swing.*;

public class BatteryPanel extends JPanel{
    private Battery battery;
    private JLabel name, timeRemaining;
    private JProgressBar remainingPercent;
    public BatteryPanel(Battery battery) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.battery = battery;
        setupPanel();
    }

    public void setupPanel() {
        name = new JLabel(battery.getName());
        timeRemaining = new JLabel(Double.toString(battery.getTimeRemaining()));
        remainingPercent = new JProgressBar(0, 100);
        remainingPercent.setValue((int) Math.round(battery.getRemainingPercent() * 100));
        this.add(name);
        this.add(timeRemaining);
        this.add(remainingPercent);
    }
}
