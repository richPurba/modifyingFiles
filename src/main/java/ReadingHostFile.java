import java.util.Scanner;

public class ReadingHostFile {

    public static void main(String[] args) {
        System.out.println("Please provide your file: ....");
//        Scanner readerInput = new Scanner(System.in);
//        String n = readerInput.next("[A-Z]");// possibly not working with '/' character
//        readerInput.close();

        String pathToFile = "/etc/hosts";

        FileToChange file = new FileToChange(pathToFile);

        file.rewritingFile();


    }
}
