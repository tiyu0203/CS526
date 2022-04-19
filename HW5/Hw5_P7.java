/*
Tiffany Yu
CS526
HW5
*/
import java.util.*;
import java.io.*;

public class Hw5_P7{
    //takes in integer and integer array
    //returns a boolean
    public static boolean exists(int number, int[] array) {
        //default integer that is input is -1 in the generateRandom method
        if (number == -1)
            return true; 

        //returns true if the integer is found
        for (int i=0; i<array.length; i++) {
            if (number == array[i])
                return true;
        }
        //returns false if not found
        return false;
    }

    //takes in an integer and returns an integer array size N of randomly generated from 1 - 1000000
    public static int [] generateRandom(int N) {
        int[] randInts = new int[N]; //generate N integers
        Random rand = new Random(System.currentTimeMillis()); //to generate random numbers 

        for (int i=0; i<randInts.length; i++) {
            int random_integer = -1; //filler number to start
       
            //generate integer while it exists in the array
            while(exists(random_integer, randInts)) {
                random_integer = rand.nextInt(1000000); //if the integer is not already in the array, it is then stored in the array
            }
       
            randInts[i] = random_integer;
       }
        return randInts;
    }
    //merge and mergeSort were taken from the following source:
    //taken from https://www.baeldung.com/java-merge-sort
    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        
            int i = 0, j = 0, k = 0;
            while (i < left && j < right) {
                if (l[i] <= r[j]) {
                    a[k++] = l[i++];
                }
                else {
                    a[k++] = r[j++];
                }
            }
            while (i < left) {
                a[k++] = l[i++];
            }
            while (j < right) {
                a[k++] = r[j++];
            }
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
    
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
    
        merge(a, l, r, mid, n - mid);
    }

    //partition and quickSort were taken from the following source:
    //https://www.baeldung.com/java-quicksort
    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);
    
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
    
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
    
        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;
    
        return i+1;
    }
    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
    
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    //heapSort and heapify was taken from the following source:
    //https://www.geeksforgeeks.org/java-program-for-heap-sort/
    public static void heapSort(int arr[])
    {
        int n = arr.length;
  
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
  
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
  
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(int arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
  
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
  
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
  
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
  
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    //insertionSort was taken from the textbook
    //takes in int [] array and returns the elapsed time as a long it took to run the insertion sort
    //Insertion-sort of an array of characters into nondecreasing order
    public static long  insertionSort(int[] array) {
        long startTime, endTime, elapsedTime; //initialize the variables
        startTime = System.currentTimeMillis(); //start the timer
        for (int i = 1; i < array.length; i++) {    //begin with second character
            int cur = array[i];                     //time to insert cur=array[i]
            int j = i;                              //find correct index j for cur
            while (j > 0 && array[j-1] > cur) {     //thus, data[j-1] must go after cur
                array[j] = array[j-1];              //slide data[j-1] rightward
                j--;                                //and consider previous j for cur
            }
            array[j] = cur;                         //this is the proper place for cur   
        }
        endTime = System.currentTimeMillis(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in milliseconds

        return elapsedTime;
    }

    //takes in an int [] array and returns the elapsed time as a long it takes to run the quicksort
    public static long quickSortTime(int[] array){
        long startTime, endTime, elapsedTime; //initialize the variables

        startTime = System.currentTimeMillis(); //start the timer
        quickSort(array, 0, array.length-1); //uses quick sort to sort the array
        endTime = System.currentTimeMillis(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in milliseconds

        return elapsedTime;
    }

    //takes in an int [] array and returns the elapsed time as a long it takes to run the mergesort
    public static long mergeSortTime(int [] array) {
        long startTime, endTime, elapsedTime; //initialize the variables

        startTime = System.currentTimeMillis(); //start the timer
        mergeSort(array, array.length); //uses merge sort to sort the array
        endTime = System.currentTimeMillis(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in milliseconds

        return elapsedTime;
    }

    //takes in an int [] array and returns the elapsed time as a long it takes to run the heapsort
    public static long heapSortTime(int [] array) {
        long startTime, endTime, elapsedTime; //initialize the variables

        startTime = System.currentTimeMillis(); //start the timer
        heapSort(array); //uses heap sort to sort the array
        endTime = System.currentTimeMillis(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in milliseconds
        
        return elapsedTime;
    }
    public static void main(String args[]) {
        int [] n = new int[10]; //initialize int [] array

        //initialize constants
        int val = 0;
        int constant = 10000;

        //generate the n array of [10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000]
        for (int i = 0; i<n.length; i++) {
            val = val + constant; 
            n[i] = val;
        }

        //initializes the long [] arrays to hold the time it took for the sorts
        long [] insertionTime = new long[n.length];
        long [] mergeTime = new long[n.length];
        long [] quickTime = new long[n.length];
        long [] heapTime = new long[n.length];

        //for every n, generate that amount of integers and sort the array
        for (int i = 0; i < n.length; i++){
            int [] array = generateRandom(n[i]); //generate integer array of random integers

            int [] tmp = array.clone(); //create a clone of the array so that the original does not get sorted
            //Run insertionsort and calculate the elapsed time 
            insertionTime[i] = insertionSort(tmp);

            int []tmp1 = array.clone(); //create a clone of the array so that the original does not get sorted
            //Run mergesort and calculate the elapsed time
            mergeTime[i] = mergeSortTime(tmp1);
            
            int [] tmp2 = array.clone(); //create a clone of the array so that the original does not get sorted
            //Run quicksort and calculate the elapsed time
            quickTime[i] = quickSortTime(tmp2);

            int [] tmp3 = array.clone(); //create a clone of the array so that the original does not get sorted
            // //Run heapsort and calculate the elapsed time
            heapTime[i] = heapSortTime(tmp3);

        }


    }
}