/**
 * Created by ozan on 3/6/17.
 */
public class Main {
    public static void main(String[] args){
        //ProcessorInfo
        System.out.println("NAME: " + ProcessorInfo.getProcessorName());
        System.out.println("PHYSICAL: " + ProcessorInfo.getPhysicalProcessorCount());
        System.out.println("LOGICAL: " + ProcessorInfo.getLogicalProcessorCount());
        System.out.println("IDENTIFIER: " + ProcessorInfo.getIdentifier());
        System.out.println("PROCESSOR ID: " + ProcessorInfo.getProcessorID());
        System.out.println("CPU LOAD: " + ProcessorInfo.getSystemCpuLoad());
        System.out.println("UPTIME: " + ProcessorInfo.getSystemUptime());
        System.out.println("PROCESSES: " + ProcessorInfo.getProcesses());
        System.out.println("THREADS: " + ProcessorInfo.getThreads());
        System.out.println("PROCESS LIST: ");
        ProcessorInfo.getProcessesList();

    }
}
