import java.io.IOException;
import java.util.List;

public class ReadingHostFile {

    public static void main(String[] args) throws IOException{
        System.out.println("Please provide your file: ....");

        String absolutePathToFile = "/etc/hosts";

        FileToChange file = new FileToChange(absolutePathToFile);

        List<String> results = file.rewritingFile();
        OutputFile outputtingFile = new OutputFile("docker.txt");

        try{
            outputtingFile.generatingFileOuput(results);
        }catch (IOException e){
            throw new IOException(" generating files is not working as expected... ",e);
        }


    }
}
