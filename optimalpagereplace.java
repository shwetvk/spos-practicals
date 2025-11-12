// SPOS Practical 4: Optimal Page Replacement Algorithm
// -----------------------------------------------------

import java.util.*;

public class optimalpagereplace {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter length of reference string: ");
        int n = sc.nextInt();

        int[] reference = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) {
            reference[i] = sc.nextInt();
        }

        int[] memory = new int[frames];        // stores pages in memory
        Arrays.fill(memory, -1);               // initially empty
        int faults = 0, hits = 0;
        boolean full = false;

        for (int i = 0; i < n; i++) {
            int currentPage = reference[i];
            boolean found = false;

            // Step 1: Check if page already exists (Hit)
            for (int j = 0; j < frames; j++) {
                if (memory[j] == currentPage) {
                    hits++;
                    found = true;
                    break;
                }
            }

            // Step 2: If page not found → page fault
            if (!found) {
                faults++;

                // Case 1: Empty frame available
                if (!full) {
                    memory[faults - 1] = currentPage;
                    if (faults == frames) full = true;
                }
                // Case 2: All frames full → replace using Optimal logic
                else {
                    int replaceIndex = findReplaceIndex(memory, reference, i + 1);
                    memory[replaceIndex] = currentPage;
                }
            }

            // Step 3: Print memory state after each reference
            System.out.print("Step " + (i + 1) + " (" + currentPage + "): ");
            for (int j = 0; j < frames; j++) {
                if (memory[j] == -1)
                    System.out.print("- ");
                else
                    System.out.print(memory[j] + " ");
            }
            System.out.println(found ? " (Hit)" : " (Fault)");
        }

        // Step 4: Print result summary
        System.out.println("\nTotal Page Hits   = " + hits);
        System.out.println("Total Page Faults = " + faults);
        System.out.printf("Hit Ratio   = %.2f\n", (float) hits / n);
        System.out.printf("Fault Ratio = %.2f\n", (float) faults / n);
    }

    // Helper Function: Find which page to replace (farthest in future)
    static int findReplaceIndex(int[] memory, int[] ref, int nextIndex) {
        int farthest = -1, indexToReplace = -1;

        for (int i = 0; i < memory.length; i++) {
            int j;
            for (j = nextIndex; j < ref.length; j++) {
                if (memory[i] == ref[j])
                    break;
            }

            // Page not used again → replace immediately
            if (j == ref.length)
                return i;

            // Find page with farthest next use
            if (j > farthest) {
                farthest = j;
                indexToReplace = i;
            }
        }

        return (indexToReplace == -1) ? 0 : indexToReplace;
    }
}

