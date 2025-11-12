
import java.io.*;

class sym {
    public static void main(String arg[]) throws IOException {
        int i;

        // Input assembly-like code as a 2D array (each row = line)
        String a[][] = {
            {"", "START", "101", ""},
            {"", "MOVER", "BREG", "ONE"},
            {"AGAIN", "MULT", "BREG", "TERM"},
            {"", "MOVER", "CREG", "TERM"},
            {"", "ADD", "CREG", "N"},
            {"", "MOVEM", "CREG", "TERM"},
            {"N", "DS", "2", ""},
            {"RESULT", "DS", "2", ""},
            {"ONE", "DC", "1", ""},
            {"TERM", "DS", "1", ""},
            {"", "END", "", ""}
        };

        // Initialize location counter (LC) with the value after START
        int lc = Integer.parseInt(a[0][2]);

        // Symbol Table - 2 columns: Symbol and Address
        String st[][] = new String[10][2];
        int cnt = 0;

        for (i = 0; i < a.length; i++) {

            // Use .equals() for string comparison instead of == in Java
            if (!a[i][0].equals("")) { // if label is not empty
                st[cnt][0] = a[i][0];       // symbol name
                st[cnt][1] = Integer.toString(lc); // address
                cnt++;

                // Check for DS (Data Storage directive)
                if (a[i][1].equals("DS")) {
                    int d = Integer.parseInt(a[i][2]);
                    lc = lc + d; // reserve 'd' memory blocks
                }
                // Check for DC (Define Constant directive)
                else if (a[i][1].equals("DC")) {
                    lc = lc + 1;
                }
                else {
                    lc++;
                }
            } else {
                // If no label, just increment LC normally
                lc++;
            }
        }

        // Print Symbol Table
        System.out.println("Symbol Table");
        System.out.println("Symbol\tAddress");
        System.out.println("--------------------");
        for (i = 0; i < cnt; i++) {
            System.out.println(st[i][0] + "\t" + st[i][1]);
        }
    }
}
