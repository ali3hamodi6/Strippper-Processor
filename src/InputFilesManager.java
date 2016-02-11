import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class InputFilesManager {

    private final int NUMBER_OF_FIlES = 13;
    private String[] letters = {"a", "b", "c", "d"};


    public InputFilesManager() {
        generateInputFileNames();
    }

    public void generateInputFileNames() {

        int NUMBER_OF_MY_FILES = 13;
        String[] letters = {"a", "b", "c", "d"};

        for (int i = 1; i <= NUMBER_OF_MY_FILES; i++) {
            StringBuilder stbr = new StringBuilder();
            String theNum = String.valueOf(i);
            stbr.append("striptest-");
            stbr.append(theNum);

            if (i == 10) {
                stbr.append("a");
            }
            if (i == 11) {
                stbr.append("a");
            }
            if (i == 12) {
                stbr.append("a");
            }
            if (i == 13) {
                stbr.append("a");
            }

            stbr.append(".txt");
            System.out.println("input file is "+stbr.toString());
            inputFilesGenerator(stbr.toString());

        }


    }


    public void inputFilesGenerator(String str) {

        try {
            File file = new File(str);
            BufferedReader br = new BufferedReader(new FileReader(file));
            CodeStripper stripper = new CodeStripper(file, br);

        } catch (Exception e) {

            System.out.println("Input file issue #################################################");
            e.printStackTrace();
        }


    }


}
