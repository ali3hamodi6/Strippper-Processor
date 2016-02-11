import java.io.*;
import java.util.Scanner;

public class CodeStripper {

    private File file;
    BufferedReader buffer;
    private String outputFileName;
    private String finalString;
    private PrintWriter pw;


    public CodeStripper(File file, BufferedReader br) {
        this.file = file;
        this.buffer = br;
        setOutputFileName();

        try {
            File outputfile = new File(outputFileName);
            pw = new PrintWriter(outputfile);
            LinesTrimmer();
            pw.print(finalString);
            pw.close();


        } catch (Exception e) {
            System.out.println("PrintWriter issue");
            e.printStackTrace();
        }

    }


    // ##########################################################
    // This method will trim any blank line and a single line comment that has no spaces (a common case)
    public void LinesTrimmer() throws IOException {

        String line;
        String trimmedString;
        StringBuilder blder = new StringBuilder();


        while ((line = buffer.readLine()) != null) {

            if (line.startsWith("//")) {
                continue;
            }

            if (line.trim().length() == 0) {
                continue;
            }
            blder.append(line);
        }//end of while

        trimmedString = blder.toString();

        //System.out.println("trimmed      " +trimmedString);
        //ArrayList<Character> list = new ArrayList<Character>();

        commentsStripper(trimmedString);
    }

    // ##########################################################
    // This enum will be used to change the flag with each iteration with each character
    // String flag is inessential for cases such as when having a comment character within a string
    enum Flag {
        TEXT_CODE, B_L_COMMENT_BEGINNING, COMMENT_ENDING, LINE_COMMENT, BLOCK_COMMENT, STRING, Fake_COMMENT
    }


    // ##########################################################
    public void commentsStripper(String str) {

        Flag flag = Flag.TEXT_CODE;

        StringBuilder strBldr = new StringBuilder();
        char currentChar = ' ';
        char previousChar = ' ';
        boolean inString = false;


        for (int i = 0; i < str.length(); i++) {
            currentChar = str.charAt(i);
            switch (flag) {
                case TEXT_CODE:
                    if (currentChar == '/')
                        flag = Flag.B_L_COMMENT_BEGINNING;
                    else if (currentChar == '"') {
                        strBldr.append(currentChar);
                        flag = Flag.STRING;
                    } else {
                        strBldr.append(currentChar);
                    }
                    break;
                case B_L_COMMENT_BEGINNING:
                    if (currentChar == '/') {
                        flag = Flag.LINE_COMMENT;
                    } else if (currentChar == '*') {
                        flag = Flag.BLOCK_COMMENT;
                    } else {
                        flag = Flag.TEXT_CODE;
                        strBldr.append(previousChar + currentChar);
                    }
                    break;
                case COMMENT_ENDING:
                    if (currentChar != '*')
                        flag = Flag.BLOCK_COMMENT;
                    else if (currentChar == '/')
                        flag = Flag.TEXT_CODE;
                case STRING:
                    if (currentChar == '"' && previousChar != '\\')
                        flag = Flag.TEXT_CODE;
                    strBldr.append(currentChar);
                    break;
                case BLOCK_COMMENT:
                    if (currentChar == '*')
                        flag = Flag.COMMENT_ENDING;
                    break;
                case LINE_COMMENT:
                    if (currentChar == '\n'|| currentChar=='\b') {
                        flag = Flag.TEXT_CODE;
                        strBldr.append(currentChar);
                    }
                    break;
            }//end of switch
            previousChar = currentChar;
        }//end of for

        String strippedString = strBldr.toString();
        StringBuilder moreStrippedString = new StringBuilder();
        Scanner scanner = new Scanner(strippedString);

        // this while loop is to trim blank lines generated from trimming comments
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().length() == 0) {
                continue;
            }
            moreStrippedString.append(line);
        }

        finalString = moreStrippedString.toString();


    }
    // ##########################################################

    public void setOutputFileName() {

        String inputFileName = file.getName();
        StringBuilder stbr = new StringBuilder(inputFileName);
        stbr.insert(inputFileName.indexOf("."), "-out");
        outputFileName = stbr.toString();
    }


}
