import java.io.*;
import java.util.Scanner;

public class CodeStripper {

    private File file;
    String inputFileName;
    private String outputFileName;
    private String finalString;
    private PrintWriter pw;
    Scanner scanner;

    public CodeStripper(File file, Scanner scan) {
        this.file = file;
        this.scanner = scan;
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


    // #####################################################################
    // This method will trim any blank line and a single line comment that has no spaces (a common case)
    public void LinesTrimmer() throws IOException {

        String trimmedString;
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            String theCurrentLine = scanner.nextLine();

            if (theCurrentLine.equals("\n")) {
                System.out.print("");

            } else if (theCurrentLine.trim().length() == 0) {
                System.out.print("");

            } else {
                builder.append(theCurrentLine + "\n");
            }
        }//end of while


        trimmedString = builder.toString();

        System.out.println("==============Blank Lines Removed================= \n" + trimmedString +
                "================================================");

        commentsStripper(trimmedString);


        //ArrayList<Character> list = new ArrayList<Character>();
        //testing
        //PrintWriter tpw = new PrintWriter("zzzzzz.txt");
        //tpw.print(trimmedString);
        //tpw.close();

    }

    // #####################################################################
    // This enum will be used to change the flag with each iteration with each character
    // String flag is inessential for cases such as when having a comment character within a string
    enum Flag {
        POSSIBLE_COMMENT, LINE_COMMENT, BLOCK_COMMENT, IN_STRING, CODE
    }


    // ##########################################################
    public void commentsStripper(String str) {

        Flag flag = Flag.CODE;

        StringBuilder strBldr = new StringBuilder();
        char previousChar = ' ';
        char currentChar = ' ';
        char nextChar = ' ';
        // boolean inString = false; // Used to prevent trimming cases such as System.out.println("http://example.com")


        for (int i = 0; i < str.length(); i++) {
            currentChar = str.charAt(i);

            if (i != 0) {
                previousChar = currentChar;
            }

            if (i != str.length() - 1) {
                nextChar = str.charAt(i + 1);
            }
            switch (flag) {
                case CODE:
                    if (currentChar == '/')
                        flag = Flag.POSSIBLE_COMMENT;
                    else {
                        if (currentChar == '"') {
                            flag = Flag.IN_STRING;
                        }
                        strBldr.append(currentChar);
                    }
                    break;
                case POSSIBLE_COMMENT:
                    if (currentChar == '/') {
                        flag = Flag.LINE_COMMENT;
                    } else if (currentChar == '*') {
                        flag = Flag.BLOCK_COMMENT;
                    } else {
                        flag = Flag.CODE;
                        strBldr.append(String.valueOf(previousChar) + String.valueOf(currentChar));

                    }
                    break;
                case IN_STRING:
                    if (currentChar == '"')
                        flag = Flag.CODE;
                    strBldr.append(currentChar);
                    break;
                case BLOCK_COMMENT:
                    if (currentChar == '*' && nextChar == '/') {
                        flag = Flag.CODE;
                        // strBldr.append("\n");
                    }

                    break;
                case LINE_COMMENT:
                    if (currentChar == '\n') {
                        flag = Flag.CODE;
                        strBldr.append(currentChar);
                    }
                    break;
            }//end of switch
        }//end of for

        String strippedString = strBldr.toString();

        //System.out.println("###### comments Trimmed ########\n" + strippedString + "##################");

        StringBuilder moreStrippedString = new StringBuilder();
        Scanner scanner = new Scanner(strippedString);

        // this while loop will trim blank lines generated from trimming comments precedented by spaces or tabs
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().length() == 0) {
                continue;
            }
            moreStrippedString.append(line.concat("\n"));
        }

        finalString = moreStrippedString.toString();


    }

    // ##########################################################
    public void setOutputFileName() {

        inputFileName = file.getName();
        StringBuilder stbr = new StringBuilder(inputFileName);
        stbr.insert(inputFileName.indexOf("."), "-out");
        outputFileName = stbr.toString();
    }


}
