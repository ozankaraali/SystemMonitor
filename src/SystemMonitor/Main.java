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
        //SystemMonitor.ProcessorInfo
        setupOSHI();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }
        JFrame main = new JFrame();
        JTabbedPane mainPanel = new JTabbedPane();
        JPanel lul = new JPanel();
        lul.setLayout(new BoxLayout(lul, BoxLayout.Y_AXIS));
        main.getContentPane().add(mainPanel);
        JPanel somePanel = new JPanel();
        somePanel.add(new JLabel("test"));
        JPanel someNewPanel = new JPanel();
        someNewPanel.add(new JLabel("tast"));
        lul.add(someNewPanel);
        lul.add(somePanel);
        mainPanel.addTab("lul", new JPanel());
        mainPanel.addTab("lul", lul);
        mainPanel.addTab("battery", new BatteryPanel(new Battery((new SystemInfo()).getHardware().getPowerSources()[0])));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(800,600);
        main.setVisible(true);


        System.out.println(ProcessorInfo.getIdentifier());


    }

    public static void setupOSHI() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        ProcessorInfo.setupOSHI(si, hal, os);
        MemoryInfo.setupOSHI(si, hal, os);
        // DiskInfo doesn't use OSHI but let's set it up anyways ¯\_(ツ)_/¯
        DiskInfo.setup();
    }
}
