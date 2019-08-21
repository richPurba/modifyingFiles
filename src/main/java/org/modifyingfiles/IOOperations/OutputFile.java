package org.modifyingfiles.IOOperations;



import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputFile {

    public FileOutputStream outputFile;
    public String fileNameWithExtension;
    private static Logger logger = Logger.getLogger(OutputFile.class.getName());

    public OutputFile(String fileNameWithExtension) throws IOException{
        this.fileNameWithExtension = System.getProperty("user.dir")+"/"+fileNameWithExtension;
        File finalFile = new File(this.fileNameWithExtension);
        try{
            if(!finalFile.exists()){
                Boolean creatingANewFile = finalFile.createNewFile();
                logger.info("creating new file is "+ creatingANewFile.toString());
            }
            outputFile = new FileOutputStream(finalFile);
        } catch (IOException e){
            throw new IOException("trying to make a new file but caught in error ...");
        }
    }

    public void generatingFileOuput(List<String> stringLists) throws IOException{

        Pattern matchedPatternGroupOne = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}.\\d{1,3}");
        Pattern matchedPatternGroupTwo = Pattern.compile("\\s+[a-zA-Z_0-9-]+");
        Iterator iterator = stringLists.iterator();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputFile));
        logger.info(" writing is starting.....");
        while(iterator.hasNext()){
            String currentString = iterator.next().toString();
            String rewritingLines = "";
            Matcher matchedGroup = matchedPatternGroupTwo.matcher(currentString);
            if(matchedGroup.find())  rewritingLines = "--add-host="+matchedGroup.group(0).trim()+":127.0.0.1 \\";
            writer.write(rewritingLines);
            writer.newLine();
        }
        writer.close();
        logger.info(" writing to file is finished....");
    }

    private void deletingFileOutput(File fileToBeDeleted) {

    }
}
