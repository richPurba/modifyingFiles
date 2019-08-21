import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OutPutFileTest {

    @Mock
    OutputFile outputFile;

    @Before
    public void initialize() throws IOException{
        try{
            outputFile = new OutputFile("testFile.txt");
        } catch (IOException e){
            throw new IOException(" Exception is caught when constructing class OutPutFile..");
        }
    }

    @Test
    public void shouldGenerateFileWithRightInput() throws IOException{
        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add(0,"12.23.45.55 AVS_Gee1_SERVICE");
        listOfStrings.add(1,"55.33.134.24 SVA_Gs02_SEEEICE");
        listOfStrings.add(2,"10.44.231.55 sva_vas_01_sss");

        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add(0,"--add-host=AVS_Gee1_SERVICE:127.0.0.1");
        expectedResult.add(1,"--add-host=SVA_Gs02_SEEEICE:127.0.0.1");
        expectedResult.add(2,"--add-host=sva_vas_01_sss:127.0.0.1");

        try{
            outputFile.generatingFileOuput(listOfStrings);
            FileInputStream fileInputStream = new FileInputStream(outputFile.fileNameWithExtension);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String data;
            int indexForReadingExpectedResult = 0;

            while ((data = reader.readLine()) != null){
                assertEquals(expectedResult.get(indexForReadingExpectedResult),data);
                indexForReadingExpectedResult++;
            }
        } catch (IOException e){
            throw new IOException("Generating new file catches an exception " + e);
        }
    }
    @Test
    public void shouldGenerateFile() throws IOException{
        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add(0,"12.23.45.55 AVS_Gee1_SERVICE");
        listOfStrings.add(1,"55.33.134.24 SVA_Gs02_SEEEICE");
        listOfStrings.add(2,"10.44.231.55 sva_vas_01_sss");

        try{
            outputFile.generatingFileOuput(listOfStrings);
            File checkingFile = new File(System.getProperty("user.dir")+"/"+"testFile.txt");
            assertTrue("the file is in the directory",checkingFile.exists());
            checkingFile.deleteOnExit();
        } catch(IOException e){
            throw new IOException("error is thrown when generating file!");
        }
    }
}
