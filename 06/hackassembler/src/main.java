import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class, manages the reading of the code, translating the code to binary and writing the output
 * to an output .hack file.
 */
public class main {
    private static final String LABEL_SYMBOL = "(";
    private static final String ASM_TYPE = ".asm";

    /**
     * The main function, if the path is of a directory- runs the assembler on all the files in the given directory.
     * Otherwise, if the given path is of a file- runs the assembler on the file.
     * @param args An array that is supposed to contain a path.
     */
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (file.isDirectory()) {
            File[] listOfFiles = file.listFiles();

            assert (listOfFiles != null);
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getPath().endsWith(ASM_TYPE)) {
//                    System.out.println(listOfFiles[i].getPath());
                    runAssembler(listOfFiles[i].getPath());
                }
            }
        } else {
            runAssembler(args[0]);
        }
    }

    /**
     * Runs the assembler on the file with the given name.
     * @param fileName The name of the file to encode.
     */
    public static void runAssembler(String fileName) {
        /* Gets the code from the given file using CodeParser. */
        String[] code = new String[0];
        try {
            code = CodeParser.getCodeLines(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        /* Initiates the symbolTable. */
        SymbolTable symbolTable = new SymbolTable();
        InitSymbolTable(symbolTable);

        /* Adds the labels from the code. */
        code = addLabels(symbolTable, code);

        /* Gets the translated code. */
        String[] binaryCode = TranslateCode(code, symbolTable);

        /* Writes the code to a new file. */
        writeCodeToFile(binaryCode, fileName);
    }

    /**
     * Initiates the symbolTable with the pre-defined symbols.
     * @param symbolTable The symbol table to initiate.
     */
    private static void InitSymbolTable(SymbolTable symbolTable) {
        String temp = "R";
        for (int i = 0; i < 16; i++) {
            symbolTable.addSymbol(temp + String.valueOf(i), i);
        }

        symbolTable.addSymbol("SCREEN", 16384);
        symbolTable.addSymbol("KBD", 24576);
        symbolTable.addSymbol("SP", 0);
        symbolTable.addSymbol("LCL", 1);
        symbolTable.addSymbol("ARG", 2);
        symbolTable.addSymbol("THIS", 3);
        symbolTable.addSymbol("THAT", 4);
    }

    /**
     * Adds the labels from the code to the symbol table and removes them from the code.
     * Note: A label is of form (xxx).
     * @param symbolTable The symbol table.
     * @param code The code array.
     * @return The label-free code.
     */
    private static String[] addLabels(SymbolTable symbolTable, String[] code) {
        ArrayList<String> processedCode = new ArrayList<>();
        int instructionCounter = 0;

        /* Removes all the label lines and saving them in the symbol table with the line reference. */
        for (String codeLine : code) {
            if (codeLine.substring(0, 1).equals(LABEL_SYMBOL)) {
                symbolTable.addSymbol(codeLine.substring(1, codeLine.length()-1), instructionCounter);
            } else {
                processedCode.add(codeLine);
                instructionCounter++;
            }
        }

        return processedCode.toArray(new String[processedCode.size()]);
    }

    /**
     * Translates the code to it's binary form.
     * @param code The code to translate.
     * @param symbolTable The program's symbol table.
     * @return A String array containing the binary code.
     */
    private static String[] TranslateCode(String[] code, SymbolTable symbolTable) {
        ArrayList<String> binaryCode = new ArrayList<>();
        InstructionTranslator it = new InstructionTranslator();
        for (String codeLine : code) {
            binaryCode.add(it.translateCodeLine(codeLine, symbolTable));
        }
        return binaryCode.toArray(new String[binaryCode.size()]);
    }

    /**
     * Writes the binary code to an output hack file.
     * @param binaryCode The code to write to the file.
     * @param filename The name of the input file.
     */
    private static void writeCodeToFile(String[] binaryCode, String filename) {
        File codeFile = new File(filename.split("\\.")[0] + ".hack");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(codeFile))) {
            for (String codeLine : binaryCode) {
                writer.write(codeLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
