import java.io.File;
import java.util.ArrayList;

/**
 * The main class, responsible for managing the reading of the given path using the parser and translating it to a
 * file using the writer.
 */
public class Main {
    private static ArrayList<String> asmCode = new ArrayList<>();
    private static CodeWriter cw;
    /**
     * The main function, if the path is of a directory- runs the translator on all the files in the given directory.
     * Otherwise, if the given path is of a file- runs the translator on the file.
     * @param args An array that is supposed to contain a path.
     */
    public static void main(String[] args) {
        File file = new File(args[0]);
        String outputName;
        if (file.isDirectory()) {
            outputName = file.getPath() + "/" + file.getName();
            File[] listOfFiles = file.listFiles();

            assert (listOfFiles != null);
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getPath().endsWith(".vm")) {
                    runTranslator(listOfFiles[i].getName().split("\\.")[0], listOfFiles[i].getPath());
                }
            }
        } else {

            String[] splittedPath = args[0].split("/");
            outputName = args[0].substring(0, args[0].lastIndexOf("."));
            runTranslator(splittedPath[splittedPath.length-1].split("\\.")[0],args[0]);
        }
        cw.writeCodeToFile(outputName);
    }

    /**
     * Runs the translator on the file with the given name.
     * @param fileName The name of the file to translate.
     * @param filePath The path of the file.
     */
    private static void runTranslator(String fileName, String filePath) {
        CodeParser cp = new CodeParser(filePath);
        cw = new CodeWriter(fileName, asmCode);
        while (cp.hasMoreCommands()) {
            CodeParser.C_TYPE commandType = cp.commandType();
            switch (commandType) {
                case C_ARITHMETIC:
                    cw.writeArithmetic(cp.getArg1());
                    break;

                case C_PUSH: case C_POP:
                    cw.writePushPop(commandType, cp.getArg1(), Integer.parseInt(cp.getArg2()));
                    break;
            }
            cp.advance();
        }
    }
}
