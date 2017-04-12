package SystemMonitor;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        ServiceHolder services = new ServiceHolder();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        // Create main frame
        JFrame mainFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainFrame.getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        // Insert tabbed pane
        JTabbedPane mainTabPane = new JTabbedPane();
        mainPanel.add(mainTabPane, BorderLayout.CENTER);

        // Create content tabs
        BatteryPanel batteryPanel = new BatteryPanel(services);
        DiskPanel diskPanel = new DiskPanel(services);

        mainTabPane.addTab("Disk", diskPanel);
        mainTabPane.addTab("Battery", batteryPanel);

        // Set the status bar
        StatusBar statusBar = new StatusBar(services);
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        // Usual swing stuff
        mainFrame.setTitle("SystemMonitor - COMP112");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

        while(true) {
            try {
                // Update panes and redraw the window
                Thread.sleep(1000);
                diskPanel.updateStats();
                batteryPanel.updateStats();
                statusBar.updateStats();

                mainFrame.revalidate();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }
}
