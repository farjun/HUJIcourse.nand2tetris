import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is the parser class, it's purpose is to read the given code file into an array and remove all
 * the white spaces and comments.
 */
public class CodeParser {
    private static final String EMPTY_STRING = "", WHITE_SPACE = " ", COMMENT_FORMAT = "//.*";

    /**
     * @param filePath The path of file to read.
     * @return A string array containing the code lines from the code file.
     */
    static String[] getCodeLines(String filePath) throws IOException {
        return processCode(getCode(filePath));
    }

    /**
     * Reads all the text from the given text file.
     *
     * @param filePath The path of the file to read.
     * @return A string array that contains the text from the file line by line.
     */
    private static String[] getCode(String filePath) throws IOException {
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
    private static String[] processCode(String[] codeArray) {
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
    private static String processLine(String line) {
        line = line.replace(WHITE_SPACE, EMPTY_STRING); // Removes all the spaces from the string.

        /*
        Removes all the comments from the string.
        Note: comment is of form //...
        */
        String[] temp = line.split(COMMENT_FORMAT);

        line = (temp.length != 0) ? temp[0] : EMPTY_STRING;

        return line;
    }

}
