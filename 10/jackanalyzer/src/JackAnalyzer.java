import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JackAnalyzer {

    public static void main(String[] args) {
        File file = new File(args[0]);
        String[] splittedPath = args[0].split("/");

        if (file.isDirectory()) {
            //TODO check "\\"
            File[] listOfFiles = file.listFiles();

            assert (listOfFiles != null);
            for (File jackFile : listOfFiles) {
                if (jackFile.isFile() && jackFile.getPath().endsWith(".jack")) {

                    String outputName = jackFile.getPath().substring(0, jackFile.getPath().lastIndexOf("."))+".xml";
                    String inputName = jackFile.getPath();

                    File outPutFile = new File(outputName);

                    File inPutFile = new File(inputName);

                    runTranslator(outPutFile, inPutFile);
                }
            }

            /*the file is not a directory */
        } else {

            String outPutPath = args[0].substring(0, args[0].lastIndexOf("."))+".xml";
            String inputName =splittedPath[splittedPath.length-1].split("\\.")[0];
            String inputPath = args[0];

            File outPutFile = new File(outPutPath);
            File inPutFile = new File(inputPath);
            runTranslator(outPutFile,inPutFile);

        }

    }

    /**
     * Runs the translator on the file and write it to the output file.
     * @param outPutFile The input file to translate.
     * @param inPutFile The output file.
     */
    private static void runTranslator(File outPutFile, File inPutFile) {

        JackTokenizer tk = new JackTokenizer(inPutFile);
        System.out.println(tk.tokens);
        CompilationEngine ce = new CompilationEngine(inPutFile,outPutFile);
        tk.tokens=null;
        ce.XMLFile = null;
    }


}
