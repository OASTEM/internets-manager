
/**
 * will take in file, copy file to List, then save to file
 */
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
public class InputFile //implements ErrorMessages
{
    private List<String> theFile;
    private Charset charset;
    private Path path;
    private ErrorMessages errs;
    //private Files file;
    
    public InputFile(){
        outPrint("Reading in file");
        charset = StandardCharsets.UTF_8;
        outPrint(".");
        //path = 
        //outPrint(".");
        readInFile();
    }
    
    public List<String> getTheFile(){
        return theFile;
    }
    
    private void printDotLoad(int num){
        for(int i = 0; i < num; i++){
            try{
                Thread.sleep(1000);
                outPrint(".");
            }
            catch(InterruptedException e){
                //outPrintEndMM(13, 10, "");
                break;
            }
        }
    }
    
    private void readInFile(){
        try{
            theFile = Files.readAllLines(Paths.get("Internets.txt"), charset);
            printDotLoad(5);
        }
        catch(IOException e){
            e.printStackTrace();
            outPrintln(errs.values()[13].errors()+" :Unable to read file");
            //outPrintln(ErrorMessages.errors[13]+" :Unable to read file");
        }
    }
    
    public void writeToFile(ArrayList<String> list){
        outPrint("Saving Document");
        try{
            Files.write(Paths.get("Internets.txt"), list, charset);
            printDotLoad(5);
            outPrintln("done.");
        }
        catch(IOException e){
            outPrintln(errs.values()[13].errors()+" :Unable to write to file");
            //outPrintln(ErrorMessages.errors[13]+" :Unable to write to file");
        }
    }
    
    private void outPrintln(String text){
        System.out.println(text);
    }
    
    private void outPrint(String text){
        System.out.print(text);
    }
}
