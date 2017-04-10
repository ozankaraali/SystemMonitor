package SystemMonitor;


import javax.swing.*;
import java.awt.*;

public class DiskDisplay extends JPanel {
    private Disk disk;
    JLabel name;
    JLabel batteryDescription;
    JProgressBar remainingPercent;

    public DiskDisplay(Disk disk) {
        super();
        this.disk = disk;

        init();
    }

    private void init() {
        name = new JLabel(disk.getName());
        batteryDescription = new JLabel(disk.toString());
        batteryDescription.setIcon(disk.getIcon());
        remainingPercent = new JProgressBar(0, 100);
        remainingPercent.setValue((int) Math.round(disk.getUsedToTotalRatio() * 100));

        this.add(name, BorderLayout.WEST);
        this.add(batteryDescription, BorderLayout.EAST);
        this.add(remainingPercent, BorderLayout.SOUTH);
    }
}
