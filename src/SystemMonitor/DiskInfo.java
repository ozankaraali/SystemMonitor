package SystemMonitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiskInfo {
    private static List<Disk> disks;

    public static void setup() {
        File[] diskRoots = File.listRoots();
        disks = new ArrayList<>();
        for(File file: diskRoots) {
            disks.add(new Disk(file));
        }
    }

    public static List<Disk> getDisks() {
        return disks;
    }

    public static void setDisks(List<Disk> disks) {
        DiskInfo.disks = disks;
    }
}
