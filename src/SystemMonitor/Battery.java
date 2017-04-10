package SystemMonitor;

import oshi.hardware.PowerSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Battery {
    private PowerSource battery;
    private DateFormat dateFormat;

    public Battery(PowerSource battery) {
        dateFormat = new SimpleDateFormat("HH 'hours,' mm 'minutes and' ss 'seconds'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
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

    public String toString() {
        if(getTimeRemaining() == -1.0) {
            return "Calculating...";
        } else if(getTimeRemaining() == -2) {
            return "Charging...";
        } else if(getTimeRemaining() < 0) {
            return "Unknown Error.";
        } else {
            return dateFormat.format(getTimeRemaining() * 1000);
        }
    }
}
