package SystemMonitor;

import java.io.File;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Disk {
    private String name;
    private File disk;
    private Icon icon;

    public Disk(File disk) {
        this.disk = disk;
        name = FileSystemView.getFileSystemView().getSystemDisplayName(this.disk);
        this.icon = FileSystemView.getFileSystemView().getSystemIcon(this.disk);
    }

    private String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
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

    public String toString() {
        return "Total Space: " + readableFileSize(getTotalSpace()) + ", Used Space: " + readableFileSize(getUsedSpace());
    }

    public Icon getIcon() {
        return icon;
    }
}
