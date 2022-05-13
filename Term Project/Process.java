/*
Tiffany Yu
CS526
Term project
*/
import java.util.*;

//create priority queue using comparable interface
public class Process implements Comparable<Process> {
 
    private int id;
    private int pr;
    private int duration;
    private int arrivalTime; 

    //returns the priority of the process
    public int getPriority() {
        return pr;
    }
    //returns the arrival time of the process
    public int getarrivalTime() {
        return arrivalTime;
    }
    //returns the duration of the process
    public int getDuration() {
        return duration;
    }
    //returns the id of the process
    public int getID() {
        return id;
    }
 
    //Parameterized process constructor
    //takes in the id, priority, duration, and arrival time to create a process
    public Process(int id, int pr, int duration, int arrivalTime) {
        super();
        this.id = id;
        this.pr = pr;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
    }
    
    //overrides the equals class already in the priority queue
    //when removing a process from the queue, this is needed in order to properly remove the process
    //it sees if the process being removed has an id that already exists, then it will return true
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Process)) return false;

        Process process = (Process) o;

        if (id != process.id) return false;

        return true;
    }

    //overrides the compareto class
    //this compares each of the process's priority and then organizes it in the priority queue based on priority
    @Override
    public int compareTo(Process pro) {
        if(this.getPriority() > pro.getPriority()) {
            return 1;
        } else if (this.getPriority() < pro.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }
 
}