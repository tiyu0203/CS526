/*
Tiffany Yu
CS526
Term project
*/

import java.util.*;
import java.io.*;

public class ProcessScheduling {


    //Takes in a string which is the filename and inputs the data into a data structure
    //the datastracture created is a hashmap that takes in an integer for the id and then an integer array
    //the method returns the hashmap
    public static Map<Integer, Integer[]> createD (String filename) {
        //initialize hashmap and process id
        Map<Integer, Integer[]> D = new HashMap<Integer, Integer[]>();
        int processID;

        try {
            //creates scanner to read in file
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            //reads in line by line
            while (myReader.hasNextLine()) {
                Integer [] tmpArray = new Integer[3]; //initilaize temporary array to store the data
                if (myReader.hasNextInt()) {
                    //saves the first integer that is in each line as the process id
                    processID = myReader.nextInt();
                    //if process id is not empty then take the rest of the integers in the line as the rest of the data
                    if (processID != 0){
                        //in each array the [0] represents the priority of the process
                        //[1] is arrivalTime
                        //[2] is duration
                        //store the data into the tmp array
                        for (int i = 0; i < tmpArray.length; i++) {
                            tmpArray[i] = myReader.nextInt();
                        }
                        //input the process data into the hashmap
                        D.put(processID, tmpArray);
                    }
                }
                else {
                    myReader.next();
                }
            }
            //close reader
            myReader.close();
        } 
        //error catching
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
  
        //return the hashmap once all the information is placed
        return D;
    }

    //method takes in an integer array and returns the smallest element in the array
    public static int findSmallest(int [] array) {
        //iterates through the array and compares each element to the next one over
        for(int i = 0; i<array.length; i++ ){
            for(int j = i+1; j<array.length; j++){
               if(array[i]>array[j]){
                    //swaps the element if element is larger than the next one over
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
               }
            }
         }
         //since the array is now sorted, the first element should be the smallest
         return array[0];
    }

    //this method takes in the hasmap and then an integer
    //the method returns an array with all of the specified data of the processes based on n
    //since the array stored in the hashmap is (0 - priority, 1 - arrivalTime, 2 - duration) the n is either 0,1,2
    //so the method will return an array of al the priorities, arrivalTime, or duration
    public static int [] getArrayValues(Map<Integer,Integer[]> D, int n) {
        //get the keys of the hashmap (process id)
        Set<Integer> keys =  D.keySet();
        //initialize array in the sie of the amount of keys
        int [] array = new int[D.size()];
        int i = 0; 
        //for each key get the associated array 
        for(Integer key:keys) {
            Integer [] values = D.get(key);
            //store either the priority, arrivalTime, or duration of the process into the array
            array[i] = values[n];
            i++;
        }
        //return the array of priority, arrivalTime, or duration
        return array;       
    }

    //method is to find the earliest time
    //it takes in an array that contains all the arrival times of the processes
    //returns the time of the shortest arrival time
    public static int findEarliest(int [] arrival){
        return findSmallest(arrival);       
    }

    //method takes in the hashmap and two integers
    //this method is to find the ID of either the priority, arrival time, or duration based on the smallest value
    //since the array stored in the hashmap is (0 - priority, 1 - arrivalTime, 2 - duration) the n is either 0,1,2
    //it then returns the ID of the process with the smallest priority, arrival time, or duration
    public static int findID(Map<Integer,Integer[]> D, int n, int smallest) {
        //get the keys of the hashmap (process id)
        Set<Integer> keys =  D.keySet();
        int ID = 0;
        //for each key in the set of keys, get the associated array 
        for(Integer key:keys) {
            Integer [] values = D.get(key);
            //if either the priority, arrival time, or duration euqals the smallest value of the specified then save the key
            if (values[n] == smallest) {
                ID = key; 
            }
        }  
        //return the key, which is the ID of the process
        return ID;   
    }

    //this method prints out the information of all the processes that are contained
    //takes in a hashmap
    public static void printProcesses(Map<Integer,Integer[]> D) {
        //get the keys of the hashmap (process id)
        Set<Integer> keys =  D.keySet();
        //for each key in the set of keys, print out the information 
        for(Integer key:keys) {
            Integer [] values = D.get(key);
            int priority = values[0];
            int duration = values[1];
            int arrival = values[2];
            System.out.println("Id = " + key + ", priority = " + priority + ", duration = " + duration + ", arrival time = " + arrival);
        }
        System.out.println("\n");
    }

    //takes in the processes from the datastructure (hashmap) and adds them to the priority queue one at a time
    //takes in teh priority queue, process id, and array with the priority, duration and arrival time
    //returns the priority queue
    public static PriorityQueue<Process> DtoQ(PriorityQueue<Process> Q, int ID, Integer [] processP) {
        //removes the priority, duration, and arrival time from the array into their own integers
        int priority = processP[0];
        int duration = processP[1];
        int arrival = processP[2];
        //creates a new process and adds it to the priority queue
        Q.add(new Process(ID, priority, duration, arrival));

        //return the priority queue
        return Q; 
    }

    //updates priorities of processes in the priority queue if their wait time is greater than the specified maximum wait time
    //takes in the priority queue, max wait time, and current time
    //returns the priority queue with updated information
    public static PriorityQueue<Process> updatePriority(PriorityQueue<Process> Q, int maxTime, int currentTime){
        //create a clone of the priority queue
        PriorityQueue<Process> newQ = new PriorityQueue<Process>(Q);

        System.out.println("Update Priority: ");
        
        //while the clone of the priority queue is not empty
        while(!newQ.isEmpty()){
            //pop out each proces one by one starting with the process with the smallest priority
            Process p = newQ.poll();
            //get the proceess's information
            int ID = p.getID();
            int Pr = p.getPriority();
            int Duration = p.getDuration();
            int Arrival = p.getarrivalTime();
            //calculate the wait time based on the current time and the process's arrival time
            int waitTime = currentTime - Arrival;

            //if the wait time is greater than the max time, update the priority by decreasing it by one
            if (waitTime > maxTime){
                //create new priority
                int newPr = Pr - 1;
                //remove the old process with the old priority from the original priority queue
                Q.remove(new Process(ID, Pr, Duration, Arrival));
                //print out information of update
                System.out.println("PID = " + ID + ", wait time = " + waitTime + ", current priority = " + Pr);
                System.out.println("PID = " + ID + ", new priority = " + newPr);
                //add a new process with the new priority to the original priority queue
                Q.add(new Process(ID, newPr, Duration, Arrival));
            }            
        }
        
        //return the priority queue
        return Q;
    }

    //takes in current time and the process and prints out the information of the process
    public static void printInfo(Process p, int currentTime){
        int id = p.getID();
        int pr = p.getPriority();
        int duration = p.getDuration();
        int arrivalTime = p.getarrivalTime(); 
        int finishedTime = currentTime + duration; //calculates finished time

        System.out.println("Process id = " + id);
        System.out.println("        Priority = " + pr);
        System.out.println("        Arrival = " + arrivalTime);
        System.out.println("        Duration = " + duration);
        System.out.println("Process " + id + " finished at time " + finishedTime);
        System.out.println("\n");
    }


    public static void main(String args[]) throws FileNotFoundException{
        // Creating a File object that represents the disk file.
        PrintStream o = new PrintStream(new File("process_scheduling_output.txt"));
        //prints out anything from System.out to the file
        PrintStream console = System.out;
        System.setOut(o);
        
        int priorityID = 0;
        int durationID = 1;
        int arrivalID = 2;
        //Read all processes from an input file and store them in an appropriate data structure, D
        Map<Integer,Integer[]> D = ProcessScheduling.createD("process_scheduling_input.txt");
        ProcessScheduling.printProcesses(D);
        //initialize all of the variables 
        int currentTime = 0; 
        int waitTime = 0;
        float totalWaitTime = 0; 
        int finishTime = 0;
        float totalProcesses = 0;
        float averageTime = 0;
        int maxWait = 30; //se the max wait time (the process should not wait longer than this amount of time before their priority is updated)

        boolean running = false; 

        System.out.println("Maximum waite time = " + maxWait);

        //create an empty priority queue Q 
        PriorityQueue<Process> Q = new PriorityQueue<Process>(); 

        //while the hashmap D is not empty
        while(!D.isEmpty()) {
            //find the process with the earliest time
            int earliestP = findEarliest(getArrayValues(D, arrivalID)); //gets the earliest arrival time
            int earliestPID = findID(D, arrivalID, earliestP); //gets the id of the process with the earliest arrival time
            Integer [] processP = D.get(earliestPID); //gets the information array with the priority, duration, and arrivla time of the process with the earliest arrival time
            //if the arrival time is less than or equal to the current time, then insert the process into the priority queue
            if (earliestP <= currentTime){
                Q = DtoQ(Q,earliestPID, processP); //insert the process into the priority queue
                D.remove(earliestPID); //remove the process from the hashmap
            }
            
            //if the priority queue is not empty and the running flag is false (a process is not running)
            if (!Q.isEmpty() && (running == false)){ 
                //takes out the process at the head of the priority queue, which should be the one with the smallest priority   
                Process p = Q.poll();
                //counts the amount of processes 
                totalProcesses++;
                //calculates the wait time
                waitTime = currentTime - p.getarrivalTime();
                //calculates the total wait time
                totalWaitTime += waitTime;
                //prints out the process information
                System.out.println("\n");
                System.out.println("Process removed from queue is: id = " + p.getID() + ", at time " + currentTime + ", wait time = " + waitTime + " Total wait time = " + totalWaitTime);
                printInfo(p, currentTime);
                //calculates the finished time 
                finishTime = currentTime + p.getDuration();
                //sets the running flag to true to indicate that a process is running
                running = true; 
            }
            //incremement the current time to represent one time unit passing
            currentTime++; 
            //If currently running process has finished
            //Set a flag running to false
            //Update priorities of processes that have been waiting longer than max wait time
            if (finishTime == currentTime) {
                running = false;
                Q = updatePriority(Q, maxWait, currentTime);
            }
        }

        //prints out when the Data structure D is empty
        //the time printed has the current time - 1 because the current time is incremented right after the hashmap has been emptied
        //to get the accurate time, 1 must be subtracted
        System.out.println("D becomes empty at time " + (currentTime-1) + "\n");

        // Execute all processes that are still in Q, one at a time
        //while the queue is not empty 
        while(!Q.isEmpty()){
            //if there are no processes running
            if (running == false){
                //takes out the process at the head of the priority queue, which should be the one with the smallest priority   
                Process p = Q.poll();
                //counts the amount of processes
                totalProcesses++;
                //calculates the wait time
                waitTime = currentTime - p.getarrivalTime();
                //calculates the total wait time
                totalWaitTime += waitTime;
                //prints out the process information
                System.out.println("\n");
                System.out.println("Process removed from queue is: id = " + p.getID() + ", at time " + currentTime + ", wait time = " + waitTime + " Total wait time = " + totalWaitTime);
                printInfo(p, currentTime);
                //calculates the finished time 
                finishTime = currentTime + p.getDuration();
                //sets the running flag to true to indicate that a process is running
                running = true;
            }
            //incremement the current time to represent one time unit passing
            currentTime++; 
            //If currently running process has finished
            //Set a flag running to false
            //Update priorities of processes that have been waiting longer than max wait time
            if (finishTime == currentTime) {
                running = false;
                Q = updatePriority(Q, maxWait, currentTime);
            }
        }

        //calculate average wait time
        averageTime = totalWaitTime/totalProcesses;
        //print out the wait time and average wait time
        System.out.println("Total wait time = "+ totalWaitTime);
        System.out.println("Average wait time = " + averageTime);
    }
}
