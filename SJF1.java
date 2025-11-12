
import java.util.Scanner;

class SJF1 {
    public static void main(String args[]) {
        int burst_time[], process[], waiting_time[], tat[];
        int i, j, n, total = 0, pos, temp;
        float wait_avg, TAT_avg;
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = s.nextInt();

        process = new int[n];
        burst_time = new int[n];
        waiting_time = new int[n];
        tat = new int[n];

        System.out.println("\nEnter Burst Time:");
        for (i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            burst_time[i] = s.nextInt();
            process[i] = i + 1; // Process number
        }

        // Sorting according to burst time (Selection Sort)
        for (i = 0; i < n; i++) {
            pos = i;
            for (j = i + 1; j < n; j++) {
                if (burst_time[j] < burst_time[pos])
                    pos = j;
            }

            // Swap burst times
            temp = burst_time[i];
            burst_time[i] = burst_time[pos];
            burst_time[pos] = temp;

            // Swap process numbers
            temp = process[i];
            process[i] = process[pos];
            process[pos] = temp;
        }

        // First process has 0 waiting time
        waiting_time[0] = 0;

        // Calculate waiting times
        for (i = 1; i < n; i++) {
            waiting_time[i] = 0;
            for (j = 0; j < i; j++)
                waiting_time[i] += burst_time[j];

            total += waiting_time[i];
        }

        // Calculate Average Waiting Time
        wait_avg = (float) total / n;
        total = 0;

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");

        // Calculate Turnaround Time and Average
        for (i = 0; i < n; i++) {
            tat[i] = burst_time[i] + waiting_time[i];
            total += tat[i];
            System.out.println("P" + process[i] + "\t\t" + burst_time[i] + "\t\t" + waiting_time[i] + "\t\t" + tat[i]);
        }

        // Calculate Average Turnaround Time
        TAT_avg = (float) total / n;

        System.out.println("\nAverage Waiting Time: " + wait_avg);
        System.out.println("Average Turnaround Time: " + TAT_avg);
    }
}
