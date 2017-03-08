package SystemMonitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //SystemMonitor.ProcessorInfo
        setupOSHI();

        OSProcess[] list = ProcessorInfo.getProcessesList();
        System.out.println("lul");
        /*
        JFrame main = new JFrame();
        JTabbedPane mainPanel = new JTabbedPane();
        main.add(mainPanel);
        mainPanel.addTab("lul", new JPanel());
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(800,600);
        main.setVisible(true);
         */

    }

    public static void setupOSHI() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        ProcessorInfo.setupOSHI(si, hal, os);
    }
}
