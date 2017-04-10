package SystemMonitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiskService {
    private List<Disk> disks;

    public DiskService() {
        File[] diskRoots = File.listRoots();
        disks = new ArrayList<>();
        for(File file: diskRoots) {
            disks.add(new Disk(file));
        }
    }

    public List<Disk> getDisks() {
        return disks;
    }
}
