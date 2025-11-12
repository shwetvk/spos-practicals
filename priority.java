// Practical 3 (Part): Priority Scheduling (Non-Preemptive)
// --------------------------------------------------------
// This program schedules processes based on their priority.
// Lower priority number = Higher priority (executed first)

import java.util.Scanner;

public class priority {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];     // Process IDs
        int[] bt = new int[n];      // Burst times
        int[] pr = new int[n];      // Priorities
        int[] wt = new int[n];      // Waiting times
        int[] tat = new int[n];     // Turnaround times

        // Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Burst Time for Process " + pid[i] + ": ");
            bt[i] = sc.nextInt();
            System.out.print("Enter Priority for Process " + pid[i] + ": ");
            pr[i] = sc.nextInt();
        }

        // Sort processes based on priority (ascending order)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pr[i] > pr[j]) {
                    // Swap priority
                    int temp = pr[i]; pr[i] = pr[j]; pr[j] = temp;
                    // Swap burst time
                    temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                    // Swap process ID
                    temp = pid[i]; pid[i] = pid[j]; pid[j] = temp;
                }
            }
        }

        // Calculate Waiting Time (like FCFS)
        wt[0] = 0;
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        // Calculate Turnaround Time
        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i];
        }

        // Calculate averages
        float totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        // Print results
        System.out.println("\nProcess\tBT\tPriority\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + bt[i] + "\t" + pr[i] + "\t\t" + wt[i] + "\t" + tat[i]);
        }

        System.out.println("\nAverage Waiting Time = " + (totalWT / n));
        System.out.println("Average Turnaround Time = " + (totalTAT / n));
    }
}
