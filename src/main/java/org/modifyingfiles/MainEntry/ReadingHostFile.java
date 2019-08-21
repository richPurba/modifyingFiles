package org.modifyingfiles.MainEntry;

import org.modifyingfiles.IOOperations.FileToChange;
import org.modifyingfiles.IOOperations.OutputFile;

import java.io.IOException;
import java.util.List;

public class ReadingHostFile {

    public static void main(String[] args) throws IOException{
        System.out.println("Your file is " +args[0]);

        String absolutePathToFile;

        try{
            absolutePathToFile = args[0];
       } catch(ArrayIndexOutOfBoundsException e){
           throw new ArrayIndexOutOfBoundsException("your argument is not valid, please try again with only ONE parameter!");
       }

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
