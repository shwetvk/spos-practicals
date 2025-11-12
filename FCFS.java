
import java.util.Scanner;

public class FCFS {
    public static void main(String args[]) {
        int i, no_p, burst_time[], TT[], WT[];
        float avg_wait = 0, avg_TT = 0;

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of processes: ");
        no_p = s.nextInt();

        // Create arrays dynamically based on number of processes
        burst_time = new int[no_p];
        TT = new int[no_p];
        WT = new int[no_p];

        // First process waiting time is 0
        WT[0] = 0;

        // Input burst times
        System.out.println("\nEnter Burst Time for processes:");
        for (i = 0; i < no_p; i++) {
            System.out.print("\tP" + (i + 1) + ": ");
            burst_time[i] = s.nextInt();
        }

        // Calculate waiting time for each process
        for (i = 1; i < no_p; i++) {
            WT[i] = WT[i - 1] + burst_time[i - 1];
            avg_wait += WT[i];
        }

        avg_wait = avg_wait / no_p;

        // Calculate turnaround time
        for (i = 0; i < no_p; i++) {
            TT[i] = WT[i] + burst_time[i];
            avg_TT += TT[i];
        }

        avg_TT = avg_TT / no_p;

        // Display results
        System.out.println("\n**********************************************************");
        System.out.println("\t\tFCFS Scheduling Results");
        System.out.println("**********************************************************");
        System.out.println("Process\tBurst Time\tWaiting Time\tTurn Around Time");

        for (i = 0; i < no_p; i++) {
            System.out.println("P" + (i + 1) + "\t\t" + burst_time[i] + "\t\t" + WT[i] + "\t\t" + TT[i]);
        }

        System.out.println("\n----------------------------------------------------------");
        System.out.printf("Average Waiting Time : %.2f\n", avg_wait);
        System.out.printf("Average Turn Around Time : %.2f\n", avg_TT);
        System.out.println("----------------------------------------------------------");

        s.close();
    }
}
