
/**
 * using the List given from Input List, creating objects of each input. then make ArrayList of them.
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class InternetsList
{
    private ArrayList<Internets> interList, listToPay;
    private int totalNets;
    private int[] percentages; //sorted greatest to least
    
    /**
     * Todo: Add method to check if account even exists
     */
    
    public InternetsList(List<String> list){
        convertToArrayList(list);
        sortByName();
        getTotalNets();
        percentagesMethods();
    }

    public void createInternets(String n, int i){
        //interList.add(new Internets(i, n));
        Internets tempIn = new Internets(i, n);
        int[] locArray = getALocArray(interList);
        outPrint("Adding Account");
        interList.add(findInternetsPlace(tempIn, interList, locArray),tempIn);
        outPrintln("done.");
    }
    
    private int findInternetsPlace(Internets i, ArrayList<Internets> list, int[] locs){
        if(list.size() > 1){
            int q = list.size()/2;
            if(i.getName().compareTo(list.get(q).getName()) < 0){
                return findInternetsPlace(i, new ArrayList<Internets>(list.subList(0,q)), Arrays.copyOfRange(locs,0,q));
            }
            else if(i.getName().compareTo(list.get(q).getName()) > 0){
                return findInternetsPlace(i, new ArrayList<Internets>(list.subList(q,list.size())), Arrays.copyOfRange(locs,q,locs.length));
            }
            else{
                return locs[q]+1;
            }
        }
        else{
            if(i.getName().compareTo(list.get(0).getName()) < 0){
                return locs[0];
            }
            else{
                return locs[0]+1;
            }
        }
    }
    
    private void percentagesMethods(){
        percentages = new int[interList.size()];
        setPercentages();
        percentages = getPercentages(interList);
        sortPercentages();
    }
    
    private void setPercentages(){
        outPrint("Setting %");
        for(int i = 0; i < interList.size(); i++){
            //outPrint(".");
            double percentageD = (double) interList.get(i).getInternets()/totalNets;
            int percentageI = (int) (percentageD*10000);
            outPrintln(percentageD +":"+percentageI);
            interList.get(i).setPercentage(percentageI);
        }
        outPrintln("done.");
    }
    
    private int[] getPercentages(ArrayList<Internets> list){
        outPrint("Getting %");
        int k = 0;
        int[] newPercentages = new int[list.size()];
        for(Internets i : list){
            outPrint(".");
            newPercentages[k] = i.getPercentage();
        }
        outPrintln("done.");
        return newPercentages;
    }
    
    private void sortPercentages(){
        MergeSort ms = new MergeSort(percentages, false);
        percentages = ms.getArray();
    }
    
    public ArrayList<Internets> convertStrToArrayList(String[] names){
        outPrint("Converting Sorted Array");
        ArrayList<Internets> thisList = new ArrayList<Internets>();
        for(int i = 0; i < names.length; i++){
            outPrint(".");
            int k = 0;
            boolean isIn = false;
            while(isIn == false && k < interList.size()){
                if(interList.get(k).getName().equals(names[i])){
                    thisList.add(new Internets(interList.get(k)));
                    isIn = true;
                    //break;
                }
                k++;
            }
        }
        outPrintln("done.");
        return thisList;
    }
    
    private void getTotalNets(){
        //outPrint("Getting Totals");
        totalNets = 0;
        for(Internets i : interList){
            //outPrint(".");
            totalNets += i.getInternets();
        }
        //outPrintln("done.");
    }
    
    public int getTotals(){
        return totalNets;
    }
    
    private void convertToArrayList(List<String> list){
        outPrintln("Parsing File");
        interList = new ArrayList<Internets>();
        for(String s : list){
            int index = s.indexOf(":");
            String name = s.substring(0,index);
            String inters = s.substring(index+1, s.length());
            outPrintln(name+":"+inters);
            double internets = Double.parseDouble(inters);
            int inter = (int) (internets*100);
            interList.add(new Internets(inter, name));
        }
        outPrintln("done.");
    }
    
    private int getSmallestNet(){
        int min = interList.get(0).getInternets();
        for(Internets i : interList){
            if(i.getInternets() < min){
                min = i.getInternets();
            }
        }
        return min;
    }
    
    private int getSmallestNetOf(int[] ins){
        int min = interList.get(ins[0]).getInternets();
        for(int i : ins){
            if(interList.get(i).getInternets() < min){
                min = interList.get(i).getInternets();
            }
        }
        return min;
    }
    
    public ArrayList<Internets> getInterList(){
        return interList;
    }
    
    /**
    public void setLocsArray(int[] nums){
        validNetsLoc = nums;
    }
    */
    
    private int getValids(int[] ins){
        int count = 0;
        for(int i : ins){
            if(interList.get(i).getInternets() > 0){
                count++;
            }
        }
        return count;
    }
    
    private int[] getValidInternets(int[] ins){
        int[] theValids = new int[getValids(ins)];
        boolean isIn = false;
        int k = 0;
        for(int i : ins){
            if(interList.get(i).getInternets() > 0){
                theValids[k] = i;
                k++;
            }
        }
        return theValids;
    }
        
    /**
    private ArrayList<Internets> getValidInternets(ArrayList<Internets> inList){
        int count = 0;
        ArrayList<Internets> newList = new ArrayList<Internets>();
        for(Internets i : inList){
            if(i.getIsEmpty() == false){
                count++;
            }
        }
        validNetsLoc = new int[count];
        int num = 0;
        for(int i = 0; i < inList.size(); i++){
            if(inList.get(i).getInternets() > 0){
                validNetsLoc[num] = i;
                num++;
            }
        }
        
        //return count;
    }
    /** 
    public void setCurrentlyPaids(int cp){
    for(int i = 0; i < interList.size(); i++){
    interList.get(i).setCurrentlyPaid(cp);
    }
    }
    */
    public void subtractInternets(int sub, int[] ins){
        //getTotalNets();
        outPrint(".");
        int[] validsLoc = getValidInternets(ins);
        int valids = validsLoc.length;
        int threshold = sub/(valids*100);
        int smallestNet = getSmallestNetOf(ins);
        if(smallestNet <= threshold){
            for(int i = 0; i < valids; i++){
                interList.get(validsLoc[i]).subtractInters(smallestNet);
                //inList.get(i).subtractInters(smallestNet);
            }
            subtractInternets(sub-(smallestNet*valids), getValidInternets(ins));
        }
        else{
            for(int i = 0; i < valids; i++){
                interList.get(validsLoc[i]).subtractInters(threshold);
                // interList.get(validNetsLoc[i]).subtractInters(threshold);
            }
        }
    }
    
    public void subtractInternet(String name, int sub){
        int[] locArray = getALocArray(interList);
        outPrint("Subtracting from Account");
        interList.get(findInternets(name, interList, locArray)).subtractInters(sub);
        outPrintln("done.");
    }
    
    private void sortByName(){
        outPrint("Generating Sort List");
        String[] strs = new String[interList.size()];
        for(int i = 0; i < interList.size(); i++){
            outPrint(".");
            strs[i] = interList.get(i).getName();
        }
        outPrintln("done.");
        MergeSort ms = new MergeSort(strs);
        strs = ms.getStrArray();
        interList = convertStrToArrayList(strs);
    }
    
    public int findInternets(String name, ArrayList<Internets> inList, int[] locs){
        if(inList.size() > 1){
            int q = inList.size()/2;
            outPrint(".");
            outPrintln(""+q);
            printArray(locs);
            if(name.compareTo(inList.get(q).getName()) < 0){
                return findInternets(name, new ArrayList<Internets>(inList.subList(0,q)), Arrays.copyOfRange(locs, 0, q));
            }
            else if(name.compareTo(inList.get(q).getName()) > 0){
                return findInternets(name, new ArrayList<Internets>(inList.subList(q,inList.size())),Arrays.copyOfRange(locs, q, locs.length));
            }
            else{
                return locs[q];
            }
        }
        return locs[0];
    }
    
    private void printArray(int[] ar){
        for(int i : ar){
            outPrint(""+i);
        }
        outPrintln("");
    }
    
    public int[] getALocArray(ArrayList<Internets> list){
        outPrint("Getting List of Locations");
        int[] locArray = new int[list.size()];
        for(int i = 0; i < locArray.length; i++){
            outPrint(".");
            locArray[i] = i;
        }
        outPrintln("done.");
        return locArray;
    }
    
    public void addInternets(String name, int ins){
        int[] locArray = getALocArray(interList);
        outPrint("Adding internets");
        interList.get(findInternets(name, interList, locArray)).addInters(ins);
    }
    
    private int[] sortByInternets(int[] locs){
        //ArrayList<Internets> tempList = new ArrayList<Internets>();
        int[][] newLocsArray = new int[2][locs.length];
        outPrint("Generating List");
        for(int l = 0; l < locs.length; l++){
            //outPrint(""+l);
            newLocsArray[0][l] = locs[l];
            newLocsArray[1][l] = interList.get(locs[l]).getInternets();
            outPrint(""+locs[l]);
            outPrintln(""+newLocsArray[1][l]);
            //tempList.add(new Internets(interList.get(l)));
            //outPrint(".");
        }
        outPrintln("done.");
        MergeSort ms = new MergeSort(newLocsArray, true);
        //tempList = new ArrayList<Internets>(ms.getList());
        newLocsArray = ms.get2Array();
        //int[][] newLocs = new int[tempList.size()];
        int[] newLocs = new int[newLocsArray[0].length];
        for(int i = 0; i < newLocsArray[0].length; i++){
            newLocs[i] = newLocsArray[0][i];
        }
        return newLocs;
        /**
        int k = 0;
        outPrint("Getting Locations");
        for(Internets i : tempList){
            outPrint(".");
            int in = 0;
            boolean isFound = false;
            while(!isFound && in < interList.size()){
                if(i.getName().equals(interList.get(in))){
                    isFound = true;
                    newLocs[k] = in;
                    k++;
                }
                else{
                    in++;
                }
            }
        }
        outPrintln("done.");
        return newLocs;
        //*/
       //return newLocsArray;
    }
    
    public String calculatePayments(int[] locsArray, int ins){
        getTotalNets();
        int totals = totalNets;
        printArray(locsArray);
        int[] internetsLeastToGreat = sortByInternets(locsArray);
        printArray(internetsLeastToGreat);
        //ArrayList<Internets> newTempList = new ArrayList<Internets>();
        int[][] perLocsArray = new int[2][internetsLeastToGreat.length];
        outPrint("Finding Accounts");
        for(int i = 0; i < locsArray.length; i++){
            //newTempList.add(new Internets(interList.get(i)));
            perLocsArray[0][i] = locsArray[i];
            perLocsArray[1][i] = interList.get(locsArray[i]).getPercentage();
            outPrint(".");
        }
        outPrintln("done");
        //int[] percentagesOfLocsArray = getPercentages(newTempList);
        MergeSort ms = new MergeSort(perLocsArray, false);
        perLocsArray = ms.get2Array();
        //printArray(perLocsArray);
        outPrint("Subtract from Accounts");
        subtractInternets(ins, locsArray); //COOKIE BUTTER
        outPrintln("done.");
        String result = "";
        //int k = 0;
        outPrint("Preparing results");
        for(int i = 0; i < locsArray.length; i++){
            outPrint(".");
            //int percentResult = perLocsArray[1][*totals;
            //result = result + interList.get(i).getName() +":"+ getPercentFront(percentResult) +"."+ getPercentBack(percentResult) +"\n";
            int percentResult = ((perLocsArray[1][i]*ins)/10000);
            result = result + interList.get(internetsLeastToGreat[i]).getName() +":"+getPercentFront(percentResult)+"."+getPercentBack(percentResult)+"$"+"\n";
            //k++;
        }
        getTotalNets();
        outPrintln("done.");
        return result;
    }
    
    private String getPercentFront(int a){
        return ""+(a/100);
    }
    
    private String getPercentBack(int a){
        return ""+(a%100);
    }
    
    public String printOutText(boolean a, boolean i, boolean n, boolean p){
        String result = "name:internets:percentage of total"+"\n";
        outPrint("Preparing printout");
        for(Internets k : interList){
            outPrint(".");
            if(a){
                result = result + k.getName()+":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets())+":"+getPercentFront(k.getPercentage())+"."+getPercentBack(k.getPercentage())+"\n";
            }
            else{
                if(n){
                    result = result + k.getName();
                }
                if(i){
                    if(n){
                        result = result + ":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets());
                    }
                    else{
                        result = result + getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets());
                    }
                }
                if(p){
                    if(n || i){
                        result = result + ":"+getPercentFront(k.getPercentage())+"."+getPercentBack(k.getPercentage());
                    }
                    else{
                        result = result + getPercentFront(k.getPercentage())+"."+getPercentBack(k.getPercentage());
                    }
                }
                result = result + "\n";
            }
        }
        outPrintln("done.");
        return result;
    }
    
    public ArrayList<String> writeFile(){
        ArrayList<String> list = new ArrayList<String>();
        outPrint("Preparing Save");
        for(Internets k: interList){
            outPrint(".");
            //outPrintln(k.getName()+":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets()));
            list.add(k.getName()+":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets()));
        }
        outPrintln("done.");
        return list;
    }
    
    private void outPrintln(String text){
        System.out.println(text);
    }
    
    private void outPrint(String text){
        System.out.print(text);
    }
}
