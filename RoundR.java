
import java.util.*;
import java.io.*;

class RoundR {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int Process[] = new int[10];
        int a[] = new int[10]; // copy of burst times for later use
        int Burst_time[] = new int[10];
        int WT[] = new int[10];
        int TAT[] = new int[10];
        int Pno, sum = 0;
        int TimeQuantum;

        System.out.println("\nEnter the number of processes: ");
        Pno = sc.nextInt();

        System.out.println("\nEnter process numbers: ");
        for (int i = 0; i < Pno; i++) {
            Process[i] = sc.nextInt();
        }

        System.out.println("\nEnter the Burst Time of each process: ");
        for (int i = 0; i < Pno; i++) {
            Burst_time[i] = sc.nextInt();
            a[i] = Burst_time[i]; // store original burst times for later
        }

        System.out.println("\nEnter the Time Quantum: ");
        TimeQuantum = sc.nextInt();

        // Initialize waiting times to 0
        for (int i = 0; i < Pno; i++) {
            WT[i] = 0;
        }

        // Round Robin Logic
        do {
            for (int i = 0; i < Pno; i++) {
                if (Burst_time[i] > TimeQuantum) {
                    Burst_time[i] -= TimeQuantum;
                    for (int j = 0; j < Pno; j++) {
                        if ((j != i) && (Burst_time[j] != 0)) {
                            WT[j] += TimeQuantum;
                        }
                    }
                } else if (Burst_time[i] > 0) { // less than or equal to quantum
                    for (int j = 0; j < Pno; j++) {
                        if ((j != i) && (Burst_time[j] != 0)) {
                            WT[j] += Burst_time[i];
                        }
                    }
                    Burst_time[i] = 0;
                }
            }

            // check if all processes completed
            sum = 0;
            for (int k = 0; k < Pno; k++) {
                sum += Burst_time[k];
            }
        } while (sum != 0);

        // Calculate Turnaround Time = WT + BT(original)
        for (int i = 0; i < Pno; i++) {
            TAT[i] = WT[i] + a[i];
        }

        // Display results
        System.out.println("\nProcess\tBT\tWT\tTAT");
        for (int i = 0; i < Pno; i++) {
            System.out.println("P" + Process[i] + "\t" + a[i] + "\t" + WT[i] + "\t" + TAT[i]);
        }

        // Calculate averages
        float avg_wt = 0, avg_tat = 0;
        for (int i = 0; i < Pno; i++) {
            avg_wt += WT[i];
            avg_tat += TAT[i];
        }

        System.out.println("\nAverage Waiting Time: " + (avg_wt / Pno));
        System.out.println("Average Turnaround Time: " + (avg_tat / Pno));
    }
}
