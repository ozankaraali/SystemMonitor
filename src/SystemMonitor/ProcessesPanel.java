package SystemMonitor;

import oshi.software.os.OSProcess;

import javax.swing.*;
import java.awt.*;

public class ProcessesPanel extends JPanel {
    private ServiceHolder services;
    private JTable table;
    private JScrollPane scrollPane;
    private final String[] columnNames = {"Name"};
    private String[][] data;


    public ProcessesPanel(ServiceHolder services) {
        super(new GridLayout(1, 0));
        this.services = services;

        setupData();
    }

    private void setupData() {
        OSProcess[] processes = services.processorService.getProcessesList();

        data = new String[processes.length][1];

        for(int i = 0; i < processes.length; i++) {
            data[i][0] = processes[i].getName();
        }

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }


}
