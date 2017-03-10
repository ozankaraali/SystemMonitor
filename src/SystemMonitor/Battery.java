package SystemMonitor;

import oshi.hardware.PowerSource;

public class Battery {
    private PowerSource battery;

    public Battery(PowerSource battery) {
        this.battery = battery;
    }

    public double getRemainingPercent() {
        return battery.getRemainingCapacity();
    }

    public double getTimeRemaining() {
        return battery.getTimeRemaining();
    }

    public String getName() {
        return battery.getName();
    }

}
