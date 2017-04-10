package SystemMonitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Main {
    public static void main(String[] args){
        ServiceHolder services = new ServiceHolder();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        JFrame mainFrame = new JFrame();
        JTabbedPane mainTabPane = new JTabbedPane();
        mainFrame.getContentPane().add(mainTabPane);
        BatteryPanel batteryPanel = new BatteryPanel(services);
        DiskPanel diskPanel = new DiskPanel(services);

        mainTabPane.addTab("Disk", diskPanel);
        mainTabPane.addTab("Battery", batteryPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

        while(true) {
            try {
                Thread.sleep(1000);
                diskPanel.updateStats();
                batteryPanel.updateStats();

                mainFrame.repaint();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }
}
