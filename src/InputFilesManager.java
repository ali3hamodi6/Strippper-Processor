import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputFilesManager {

    private final int NUMBER_OF_FIlES = 13;
    private String []letters= {"a", "b", "c", "d"};


    public InputFilesManager() {
        generateFileNames();
    }

    public void generateFileNames() {

        //for(int i = 1;i<=NUMBER_OF_FIlES;i++)
       // {
//            StringBuilder stbr = new StringBuilder();
//            int j=0;
//            String theNum = String.valueOf(i);
//            stbr.append("../src/striptest-");
//            stbr.append(theNum);
//
//            if (i > 9 && i<14) {
//                stbr.append(letters[j]);
//                j++;
//            }
//            stbr.append(".txt");
//            System.out.println(stbr);
        String stbr = "striptest-3.txt";
            fileOpener(stbr/*.toString()*/);
      //  }
    }


    public void fileOpener(String str){

        try {
            File file = new File(str);
            BufferedReader br = new BufferedReader(new FileReader(file));
             CodeStripper stripper = new CodeStripper(file,br);

        }catch (IOException e){

            System.out.println("Not Found #################################################");
            e.printStackTrace();
        }



    }



}
