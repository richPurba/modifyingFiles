package org.modifyingfiles.IOOperations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileToChange {
    public String pathToFile;

    public FileToChange(String pathToFile){
        this.pathToFile = pathToFile;
    }

    public List<String> rewritingFile() throws IllegalArgumentException{
        List<String> results = new ArrayList<String>();
        try{
            FileInputStream fileInputStream = new FileInputStream(this.pathToFile);
            InputStreamReader inputStream = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStream);
            Pattern findingPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}.\\d{1,3}\\s+[a-zA-Z_0-9-]+");
            String data;

            while( (data = reader.readLine()) != null){
                Matcher matcher = findingPattern.matcher(data);
                if(matcher.matches()){
                    results.add(data);
                }
            }
        }catch ( IOException e){
            throw new IllegalArgumentException("there is something wrong with the file, check file's path" + e);
        }
        return results;

    }
}
