package SystemMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static DiskPanel diskPanel;
    public static ProcessingPanel processingPanel;
    public static BatteryPanel batteryPanel;
    public static ProcessesPanel processesPanel;
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
        processesPanel = new ProcessesPanel(services);

        // Add the panels as tabs
        mainTabPane.addTab("Performance", processingPanel);
        mainTabPane.addTab("Disk", diskPanel);
        mainTabPane.addTab("Battery", batteryPanel);
        mainTabPane.addTab("Processes", processesPanel);

        // Set the status bar
        statusBar = new StatusBar(services);
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        // Usual swing stuff
        mainFrame.setTitle("SystemMonitor");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

        while(true) {
            try {
                // Update panes and redraw the window
                updateStats();
                // Fetching stats usually takes around 250-300ms
                // we want to refresh every second so 700ms is fit
                Thread.sleep(700);
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
        processesPanel.updateStats();

        mainFrame.revalidate();
    }
}
