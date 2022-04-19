import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Hw1_p2 {
	//This method takes in an array of car objects and a string that contains a specific car make
	//the method finds which cars have the specificed make and prints out all of the cars in the array that have the same make
	public static void findByMake(Car[] cars, String make) {
		// implement this method
		int count = 0; //counts how many cars have been verified in the array
		for (int i = 0; i < cars.length; i++) {
			//checks to see if the car make in the array is the same as the specified make
			if (cars[i].getMake().equals(make)){
				System.out.println(cars[i]);
			}
			//verifies that all the car makes in the array did not match with the specified make
			if (cars[i].getMake().equals(make) == false){
				count++;
				if (count == (cars.length - 1)){
					System.out.println("There are no cars with the make " + make);
				}
			}	
		}
	}
	//This method takes in an array of car objects and an int that contains a specific year
	//The method finds which cars were made before the specified year and then prints out all of the cars in the array that are older
	public static void olderThan(Car[] cars, int year) {
		// implement this method
		int count = 0; //counts how many cars have been verified in the array 
		for (int i = 0; i < cars.length; i++) {
			//checks if the car year in the array is less than the specified year (which means the car is older)
			if (cars[i].getYear() < year) {
				System.out.println(cars[i]);
			}
			//verifies that the amount of cars that are in the array are all not older than the specified year
			if (cars[i].getYear() > year){
				count++;
				if (count == (cars.length - 1)){
					System.out.println("There are no cars older than " + year);
				}
			}
		}	
	}
	
	public static void main(String[] args) throws IOException {

		// complete this part
		
		Car cars[] = new Car[10]; 		// create an array of Car objects, cars, of size 10

	
		File file = new File("car_input.txt"); 		// read input file and store 10 car Objects in the array

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
				//reads in the text file line by line
				for (int i =0; i < cars.length; i++) {
					String line = scanner.nextLine();
					//splits the lines to get each word as it's own part
					String[] parts = line.split(", ");
					String make = parts[0]; //saves the make into a string
					int price = Integer.parseInt(parts[1]); //saves price into int
					int year = Integer.parseInt(parts[2]); //saves year into int
					cars[i] = new Car(make, year, price); //create an array with the new car object
				} 
            }
        } catch (IOException e) {
			System.out.println (e.toString());
        }
		System.out.println("\nAll cars:");
		for (int i=0; i<cars.length; i++) {
			System.out.println(cars[i]);
		}
		
		String make = "Honda";
		int year = 2017;
		
		System.out.println("\nAll cars made by " + make);
		findByMake(cars, make);
		System.out.println("\nAll cars made before " + year);
		olderThan(cars, year);
	}

}
