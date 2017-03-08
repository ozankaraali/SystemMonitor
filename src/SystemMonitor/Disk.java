package SystemMonitor;

import java.io.File;
import javax.swing.filechooser.FileSystemView;

public class Disk {
    private String name;
    private File disk;

    public Disk(File disk) {
        this.disk = disk;
        name = FileSystemView.getFileSystemView().getSystemDisplayName(this.disk);
    }

    public long getUsedSpace() {
        return disk.getTotalSpace() - disk.getFreeSpace();
    }

    public long getFreeSpace() {
        return disk.getFreeSpace();
    }

    public long getTotalSpace() {
        return disk.getTotalSpace();
    }

    public double getUsedToTotalRatio() {
        return (double)getUsedSpace() / (double)getTotalSpace();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
