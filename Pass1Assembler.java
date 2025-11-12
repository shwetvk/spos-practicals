import java.io.*;

public class Pass1Assembler {

    public static void main(String[] args) {

        String code[][] = {
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

        int lc = Integer.parseInt(code[0][2]);
        String symtab[][] = new String[10][2];
        int stIndex = 0;

        for (int i = 0; i < code.length; i++) {

            String label = code[i][0];
            String opcode = code[i][1];

            if (!label.equals("")) {
                symtab[stIndex][0] = label;
                symtab[stIndex][1] = Integer.toString(lc);
                stIndex++;
            }

            if (opcode.equals("DS")) {
                lc += Integer.parseInt(code[i][2]);
            } else if (!opcode.equals("START") && !opcode.equals("END")) {
                lc++;
            }
        }

        System.out.println("Symbol Table:");
        for (int i = 0; i < stIndex; i++) {
            System.out.println(symtab[i][0] + "\t" + symtab[i][1]);
        }
    }
}
