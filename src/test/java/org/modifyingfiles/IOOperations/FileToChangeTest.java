import org.modifyingfiles.IOOperations.FileToChange;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
public class FileToChangeTest {
    public File temporaryFile;

    @Mock
    FileToChange fileToChange;

    @Before
    public void initialize() throws IOException{
        try{
            temporaryFile =  File.createTempFile("temp","txt");
            fileToChange = new FileToChange(temporaryFile.getAbsolutePath());
        } catch (IOException e){
            throw new IOException("error on making new file...");
        }
    }

    @AfterEach
    public void finishingClass() throws IOException{
        temporaryFile.deleteOnExit();
        try{
            temporaryFile =  File.createTempFile("temp","txt");
        } catch(IOException e){
            throw new IOException("error on making new file...");
        }
    }


    @Test
    public void shouldReadTheRightPattern() throws IOException {
        String ipAddress = "12.45.23.21 SVA_WHATDOYOUTHING_MXS";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(temporaryFile));
            writer.write("");
            writer.write(ipAddress,0,ipAddress.length());
            writer.close();
            List<String> testingString = fileToChange.rewritingFile();
            assertEquals(ipAddress,testingString.get(0));
            assertNotNull(testingString.get(0));
        } catch(IOException e){
            throw new IOException( "this file doesn't exist");
        }
    }

    @Test
    public void shouldReadMultipleLines() throws IOException{
        String ipAddressOne = "12.23.45.55 AVS_Gee1_SERVICE";
        String ipAddressTwo = "55.33.134.24 SVA_Gs02_SEEEICE";
        String ipAddressThree = "10.44.231.55 sva_vas_01_sss";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(temporaryFile));
            writer.write("");
            writer.write(ipAddressOne);
            writer.newLine();
            writer.write(ipAddressTwo);
            writer.newLine();
            writer.write(ipAddressThree);
            writer.newLine();
            writer.close();
            List<String> testing = fileToChange.rewritingFile();
            assertEquals(ipAddressOne,testing.get(0));
            assertEquals(ipAddressTwo,testing.get(1));
            assertEquals(ipAddressThree,testing.get(2));
            assertNotNull(testing);
        }catch(IOException e){
            throw new IOException("error on making new file...");
        }
    }
}
