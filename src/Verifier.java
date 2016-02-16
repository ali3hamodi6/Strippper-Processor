import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Verifier {

    String handFileName;     // Files created by hand
    String outputFileName;
    Scanner handScanner;
    Scanner outputScanner;
    File handFile;
    File outputFile;

    public Verifier(String in, String out) {
        handFileName = in;
        outputFileName = out;
        try {
            handFile = new File(handFileName);
            outputFile = new File(outputFileName);
            handScanner = new Scanner(handFile);
            outputScanner = new Scanner(outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         compare();

    }

    private void compare() {
       //
    }


}
