
import java.util.Arrays;

public class Hw1_p1 {
	//This method takes in an int array and an integary as input
	//It uses a linear search in order to determine if the integer is present in the int array
	//If the integer is in the array, the method then prints out a statement that says the integer is in the array and in which index(ex: 5 is in a[0])
	//if the integer is not found in the array then it prints out that the integer does not exist
	public static void find(int[] a, int x) {
		// implement this method	
		int count = 0; //counts how many integers in the array has been verified
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) {
				System.out.println(x + " is in a[" + i + "]");
			}
			//traverses through the entire int array first and verifies that the entire int array has been traversed 
			//and the integer is not found before printing out the statement that the integer does not exist
			if (a[i] != x){
				count++;
				if (count == (a.length - 1)){
					System.out.println(x + " does not exist" );
				}
			}		
		}
	}
	
	//This method takes in two strings with the assumption that the first is shorter than the second
	//It compares each string character by character to determin if the first is a prefix of the second string
	//the method returns the result as a boolean
	public static boolean isPrefix(String s1, String s2) {
		// implement this method
		boolean result = false; //initialize the boolean variable result
		for (int i = 0; i < s1.length(); i++) {
			//checks if the characters are the same
			if (s1.charAt(i) == s2.charAt(i)) {
				//after the entire first string has been looped through, it checks to see if the ending 
				//character's index matches with the length of the first string
				if (i == (s1.length() - 1)) {
					result = true;
				}
			}

		}
		return result; 
	}
	
	
	public static void main(String[] args) {
		
		int[] a = {5, 3, 5, 6, 1, 2, 12, 5, 6, 1};
		
		find(a, 5);
		find(a, 10);
		System.out.println();
		
		String s1 = "abc";
		String s2 = "abcde";
		String s3 = "abdef";
		
		if (isPrefix(s1,s2)) {
			System.out.println(s1 + " is a prefix of " + s2);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s2);
		}
		
		if (isPrefix(s1,s3)) {
			System.out.println(s1 + " is a prefix of " + s3);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s3);
		}
	}
}
