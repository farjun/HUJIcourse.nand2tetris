import java.io.*;


public class VMWriter {



    private BufferedWriter VMfile;

    /**
     * VM writer constructor
     * @param outPutFile sets the output file
     */
    VMWriter(File outPutFile){
        try {
            VMfile = new BufferedWriter(new FileWriter(outPutFile));
        }catch (IOException e){
            System.out.println("problem VMwriter constructor");
        }
    }

    /**
     * write a push command
     * @param Segment (CONST, ARG, LOCAL, STATIC, THIS, THAT, POINTER, TEMP)
     * @param index the index
     */
    void writePush(String Segment,int index){
        try {
            VMfile.write("push"+" "+Segment +" "+ index);
        }catch (IOException e){
            System.out.println("problem writePush");
        }
    }

    /**
     * write a pop command
     * @param Segment (CONST, ARG, LOCAL, STATIC, THIS, THAT, POINTER, TEMP)
     * @param index the index
     */
    void  writePop (String Segment,int index){
        try {
            VMfile.write("pop"+" "+Segment +" "+ index);
        }catch (IOException e){
            System.out.println("problem writePop");
        }
    }

    /**
     * writes an arithmetic command
     * @param command - (ADD, SUB, NEG, EQ, GT, LT, AND, OR, NOT)
     */
    void writeArithmetic(String command){
        try {
            VMfile.write(command);
        }catch (IOException e){
            System.out.println("problem writeArithmetic");
        }
    }


    /**
     * writes a label
     * @param label - the label
     */
    void WriteLabel(String label){
        try {
            VMfile.write(label);
        }catch (IOException e){
            System.out.println("problem WriteLabel");
        }
    }

    /**
     * writes goto "label"
     * @param label the label to go to
     */
    void WriteGoto(String label){
        try {
            VMfile.write("goto"+" "+label);
        }catch (IOException e){
            System.out.println("problem WriteGoto");
        }
    }

    /**
     * writes if-goto command
     * @param label the label to go to
     */
    void WriteIf(String label){
        try {
            VMfile.write("if-goto"+" "+label);
        }catch (IOException e){
            System.out.println("problem WriteIf");
        }
    }

    /**
     * Writes a VM call command.
     * @param name - name of the func
     * @param nArgs Number of arguments
     */
    void writeCall(String name,int nArgs){
        try {
            VMfile.write("call"+" "+name+" "+nArgs);
        }catch (IOException e){
            System.out.println("problem writeCall");
        }
    }

    /**
     * Writes a VM function command.
     * @param name the funcs name
     * @param nLocals the number of arguments
     */
    void writeFunction(String name,int nLocals){
        try {
            VMfile.write("call"+" "+name+" "+nLocals);
        }catch (IOException e){
            System.out.println("problem writeFunction");
        }
    }

    /**
     * writes return
     */
    void writeReturn(){
        try {
            VMfile.write("return");
        }catch (IOException e){
            System.out.println("problem writeReturn");
        }
    }

    /**
     * closes the file
     */
    void close(){
        try {
            VMfile.close();
        }catch (IOException e){
            System.out.println("problem close");
        }
    }

}
