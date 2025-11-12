import java.util.Scanner;

class SJF{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int process[] = new int[n];
        int burst_time[] = new int[n];
        int waiting_time[] = new int[n];
        int turnaround_time[] = new int[n];

        // Input burst times
        System.out.println("\nEnter Burst Time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            burst_time[i] = sc.nextInt();
            process[i] = i + 1; // Assign process ID
        }

        // ----------------------------
        // Step 1: Sort processes by burst time (ascending)
        // ----------------------------
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (burst_time[j] > burst_time[j + 1]) {
                    // Swap burst times
                    int temp = burst_time[j];
                    burst_time[j] = burst_time[j + 1];
                    burst_time[j + 1] = temp;

                    // Swap process IDs as well
                    temp = process[j];
                    process[j] = process[j + 1];
                    process[j + 1] = temp;
                }
            }
        }

        // ----------------------------
        // Step 2: Calculate waiting times (same logic as FCFS)
        // ----------------------------
        waiting_time[0] = 0; // first process has no waiting time
        int totalWT = 0, totalTAT = 0;

        for (int i = 1; i < n; i++) {
            waiting_time[i] = waiting_time[i - 1] + burst_time[i - 1];
            totalWT += waiting_time[i];
        }

        // ----------------------------
        // Step 3: Calculate turnaround times
        // ----------------------------
        for (int i = 0; i < n; i++) {
            turnaround_time[i] = burst_time[i] + waiting_time[i];
            totalTAT += turnaround_time[i];
        }

        // ----------------------------
        // Step 4: Display results
        // ----------------------------
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" + burst_time[i] + "\t\t" + waiting_time[i] + "\t\t" + turnaround_time[i]);
        }

        // ----------------------------
        // Step 5: Calculate averages
        // ----------------------------
        float avgWT = (float) totalWT / n;
        float avgTAT = (float) totalTAT / n;

        System.out.println("\nAverage Waiting Time: " + avgWT);
        System.out.println("Average Turnaround Time: " + avgTAT);

        sc.close();
    }
}
