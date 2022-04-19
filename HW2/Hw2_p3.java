
/* 
Tiffany Yu
CS 526
Assignment 2
*/

public class Hw2_p3 {

	// implement reverse method
	// you may want to write a separate method with additional parameters, which is recursive
	
	//method takes in a DoubleLinkNode<Integer> node and returns either a null or the node's previous node
	public static DoubleLinkNode<Integer> Reverse(DoubleLinkNode<Integer> node)
	{	
		//If current node is empty return null
		//when it hits the end of the Doubly linked list then it stops the recursion
		if(node.getElement() == null){
			return null;
		}

		//Swap the pointers
		DoubleLinkNode<Integer> temp = node.getNext();
		node.setNext(node.getPrev()); //sets the next node as the previous
		node.setPrev(temp); //sets the previous node as the next one

		//continues to go through the list if there are still things left
		return Reverse(node.getPrev());
	}
	
	//Takes in a Doublylinked<integer> intless
	//doesn't return anything
	public static void reverse(DoublyLinkedList<Integer> intList) {
		// complete this method
		DoubleLinkNode<Integer> header = intList.getHeader(); //gets the header
		Reverse(header.getNext()); //gets the first element which is the one after the header

		DoubleLinkNode<Integer> trailer = intList.getTrailer(); //gets the trailer
		DoubleLinkNode<Integer> temp = header.getNext(); // gets the element next to the header
		//swaps the header and the trailer
		header.setNext(trailer.getPrev()); 
		trailer.setPrev(temp);
		intList.setTrailer(header); //sets the trailer as the head 
	}
	
	// use the main method for testing
	// test with arrays of different lenghts
	public static void main(String[] args) {

		
		DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
		
		int[] a = {10, 20, 30, 40, 50};
		for (int i=0; i<a.length; i++) {
			intList.addLast(a[i]);
		}
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());
		
		intList = new DoublyLinkedList<>();
		int[] b = {10, 20, 30, 40, 50, 60};
		for (int i=0; i<b.length; i++) {
			intList.addLast(b[i]);
		}
		System.out.println();
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());

	}

}
