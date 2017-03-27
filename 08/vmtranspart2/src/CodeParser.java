import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is the parser class, it's purpose is to read the given code file into an array and remove all
 * the white spaces and comments.
 */
public class CodeParser {
    public enum C_TYPE {
        C_ARITHMETIC("add", "sub", "neg", "eq", "gt", "lt", "and", "or", "not"),
        C_PUSH("push"), C_POP("pop"),
        C_LABEL("label"), C_GOTO("goto"),
        C_IF("if-goto"),
        C_FUNCTION("function"),
        C_RETURN("return"),
        C_CALL("call");

        private String[] commandText;
        C_TYPE(String... args) {
            commandText = args;
        }

        /**
         * Finds which type is described with the given string.
         * @param s The string to check.
         * @return The type of command that is represented by the given string.
         */
        public static C_TYPE getType(String s) {
            for (C_TYPE type : C_TYPE.values()) {
                if (type.contains(s)){
                    return type;
                }
            }
            return null;
        }

        /**
         * @param s A string.
         * @return true iff the given string is a command text that represents this command type.
         */
        private boolean contains(String s) {
            for (String ct : commandText) {
                if (ct.equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }
    private static final String EMPTY_STRING = "", WHITE_SPACE = " ", COMMENT_FORMAT = "//.*",
            READ_FILE_ERROR_MESSAGE = "error reading file in path: ";
    private String[] codeArray;
    private int currentLine;
    private int FAIL_VALUE = -1;

    /**
     * The constructor, parses the file with the given path.
     * @param filePath The path of file to read.
     */
    public CodeParser(String filePath) {
        try {
            codeArray = processCode(getCode(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentLine = 0;
    }

    /**
     * Reads all the text from the given text file.
     *
     * @param filePath The path of the file to read.
     * @return A string array that contains the text from the file line by line.
     */
    private String[] getCode(String filePath) throws IOException {
        ArrayList<String> fileText = new ArrayList<>();

        /* Creates the objects that are necessary for reading the file. */
        FileReader reader = new FileReader(filePath);
        BufferedReader textReader = new BufferedReader(reader);
        String line = textReader.readLine();

        /* Reads the lines from the file line by line into the array. */
        while (line != null) {
            fileText.add(line);
            line = textReader.readLine();
        }

        /* Closes the reading objects. */
        textReader.close();
        reader.close();

        return fileText.toArray(new String[fileText.size()]);
    }

    /**
     * Processes the code, removing all comments and white spaces.
     * @param codeArray A String array that contains all the code to process.
     * @return A String array that contains the processed code.
     */
    private String[] processCode(String[] codeArray) {
        ArrayList<String> processedCode = new ArrayList<>();

        /* Removes all empty lines and comments from the code. */
        for (int i = 0; i < codeArray.length; i++) {
            String line = processLine(codeArray[i]);
            if (!line.isEmpty()) {
                processedCode.add(line);
            }
        }
        return processedCode.toArray(new String[processedCode.size()]);
    }

    /**
     * Processes one line of code, removing all white spaces and comments.
     * @param line The code line to process.
     * @return The processed line, might be an empty string if the line didn't include any instruction.
     */
    private String processLine(String line) {
        /*
        Removes all the comments from the string.
        Note: comment is of form //...
        */
        String[] temp = line.split(COMMENT_FORMAT);
        line = (temp.length != 0) ? temp[0] : EMPTY_STRING;
        return line;
    }

    /**
     * @return true iff there are more commands in the file.
     */
    boolean hasMoreCommands() {
        return !(codeArray.length == currentLine);
    }

    /**
     * Advances the current line index, should only be called if hasMoreCommands returns true.
     */
    public void advance() {
        currentLine++;
    }

    public C_TYPE commandType() {
        return C_TYPE.getType(codeArray[currentLine].split(WHITE_SPACE)[0]);
    }

    public String getCurrentLine() {
        return codeArray[currentLine];
    }

    /**
     * @return The first argument of the current command, if the command is arithmetic returns the command
     * itself.
     * Note: Should not be called for a command of type C_RETURN.
     */
    public String getArg1(){
        if (commandType() == C_TYPE.C_ARITHMETIC) {
            return codeArray[currentLine].replaceAll("\\s*", "");
        }
        return codeArray[currentLine].split(WHITE_SPACE)[1].replaceAll("\\s*", "");
    }

    /**
     * @return The second argument of the current command.
     * Note:  Should be called only if the current command is C_PUSH, C_POP, C_FUNCTION, or C_CALL.
     */
    public String getArg2(){
        return codeArray[currentLine].split(WHITE_SPACE)[2].replaceAll("\\s*", "");
    }

}
