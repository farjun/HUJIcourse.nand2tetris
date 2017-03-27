import java.io.File;


class CompilationEngine {
    //Segment Commands
    private static final String  CONST= "const",ARG = "arg" ,LOCAL = "local",STATIC = "static";
    private static final String THIS = "this", THAT = "that", POINTER = "pointer", TEMP = "temp";
    private static final String MUL = "Math.multiply 2",DIV ="Math.div 2";

    //Arithmetic commands
    private static final String ADD = "add", SUB = "sub", NEG =  "neg",EQ = "eq", GT = "gt",LT = "lt",AND = "and";
    private static final String OR = "or", NOT = "not";

    private JackTokenizer jackTokenizer;
    private SymbolTable symbolTable;
    private VMWriter writer;

    private int labelCounter = 0;

    /**
     * Creates a new compilation engine with the
     * given input and output
     * @param inputfile - the input file of the current class
     */
    CompilationEngine(File inputfile, File outputFile) {
        jackTokenizer = new JackTokenizer(inputfile);
        symbolTable = new SymbolTable(jackTokenizer.getClassName());
        writer = new VMWriter(outputFile);
        compileClass();
        writer.close();

    }
//------------------Helper funcs---------------------------

    /**
     * @return a new label
     */
    String getNewlabel(){
        labelCounter++;
        return "label" + labelCounter;
    }

    /**
     * advance the tokenizer
     */
    private void advance() {
        System.out.println("Tits!!!!!");
        jackTokenizer.advance();
    }



//----------------compile class------------------------------
    /**
     * Compiles a complete class.
     */
    void compileClass() {

        //write class
        //advance();

        //write identifier
       // advance();

        //write "{"
        //advance();

        compileClassVarDec();

        while (jackTokenizer.getToken().equals("constructor") || jackTokenizer.getToken().equals("function") ||
                jackTokenizer.getToken().equals("method")) {
            compileSubroutine();
        }

        //compile "}"
       // advance();

    }

    /**
     * Compiles a static declaration or a field
     * declaration.
     */
    void compileClassVarDec() {
        while ((jackTokenizer.getToken().equals("static") || jackTokenizer.getToken().equals("field"))) {

            //compile keyword "static" or "field"
            //advance();

            //compile type
            //advance();

            //compile varName
            //advance();

            while (jackTokenizer.getToken().equals(",")) {
                //compile ","
                //advance();
                //compile another varName
                //advance();
            }

            //compile ";"
            //advance();

        }
    }

    /**
     * Compiles a complete method, function, or
     * constructor.
     */
    void compileSubroutine() {
        //compile keyword
        //advance();

        //compile return type keyword
        //advance();

        //compile identifier
        //advance();


        //compile '(' parameters
        //advance();

        //compile parameter list
        compileParameterList();

        //compile ')' parameters
        //advance();


        //write "{"
        //advance();

        while (jackTokenizer.getToken().equals("var")) {
            compileVarDec();
        }

        //compile the functions body
        compileStatements();

        //compile '}' parameters
        //advance();


    }

    /**
     * Compiles a (possibly empty) parameter list,
     * not including the enclosing “()”.
     */
    void compileParameterList() {
        while (!jackTokenizer.getToken().equals(")")) {
            advance();
        }

    }


    /**
     * Compiles a sequence of statements, not
     * including the enclosing “{}”.
     */
    void compileStatements() {
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
    }

    /**
     * Compiles a var declaration.
     */
    void compileVarDec() {
        //compile var
        //advance();

        do {
            //compile initializer
            //advance();

            //compile varName
            //advance();

        } while (jackTokenizer.getToken().equals(","));

        //compile ";"
        //advance();


    }

    /**
     * Compiles a do statement.
     */
    void compileDo() {
        //compile "do"
        //advance();

        //compile "var Name"
        //advance();

        compileTermHelper();
        
        //compile ";"
        //advance();

    }

    /**
     * Compiles a let statement.
     */
    void compileLet() {
        //write let
        //advance();

        //write identifier
        //advance();

        if (jackTokenizer.getToken().equals("[")) {
            //write "["
            //advance();
            compileExpression();

            //write "]"
            //advance();
        }

        //write "="
        //advance();

        compileExpression();

        //write ";"
        //advance();

    }

    /**
     * Compiles a while statement.
     */
    void compileWhile() {

        //compile while
        //advance();

        //compile "("
        //advance();

        compileExpression();

        //compile ")"
        //advance();

        //compile "{"
        //advance();

        compileStatements();

        //compile "}"
        //advance();

    }

    /**
     * Compiles a return statement.
     */
    void compileReturn() {


        //compile return
       //advance();

        //if the next token is not ";" then compile the expression
        if (!jackTokenizer.getToken().equals(";"))
            compileExpression();

        //compile ;
        //advance();
    }

    /**
     * Compiles an if statement,
     * possibly with a trailing else clause.
     */
    void compileIf() {

        //compile if
        //advance();

        //compile "("
        //advance();

        compileExpression();

        //compile ")"
        //advance();

        //compile "{"
        //advance();

        compileStatements();

        //compile "}"
        //advance();

        if (jackTokenizer.getToken().equals("else")) {
            //compile "else"
            //advance();

            //compile "{"
            //advance();

            compileStatements();

            //compile "}"
            //advance();
        }

    }

    /**
     * Compiles an expression.
     */
    void compileExpression() {
        CompileTerm();

        boolean nextTokenIsOp = true;
        while(nextTokenIsOp) {
            //if there is a "term op term" case
            switch (jackTokenizer.getToken()) {
                case "+":
                    writer.writeArithmetic(ADD);
                    break;
                case "-":
                    writer.writeArithmetic(SUB);
                    break;
                case "*":
                    writer.writeArithmetic(MUL);
                    break;
                case "/":

                    break;
                case "&amp;":
                case "|":
                case "&lt;":
                case "&gt;":
                case "=":
                    break;

                default:
                    nextTokenIsOp = false;
                    break;
            }
            //compile symbol
            //advance();
            CompileTerm();
        }
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

        //compile unary op
        if(jackTokenizer.getToken().equals("-")||jackTokenizer.getToken().equals("~")) {

            String unaryop = jackTokenizer.getToken();

            //advance "-"or "~"
            advance();

            //compile another term
            CompileTerm();

            switch (unaryop){
                case "-":
                    writer.writeArithmetic(NEG);
                    break;
                case "~":
                    writer.writeArithmetic(NOT);
                    break;
            }

            //break
            return;
        }

        //if it is in the form of ("expression")
        if(jackTokenizer.getToken().equals("(")){
            //advance "("
            advance();

            //compile the expression
            compileExpression();

            //advance ")"
            advance();
            //break;
            return;
        }

        switch (jackTokenizer.getTokenKeyword()) {
            case "stringConstant":
                writeStringConst();
                advance();
                break;

            case "integerConstant":
                writer.writePush(CONST,jackTokenizer.intVal());

            case "keyword":
                writeTermKeyWord();
                advance();
                break;

            case "identifier":
                //compile identifier
                //unknown var
                if(symbolTable.kindOf(jackTokenizer.getToken()).equals("none")){
                    compileTermHelper();
                }else {
                    writer.writePush(symbolTable.kindOf(jackTokenizer.getToken()), symbolTable.indexOf(jackTokenizer.getToken()));
                }
                //advance identifier
                advance();
                break;
        }

    }

    private void compileTermHelper() {
        //save the last token
        String className;
        String funcName;
        int variableCounter;

        String temp = jackTokenizer.getToken();

        //advance func \ class \ var name
        advance();

        switch (jackTokenizer.getToken()) {
            case ".":
                className = temp;

                //advance "."
                advance();

                funcName = jackTokenizer.getToken();

                //"funcName"
                advance();

                //advance "("
                advance();

                //count the variables
                variableCounter = jackTokenizer.countVars();

                //compile the funcs expression list
                CompileExpressionList();

                //temp token is the classes name
                writer.writeFunction(className+"."+funcName,variableCounter);

                //advance ")"
                advance();
                break;

            case "(":

                className = symbolTable.getClassName();

                funcName = temp;

                //advance "("
                advance();

                //count the variables
                variableCounter = jackTokenizer.countVars();

                //compile the funcs expression list
                CompileExpressionList();

                //write the function call
                writer.writeFunction(className+"."+funcName,variableCounter);

                //advance ")"
                advance();
                break;
            case "[":
                // Push the array base
                writer.writePush(symbolTable.kindOf(temp), symbolTable.indexOf(temp));
                //advance "["
                advance();
                // push the array index
                compileExpression();

                // add the location in the stack
                writer.writeArithmetic(ADD);


                compileExpression();

                //advance "]"
                advance();
                break;
        }
    }

    /**
     * writes Term keyWord (FALSE,TRUE OR NULL)
     */
    private void writeTermKeyWord(){
        switch (jackTokenizer.getToken()){
            case "false":
            case "null":
                writer.writePush(CONST,0);
                break;
            case "true":
                writer.writePush(CONST,1);
                writer.writeArithmetic(NEG);
                break;

        }
    }

    /**
     * writes string const
     */
    private void writeStringConst(){
        int stringLenght = jackTokenizer.getToken().length();
        writer.writePush(CONST,stringLenght);
        writer.writeFunction("String.new",1);
        int charASCI;

        for(char c : jackTokenizer.getToken().toCharArray()){
            charASCI = (int) c;
            writer.writePush(CONST,charASCI);
            writer.writeFunction("String.appendChar",2);
        }
    }

    /**
     * Compiles a (possibly empty) comma separated
     * list of expressions.
     */
    void CompileExpressionList() {

        if (!jackTokenizer.getToken().equals(")")) {
            compileExpression(); //write the expression
        }
        //while there are more expressions
        while (jackTokenizer.getToken().equals(",")) {
            //advance ","
            advance();
            //if there are more expressions to write
            compileExpression();
        }

    }
}