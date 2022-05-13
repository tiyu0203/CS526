/*
Tiffany Yu
CS526
HW6
 */
import java.util.*;
import java.io.*;

public class Hw6_p5 {
    //class to create adjacencyList
    public class AdjacencyList {

        Node firstPerson; //creates a node for each person
        ArrayList<Node> relationship; //create an arraylist of nodes 
    
        //create adjacency list
        public AdjacencyList() {
            relationship = new ArrayList<Node>();

        }

        //takes in the Node and adds it to the adjacency list
        public void addPerson(Node firstPerson) {
            relationship.add(firstPerson);
        }

    }	

    //class to create nodes
    static class Node{			
        private String person; //each person is a string
        private ArrayList<String> followers; //creates an arraylist for the followers of each person
        
        //creates nodee and takes in a string to create the node
        //initializes the followers arraylist for each person
		public Node(String person){
            this.person = person;
            followers = new ArrayList<String>();
		}
		
		//Get method to get the person as a string
		public String getPerson(){
			return person;
		}
		
		//Get method to get the followers of the person
		public ArrayList<String> getfollowers(){
			return followers;
		}
		
		//takes in a string and adds the followers into the array list of followers
		public void addFollower(String follower){
			followers.add(follower);
		}

    }
    
    //method that creates an adjacencylist
    //returns the adjacencylist
    //used because normally can't call a non-static method within a static method so this is a helper function
    public AdjacencyList createAdjacencyList(){
        AdjacencyList adjList = new AdjacencyList();
        return adjList;
    }

    //takes in a string that is the file name
    //reads the file and then creates the adjacency list based on the information given
    //returns the adjacency list
    public static AdjacencyList readFile(String filename) {
        //initializes and creates the adjacency list
        Hw6_p5 graph = new Hw6_p5();
        AdjacencyList adjList = graph.createAdjacencyList();

        try {
            //creates scanner to read in file
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            //while the file has a next line, it strips each line of the commas and then stores each person as a string array
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replace(",", "");
                String [] people = line.split(" ");

                Node person = new Node(people[0]); //creates a node for each person

                //adds the rest of the string as the followers for the node
                for (int i = 1; i<people.length; i++){
                    person.addFollower(people[i]);
                }

                //adds the person as a node into the adjacency list
                adjList.addPerson(person);
                }

            //closes reader
            myReader.close();
        } 

        //error catching
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return adjList; //returns the adjacency list
    }

    //method receives a person X as a string and an adjacency list
    //it prints out all people X directly follows and all people X indirectly follows
    public static void allFollows(String person, AdjacencyList adjList){
        //initializes array list of strings and empty strings
        ArrayList<String> followers = new ArrayList<String>();
        ArrayList<String> tmp = new ArrayList<String>();
        ArrayList<String> indirectFollowers = new ArrayList<String>();

        String directFollows = "";
        String indirectFollows = "";

        //gets the people that person X directly follows
        for (Node n: adjList.relationship){
            if (n.getPerson().equals(person)){

                followers = n.getfollowers();

                //converts the array list into a string 
                String followersString= followers.toString();
                //removes the brackets from the string
                directFollows= followersString.substring(1, followersString.length()-1);

            }
        }

        //gets the people that person X indirectly follows 
        for (String p: followers){ //for each person that person X directly follows
            for (Node n: adjList.relationship){
                if (n.getPerson().equals(p)){

                    tmp = n.getfollowers(); //get the people that they(the people person X directly follow) directly follow

                }

                //for each person in the direct follower's direct followers
                for (String f: tmp){
                    //if that person is not in the directfollowers array list and not already in the indirect followers array list
                    if(!indirectFollowers.contains(f) && !followers.contains(f)){

                        indirectFollowers.add(f);//add the person into the indirect followers array list

                    }
                }
            }
        }

        //converts the array list into a string 
        String indirectFollowersString= indirectFollowers.toString();
        //removes the brackets from the string
        indirectFollows= indirectFollowersString.substring(1, indirectFollowersString.length()-1);

        //print out who Person X directly and indirectly follows
        System.out.println(person + " directly follows {" + directFollows +"}");
        System.out.println(person + " indirectly follows {" + indirectFollows + "}");

    }

    public static void main(String args[]) {
        //read in textfile and create adjacency list
        AdjacencyList adjList = readFile("follows_input.txt");

        //invoke the allFollows method multiple times with different arguments to test the method
        allFollows("A", adjList);
        System.out.println("\n");
        allFollows("B", adjList);
        System.out.println("\n");
        allFollows("C", adjList);
        System.out.println("\n");
        allFollows("D", adjList);
        System.out.println("\n");
        allFollows("E", adjList);
        System.out.println("\n");
        allFollows("F", adjList);
        System.out.println("\n");
        allFollows("G", adjList);


    }

}
