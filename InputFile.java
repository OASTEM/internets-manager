
/**
 * will take in file, copy file to List, then save to file
 */
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.charset.Charset;
public class InputFile
{
    private List<String> theFile;
    private Charset charset;
    private Path path;
    private Files file;
    
    public InputFile(){
        charset = Charset.forName("US-ASCII");
        theFile = new ArrayList<String>();
        path = FileSystems.getDefault().getPath("Internets.text");
        readInFile();
    }
    
    public List<String> getTheFile(){
        return theFile;
    }
    
    private void readInFile(){
        try{
            theFile = file.readAllLines(path, charset);
        }
        catch(IOException e){
            //
        }
    }
}
