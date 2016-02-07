import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CodeStripper {

    private File file;
    BufferedReader buffer;
    private String outputFile;
    private PrintWriter pw;


    public CodeStripper(File file, BufferedReader br){
        this.file=file;
        buffer=br;
        setOutputFile();

        try {
            File outputfile = new File(outputFile);
            pw = new PrintWriter(outputfile);
            LinesReader();

        }catch (Exception e){
            System.out.println("PrintWriter issue");
        }
    }



    public void LinesReader() throws IOException{


        String line;
        String strippedLine;
        while ((line = buffer.readLine()) != null) {

           // if (line.equals("\n")){System.out.println("a new line is removed")}
            //else if (line.startsWith("\t") &&line.)
            //List<Character> list = new ArrayList<>(line.toCharArray());




            //pw.println(line);
            //pw.println("ops");

        }
        pw.close();

    }

    public String blankLineStripper(List<Character> list){

//        Iterator<Character> itr = list.iterator();
//        while (itr.hasNext()){
//            if()


        }

    }

    public String lineCommentStripper(){

    }

    public String blockCommentStripper(){


    }





    public void setOutputFile(){

        String inputFileName = file.getName();
        System.out.println("input file name ==>"+inputFileName);
        StringBuilder stbr = new StringBuilder(inputFileName);
        stbr.insert(inputFileName.indexOf("."),"out");
        System.out.println("output file naem ###>"+stbr.toString());
        outputFile = stbr.toString();
    }



}
