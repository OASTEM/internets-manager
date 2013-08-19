
/**
 * Internets Runner runs the thing with text-based options
 */

import java.util.Scanner;
//import java.util.List;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.lang.InterruptedException;
public class InternetsRunner implements ErrorMessages
{
    /**
     * Internets
     * I should be able to:
     * create a new internets account
     * add internets to an exisiting account
     * subtract internets from an exising account
     * printout internets data of everyone
     * printout internets data of an account
     * buy something with certain number of internets (which should display the amount each person pays)
     * quit the machine
     */
    private static Scanner sc;
    private static String helpText =   
                                "add [name]:[internets]     ;Adds number of internets to " +"\n"+
                                "                            an account" +"\n"+
                                "buy                        ;buys something with internets" +"\n"+
                                "                            and displays amount each    " +"\n"+
                                "                            person pays"+"\n"+
                                "create                     ;Adds a new Internets Account" +"\n"+
                                "                            (default name: blank)"+"\n"+
                                "create [name]:[internets]  ;Adds a new Internets Account" +"\n"+
                                "                            with given name and internets" +"\n"+
                                "                            amount" +"\n"+
                                "help                       ;Prints this page"+"\n"+
                                "printout -OPTION(S)        ;Prints out something"+"\n"+
                                "   -a                      ;Print all accounts information"+"\n"+ //sorted by name, cannot be used with other flags
                                "   -i                      ;Print all internets"+"\n"+ //sorted by name
                                "   -n                      ;Print all names"+"\n"+ //sorted by name
                                "   -p                      ;Print all percentages"+"\n"+ //sorted by name
                                "printout [name]:[internets];Print out an account"+"\n"+
                                "q                          ;Quits program"+"\n"+
                                "save                       ;Saves current internets accounts"+"\n"+
                                "                            to file"+"\n"+
                                "subtract [name]:[internets];Subtracts number of internets" +"\n"+
                                "                            from an account"+"\n"+
                                "v                          ;Prints current version"+"\n"+"\n"+
                                "Multiply internets values by 100 before entering"+"\n"+
                                "Press enter to return to main menu";
    private static String version = "InternetsAccounts v0.4";
    private static String name;
    private static int internets;
    //private static List<String> names;
    private static InputFile inFile;
    private static InternetsList interList;
    private static final String gback = "Going back to main menu";
    private static String result;
    private static final String rts = "done. Remember to save";
    //private static final String gback1 = "Going back a menu";
    private static boolean notD, listEmpty, quit, isValid;//, negInput;
    private static int[] buyLocs, locArray;
    public static void main(String[] args){
        
        
        startUpCommands();
        outPrintln("Welcome to the Internets.");
        
    }
    
    static void startUpCommands(){
        coreStartUp();
        primitiveTypeStartUp();
        otherObjectStartUp();
        runStartUp();
    }
    
    static void coreStartUp(){
        inFile = new InputFile();
        interList = new InternetsList(inFile.getTheFile());
    }
    
    static void primitiveTypeStartUp(){
        quit = false;
        result = "No result.";
        name = "blank";
        isValid = true;
        internets = 0;
    }
    
    static void otherObjectStartUp(){
        sc = new Scanner(System.in);
    }
    
    static void runStartUp(){
        locArray = interList.getALocArray(interList.getInterList());
    }
    
    static void outPrintEndMM(int errNum, int dotNum, String end){
        outPrint(errors[errNum]);
        printDotLoad(dotNum);
        outPrintln(end);
    }
    
    static void outPrintEndMMT(String first, int dotNum, String end){
        outPrint(first);
        printDotLoad(dotNum);
        outPrintln(end);
    }
    
    //static void outPrintEndM(int errNum, int dotNum)
    
    static void printDotLoad(int num){
        for(int i = 0; i < num; i++){
            try{
                Thread.sleep(1000);
                outPrint(".");
            }
            catch(InterruptedException e){
                outPrintEndMM(13, 10, "");
                break;
            }
        }
    }
    
    static void parseText(String str, int afterSpace){
        int colIndex = str.indexOf(":");
        if(colIndex < 0){
            outPrintEndMM(1, 3, gback);
            isValid = false;
        }
        else if(str.substring(afterSpace,afterSpace+1).equals(" ")){
            outPrintEndMM(1, 3, gback);
            isValid = false;
        }
        else{
            name = str.substring(afterSpace,colIndex);
            try{
                internets = Integer.parseInt(str.substring(colIndex+1, str.length()));
            }
            catch(NumberFormatException e){
                outPrintEndMM(1, 3, gback);
                isValid = false;
            }
        }
    }
    
    static void enterInList(){
        notD = true;
        listEmpty = false;
        ArrayList<Integer> buyLocsInt = new ArrayList<Integer>();
        while(notD){
            String inStr = sc.nextLine();
            if(inStr.equalsIgnoreCase("d")){
                notD = false;
            }
            else if(inStr.equalsIgnoreCase("q")){
                notD = false;
                quit = true;
            }
            else{
                int index = interList.findInternets(inStr,interList.getInterList(), locArray);
                if(index < 0){
                    //notD = false;
                    outPrintEndMM(3, 3, "Try Again or enter 'd' if done");
                }
                else{
                    buyLocsInt.add(index);
                }
            }
        }
        if(buyLocsInt.size() < 1){
            listEmpty = true;
        }
        else{
            buyLocs = Ints.toArray(buyLocsInt);
        }
    }
    
    static void correctPurchase(){
        outPrintln("Type in the amount of purchase");
        parseIntText();
        outPrintln("Is " +internets+ " the correct amount of purchase? (Y/n)");
        String inTemp = sc.nextLine();
        if(inTemp.equalsIgnoreCase("Y")){
            result = interList.calculatePayments(buyLocs, internets);
        }
        else if(inTemp.equalsIgnoreCase("n")){
            outPrintEndMMT("Reloading",3,"done");
            correctPurchase();
        }
        else if(inTemp.equalsIgnoreCase("q")){
            quit = true;
        }
        else{
            outPrintEndMM(1,3,"Try Again");
        }
    }    
    
    static void correctAccounts(){
        outPrintln("Are these the right accounts to modify? (Y/n)");
        for(int bl : buyLocs){
            outPrintln(interList.getInterList().get(bl).getName());
        }
        String inTemp = sc.nextLine();
        if(inTemp.equalsIgnoreCase("y")){                        
            correctPurchase();
        }
        else if(inTemp.equalsIgnoreCase("n")){
            outPrintEndMMT("Reloading",3,"done");
            buyCommand();
        }
        else if(inTemp.equalsIgnoreCase("q")){
            quit = true;
        }
        else{
            outPrintEndMM(1,3,"Try Again");
            correctAccounts();
        }
    }
        
    static void buyCommand(){
        outPrintln("Type in names of accounts making purchase then press enter. Type in 'd' when finished");
        enterInList();
        if(!quit){
            if(listEmpty){
                outPrintEndMM(4, 3, gback);
            }
            else{
                correctAccounts();
            }
        }
    }
    
    static void parseIntText(){
        String inStr = sc.nextLine();
        if(inStr.equalsIgnoreCase("q")){
            outPrintEndMMT("Stopping", 3, "");
            quit = true;
        }
        else{
            try{
                internets = Integer.parseInt(inStr);
                if(internets < 0){
                    outPrintEndMM(6,3,"Try Again:");
                    parseIntText();
                }
            }
            catch(NumberFormatException e){
                outPrintEndMM(1,3,"Try Again:");
                parseIntText();
            }
        }
    }
            
    
    static void begin(){
        primitiveTypeStartUp();
        outPrintln("What would you like to do? (Type in 'help' to list options)");
        String input = sc.nextLine();
        if(input.equals("help")){
            outPrintln(helpText);
            String txt = sc.nextLine();
            begin();
        }
        else if(input.equals("q")){
            //break;
            quit = true;
        }
        else if(input.equals("v")){
            outPrintln(version);
            outPrintEndMMT("", 3, "");
            begin();
        }
        else if(input.length() < 3){
            outPrintEndMM(0, 3, "");
        }
        else if(input.substring(0,3).equals("add")){
            parseText(input, 4);
            if(isValid){
                outPrintln("Add "+internets+" to account "+name+"? (Y/n)");
                String inp = sc.nextLine();
                if(inp.equalsIgnoreCase("Y")){
                    interList.addInternets(name, internets);
                    outPrintEndMMT(rts, 3, gback);
                }
                else if(inp.equalsIgnoreCase("n")){
                    outPrintEndMMT("Stopping", 3, gback);
                }
                else if(inp.equalsIgnoreCase("q")){
                    quit = true;
                }
                else{
                    outPrintEndMM(5, 3, gback);
                }
            }
        }
        else if(input.equals("buy")){
            buyCommand();
            outPrintEndMMT(rts, 3, gback);
        }
        else if(input.equals("save")){
            //
        }
        else if(input.substring(0,6).equals("create")){
            if(input.length() > 7){
                parseText(input, 7);
            }
            interList.createInternets(name, internets);
            outPrintEndMMT(rts, 3, gback);
        }
        else if(input.substring(0,8).equals("subtract")){
            parseText(input,9);
            if(isValid){
                outPrintln("Subtract " +internets+ " from account " +name+ " ? (Y/n)");
                String inp = sc.nextLine();
                if(inp.equalsIgnoreCase("y")){
                    interList.subtractInternet(name, internets);
                    outPrintEndMMT(rts,3,gback);
                }
                else if(inp.equalsIgnoreCase("n")){
                    outPrintEndMMT("Stopping", 3, gback);
                }
                else if(inp.equalsIgnoreCase("q")){
                    quit = true;
                }
                else{
                    outPrintEndMM(5,3,gback);
                }
            }
        }
        else if(input.substring(0,8).equals("printout")){
            
        }
    }
    
    static void outPrintln(String text){
        System.out.println(text);
    }
    
    static void outPrint(String text){
        System.out.print(text);
    }
    
}
