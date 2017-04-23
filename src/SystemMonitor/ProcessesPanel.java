package SystemMonitor;

import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProcessesPanel extends JPanel {
    private ServiceHolder services;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton sortByMemory;
    private JButton sortByName;
    private JButton sortByPID;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private CustomTableModel customTableModel;

    private final String[] columnNames = {"Name", "PID", "Memory"};
    private String[][] data;
    private boolean isWindows;
    SimpleDateFormat dateFormat;

    private OperatingSystem.ProcessSort sort;

    public ProcessesPanel(ServiceHolder services) {
        super(new BorderLayout());
        sort = OperatingSystem.ProcessSort.PID;

        dateFormat = new SimpleDateFormat("HH 'hours,' mm 'minutes and' ss 'seconds'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        this.services = services;

        isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        buttonPanel = new JPanel(new FlowLayout());
        dataPanel = new JPanel();

        setupPanel();

        if(isWindows) {
            setupDataWin(sort);
        } else {
            setupDataUnix(sort);
        }
    }

    private void setupPanel() {
        ButtonListener buttonListener = new ButtonListener();
        sortByName = new JButton("Sort by name");
        sortByPID = new JButton("Sort by PID");
        sortByMemory = new JButton("Sort by memory");

        sortByName.addActionListener(buttonListener);
        sortByMemory.addActionListener(buttonListener);
        sortByPID.addActionListener(buttonListener);

        buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(sortByName);
        buttonPanel.add(sortByPID);
        buttonPanel.add(sortByMemory);

        this.add(buttonPanel, BorderLayout.SOUTH);
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        customTableModel = new CustomTableModel();
        table.setModel(customTableModel);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupDataUnix(OperatingSystem.ProcessSort sort) {
        OSProcess[] processes = services.processorService.getProcessesList(sort);
        data = new String[processes.length][5];

        for(int i = 0; i < processes.length; i++) {
            // path, uptime, pid, name
            data[i][0] = processes[i].getName();
            data[i][1] = Integer.toString(processes[i].getProcessID());
            data[i][2] = Long.toString(processes[i].getResidentSetSize() / (1024 * 1024)) + " MB";
        }
    }

    private void setupDataWin(OperatingSystem.ProcessSort sort) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist /nh /fo CSV");
            Scanner scan = new Scanner(process.getInputStream());
            ArrayList<ArrayList<String>> processList = new ArrayList<>();

            while(scan.hasNext()) {
                String line = scan.nextLine();
                line = line.replace("\"", "");
                String[] info = line.split(",");

                ArrayList<String> l = new ArrayList<>();
                l.add(info[0]);
                l.add(info[1]);
                long ramInfo = Long.parseLong(info[4].substring(0, info[4].length() - 2).replace(".", "")) / (1024);
                l.add(ramInfo + " MB");
                processList.add(l);
            }

            scan.close();

            data = new String[processList.size()][3];

            for(int i = 0; i < processList.size(); i++) {
                // name
                data[i][0] = processList.get(i).get(0);
                // pid
                data[i][1] = processList.get(i).get(1);
                // mem
                data[i][2] = processList.get(i).get(2);
            }

            if(sort == OperatingSystem.ProcessSort.NAME) {
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o1[0].toLowerCase().compareTo(o2[0].toLowerCase());
                    }
                });
            } else if(sort == OperatingSystem.ProcessSort.PID) {
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        int x = Integer.parseInt(o1[1]);
                        int y = Integer.parseInt(o2[1]);

                        return x - y;
                    }
                });
            } else if(sort == OperatingSystem.ProcessSort.MEMORY) {
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        int x = Integer.parseInt(o1[2].substring(0, o1[2].length() - 3));
                        int y = Integer.parseInt(o2[2].substring(0, o2[2].length() - 3));

                        return y - x;
                    }
                });
            }
        } catch(Exception e) {
            System.out.println("Fatal error, closing.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void updateStats() {
        if(isWindows) {
            setupDataWin(sort);
        } else {
            setupDataUnix(sort);
        }

        customTableModel.fireTableDataChanged();
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
            } else if(e.getSource() == sortByName) {
                sort = OperatingSystem.ProcessSort.NAME;
            } else if(e.getSource() == sortByPID) {
                sort = OperatingSystem.ProcessSort.PID;
            }

            updateStats();
        }
    }
}
