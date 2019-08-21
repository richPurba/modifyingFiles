package org.IOOperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputFile {

    public FileOutputStream outputFile;
    public String fileNameWithExtension;
    private static Logger logger = LoggerFactory.getLogger(OutputFile.class);

    public OutputFile(String fileNameWithExtension) throws IOException{
        this.fileNameWithExtension = System.getProperty("user.dir")+"/"+fileNameWithExtension;
        File finalFile = new File(this.fileNameWithExtension);
        try{
            if(!finalFile.exists()){
                Boolean creatingANewFile = finalFile.createNewFile();
                logger.debug("creating new file is "+ creatingANewFile.toString());
            }
            outputFile = new FileOutputStream(finalFile);
        } catch (IOException e){
            throw new IOException("trying to make a new file but caught in error ...");
        }
    }

    public void generatingFileOuput(List<String> stringLists) throws IOException{

        Pattern matchedPatternGroupOne = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}.\\d{1,3}");
        Pattern matchedPatternGroupTwo = Pattern.compile("\\s+\\w+");
        Iterator iterator = stringLists.iterator();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFile));
        logger.debug(" writing is starting.....");
        while(iterator.hasNext()){
            String currentString = iterator.next().toString();
            String rewritingLines = "";
            Matcher matchedGroup = matchedPatternGroupTwo.matcher(currentString);
            if(matchedGroup.find())  rewritingLines = "--add-host="+matchedGroup.group(0).trim()+":127.0.0.1";
            writer.write(rewritingLines);
            writer.newLine();
        }
        writer.close();
        logger.debug(" writing to file is finished....");
    }

    private void deletingFileOutput(File fileToBeDeleted) {

    }
}
