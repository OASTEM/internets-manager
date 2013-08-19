/**
 * Each person is an object. they will have certain number of internets.
 */


public class Internets
{
    private int internets, percentageOfTotal;// currentlyPaid;
    private String name;
    //private boolean isEmpty;
    
    public Internets(int i, String n){
        internets = i;
        name = n;
        //checkForEmpty();
    }
    
    public Internets(Internets newInts){
        internets = newInts.getInternets();
        name = newInts.getName();
        //checkForEmpty();
    }
    
    public void setPercentage(int num){
        percentageOfTotal = num;
    }
    
    public int getPercentage(){
        return percentageOfTotal;
    }
    /**
    public boolean getIsEmpty(){
        return isEmpty;
    }
    
    private void checkForEmpty(){
        if(internets > 0){
            isEmpty = false;
        }
        else{
            isEmpty = true;
        }
    }
    /**
    public void setCurrentlyPaid(int cp){
        currentlyPaid = cp;
    }
    
    public void addCurrentlyPaid(int cp){
        currentlyPaid += cp;
    }
    */
    public String getName(){
        return name;
    }
    
    public int getInternets(){
        return internets;
    }
    
    public String getPrintOut(){
        return name +":" + internets;
    }
    
    public void subtractInters(int sub){
        internets -= sub;
    }
    
    public void addInters(int sub){
        internets += sub;
    }
}
