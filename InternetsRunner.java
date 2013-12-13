
/**
 * Internets Runner runs the thing with text-based options
 */

import java.util.Scanner;
//import java.util.List;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.lang.InterruptedException;
public class InternetsRunner// implements ErrorMessages
{
    /**
     * Internets
     * I should be able to:
     * create a new internets account
     * add internets to an exisiting account
     * subtract internets from an exising account
     * printout internets data of everyone
     * //printout internets data of an account
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
                                ""+ //todo, add in 'get' method which will get something and print it out
                                "help                       ;Prints this page"+"\n"+
                                "printout -OPTION(S)        ;Prints out something"+"\n"+
                                "   -a                      ;Print all accounts information"+"\n"+ //sorted by name, cannot be used with other flags
                                "   -i                      ;Print all internets"+"\n"+ //sorted by name
                                "   -n                      ;Print all names"+"\n"+ //sorted by name
                                "   -p                      ;Print all percentages"+"\n"+ //sorted by name
                               // "printout [name]:[internets];Print out an account"+"\n"+ NOT IMPLEMENTED YET
                                "q                          ;Quits program"+"\n"+
                                "save                       ;Saves current internets accounts"+"\n"+
                                "                            to file"+"\n"+
                                "subtract [name]:[internets];Subtracts number of internets" +"\n"+
                                "                            from an account"+"\n"+
                                "v                          ;Prints current version"+"\n"+"\n"+
                                "Multiply internets values by 100 before entering"+"\n"+
                                "Press enter to return to main menu";
    private static String version = "InternetsAccounts v0.9.2"; //Give compile error to prompt updated version #
    private static ErrorMessages errs;
    private static String name;
    private static int internets;
    //private static static List<String> names;
    private static InputFile inFile;
    private static InternetsList interList;
    private static final String gback = "Going back to main menu";
    private static String result;
    private static final String rts = "done. Remember to save";
    //private static static final String gback1 = "Going back a menu";
    private static boolean notD, listEmpty, quit, isValid, aFlag, iFlag, nFlag, pFlag, action;//, negInput;
    private static int[] buyLocs, locArray;
    
    /**
     * Main method, begins startup routine
     * results in program start
     */
    public static void main(String[] args){
        startUpCommands();
        outPrintln("Startup Complete");
        outPrintln("Welcome to the Internets.");
        begin();
    }
    
    /**
     * Startup methods
     * of pertaining to initial program startup
     */
    
    /**
     * method contains all the start up methods in one location
     * Begin: starts up program
     * End: results in program intial startup complete
     */
    private static void startUpCommands(){
        coreStartUp();
        //primitiveTypeStartUp(); //already planned to run in begin method
        outPrintln("Eating Bagel");
        otherObjectStartUp();
        runStartUp();
        outPrintln("Initializing Potato");
    }
    
    /**
     * core parts of program (input file and internet accounts list)
     * Begin: starts up program
     * End: results in readIn from file and list of Internet Accounts created
     */
    private static void coreStartUp(){
        outPrintln("Begin core start up");
        inFile = new InputFile();
        outPrintln("done.");
        interList = new InternetsList(inFile.getTheFile());
        outPrintln("End core start up");
    }
    
    /**
     * method runs in begin 
     * NOT in startup commands
     * Begin: certain variables undeclared or data dirty
     * End: variables declared, dirty data cleaned
     */
    private static void primitiveTypeStartUp(){
        outPrint("Setting values");
        quit = false;
        outPrint(".");
        result = "No result.";
        outPrint(".");
        name = "blank";
        outPrint(".");
        isValid = true;
        outPrint(".");
        internets = 0;
        outPrint(".");
        aFlag = false;
        outPrint(".");
        iFlag = false;
        outPrint(".");
        nFlag = false;
        outPrint(".");
        pFlag = false;
        outPrint(".");
        action = false;
        outPrintln("done.");
    }
    
    /**
     * creates objects less important to core startup
     * Begin: certain objects unintialized
     * End: Those objects are intialized
     */
    private static void otherObjectStartUp(){
        outPrint("Creating Necessary Objects");
        sc = new Scanner(System.in);
        outPrint(".");
        //String b = errs.errors();
        outPrintln("done.");
    }
    
    /**
     * methods that run (no default declarations or object intilzation
     * Begin: variables/objects requring running of method to create
     * End: variables/objects created after running method
     */
    private static void runStartUp(){
        outPrintln("Defeating Wheatly");
        locArray = interList.getALocArray(interList.getInterList());
    }
    
    //End start up methods
        
    /**
     * Outprint methods,
     * methods for printing text to console.
     */
    
    private static void outPrintEndMM(int errNum, int dotNum, String end){
        //outPrint(ErrorMessages.errors[errNum]);
        //String errtxt = ErrorMessages.values()[errNum];
        outPrint(errs.values()[errNum].errors());
        printDotLoad(dotNum);
        outPrintln(end);
    }
    
    private static void outPrintEndMMT(String first, int dotNum, String end){
        outPrint(first);
        printDotLoad(dotNum);
        outPrintln(end);
    }
    
    private static void outPrintln(String text){
        System.out.println(text);
    }
    
    private static void outPrint(String text){
        System.out.print(text);
    }
    
    //private static void outPrintEndM(int errNum, int dotNum)
    
    private static void printDotLoad(int num){
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
    
    //End outPrint methods
    
    /**
     * User input and processing methods
     * Begin with begin()
     */
    
     private static void begin(){
        primitiveTypeStartUp();
        outPrintln("What would you like to do? (Type in 'help' to list options)");
        String inputLine = sc.nextLine();
        String input = inputLine;
        if(input.equals("help")){
            outPrintln(helpText);
            String txt = sc.nextLine();
        }
        else if(input.equals("q")){
            //break;
            quit = true;
        }
        else if(input.equals("v")){
            outPrintln(version);
            outPrintEndMMT("", 3, "");
        }
        else if(input.length() < 3){
            outPrintEndMM(0, 3, "");
        }
        else if(input.substring(0,3).equals("add")){
            parseText(inputLine, 4);
            if(isValid){
                outPrintln("Add "+internets+" to account "+name+"? (Y/n)");
                String inp = sc.nextLine();
                if(inp.equalsIgnoreCase("Y")){
                    interList.addInternets(name, internets);
                    action = true;
                    //outPrintEndMMT(rts, 3, gback);
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
            //outPrintEndMMT(rts, 3, gback);
        }
        else if(input.equals("save")){
            inFile.writeToFile(interList.writeFile());
        }
        else if(input.substring(0,6).equals("create")){
            if(input.length() > 7){
                parseText(input, 7);
            }
            interList.createInternets(name, internets);
            action = true;
            //outPrintEndMMT(rts, 3, gback);
        }
        else if(input.substring(0,8).equals("subtract")){
            parseText(inputLine,9);
            if(isValid){
                outPrintln("Subtract " +internets+ " from account " +name+ " ? (Y/n)");
                String inp = sc.nextLine();
                if(inp.equalsIgnoreCase("y")){
                    interList.subtractInternet(name, internets);
                    action = true;
                    //outPrintEndMMT(rts,3,gback);
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
            parsePrintText(inputLine);
            if(!quit){
                result = interList.printOutText(aFlag, iFlag, nFlag, pFlag);
                action = true;
            }
        }
        else{
            outPrintEndMM(1, 3, gback);
        }
        if(quit){
            outPrintEndMMT("Quitting Program",3,"I hope you saved..");
        }
        else{
            if(action){
                outPrintln(result);
                outPrintEndMMT(rts, 3, gback);
            }
            begin();
        }
    }
    
    /**
     * Parse Text methods
     */
    
    /**
     * Used in:
     * add
     * create
     * subtract
     */
    private static void parseText(String str, int afterSpace){
        int colIndex = str.indexOf(":");
        if(colIndex < 0){
            //outPrint("in here");
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
    
    /**
     * used in:
     * printout
     * -parsePrintText
     */
    private static void parseFlags(String in){
        String toParse = in.substring(0,1);
        if(toParse.equals("q")){
            quit = true;
        }
        else if(toParse.equals("a")){
            aFlag = true;
        }
        else if(toParse.equals("i")){
            iFlag = true;
        }
        else if(toParse.equals("n")){
            nFlag = true;
        }
        else if(toParse.equals("p")){
            pFlag = true;
        }
        if(!quit && !aFlag){
            if(in.length() > 1){
                parseFlags(in.substring(1,in.length()));
            }
        }
    }
    
    /**
     * Used in:
     * printout
     */
    private static void parsePrintText(String in){
        if(in.length() < 11){
            outPrintEndMM(7,3,gback);
        }
        else{
            int index = in.indexOf("-");
            if(index < 0){
                outPrintEndMM(7,3,gback);
            }
            else{
                parseFlags(in.substring(index+1,in.length()));
            }
        }
    }
    
    /**
     * Used in:
     * buy
     * -buyCommand
     * --correctAccounts
     * ---correctPurchase
     */
    private static void parseIntText(){
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
    
    //End parse text methods
    
    /**
     * buy methods
     */
    
    private static void buyCommand(){
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
    
    private static void enterInList(){
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
    
    private static void correctAccounts(){
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
    
    private static void correctPurchase(){
        outPrintln("Type in the amount of purchase");
        parseIntText();
        outPrintln("Is " +internets+ " the correct amount of purchase? (Y/n)");
        String inTemp = sc.nextLine();
        if(inTemp.equalsIgnoreCase("Y")){
            result = interList.calculatePayments(buyLocs, internets);
            action = true;
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
    
    //End buy methods
    
    //End user input and processing methods
}
