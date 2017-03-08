package SystemMonitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //SystemMonitor.ProcessorInfo
        setupOSHI();
        /*
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            System.exit(1);
        }
        JFrame main = new JFrame();
        JTabbedPane mainPanel = new JTabbedPane();
        main.add(mainPanel);
        mainPanel.addTab("lul", new JPanel());
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(800,600);
        main.setVisible(true);
        */

        System.out.println(ProcessorInfo.getIdentifier());


    }

    public static void setupOSHI() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        ProcessorInfo.setupOSHI(si, hal, os);
        MemoryInfo.setupOSHI(si, hal, os);
        // DiskInfo doesn't use OSHI but let's set it up anyways ¯\_(ツ)_/¯
        PowerSource[] sources = hal.getPowerSources();
        System.out.println(sources[0].getName());
        DiskInfo.setup();
    }
}
