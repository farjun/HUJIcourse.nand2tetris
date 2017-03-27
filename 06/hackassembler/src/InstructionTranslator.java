import java.util.HashMap;

/**
 * This class is responsible for translating a code line to it's binary form.
 */
public class InstructionTranslator {
    private static int varAddress = 16;
    private static final String A_INSTRUCTION_SYMBOL = "@", EQUAL_SYMBOL = "=", SEMI_COLON= ";",
            NULL = "null";

    /*
     Creates HashMaps that are going to hold all the information regarding translation of Literal code to
     binary code.
     */
    private static HashMap<String, String> compValues = new HashMap<>(),
            destValues = new HashMap<>(),
            jumpValues = new HashMap<>();

    /**
     * The constructor.
     * Creates a new symbol table.
     */
    public InstructionTranslator() {
        initTables();
    }

    /* Separates A and C instructions to be translated as needed and returns the outcome. */
    public String translateCodeLine(String codeLine, SymbolTable symbolTable) {

        /* . */
        if (codeLine.substring(0, 1).equals(A_INSTRUCTION_SYMBOL)) {
            return AInstruction(codeLine, symbolTable);
        } else {
            return CInstruction(codeLine);
        }
    }

    /**
     * Translates an A-instruction.
     * @param codeLine The A-instruction to translate.
     * @param symbolTable The program's symbol table.
     * @return A binary string representing the code line.
     */
    private String AInstruction(String codeLine, SymbolTable symbolTable) {
        /* Removes the A instruction symbol from the beginning of the string. */
        codeLine = codeLine.substring(1, codeLine.length());
        int value;

        try {
            value = Integer.parseInt(codeLine); // If the string is an integer, value is the numeric value
            // of the string.
        } catch (NumberFormatException nfe) { // If the value is a symbol, add it to the symbol table if it
            // is not there already and then value is the value of that symbol from the table.
            value = symbolTable.addSymbol(codeLine, varAddress);
            if (value == varAddress) {
                varAddress++;
            }
        }

        String binVal = Integer.toBinaryString(value); // Translates the value from decimal to binary.
        for (int i = binVal.length(); i < 16; i++) { // Makes the binary value 16-bit long.
            binVal = "0" + binVal;
        }

        return binVal;
    }

    /**
     * Translates a C-instruction.
     * @param codeLine The A-instruction to translate.
     * @return A binary string representing the code line.
     */
    private String CInstruction(String codeLine) {
        String a = "0", comp, dest, jump, extendAlu;
        String[] parts;

        /* Splits the given code line according to it's type: assignment statement or jump statement.*/
        if (codeLine.contains(EQUAL_SYMBOL)) {
            parts = codeLine.split(EQUAL_SYMBOL);
            comp = parts[1];
            dest = parts[0];
            jump = NULL;
        } else {
            parts = codeLine.split(SEMI_COLON);
            comp = parts[0];
            dest = NULL;
            jump = parts[1];
        }

        /* If the computation is a shift, set the relevant bits. */
        if (comp.contains("<<")||comp.contains(">>")){
            extendAlu = "01";
        } else {
            extendAlu = "11";
        }

        /* Sets a to be 0 if the computation contains A and 1 if it contains M. */
        if (comp.contains("M")) {
            a = "1";
            comp = comp.replace("M", "A");
        }

        /* Using the value tables, translates the fields to their binary form. */
        comp = compValues.get(comp);
        dest = destValues.get(dest);
        jump = jumpValues.get(jump);



        /* This order is pre-defined. */
        return "1" + extendAlu + a + comp + dest + jump;
    }

    /**
     * Initiates the data hashMaps according to pre-defined rules of the translation.
     */
    private void initTables() {
        /* Initiates the comp translation values. */
        compValues.put("0", "101010");
        compValues.put("1", "111111");
        compValues.put("-1", "111010");
        compValues.put("D", "001100");
        compValues.put("A", "110000");
        compValues.put("!D", "001101");
        compValues.put("!A", "110001");
        compValues.put("-D", "001111");
        compValues.put("-A", "110011");
        compValues.put("D+1", "011111");
        compValues.put("A+1", "110111");
        compValues.put("D-1", "001110");
        compValues.put("A-1", "110010");
        compValues.put("D+A", "000010");
        compValues.put("D-A", "010011");
        compValues.put("A-D", "000111");
        compValues.put("D&A", "000000");
        compValues.put("D|A", "010101");

        compValues.put("D<<", "110000");
        compValues.put("A<<", "100000");
        compValues.put("D>>", "010000");
        compValues.put("A>>", "000000");


        /* Initiates the dest translation values. */
        destValues.put("null", "000");
        destValues.put("M", "001");
        destValues.put("D", "010");
        destValues.put("MD", "011");
        destValues.put("A", "100");
        destValues.put("AM", "101");
        destValues.put("AD", "110");
        destValues.put("AMD", "111");

        /* Initiates the jump translation values. */
        jumpValues.put("null", "000");
        jumpValues.put("JGT", "001");
        jumpValues.put("JEQ", "010");
        jumpValues.put("JGE", "011");
        jumpValues.put("JLT", "100");
        jumpValues.put("JNE", "101");
        jumpValues.put("JLE", "110");
        jumpValues.put("JMP", "111");
    }
}
