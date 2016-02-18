// Ali Hamodi
// Principles of programming languages

import java.io.File;
import java.util.Scanner;

public class InputFilesManager {

    private final int NUMBER_OF_FIlES = 13;
    private String[] letters = {"a", "b", "c", "d"};  // used with files 10 11 12 13


    public InputFilesManager() {
        generateInputFileNames();
    }

    // #####################################################################
    public void generateInputFileNames() {

        for (int i = 1; i <= NUMBER_OF_FIlES; i++) {
            if (i > 9) {
                // this inner for loop is to generate 10a 10b 10c 10d   11a 11b 11c 11d ... 12a 12b 12c 12d
                for (int j = 0; j < letters.length; j++) {
                    StringBuilder stbr = new StringBuilder();
                    String theNum = String.valueOf(i);
                    stbr.append("striptest-");
                    stbr.append(theNum);

                    stbr.append(letters[j]);
                    stbr.append(".txt");
                    //System.out.println(stbr.toString());
                    inputFilesGenerator(stbr.toString());
                }

            } else {
                StringBuilder stbr = new StringBuilder();
                String theNum = String.valueOf(i);
                stbr.append("striptest-");
                stbr.append(theNum);

                stbr.append(".txt");
                //System.out.println(stbr.toString());
                inputFilesGenerator(stbr.toString());
            }
        } // end of for

        // testing files individually
        //inputFilesGenerator("striptest-13a.txt");
    }

    // #####################################################################
    public void inputFilesGenerator(String str) {

        try {
            File file = new File(str);
            Scanner scan = new Scanner(file);
            CodeStripper stripper = new CodeStripper(file, scan);

        } catch (Exception e) {

            System.out.println(""+ str + " Input file was not found ");
            //e.printStackTrace();
        }

    }

}