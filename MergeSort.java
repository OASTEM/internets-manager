import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class MergeSort {

    /**
    public static void main(String[] args) throws IOException{
        //BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
        //int arraySize = Integer.parseInt(R.readLine());
        /**int[] inputArray = 
        for (int i = 0; i < arraySize; i++) {
            inputArray[i] = Integer.parseInt(R.readLine());
        }
        
        int[] inputArray = {0, 7, 25, 43, 2, 10, 3, 35, 21, 47, 6};
        mergeSort(inputArray);

        for (int j = 0; j < inputArray.length; j++) {
            System.out.println(inputArray[j]);
        }

    }
    /**
     * {0, 7, 25, 43, 2, 10, 3, 35, 21, 47, 6}
     * 0   1   2   3   4 5   5  6   8   9   10
     * length = 11;
     * --> q = 5 
     * --> left = {0, 7, 25, 43, 2}
     * --> right = {1-, 3, 35, 21, 47, 6}
     * l--> q = 3
     * l--> left = {0, 7}
     * l--> right = {25, 43, 2}
     * ll--> q = 1
     * ll--> left = {0}
     * ll--> right = {7};
     * lr--> q = 1;
     * lr--> left = {25};
     * lr--> right = {43, 2};
     * lrr--> q = 1;
     * lrr--> left={43}
     * lrr--> right={2};
     * lrr--> totElem = 2;
     * lrr--> f{2, 43}
     * 
     * 
     * 
     */
    
    private static int[] array;
    private static String[] strArray;
    private static boolean isLow;
    private static int[][] dArray;
    //private static Simple2DArray s2A;
    //private static CopyOnWriteArrayList<Internets> theList;
    
    public MergeSort(int[] nums, boolean iL){
        outPrint("Sorting");
        isLow = iL;
        mergeSort(nums);
        outPrintln("done.");
        array = nums;
    }
    
    public MergeSort(int[][] nums, boolean iL){
        outPrint("Sorting");
        //s2A = new Simple2DArray();
        isLow = iL;
        mergeSortDual(nums);
        dArray = nums;
    }
    /**
    public MergeSort(List<Internets> list, boolean iL){
        outPrint("Sorting");
        isLow = iL;
        theList = new CopyOnWriteArrayList<Internets>(list);
        mergeSortList(theList);
        outPrintln("done.");
    }
    */
    
    public MergeSort(String[] strs){
        outPrint("Sorting");
        mergeSortStr(strs);
        outPrintln("done.");
        strArray = strs;
    }
    
    public String[] getStrArray(){
        return strArray;
    }
    
    public int[] getArray(){
        return array;
    }
    /**
    public CopyOnWriteArrayList<Internets> getList(){
        return theList;
    }*/
    public int[][] get2Array(){
        return dArray;
    }
    
    private static void outPrintln(String text){
        System.out.println(text);
    }
    
    private static void outPrint(String text){
        System.out.print(text);
    }

    static void mergeSort(int[] A) {
        if (A.length > 1) {
            int q = A.length/2;
            outPrint(".");
            int[] leftArray = Arrays.copyOfRange(A, 0, q);
            int[] rightArray = Arrays.copyOfRange(A,q,A.length);
            
            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(A,leftArray,rightArray);
        }
        //outPrintln(".");
    }

    static void merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        if(isLow){
            while ( i < totElem) { 
                outPrint(".");
                if ((li < l.length) && (ri<r.length)) {
                    if (l[li] < r[ri]) {
                        a[i] = l[li];
                        i++;
                        li++;
                    }
                    else {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                else {
                    if (li >= l.length) {
                        while (ri < r.length) {
                            outPrint(".");
                            a[i] = r[ri];
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.length) {
                        while (li < l.length) {
                            outPrint(".");
                            a[i] = l[li];
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        else{
            while ( i < totElem) { 
                outPrint(".");
                if ((li < l.length) && (ri<r.length)) {
                    if (l[li] > r[ri]) {
                        a[i] = l[li];
                        i++;
                        li++;
                    }
                    else {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                else {
                    if (li >= l.length) {
                        while (ri < r.length) {
                            outPrint(".");
                            a[i] = r[ri];
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.length) {
                        while (li < l.length) {
                            outPrint(".");
                            a[i] = l[li];
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        //return a;
    }
    
    static int[][] copy2DArray(int[][] nums, int start, int end, int size){
        int[][] tempArray = new int[2][size];
        outPrintln("");
        outPrintln("-----------");
        outPrintln(start +" "+ end+" "+size+" "+nums.length+" "+nums[0].length);
        outPrintln(tempArray.length+" "+tempArray[0].length);
        outPrintln("-----------");
        int k = 0;
        for(int i = start; i < end;i++){
            outPrint(""+nums[0][i]+":");
            outPrintln(""+nums[1][i]);
            tempArray[0][k] = nums[0][i];
            tempArray[1][k] = nums[1][i];
            k++;
        }
        return tempArray;
    }

    static void mergeSortDual(int[][] A) {
        if (A[0].length > 1) {
            int q = A[0].length/2;
            outPrint(".");
            int[][] leftArray = copy2DArray(A, 0, q, q);//Arrays.copyOfRange(A, 0, q);
            int[][] rightArray = copy2DArray(A, q, A[0].length, A[0].length-q);//Arrays.copyOfRange(A,q,A.length);
            
            
            mergeSortDual(leftArray);
            mergeSortDual(rightArray);

            mergeDual(A,leftArray,rightArray);
        }
        //outPrintln(".");
    }

    static void mergeDual(int[][] a, int[][] l, int[][] r) {
        int totElem = l[0].length + r[0].length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        if(isLow){
            while ( i < totElem) { 
                outPrint(".");
                if ((li < l[0].length) && (ri<r[0].length)) {
                    if (l[1][li] < r[1][ri]) {
                        a[0][i] = l[0][li];
                        a[1][i] = l[1][li];
                        i++;
                        li++;
                    }
                    else {
                        a[0][i] = r[0][ri];
                        a[1][i] = r[1][ri];
                        i++;
                        ri++;
                    }
                }
                else {
                    if (li >= l[0].length) {
                        while (ri < r[0].length) {
                            outPrint(".");
                            a[0][i] = r[0][ri];
                            a[1][i] = r[1][ri];
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r[0].length) {
                        while (li < l[0].length) {
                            outPrint(".");
                            a[0][i] = l[0][li];
                            a[1][i] = l[1][li];
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        else{
            while ( i < totElem) { 
                outPrint(".");
                if ((li < l[0].length) && (ri<r[0].length)) {
                    if (l[1][li] > r[1][ri]) {
                        a[0][i] = l[0][li];
                        a[1][i] = l[1][li];
                        i++;
                        li++;
                    }
                    else {
                        a[0][i] = r[0][ri];
                        a[1][i] = r[1][ri];
                        i++;
                        ri++;
                    }
                }
                else {
                    if (li >= l[0].length) {
                        while (ri < r[0].length) {
                            outPrint(".");
                            a[0][i] = r[0][ri];
                            a[1][i] = r[1][ri];
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r[0].length) {
                        while (li < l[0].length) {
                            outPrint(".");
                            a[0][i] = l[0][li];
                            a[1][i] = l[1][li];
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        //return a;
    }
    
    static void mergeSortStr(String[] A) {
        if (A.length > 1) {
            int q = A.length/2;
            outPrint(".");

            String[] leftArray = Arrays.copyOfRange(A, 0, q);
            String[] rightArray = Arrays.copyOfRange(A,q,A.length);

            mergeSortStr(leftArray);
            mergeSortStr(rightArray);

            mergeStr(A,leftArray,rightArray);
        }
        //outPrintln(".");
    }

    static void mergeStr(String[] a, String[] l, String[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) { 
            outPrint(".");
            if ((li < l.length) && (ri<r.length)) {
                if (l[li].compareTo(r[ri]) < 0) {
                    a[i] = l[li];
                    i++;
                    li++;
                }
                else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }
            else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        outPrint(".");
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        outPrint(".");
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        //return a;

    }
    
    /**
    static void mergeSortList(CopyOnWriteArrayList<Internets> A) {
        if (A.size() > 1) {
            int q = A.size()/2;
            outPrint(".");

            CopyOnWriteArrayList<Internets> leftArray = new CopyOnWriteArrayList<Internets>(A.subList(0, q));
            CopyOnWriteArrayList<Internets> rightArray = new CopyOnWriteArrayList<Internets>(A.subList(q,A.size()));

            mergeSortList(leftArray);
            mergeSortList(rightArray);

            mergeList(A,leftArray,rightArray);
        }
        //outPrintln(".");
    }

    static void mergeList(CopyOnWriteArrayList<Internets> a, CopyOnWriteArrayList<Internets> l, CopyOnWriteArrayList<Internets> r) {
        int totElem = l.size() + r.size();
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        if(isLow){
            while ( i < totElem) { 
                outPrint(".");
                if ((li < l.size()) && (ri<r.size())) {
                    if (l.get(li).getInternets() < r.get(ri).getInternets()) {
                        a.add(l.get(li));
                        i++;
                        li++;
                    }
                    else {
                        a.add(r.get(ri));
                        i++;
                        ri++;
                    }
                }
                else {
                    if (li >= l.size()) {
                        while (ri < r.size()) {
                            outPrint(".");
                            a.add(r.get(ri));
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.size()) {
                        while (li < l.size()) {
                            outPrint(".");
                            a.add(l.get(li));
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        else{
            while ( i < totElem) { 
                outPrint(".");
                if ((li < l.size()) && (ri<r.size())) {
                    if (l.get(li).getInternets() > r.get(ri).getInternets()) {
                        a.add(l.get(li));
                        i++;
                        li++;
                    }
                    else {
                        a.add(r.get(ri));
                        i++;
                        ri++;
                    }
                }
                else {
                    if (li >= l.size()) {
                        while (ri < r.size()) {
                            outPrint(".");
                            a.add(r.get(ri));
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.size()) {
                        while (li < l.size()) {
                            outPrint(".");
                            a.add(l.get(li));
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        //return a;
        
    }*/
}