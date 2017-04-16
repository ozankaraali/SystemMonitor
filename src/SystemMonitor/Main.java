package SystemMonitor;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static DiskPanel diskPanel;
    public static ProcessingPanel processingPanel;
    public static BatteryPanel batteryPanel;
    public static StatusBar statusBar;

    public static JFrame mainFrame;

    public static void main(String[] args){
        ServiceHolder services = new ServiceHolder();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        // Create main frame
        mainFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainFrame.getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        // Insert tabbed pane
        JTabbedPane mainTabPane = new JTabbedPane();
        mainPanel.add(mainTabPane, BorderLayout.CENTER);

        // Create menu
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JButton triggerUpdateButton = new JButton("Trigger Update");
        triggerUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStats();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Ozan KARAALİ & Şamil NART");
            }
        });

        menuBar.add(triggerUpdateButton);
        menuBar.add(quitButton);
        menuBar.add(aboutButton);


        // Create content tabs
        batteryPanel = new BatteryPanel(services);
        diskPanel = new DiskPanel(services);
        processingPanel = new ProcessingPanel(services);

        mainTabPane.addTab("Performance", processingPanel);
        mainTabPane.addTab("Disk", diskPanel);
        mainTabPane.addTab("Battery", batteryPanel);

        // -------------------------------------------------
        // TEMPORARILY DISABLED BECAUSE OF INCOMING REFACTOR
        // -------------------------------------------------
        mainTabPane.addTab("Processes", new ProcessesPanel(services));

        // Set the status bar

        statusBar = new StatusBar(services);
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        // Usual swing stuff
        mainFrame.setTitle("SystemMonitor - COMP112");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

        while(true) {
            try {
                // Update panes and redraw the window
                updateStats();
                Thread.sleep(1000);

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void updateStats() {
        diskPanel.updateStats();
        batteryPanel.updateStats();
        statusBar.updateStats();
        processingPanel.updateStats();

        mainFrame.revalidate();
    }
}
