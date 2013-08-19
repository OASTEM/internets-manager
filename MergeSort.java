import java.io.*;
import java.util.Arrays;
import java.util.List;


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
    private static List<Internets> theList;
    
    public MergeSort(int[] nums, boolean iL){
        
        isLow = iL;
        mergeSort(nums);
        array = nums;
    }
    
    public MergeSort(List<Internets> list, boolean iL){
        isLow = iL;
        theList = list;
        mergeSortList(theList);
    }
    
    public MergeSort(String[] strs){
        mergeSortStr(strs);
        strArray = strs;
    }
    
    public String[] getStrArray(){
        return strArray;
    }
    
    public int[] getArray(){
        return array;
    }
    
    public List<Internets> getList(){
        return theList;
    }
    
    private void outPrintln(String text){
        System.out.println(text);
    }
    
    private void outPrint(String text){
        System.out.print(text);
    }

    static void mergeSort(int[] A) {
        if (A.length > 1) {
            int q = A.length/2;

            int[] leftArray = Arrays.copyOfRange(A, 0, q);
            int[] rightArray = Arrays.copyOfRange(A,q,A.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(A,leftArray,rightArray);
        }
    }

    static void merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        if(isLow){
            while ( i < totElem) { 
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
                            a[i] = r[ri];
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.length) {
                        while (li < l.length) {
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
                            a[i] = r[ri];
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.length) {
                        while (li < l.length) {
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

    static void mergeSortStr(String[] A) {
        if (A.length > 1) {
            int q = A.length/2;

            String[] leftArray = Arrays.copyOfRange(A, 0, q);
            String[] rightArray = Arrays.copyOfRange(A,q,A.length);

            mergeSortStr(leftArray);
            mergeSortStr(rightArray);

            mergeStr(A,leftArray,rightArray);
        }
    }

    static void mergeStr(String[] a, String[] l, String[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) { 
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
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        //return a;

    }
    
    static void mergeSortList(List<Internets> A) {
        if (A.size() > 1) {
            int q = A.size()/2;

            List<Internets> leftArray = A.subList(0, q);
            List<Internets> rightArray = A.subList(q,A.size());

            mergeSortList(leftArray);
            mergeSortList(rightArray);

            mergeList(A,leftArray,rightArray);
        }
    }

    static void mergeList(List<Internets> a, List<Internets> l, List<Internets> r) {
        int totElem = l.size() + r.size();
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        if(isLow){
            while ( i < totElem) { 
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
                            a.add(r.get(ri));
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.size()) {
                        while (li < l.size()) {
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
                            a.add(r.get(ri));
                            i++;
                            ri++;
                        }
                    }
                    if (ri >= r.size()) {
                        while (li < l.size()) {
                            a.add(l.get(li));
                            li++;
                            i++;
                        }
                    }
                }
            }
        }
        //return a;
    }
}