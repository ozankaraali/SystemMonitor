package SystemMonitor;

import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ProcessesPanel extends JPanel {
    private ServiceHolder services;
    private JTable table;
    private JScrollPane scrollPane;
    private final String[] columnNames = {"Name", "PID", "Uptime"};
    private String[][] data;
    SimpleDateFormat dateFormat;
    private JButton sortByMemory, sortByName, sortByPID, sortByUptime;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private OperatingSystem.ProcessSort sort;

    public ProcessesPanel(ServiceHolder services) {
        super(new BorderLayout());
        sort = OperatingSystem.ProcessSort.CPU;

        dateFormat = new SimpleDateFormat("HH 'hours,' mm 'minutes and' ss 'seconds'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        this.services = services;

        buttonPanel = new JPanel(new FlowLayout());
        dataPanel = new JPanel();

        setupPanel();
        setupData(sort);
    }

    private String getHumanTime(long milliseconds) {
        return dateFormat.format(milliseconds);
    }

    private void setupPanel() {
        ButtonListener buttonListener = new ButtonListener();
        sortByName = new JButton("Sort by name");
        sortByMemory = new JButton("Sort by memory");
        sortByPID = new JButton("Sort by PID");
        sortByUptime = new JButton("Sort by uptime");
        sortByName.addActionListener(buttonListener);
        sortByMemory.addActionListener(buttonListener);
        sortByPID.addActionListener(buttonListener);
        sortByUptime.addActionListener(buttonListener);

        buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(sortByName);
        buttonPanel.add(sortByMemory);
        buttonPanel.add(sortByPID);
        buttonPanel.add(sortByUptime);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupData(OperatingSystem.ProcessSort sort) {
        OSProcess[] processes = services.processorService.getProcessesList(sort);
        data = new String[processes.length][3];

        for(int i = 0; i < processes.length; i++) {
            // path, uptime, pid, name
            data[i][0] = processes[i].getName();
            data[i][1] = Integer.toString(processes[i].getProcessID());
            data[i][2] = getHumanTime(processes[i].getUpTime());
        }

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.setModel(new CustomTableModel());
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void updateStats() {
        setupData(sort);
    }

    private class CustomTableModel extends AbstractTableModel {
        // Java in a nutshell, i need to create a class to set editable to false..
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == sortByMemory) {
                sort = OperatingSystem.ProcessSort.MEMORY;
                setupData(sort);
            } else if(e.getSource() == sortByName) {
                sort = OperatingSystem.ProcessSort.NAME;
                setupData(sort);
            } else if(e.getSource() == sortByPID) {
                sort = OperatingSystem.ProcessSort.PID;
                setupData(sort);
            } else if(e.getSource() == sortByUptime) {
                sort = OperatingSystem.ProcessSort.OLDEST;
                setupData(sort);
            } else {

            }
        }
    }


}
