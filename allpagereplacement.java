// Practical 4: Page Replacement Algorithms in Java
// -----------------------------------------------

import java.io.*;
import java.util.*;

public class pagereplacement {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("\n=== PAGE REPLACEMENT ALGORITHMS ===");
            System.out.println("1. FIFO");
            System.out.println("2. LRU");
            System.out.println("3. Optimal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int ch = Integer.parseInt(br.readLine());

            switch (ch) {
                case 1 -> FIFO(br);
                case 2 -> LRU(br);
                case 3 -> Optimal(br);
                case 4 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // 1️⃣ FIFO
    static void FIFO(BufferedReader br) throws IOException {
        System.out.print("Enter number of Frames: ");
        int frames = Integer.parseInt(br.readLine());
        System.out.print("Enter length of Reference string: ");
        int refLen = Integer.parseInt(br.readLine());

        int[] ref = new int[refLen];
        int[] buffer = new int[frames];
        int[][] layout = new int[refLen][frames];
        Arrays.fill(buffer, -1);

        System.out.println("Enter reference string:");
        for (int i = 0; i < refLen; i++) ref[i] = Integer.parseInt(br.readLine());

        int pointer = 0, hits = 0, faults = 0;
        for (int i = 0; i < refLen; i++) {
            boolean found = false;
            for (int j = 0; j < frames; j++)
                if (buffer[j] == ref[i]) { hits++; found = true; break; }

            if (!found) {
                buffer[pointer] = ref[i];
                pointer = (pointer + 1) % frames;
                faults++;
            }
            for (int j = 0; j < frames; j++) layout[i][j] = buffer[j];
        }

        printLayout(layout, frames, refLen);
        printResult(hits, faults, refLen);
    }

    // 2️⃣ LRU
    static void LRU(BufferedReader br) throws IOException {
        System.out.print("Enter number of Frames: ");
        int frames = Integer.parseInt(br.readLine());
        System.out.print("Enter length of Reference string: ");
        int refLen = Integer.parseInt(br.readLine());

        int[] ref = new int[refLen];
        int[] buffer = new int[frames];
        int[][] layout = new int[refLen][frames];
        Arrays.fill(buffer, -1);
        ArrayList<Integer> recent = new ArrayList<>();

        System.out.println("Enter reference string:");
        for (int i = 0; i < refLen; i++) ref[i] = Integer.parseInt(br.readLine());

        int pointer = 0, hits = 0, faults = 0;
        boolean full = false;

        for (int i = 0; i < refLen; i++) {
            if (recent.contains(ref[i])) recent.remove((Integer) ref[i]);
            recent.add(ref[i]);

            boolean found = false;
            for (int j = 0; j < frames; j++)
                if (buffer[j] == ref[i]) { hits++; found = true; break; }

            if (!found) {
                if (full) {
                    // find least recently used
                    int least = recent.get(0);
                    int idx = -1;
                    for (int j = 0; j < frames; j++)
                        if (buffer[j] == least) { idx = j; break; }
                    buffer[idx] = ref[i];
                } else {
                    buffer[pointer++] = ref[i];
                    if (pointer == frames) { full = true; pointer = 0; }
                }
                faults++;
            }
            for (int j = 0; j < frames; j++) layout[i][j] = buffer[j];
        }

        printLayout(layout, frames, refLen);
        printResult(hits, faults, refLen);
    }

    // 3️⃣ Optimal
    static void Optimal(BufferedReader br) throws IOException {
        System.out.print("Enter number of Frames: ");
        int frames = Integer.parseInt(br.readLine());
        System.out.print("Enter length of Reference string: ");
        int refLen = Integer.parseInt(br.readLine());

        int[] ref = new int[refLen];
        int[] buffer = new int[frames];
        int[][] layout = new int[refLen][frames];
        Arrays.fill(buffer, -1);

        System.out.println("Enter reference string:");
        for (int i = 0; i < refLen; i++) ref[i] = Integer.parseInt(br.readLine());

        int pointer = 0, hits = 0, faults = 0;
        boolean full = false;

        for (int i = 0; i < refLen; i++) {
            boolean found = false;
            for (int j = 0; j < frames; j++)
                if (buffer[j] == ref[i]) { hits++; found = true; break; }

            if (!found) {
                if (full) {
                    int farthest = i + 1, idx = 0;
                    for (int j = 0; j < frames; j++) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int k = i + 1; k < refLen; k++)
                            if (buffer[j] == ref[k]) { nextUse = k; break; }
                        if (nextUse > farthest) { farthest = nextUse; idx = j; }
                    }
                    buffer[idx] = ref[i];
                } else {
                    buffer[pointer++] = ref[i];
                    if (pointer == frames) { full = true; pointer = 0; }
                }
                faults++;
            }
            for (int j = 0; j < frames; j++) layout[i][j] = buffer[j];
        }

        printLayout(layout, frames, refLen);
        printResult(hits, faults, refLen);
    }

    // Helper functions
    static void printLayout(int[][] layout, int frames, int refLen) {
        System.out.println("\nMemory Layout:");
        for (int i = 0; i < frames; i++) {
            for (int j = 0; j < refLen; j++)
                System.out.printf("%3d ", layout[j][i]);
            System.out.println();
        }
    }

    static void printResult(int hits, int faults, int total) {
        System.out.println("\nHits: " + hits);
        System.out.println("Faults: " + faults);
        System.out.printf("Hit Ratio: %.2f\n", (float) hits / total);
    }
}
