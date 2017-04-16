package SystemMonitor;

import sun.plugin2.gluegen.runtime.CPU;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ProcessingPanel extends JPanel  {
    private ServiceHolder services;
    private JPanel coreLoads, processorAndMemoryPanel;
    private CpuInfoDisplay cpuInfoDisplay;
    private RamInfoDisplay ramInfoDisplay;
    private List<cpuLoadDisplay> cpuLoadDisplays;


    public ProcessingPanel(ServiceHolder services) {
        super(new BorderLayout());
        this.services = services;
        processorAndMemoryPanel = new JPanel(new FlowLayout());
        coreLoads = new JPanel(new FlowLayout());
        cpuInfoDisplay = new CpuInfoDisplay();
        ramInfoDisplay = new RamInfoDisplay();
        this.processorAndMemoryPanel.add(cpuInfoDisplay);
        this.processorAndMemoryPanel.add(ramInfoDisplay);


        setupPanel();
    }

    private void setupPanel() {
        cpuLoadDisplays = new ArrayList<>();
        double[] loads = services.processorService.getCpuLoads();

        for(int i = 0; i < services.processorService.getLogicalProcessorCount(); i++) {
            cpuLoadDisplay c = new cpuLoadDisplay("Core" + (i + 1), loads[i]);

            cpuLoadDisplays.add(c);
            coreLoads.add(c);
        }

        this.add(coreLoads, BorderLayout.NORTH);
        this.add(processorAndMemoryPanel, BorderLayout.CENTER);
    }

    public void updateStats() {
        double[] loads = services.processorService.getCpuLoads();

        for(int i = 0; i < cpuLoadDisplays.size(); i++) {
            cpuLoadDisplays.get(i).update(loads[i]);
        }

        this.cpuInfoDisplay.updateStats();
        this.ramInfoDisplay.updateStats();
    }

    private Color getPercentageColor(float percentage) {
        float h = (float) (120.0 * (1.0 - percentage)) / 360;
        float s = (float) 1.0;
        float b = (float) 0.5;
        return Color.getHSBColor(h, s, b);
    }

    private class cpuLoadDisplay extends JPanel {
        private JLabel text;

        public cpuLoadDisplay(String label, double load) {
            super();

            this.text = new JLabel(label);
            this.text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            this.add(text);
            this.setBackground(getPercentageColor((float) load));
            this.setToolTipText(Math.round(load * 100) + "%");
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        }

        public void update(double load) {
            this.setBackground(getPercentageColor((float) load));
            this.setToolTipText(Math.round(load * 100) + "%");
        }
    }

    private class CpuInfoDisplay extends JPanel {
        private DateFormat dateFormat;
        private JPanel cpuStats;
        private JLabel cpuName, utilization, coreCount, threadCount, uptime, is64Bit;

        public CpuInfoDisplay() {
            super(new BorderLayout());
            dateFormat = new SimpleDateFormat("HH 'hours,' mm 'minutes and' ss 'seconds'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            cpuStats = new JPanel();
            cpuStats.setLayout(new BoxLayout(cpuStats, BoxLayout.Y_AXIS));
            cpuName = new JLabel(services.processorService.getProcessorName());
            cpuName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            this.add(cpuName, BorderLayout.NORTH);
            this.add(cpuStats, BorderLayout.CENTER);

            this.setupPanel();
        }

        public void setupPanel() {
            Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
            utilization = new JLabel("Utilization: " + (int) services.processorService.getSystemCpuLoad() + "%");
            coreCount = new JLabel("Physical Cores: " + services.processorService.getPhysicalProcessorCount());
            threadCount = new JLabel("Logical Cores: " + services.processorService.getLogicalProcessorCount());
            uptime = new JLabel("Uptime: " + this.getHumanTime(services.processorService.getSystemUptime()));
            is64Bit = new JLabel("CPU Architecture: " + System.getProperty("os.arch"));

            utilization.setFont(font);
            coreCount.setFont(font);
            threadCount.setFont(font);
            uptime.setFont(font);
            is64Bit.setFont(font);

            cpuStats.add(utilization);
            cpuStats.add(coreCount);
            cpuStats.add(threadCount);
            cpuStats.add(uptime);
            cpuStats.add(is64Bit);
        }

        public void updateStats() {
            utilization.setText("Utilization: " + (int) services.processorService.getSystemCpuLoad() + "%");
            uptime.setText("Uptime: " + this.getHumanTime(services.processorService.getSystemUptime()));
        }

        public String getHumanTime(long seconds) {
            return dateFormat.format(seconds * 1000);
        }
    }

    private class RamInfoDisplay extends JPanel {
        private JLabel ramUsed, ramTotal, swapTotal, swapUsed;

        public RamInfoDisplay() {
            super();
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            setupPanel();
        }

        public void setupPanel() {
            Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

            this.ramTotal = new JLabel("RAM Capacity: " + getSizeToMB(services.memoryService.getTotalMemory()));
            this.ramUsed = new JLabel("RAM Used: " + getSizeToMB(services.memoryService.getUsedMemory()));
            this.swapTotal = new JLabel("SWAP Capacity: " + getSizeToMB(services.memoryService.getTotalSwap()));
            this.swapUsed = new JLabel("SWAP Used: " + getSizeToMB(services.memoryService.getUsedSwap()));

            this.ramTotal.setFont(font);
            this.ramUsed.setFont(font);
            this.swapUsed.setFont(font);
            this.swapTotal.setFont(font);

            this.add(ramTotal);
            this.add(ramUsed);
            this.add(swapTotal);
            this.add(swapUsed);
        }

        public void updateStats() {
            this.ramUsed.setText("RAM Used: " + getSizeToMB(services.memoryService.getUsedMemory()));
            this.swapUsed.setText("SWAP Used: " + getSizeToMB(services.memoryService.getUsedSwap()));
        }

        private String getSizeToMB(long size) {
            size /= 1024 * 1024;
            return Long.toString(size) + "MB";
        }
    }
}
