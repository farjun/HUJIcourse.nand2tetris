import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Translates the VM commands to assembly commands.
 */
public class CodeWriter {
    private static ArrayList<String> asmCode = new ArrayList<>();
    private String fileName;
    /* A counter to track the continue and true labels. */
    private int lblCounter = 0;
    private String currFuncName = "";
    private HashMap<String, String> segSymbolMap = new HashMap<>();

    /**
     * Ctor, creates a nnw codeWriter object.
     */
    public CodeWriter() {
        writeInit();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes the code of arithmetic, logic, jump operations.
     *
     * @param command The command to translate.
     */
    public void writeArithmetic(String command) {
        writeGetTopmost("Y");
        if (!command.equals("neg") && !command.equals("not")) {
            writeGetTopmost("X");
        }

        switch (command) {

            case "add":
            case "sub":
            case "and":
            case "or":
                String op;
                if (command.equals("add")) {
                    op = "+";
                } else if (command.equals("sub")) {
                    op = "-";
                } else if (command.equals("and")) {
                    op = "&";
                } else {
                    op = "|";
                }
                writeSetD("Y", "M");
                asmCode.add("@X");
                asmCode.add("D = M " + op + " D");
                break;

            case "neg":
            case "not":
                if (command.equals("neg")) {
                    op = "-";
                } else {
                    op = "!";
                }
                writeSetD("Y", "M");
                asmCode.add("D = " + op + "D");
                break;

            case "eq":
            case "gt":
            case "lt":
                String trueJmp;
                if (command.equals("eq")) {
                    trueJmp = "JEQ";
                } else if (command.equals("gt")) {
                    trueJmp = "JGT";
                } else {
                    trueJmp = "JLT";
                }

                //checks whether X is negative or positive.
                asmCode.add("@X");
                asmCode.add("D = M");
                asmCode.add("@NEG" + lblCounter);
                asmCode.add("D;JLT");
                asmCode.add("@POS" + lblCounter);
                asmCode.add("D;JGT");

                asmCode.add("(EQSGN" + lblCounter + ")");

                //equal sign
                writeSetD("Y", "M");
                asmCode.add("@X");
                asmCode.add("D = M - D");

                //equal sign

                asmCode.add("@CONT" + lblCounter);
                asmCode.add("0;JMP");

                asmCode.add("(NEG" + lblCounter + ")");
                asmCode.add("@Y");
                asmCode.add("D = M");
                asmCode.add("@EQSGN" + lblCounter);
                asmCode.add("D;JLT");
                // x<0 y>0
                asmCode.add("D = -1");
                // x<0 y>0
                asmCode.add("@CONT" + lblCounter);
                asmCode.add("0;JMP");

                asmCode.add("(POS" + lblCounter + ")");
                asmCode.add("@Y");
                asmCode.add("D = M");
                asmCode.add("@EQSGN" + lblCounter);
                asmCode.add("D;JGT");
                //x>0 y<0
                asmCode.add("D = 1");
                //x>0 y<0

                asmCode.add("(CONT" + lblCounter + ")");
                asmCode.add("@TRUE" + lblCounter);
                asmCode.add("D;" + trueJmp);
                asmCode.add("D=0");
                asmCode.add("@CONTINUE" + lblCounter);
                asmCode.add("0;JMP");
                asmCode.add("(TRUE" + lblCounter + ")");
                asmCode.add("D = -1");
                asmCode.add("(CONTINUE" + lblCounter + ")");
                lblCounter++;
                break;

        }
        writePushDToStack();
    }

    /**
     * Writes the code that pushes the value of the D register onto the stack.
     */
    private void writePushDToStack() {
        asmCode.add("@SP");
        asmCode.add("A = M");
        asmCode.add("M = D");
        asmCode.add("@SP");
        asmCode.add("M=M+1");
    }

    /**
     * Writes the code of push and pop commands.
     *
     * @param command The command to translate.
     * @param segment The segment which the command is going to affect.
     * @param index   The index of the command (the meaning depends on the command).
     */
    public void writePushPop(CodeParser.C_TYPE command, String segment, int index) {
        switch (command) {
            case C_PUSH:
                switch (segment) {
                    case "constant":
                        asmCode.add("@" + index);
                        asmCode.add("D = A");
                        writePushDToStack();
                        break;
                    case "local":
                    case "argument":
                    case "this":
                    case "that":
                    case "pointer":
                    case "temp":
                        String reg;
                        if (segment.equals("pointer") || segment.equals("temp")) {
                            reg = "A";
                        } else {
                            reg = "M";
                        }
                        asmCode.add("@" + segSymbolMap.get(segment));
                        asmCode.add("D = " + reg);
                        asmCode.add("@" + index);
                        asmCode.add("D = D + A");
                        asmCode.add("A = D");
                        asmCode.add("D = M");
                        writePushDToStack();
                        break;
                    case "static":
                        asmCode.add("@" + fileName + "" + index);
                        asmCode.add("D = M");
                        writePushDToStack();
                }
                break;
            case C_POP:
                switch (segment) {
                    case "local":
                    case "argument":
                    case "this":
                    case "that":
                    case "pointer":
                    case "temp":
                        String reg;
                        if (segment.equals("pointer") || segment.equals("temp")) {
                            reg = "A";
                        } else {
                            reg = "M";
                        }
                        writeGetTopmost("poped");
                        asmCode.add("@" + segSymbolMap.get(segment));
                        asmCode.add("D = " + reg);
                        asmCode.add("@" + index);
                        asmCode.add("D = D + A");
                        asmCode.add("@tempAddress");
                        asmCode.add("M = D");
                        asmCode.add("@poped");
                        asmCode.add("D = M");
                        asmCode.add("@tempAddress");
                        asmCode.add("A = M");
                        asmCode.add("M = D");
                        break;
                    case "static":
                        writeGetTopmost("poped");
                        asmCode.add("@poped");
                        asmCode.add("D = M");
                        asmCode.add("@" + fileName + "" + index);
                        asmCode.add("M = D");
                }
                break;
        }
    }

    /**
     * Sets the value to the variable.
     *
     * @param variable The name of the variable.
     * @param value    The value to assign to the variable.
     */
    private void writeSetValue(String variable, int value) {
        writeSetD(String.valueOf(value), "A");
        asmCode.add("@" + variable);
        asmCode.add("M = D");
    }

    /**
     * Writes src to the D register.
     *
     * @param src      a name of a variable or a value.
     * @param register the address or the content, A or M respectively.
     */
    private void writeSetD(String src, String register) {
        asmCode.add("@" + src);
        asmCode.add("D = " + register);
    }

    /**
     * Pops the topmost element of the stack into a variable var.
     *
     * @param varName The name of the variable to pop the topmost value into.
     */
    private void writeGetTopmost(String varName) {
        asmCode.add("@SP");
        asmCode.add("A = M - 1");
        asmCode.add("D = M");
        asmCode.add("@" + varName);
        asmCode.add("M = D");
        asmCode.add("@SP");
        asmCode.add("M=M-1");
    }

    /**
     * Writes the assembly code to an output asm file.
     * @param outputName The name of the output file.
     */
    public void writeCodeToFile(String outputName) {
        File codeFile = new File(outputName + ".asm");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(codeFile))) {
            for (String codeLine : asmCode) {
                writer.write(codeLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Writes the bootstrap code.
     */
    public void writeInit() {
        writeSetValue("SP", 261);
        segSymbolMap.put("local", "LCL");
        segSymbolMap.put("argument", "ARG");
        segSymbolMap.put("this", "THIS");
        segSymbolMap.put("that", "THAT");
        segSymbolMap.put("pointer", "R3");
        segSymbolMap.put("temp", "R5");
        writeGoto("Sys.init");


    }

    /**
     * Writes a label to the code.
     * @param label The label to write.
     */
    public void writeLabel(String label) {
        asmCode.add((currFuncName.equals("")) ? "(" + label + ")" : "(" + currFuncName + "$" + label + ")");
    }

    /**
     * Writes the translation of the goto command.
     * @param label The label to go to.
     */
    public void writeGoto(String label) {
        asmCode.add("@" + label);
        if (label.equals("RET")) {
            asmCode.add("A = M");
        }
        asmCode.add("0;JMP");
    }

    /**
     * Writes the translation of the if-goto command.
     * @param label The label to go to if the condition holds.
     */
    public void writeIf(String label) {
        writeGetTopmost("TruthVal");
        writeSetD("TruthVal", "M");
        asmCode.add("@" + label);
        asmCode.add("D;JNE");

    }

    /**
     * Writes the translation of the call command.
     * (Saves all of the data about the calling function and calling goto to the called function).
     * @param functionName The name of the function to call.
     * @param numArguments The number of arguments that were pushed to the stack before the call.
     */
    public void writeCall(String functionName, int numArguments) {
        String label = functionName + "$Return" + lblCounter;

        /* Push return address. */
        writeSetD(label, "A");
        writePushDToStack();

        /* Push local. */
        writeSetD("LCL", "M");
        writePushDToStack();

        /* Push argument. */
        writeSetD("ARG", "M");
        writePushDToStack();

        /* Push this. */
        writeSetD("THIS", "M");
        writePushDToStack();

        /* Push that. */
        writeSetD("THAT", "M");
        writePushDToStack();

        /* ARG = SP - numArguments - 5 */
        writeSetD("SP", "M");
        asmCode.add("@" + numArguments);
        asmCode.add("D = D - A");
        asmCode.add("@5");
        asmCode.add("D = D - A");
        asmCode.add("@ARG");
        asmCode.add("M = D");

        /* LCL = SP */
        writeSetD("SP", "M");
        asmCode.add("@LCL");
        asmCode.add("M = D");

        /* Goto the function. */
        writeGoto(functionName);

        /* Write the label. */
        writeLabel(label);

        lblCounter++;
    }

    /**
     *  sets dest = *(src op num)
     */
    private void WriteSetVarWithValue(String src, String dest, String op, int num) {
        /* D = src */
        writeSetD(src, "M");

        /* D = src op num */
        asmCode.add("@" + num);
        asmCode.add("D = D" + op + " A");

        /* D =  *(src op num) */
        asmCode.add("A = D");
        asmCode.add("D = M");

        /* dest = *(src op num)*/
        asmCode.add("@" + dest);
        asmCode.add("M = D");
    }

    /**
     * Writes the translation of the return keyword.
     * (Restores the data of the caller function).
     */
    public void writeReturn() {
        /* FRAME = LCL */
        writeSetD("LCL", "M");
        asmCode.add("@FRAME");
        asmCode.add("M = D");

        WriteSetVarWithValue("FRAME", "RET", "-", 5);

        /* *ARG = pop() */
        writePushPop(CodeParser.C_TYPE.C_POP, "argument", 0);

        /* SP = ARG + 1 */
        asmCode.add("@ARG");
        asmCode.add("D = M");
        asmCode.add("D = D + 1");
        asmCode.add("@SP");
        asmCode.add("M = D");

        WriteSetVarWithValue("FRAME", "LCL", "-", 4);
        WriteSetVarWithValue("FRAME", "ARG", "-", 3);
        WriteSetVarWithValue("FRAME", "THIS", "-", 2);
        WriteSetVarWithValue("FRAME", "THAT", "-", 1);

        writeGoto("RET");
    }

    /**
     * Declares a function.
     * @param functionName The name of the function to declare.
     * @param numLocals The number of local arguments that the declared function has.
     */
    public void writeFunction(String functionName, int numLocals) {
        writeLabel(functionName);
        for (int i = 0; i < numLocals; i++) {
            writePushPop(CodeParser.C_TYPE.C_PUSH, "constant", 0);
        }
    }
}
