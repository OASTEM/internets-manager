
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
    
    public InternetsList(List<String> list){
        convertToArrayList(list);
        sortByName();
        getTotalNets();
        percentagesMethods();
    }
    
    public void createInternets(String n, int i){
        //interList.add(new Internets(i, n));
        Internets tempIn = new Internets(i, n);
        interList.add(findInternetsPlace(tempIn, interList, getALocArray(interList)),tempIn);
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
        for(int i = 0; i < interList.size(); i++){
            interList.get(i).setPercentage(interList.get(i).getInternets()/totalNets);
        }
    }
    
    private int[] getPercentages(ArrayList<Internets> list){
        int k = 0;
        int[] newPercentages = new int[list.size()];
        for(Internets i : list){
            newPercentages[k] = i.getPercentage();
        }
        return newPercentages;
    }
    
    private void sortPercentages(){
        MergeSort ms = new MergeSort(percentages, false);
        percentages = ms.getArray();
    }
    
    public ArrayList<Internets> convertStrToArrayList(String[] names){
        ArrayList<Internets> thisList = new ArrayList<Internets>();
        for(int i = 0; i < names.length; i++){
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
        return thisList;
    }
    
    private void getTotalNets(){
        totalNets = 0;
        for(Internets i : interList){
            totalNets += i.getInternets();
        }
    }
    
    public int getTotals(){
        return totalNets;
    }
    
    private void convertToArrayList(List<String> list){
        interList = new ArrayList<Internets>();
        for(String s : list){
            int index = s.indexOf(":");
            String name = s.substring(index,index+1);
            String inters = s.substring(index+1, s.length());
            double internets = Double.parseDouble(inters);
            int inter = (int) internets*100;
            interList.add(new Internets(inter, name));
        }
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
        interList.get(findInternets(name, interList, locArray)).subtractInters(sub);
    }
    
    private void sortByName(){
        String[] strs = new String[interList.size()];
        for(int i = 0; i < interList.size(); i++){
            strs[i] = interList.get(i).getName();
        }
        MergeSort ms = new MergeSort(strs);
        strs = ms.getStrArray();
        interList = convertStrToArrayList(strs);
    }
    
    public int findInternets(String name, ArrayList<Internets> inList, int[] locs){
        int q = interList.size()/2;
        outPrint(".");
        if(name.compareTo(inList.get(q).getName()) < 0){
            return findInternets(name, new ArrayList<Internets>(inList.subList(0,q)), Arrays.copyOfRange(locs, 0, q));
        }
        else if(name.compareTo(inList.get(q).getName()) > 0){
            return findInternets(name, new ArrayList<Internets>(inList.subList(q,inList.size())),Arrays.copyOfRange(locs, q, locs.length));
        }
        else{
            return locs[0];
        }
    }
    
    public int[] getALocArray(ArrayList<Internets> list){
        int[] locArray = new int[list.size()];
        for(int i = 0; i < locArray.length; i++){
            locArray[i] = i;
        }
        return locArray;
    }
    
    public void addInternets(String name, int ins){
        int[] locArray = getALocArray(interList);
        interList.get(findInternets(name, interList, locArray)).addInters(ins);
    }
    
    private int[] sortByInternets(int[] locs){
        ArrayList<Internets> tempList = new ArrayList<Internets>();
        outPrint("Generating List");
        for(int l : locs){
            tempList.add(new Internets(interList.get(l)));
            outPrint(".");
        }
        MergeSort ms = new MergeSort(tempList, true);
        tempList = new ArrayList<Internets>(ms.getList());
        int[] newLocs = new int[tempList.size()];
        int k = 0;
        for(Internets i : tempList){
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
        return newLocs;
    }
    
    public String calculatePayments(int[] locsArray, int ins){
        getTotalNets();
        int totals = totalNets;
        int[] internetsLeastToGreat = sortByInternets(locsArray);
        ArrayList<Internets> newTempList = new ArrayList<Internets>();
        for(int i : locsArray){
            newTempList.add(new Internets(interList.get(i)));
        }
        int[] percentagesOfLocsArray = getPercentages(newTempList);
        MergeSort ms = new MergeSort(percentagesOfLocsArray, false);
        percentagesOfLocsArray = ms.getArray();
        subtractInternets(ins, locsArray); //COOKIE BUTTER
        String result = "";
        int k = 0;
        for(int i : internetsLeastToGreat){
            int percentResult = percentagesOfLocsArray[k]*totals;
            int percentResultFront = percentResult / 100;
            int percentResultBack = percentResult % 100;
            result = result + interList.get(i).getName() +":"+ percentResultFront +"."+ percentResultBack +"\n";
        }
        getTotalNets();
        return result;
    }
    
    private void outPrintln(String text){
        System.out.println(text);
    }
    
    private void outPrint(String text){
        System.out.print(text);
    }
}
