package SystemMonitor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    private ServiceHolder services;
    private JLabel procCount;
    private JLabel cpuUsage;
    private JLabel memUsage;

    public StatusBar(ServiceHolder services) {
        super();
        this.services = services;
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setLayout(new FlowLayout());

        setupPanel();
    }

    private void setupPanel() {
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        this.procCount = new JLabel("Processes: " + services.processorService.getProcessCount());
        this.cpuUsage = new JLabel("CPU Usage: " + (int) services.processorService.getSystemCpuLoad() + "%");
        this.memUsage = new JLabel("Memory Usage: " + getMemUsageText());

        p1.add(procCount);
        p2.add(cpuUsage);
        p3.add(memUsage);

        this.add(p1);
        this.add(p2);
        this.add(p3);
    }

    private String getMemUsageText() {
        long usedMem = services.memoryService.getUsedMemory();
        usedMem /= 1024 * 1024;
        long totalMem = services.memoryService.getTotalMemory();
        totalMem /= 1024 * 1024;
        return Long.toString(usedMem) + "MB / " + Long.toString(totalMem) + "MB";
    }

    public void updateStats() {
        this.removeAll();
        this.setupPanel();
    }
}
