import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CompilationEngine {

     BufferedWriter XMLFile;

    private JackTokenizer jackTokenizer;

    private int spaces = 0;


    /**
     * Creates a new compilation engine with the
     * given input and output
     * @param inputfile - the input file of the current class
     */
    CompilationEngine(File inputfile, File OutputFile) {
        try {
            this.XMLFile = new BufferedWriter(new FileWriter(OutputFile));
            jackTokenizer = new JackTokenizer(inputfile);
            compileClass();
            XMLFile.close();
            } catch(
            IOException e)
            {
                System.out.println("gal fix me");
            }
    }


    private void writeToFile(String toWrite) {
        //checks if the spaces are added or removed
        if (toWrite.contains("/"))
            spaces--;

        try {
            for (int i = 0; i < spaces; i++)
                XMLFile.write("  ");
            XMLFile.write(toWrite);
            XMLFile.write("\n");
            XMLFile.flush();
        } catch (IOException e) {
            System.out.println("error in writeToFile");
        }

        if (!toWrite.contains("/"))
            spaces++;

    }

    /**
     * writes the current token pattern
     */
    private void writePattern() {
        try {
            for (int i = 0; i < spaces; i++) {
                XMLFile.write("  ");
            }
            XMLFile.write("<" + jackTokenizer.getTokenKeyword() + "> ");
            XMLFile.write(jackTokenizer.getToken());
            XMLFile.write(" </" + jackTokenizer.getTokenKeyword() + ">");
            XMLFile.newLine();
            XMLFile.flush();
        } catch (IOException e) {
            System.out.println("cant write to file");
        }
    }

    private void writeAndAdvance() {
        writePattern();
        jackTokenizer.advance();
    }

    /**
     * Compiles a complete class.
     */
    void compileClass() {
        writeToFile("<class>");
        //write class
        writeAndAdvance();

        //write identifier
        writeAndAdvance();

        //write "{"
        writeAndAdvance();

        compileClassVarDec();

        while (jackTokenizer.getToken().equals("constructor") || jackTokenizer.getToken().equals("function") ||
                jackTokenizer.getToken().equals("method")) {
            compileSubroutine();
        }


        writeAndAdvance();

        writeToFile("</class>");
    }

    /**
     * Compiles a static declaration or a field
     * declaration.
     */
    void compileClassVarDec() {
        while ((jackTokenizer.getToken().equals("static") || jackTokenizer.getToken().equals("field"))) {
            writeToFile("<classVarDec>");
            //compile keyword "static" or "field"
            writeAndAdvance();

            //compile type
            writeAndAdvance();

            //compile varName
            writeAndAdvance();

            while (jackTokenizer.getToken().equals(",")) {
                //compile ","
                writeAndAdvance();
                //compile another varName
                writeAndAdvance();
            }

            //compile ";"
            writeAndAdvance();
            writeToFile("</classVarDec>");
        }
    }

    /**
     * Compiles a complete method, function, or
     * constructor.
     */
    void compileSubroutine() {
        writeToFile("<subroutineDec>");
        //compile keyword
        writeAndAdvance();

        //compile return type keyword
        writeAndAdvance();

        //compile identifier
        writeAndAdvance();


        //compile '(' parameters
        writeAndAdvance();

        //compile parameter list
        compileParameterList();

        //compile ')' parameters
        writeAndAdvance();

        writeToFile("<subroutineBody>");

        //write "{"
        writeAndAdvance();

        while (jackTokenizer.getToken().equals("var")) {
            compileVarDec();
        }

        //compile the functions body
        compileStatements();

        //compile '}' parameters
        writeAndAdvance();


        writeToFile("</subroutineBody>");

        writeToFile("</subroutineDec>");

    }

    /**
     * Compiles a (possibly empty) parameter list,
     * not including the enclosing “()”.
     */
    void compileParameterList() {
        writeToFile("<parameterList>");

        while (!jackTokenizer.getToken().equals(")")) {
            writeAndAdvance();
        }

        writeToFile("</parameterList>");
    }


    /**
     * Compiles a sequence of statements, not
     * including the enclosing “{}”.
     */
    void compileStatements() {
        writeToFile("<statements>");
        boolean flag = true;
        while (flag) {
            switch (jackTokenizer.getToken()) {
                case "let":
                    compileLet();
                    break;
                case "do":
                    compileDo();
                    break;
                case "while":
                    compileWhile();
                    break;
                case "if":
                    compileIf();
                    break;
                case "return":
                    compileReturn();
                    break;
                default:
                    flag = false;
            }
        }
        writeToFile("</statements>");
    }

    /**
     * Compiles a var declaration.
     */
    void compileVarDec() {
        writeToFile("<varDec>");
        //compile var
        writeAndAdvance();

        do {
            //compile initializer
            writeAndAdvance();

            //compile varName
            writeAndAdvance();

        } while (jackTokenizer.getToken().equals(","));

        //compile ";"
        writeAndAdvance();

        writeToFile("</varDec>");
    }

    /**
     * Compiles a do statement.
     */
    void compileDo() {
        writeToFile("<doStatement>");


        //compile "do"
        writeAndAdvance();

        //compile "var Name"
        writeAndAdvance();

        compileTermHelper();
        
        //compile ";"
        writeAndAdvance();

        writeToFile("</doStatement>");

    }

    /**
     * Compiles a let statement.
     */
    void compileLet() {
        writeToFile("<letStatement>");

        //write let
        writeAndAdvance();

        //write identifier
        writeAndAdvance();

        if (jackTokenizer.getToken().equals("[")) {
            //write "["
            writeAndAdvance();
            compileExpression();

            //write "]"
            writeAndAdvance();
        }

        //write "="
        writeAndAdvance();

        compileExpression();

        //write ";"
        writeAndAdvance();


        writeToFile("</letStatement>");
    }

    /**
     * Compiles a while statement.
     */
    void compileWhile() {
        writeToFile("<whileStatement>");

        //compile while
        writeAndAdvance();

        //compile "("
        writeAndAdvance();

        compileExpression();

        //compile ")"
        writeAndAdvance();

        //compile "{"
        writeAndAdvance();

        compileStatements();

        //compile "}"
        writeAndAdvance();

        writeToFile("</whileStatement>");

    }

    /**
     * Compiles a return statement.
     */
    void compileReturn() {
        writeToFile("<returnStatement>");

        //compile return
        writeAndAdvance();

        //if the next token is not ";" then compile the expression
        if (!jackTokenizer.getToken().equals(";"))
            compileExpression();

        //compile ;
        writeAndAdvance();

        writeToFile("</returnStatement>");
    }

    /**
     * Compiles an if statement,
     * possibly with a trailing else clause.
     */
    void compileIf() {
        writeToFile("<ifStatement>");

        //compile if
        writeAndAdvance();

        //compile "("
        writeAndAdvance();

        compileExpression();

        //compile ")"
        writeAndAdvance();

        //compile "{"
        writeAndAdvance();

        compileStatements();

        //compile "}"
        writeAndAdvance();

        if (jackTokenizer.getToken().equals("else")) {
            //compile "else"
            writeAndAdvance();

            //compile "{"
            writeAndAdvance();

            compileStatements();

            //compile "}"
            writeAndAdvance();
        }

        writeToFile("</ifStatement>");

    }

    /**
     * Compiles an expression.
     */
    void compileExpression() {
        writeToFile("<expression>");
        CompileTerm();

        //if there is a "term op term" case
        switch (jackTokenizer.getToken()) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "&amp;":
            case "|":
            case "&lt;":
            case "&gt;":
            case "=":
                //compile symbol
                writeAndAdvance();

                CompileTerm();
                break;
        }
        writeToFile("</expression>");
    }

    /**
     * Compiles a term. This routine is faced with a
     * slight difficulty when trying to decide
     * between some of the alternative parsing rules.
     * Specifically, if the current token is an
     * identifier, the routine must distinguish
     * between a variable, an array entry, and a
     * subroutine call. A single look-ahead token,
     * which may be one of “[“, “(“, or “.”
     * suffices to distinguish between the three
     * possibilities. Any other token is not part of
     * this term and should not be advanced over.
     */
    void CompileTerm() {
        writeToFile("<term>");
        boolean withbrackets = false;

        if(jackTokenizer.getToken().equals("~")||jackTokenizer.getToken().equals("-")) {
            //compile unary oop
            writeAndAdvance();
            //complile another term
            CompileTerm();

        }
        else {
            //if it is in the form of ("expression")
            if(jackTokenizer.getToken().equals("(")){
                //write "("
                writeAndAdvance();
                //compile the expression
                compileExpression();
                withbrackets=true;
            }

            switch (jackTokenizer.getTokenKeyword()) {
                case "stringConstant":
                case "integerConstant":
                case "keyword":
                    writeAndAdvance();
                    break;
                case "identifier":
                    //compile identifier
                    writeAndAdvance();

                    compileTermHelper();
                    break;
            }
        }

        if(withbrackets)
            //write ")"
            writeAndAdvance();
        writeToFile("</term>");
    }

    private void compileTermHelper() {

        switch (jackTokenizer.getToken()) {
            case ".":
                //write "."
                writeAndAdvance();

                //write "funcName"
                writeAndAdvance();

                //write "("
                writeAndAdvance();

                CompileExpressionList();

                //write ")"
                writeAndAdvance();
                break;
            case "(":
                //write "("
                writeAndAdvance();

                CompileExpressionList();

                //write ")"
                writeAndAdvance();
                break;
            case "[":
                //write "["
                writeAndAdvance();

                compileExpression();

                //write "]"
                writeAndAdvance();
                break;
        }
    }

    /**
     * Compiles a (possibly empty) commaseparated
     * list of expressions.
     */
    void CompileExpressionList() {
        writeToFile("<expressionList>");

        if (!jackTokenizer.getToken().equals(")"))
            compileExpression(); //write the expression

        //while there are more expressions
        while (jackTokenizer.getToken().equals(",")) {
            //compile ","
            writeAndAdvance();
            //if there are more expressions to write
            compileExpression();
        }
        writeToFile("</expressionList>");
    }
}