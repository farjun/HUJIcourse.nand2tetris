import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JackCompiler {

    public static void main(String[] args) {

        File inputFile = new File(args[0]);

        if (inputFile.isDirectory()) {
            //TODO check "\\"
            File[] listOfFiles = inputFile.listFiles();
            assert (listOfFiles != null);

            for (File jackFile : listOfFiles) {
                if (jackFile.isFile() && jackFile.getPath().endsWith(".jack")) {

                    String outputFilePath = jackFile.getPath().substring(0, jackFile.getPath().lastIndexOf("."))+".vm";
                    File outPutFile = new File(outputFilePath);

                    runTranslator(outPutFile, jackFile);
                }
            }

            /*the file is not a directory */
        } else if(inputFile.isFile()) {

            String outPutPath = args[0].substring(0, args[0].lastIndexOf("."))+".vm";

            File outPutFile = new File(outPutPath);
            runTranslator(outPutFile,inputFile);

        }

    }

    /**
     * Runs the translator on the file and write it to the output file.
     * @param outPutFile The input file to translate.
     * @param inPutFile The output file.
     */
    private static void runTranslator(File outPutFile, File inPutFile) {
        CompilationEngine ce = new CompilationEngine(inPutFile,outPutFile);

    }


}
