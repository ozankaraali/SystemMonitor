/**
 * Created by ozan on 3/6/17.
 */
public class Main {
    public static void main(String[] args){
        //CPUInfo
        System.out.println("NAME: " + CPUInfo.getProcessorName());
        System.out.println("PHYSICAL: " + CPUInfo.getPhysicalProcessorCount());
        System.out.println("LOGICAL: " + CPUInfo.getLogicalProcessorCount());
        System.out.println("IDENTIFIER: " + CPUInfo.getIdentifier());
        System.out.println("PROCESSOR ID: " + CPUInfo.getProcessorID());
        System.out.println("CPU LOAD: " + CPUInfo.getSystemCpuLoad());
        System.out.println("UPTIME: " + CPUInfo.getSystemUptime());
        System.out.println("PROCESSES: " + CPUInfo.getProcesses());
        System.out.println("THREADS: " + CPUInfo.getThreads());
        System.out.println("PROCESS LIST: ");
        CPUInfo.getProcessesList();

    }
}
