import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class JackTokenizer {

    private static final String EMPTY_STRING = "", WHITE_SPACE = " ", LINE_COMMENT_FORMAT = "//";
    private static final String API_COMMENT = "/*", COMMENT_UNTIL_CLOSING = "/**", COMMENT_CLOSING = "*/";
    private static final String SPLIT_ELEMENTS = "\\s";
    private static final String[] symbles = {"\\{", "\\}", "\\(", "\\)", "\\[", "\\]", "\\.", "\\,", "\\;", "\\+", "\\-", "\\*"
            , "\\/", "\\&", "\\|", "\\<", "\\>", "\\=", "\\~"};

    /*
    the jack file
     */
    private File jackFile;

    /*
    the tokens created from the file
     */
    ArrayList<String> tokens = new ArrayList<>();

    /*
    the saved jack file we will process
     */
    private ArrayList<String> unproccedFile = new ArrayList<>();

    /*
    the saved jack file we will process
     */
    private ArrayList<String> proccedFile = new ArrayList<>();

    /*
    the index that points to the current token
     */
    private int currentTokenIndex = 0;

    private enum TokenKind {

        keyword("class", "constructor", "function", "method", "field", "static",
                "var", "int", "char", "boolean", "void", "true", "false", "null", "this",
                "let", "do", "if", "else", "while", "return"),
        symbol("{", "}", "(", ")", "[", "]", ".", ",", ";", "+", "-", "*"
                , "/", "&amp;", "|", "&lt;", "&gt;", "=", "~"),
        identifier,
        integerConstant,
        stringConstant;

        private String[] commandText;

        TokenKind(String... args) {
            commandText = args;
        }

        /**
         * Finds which type is described with the given string.
         *
         * @param s The string to check.
         * @return The type of command that is represented by the given string.
         */
        public static TokenKind getType(String s) {

            for (TokenKind type : TokenKind.values()) {
                if (type.contains(s)) {
                    return type;
                }
            }

            Pattern p = Pattern.compile("\\s*\\d+\\s*");
            if (p.matcher(s).matches()) {
                return integerConstant;
            }

            //Pattern p = Pattern.compile();
            p = Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]*$");
            if (p.matcher(s).matches())
                return identifier;

            return stringConstant;
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

    JackTokenizer(File jackfile) {
        jackFile = jackfile;
        processFileToArray();
        removeCommentsFromCode();
        generateTokens();
    }


    /**
     * Reads all the text from the given text file.
     */
    private void processFileToArray() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jackFile));
            /* while the files not empty*/
            String line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty())
                    unproccedFile.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("gal the file is Not Open");
        }
    }

    /**
     * iterates over the file code and does MAGIC.. you know.. stuff
     * like "abdra parsara! no commentitos"
     */
    private void removeCommentsFromCode() {
            String allCode = "";
            for (String line : unproccedFile) {
                allCode += line + "\n";
            }

            char codeArray[] = allCode.toCharArray();

            boolean inString = false;
            char c, nc;
            for (int i = 0; i < codeArray.length; i++) {
                c = codeArray[i];
                if (c == '\"'){
                    inString = !inString;
                }

                if (inString) {
                    continue;
                }


                if (c == '/') {
                    nc = codeArray[i + 1];
                    if (nc == '/') {
                        while (nc != '\n') {
                            nc = codeArray[i + 1];
                            codeArray[i] = 1;
                            i++;
                        }
                        codeArray[i] = 1;
                        continue;
                    }

                    if (nc == '*') {
                        codeArray[i] = 1;
                        do{
                            i++;
                            c = codeArray[i];
                            if (c == '\n') {
                                codeArray[i] = 1;
                                continue;
                            }
                            codeArray[i] = 1;
                        } while (!(codeArray[i + 1] == '*' && codeArray[i + 2]
                                == '/'));
                        if (i + 1 < codeArray.length) {
                            codeArray[i + 1] = 1;
                        }
                        if (i + 2 < codeArray.length) {
                            codeArray[i + 2] = 1;
                        }
                    }
                }
            }

            String codeStr = "";
            for (char t : codeArray) {
                if (t == 1) {
                    codeStr += "\n";
                }
                else if (t == ';') {
                    codeStr += ";\n";
                }

                else if (t != 0) {
                    codeStr += String.valueOf(t);
                }
            }
        String arr[] = codeStr.split("\n");
        for (String s : arr) {
            if (!s.isEmpty()) {
                proccedFile.add(s);
            }
        }
    }


    private String[] splitLine(String line) {

        for (String symbol : symbles) {
            line = line.replaceAll(symbol, " " + symbol + " ");
        }
        return line.split(SPLIT_ELEMENTS);

    }

    /**
     * creates the string const split we need to add to the token array
     *
     * @param line The line with the string const
     * @return a splited line with the string const
     */
    private String[] handleConstString(String line) {

        String stringConst;
        int startOfString = line.indexOf('\"');
        int endOfString = line.lastIndexOf('\"');

        stringConst = line.substring(startOfString + 1, endOfString);
        String[] beforeStringConst = splitLine(line.substring(0, startOfString));
        String[] afterStringConst = splitLine(line.substring(endOfString + 1));

        String[] returnTokens = new String[beforeStringConst.length + afterStringConst.length + 1];

        System.arraycopy(beforeStringConst, 0, returnTokens, 0, beforeStringConst.length);
        returnTokens[beforeStringConst.length] = stringConst;
        System.arraycopy(afterStringConst, 0, returnTokens, beforeStringConst.length + 1, afterStringConst.length);

        return returnTokens;
    }

    private void generateTokens() {
        Boolean flag = false;
        String[] splittedLine;

        for (String line : proccedFile) {
              /* if we are in a string*/
            if (line.matches(".*\".*")) {
                flag = true;
            }

            if (!flag) {
                splittedLine = splitLine(line);
            } else { // dont split lines
                splittedLine = handleConstString(line);
                flag = false;
            }

            for (String token : splittedLine) {
                token = token.trim();
                if (!token.isEmpty())
                    switch (token) {
                        case "<":
                            tokens.add("&lt;");
                            break;
                        case ">":
                            tokens.add("&gt;");
                            break;
                        case "\"":
                            tokens.add("&quot;");
                            break;
                        case "&":
                            tokens.add("&amp;");
                            break;
                        default:
                            tokens.add(token);
                            break;
                    }

            }

        }
    }

    String getClassName(){
        return tokens.get(1);
    }

    int countVars(){
        int curLocation = currentTokenIndex;
        int counter = 0;

        while(!tokens.get(curLocation).equals(")")){
            if(tokens.get(curLocation).equals(","))
                counter++;

            curLocation++;
        }

        return counter;
    }

    /**
     * checks if the Array has more tokens
     * @return True if we have more tokens or false otherwise
     */
    boolean hasMoreTokens() {
        return currentTokenIndex < tokens.size() - 1;
    }

    /**
     * advances the current token by 1
     * does so only if the Array has more tokens
     */
    void advance() {
        if (hasMoreTokens())
            currentTokenIndex++;
    }

    String getToken() {
        return tokens.get(currentTokenIndex);
    }


    String getTokenKeyword() {
        TokenKind tokenKind = TokenKind.getType(tokens.get(currentTokenIndex));
        return tokenKind.toString();
    }


    /**
     * @return The current Token Type
     */
    TokenKind tokenType() {
        return TokenKind.getType(tokens.get(currentTokenIndex));
    }


    String keyWord() {
        return tokens.get(currentTokenIndex);
    }

    /**
     * @return the Token if its a symble
     */
    char symble() {
        return tokens.get(currentTokenIndex).charAt(0);
    }

    /**
     * @return a token if its an integer
     */
    int intVal() {
        return Integer.parseInt(tokens.get(currentTokenIndex));
    }

    String stringVal() {
        return tokens.get(currentTokenIndex);
    }


}
