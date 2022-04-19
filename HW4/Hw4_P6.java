/* 
Tiffany Yu
CS 526
Assignment 4
*/
import java.util.*;
public class Hw4_P6 {
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

    //takes in an integer and returns an integer array of randomly generated from 1 - N 
    public static int [] generateRandom(int N) {
        int[] keys = new int[100000]; //generate 100000 integers
        Random rand = new Random(System.currentTimeMillis()); //to generate random numbers 

        for (int i=0; i<keys.length; i++) {
            int random_integer = -1; //filler number to start
       
            //generate integer while it exists in the array
            while(exists(random_integer, keys)) {
                random_integer = rand.nextInt(N); //if the integer is not already in the array, it is then stored in the array
            }
       
            keys[i] = random_integer;
       }
        return keys;
    }

    //takes in an integer array and hashmap and returns hashmap
    public static HashMap<Integer, String> insertHashMap (int[] keys, HashMap<Integer, String> myMap) {
        //goes through the hashmap and inserts the key one at a time
        for (int i = 0; i < keys.length; i++){
            myMap.put(keys[i], Integer.toString(i));
        }
        return myMap;
    }
    
    //takes in an integer array and arraylist and returns array list
    public static ArrayList<Integer> insertArrayList (int[] keys, ArrayList<Integer> myArrayList) {
        //goes through the arraylist and inserts the key one at a time
        for (int i = 0; i < keys.length; i++) {
            myArrayList.add(keys[i]);
        }
        return myArrayList;
    }

    //takes in an integer array and linkedlist and returns a linkedlist
    public static LinkedList<Integer> insertLinkedList (int[] keys, LinkedList<Integer> myLinkedList) {
        //goes through the arraylist and inserts the key one at a time
        for (int i = 0; i < keys.length; i++) {
            myLinkedList.add(keys[i]);
        }
        return myLinkedList;        
    }

    //takes in an integer array and hashmap and returns the total elapsedtime it took to search through it
    public static long searchHashMap (int[] search, HashMap<Integer, String> myMap) {
        long startTime, endTime, elapsedTime; //initialize the variables
        startTime = System.nanoTime(); //starts the timer

        //goes through the hashmap and searches if there is the key
        for (int i = 0; i < search.length; i++) {
            myMap.containsKey(search[i]);
        }

        endTime = System.nanoTime(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in nanoseconds

        return elapsedTime;
    }

    //takes in an integer array and arraylist and returns the total elapsedtime it took to search through it
    public static long searchArrayList (int[] search, ArrayList<Integer> myArrayList) {
        long startTime, endTime, elapsedTime; //initialize the variables
        startTime = System.nanoTime(); //start the timer

        //goes through the arraylist and searches if there is the key
        for (int i = 0; i < search.length; i++) {
            myArrayList.contains(search[i]);
        }

        endTime = System.nanoTime(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in nanoseconds

        return elapsedTime;
    }

    //takes in an integer array and linkedlist and returns the total elapsedtime it took to searh through it
    public static long searchLinkedList (int[] search, LinkedList<Integer> myLinkedList) {
        long startTime, endTime, elapsedTime; //initialize the variables
        startTime = System.nanoTime(); //start the time

        //goes through the linkedlist and searches if there is the key
        for (int i = 0; i < search.length; i++) {
            myLinkedList.contains(search[i]);
        }

        endTime = System.nanoTime(); //ends the timer
        elapsedTime = endTime - startTime; //calculates the elapsed time in nanoseconds

        return elapsedTime;
    }

    //takes in a long array and returns the calculated average of all the numbers within the array
    public static long calculateAverage(long[] array) {
        long sum = 0; //initialize sum
        //goes through the array and adds each number to the sum as it loops through
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }

        long average = sum/array.length; //divides the sum by the length of the array to calculate the average

        return average;
    }
    //returns a long[] 
    //runs the assignment
    public static long[] insertAndSearchTime() {
        long startTime, endTime;  //initialize variables
        long[] info = new long[7]; //initialize the information array

        HashMap<Integer,String> myMap =new HashMap<Integer,String>(); //Creating HashMap  
        ArrayList<Integer> myArrayList = new ArrayList<Integer>();  //Create an Array list instance
        LinkedList<Integer> myLinkedList = new LinkedList<Integer>();  //Create a Linked list

        int[] insertKeys = generateRandom(1000000); //generate 100,000 distinct random integers in the range [1, 1,000,000] and store them in the array of integers insertKeys[ ]

        info[0] = insertKeys.length; //store the length of the insertKeys array in the first slot of the info array

        //measure total time it takes to insert the keys into the hashmap
        //store the total time in the second slot of the info array
        startTime = System.nanoTime();
        myMap = insertHashMap(insertKeys, myMap);
        endTime = System.nanoTime();
        info[1] = endTime - startTime;

        //measure total time it takes to insert the keys into the arraylist
        //store the total time in the third slot of the info array
        startTime = System.nanoTime();
        myArrayList = insertArrayList(insertKeys, myArrayList);
        endTime = System.nanoTime();
        info[2] = endTime - startTime;

        //measure total time it takes to insert the keys into the linkedlist
        //store the total time in the fourth slot of the info array
        startTime = System.nanoTime();
        myLinkedList = insertLinkedList(insertKeys, myLinkedList);
        endTime = System.nanoTime();
        info[3] = endTime - startTime;

        int[] searchKeys = generateRandom(2000000); //generate 100,000 distinct random integers in the range [1, 2,000,000] and store them in the array searchKeys[ ]. 

        info[4] = searchHashMap(searchKeys, myMap); //measure total time it takes to search for keys in the hashmap and stores it in the fifth slot of the info array
        info[5] = searchArrayList(searchKeys, myArrayList); //measure total time it takes to search for keys in the arraylist and stores it in the sixth slot of the info array
        info[6] = searchLinkedList(searchKeys, myLinkedList); //measure total time it takes to search for keys in the linkedlist and stores it in the seventh slot of the infor array

        //returns the info array
        return info;
    }

    public static void main(String args[]){
        //initializes arrays of size 10 to hold the results
        long[] insertKeySize = new long[10];

        long [] insertHashTime = new long[10];
        long [] insertArrayListTime = new long[10];
        long [] insertLinkedListTime = new long[10];

        long [] searchHashTime = new long[10];
        long [] searchArrayListTime = new long[10];
        long [] searchLinkedListTime = new long[10];

        //runs 10 times and stores the results in the arrays
        for (int i = 0; i < 10; i++) {
            long [] results = insertAndSearchTime();
            //stores the respective result in the specific array
            insertKeySize[i] = results[0];
            insertHashTime[i] = results[1];
            insertArrayListTime[i] = results[2];
            insertLinkedListTime[i] = results[3];
            searchHashTime[i] = results[4];
            searchArrayListTime[i] = results[5];
            searchLinkedListTime[i] = results[6];
        }
        
        //calculates the average of the array to get the average total insertion time and average total search time for each data structure 
        System.out.println("Number of keys = " + calculateAverage(insertKeySize));

        System.out.println('\n');

        System.out.println("Hashmap average total insert time = " + calculateAverage(insertHashTime)); // O(1)
        System.out.println("ArrayList average total insert time = " + calculateAverage(insertArrayListTime)); // O(1) when a new array has to be created and all the elements copied to it, it's O(n)
        System.out.println("LinkedList average total insert time = " + calculateAverage(insertLinkedListTime)); // O(1)

        System.out.println("\n");
        
        System.out.println("HashMap average total search time = " + calculateAverage(searchHashTime)); // O(1)
        System.out.println("ArrayList average total search time = " + calculateAverage(searchArrayListTime)); // O(N)
        System.out.println("LinkedList average total search time = " + calculateAverage(searchLinkedListTime)); // O(N)

    }
}