package SystemMonitor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DiskPanel extends JPanel {
    private ServiceHolder services;
    private List<DiskDisplay> diskDisplays;

    public DiskPanel(ServiceHolder services) {
        super();
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.services = services;
        setupPanel();
    }

    private void setupPanel() {
        List<Disk> diskList = services.diskService.getDisks();
        this.diskDisplays = new ArrayList<>();

        for(Disk d: diskList) {
            DiskDisplay dDisplay = new DiskDisplay(d);
            diskDisplays.add(dDisplay);
            this.add(dDisplay);
        }
    }

    public void updateStats() {
        this.removeAll();
        setupPanel();
    }
}
