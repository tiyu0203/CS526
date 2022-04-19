/* 
Tiffany Yu
CS526
Assignment 3
*/
//package nodeTrees; //I commented this out because it prevented it from compiling on my end

import java.util.ArrayList;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	//private int size = 0;

	public IntBST() {	}

	public int size() {
		return size;
	}

	public void setSize(int s) { size = s; }
	
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */
	
	  public void print(Node<Integer> n){ print ("", n); }
	  
	  public void print(String prefix, Node<Integer> n){
		  if (n != null){
			  print(prefix + "       ", right(n));
			  System.out.println (prefix + ("|-- ") + n.getElement());
			  print(prefix + "       ", left(n));
		  }
	  }
	  
	  
	  public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	
	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2 
		if (left(n) != null) 
			snapshot.add(left(n)); 
		if (right(n) != null)
			snapshot.add(right(n)); return snapshot; 
	}
	
	public int height(Node<Integer> n) throws IllegalArgumentException { 
		if (isExternal(n)) { return 0; } 
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n)) h = Math.max(h, height(c)); return h + 1; 
	}
	
	//helper function to split arrays
	//takes in the int array that you want to cut and the start and end of where you want to cut
	//returns the sliced array
    public static int[] subtreeArray(int[] arr, int start, int end)
    {
        // Get the slice of the Array
        int[] array_slice = new int[end - start]; //initializes the int array
  
        // Copy elements of array to slice
        for (int i = 0; i < array_slice.length; i++) {
            array_slice[i] = arr[start + i];
        }
  
        // return the slice
        return array_slice;
	}

	//Complete this method
	//takes in integary array and returns the tree
	//we assume that the integer array taken in is always 2^k -1
	public static IntBST makeBinaryTree(int[] a){
		//if there is only one element in the array 
		if (a.length == 1) {
			IntBST t = new IntBST();
			Node<Integer> r = t.addRoot(a[0]); //adds the only element as the root
			return t;
		}
		
		//we want to split the array into three parts: left subarray, root, right subarray
		int mid = a.length/2; //get the index of the middle of the array
		int tree_root = a[mid]; //gets the root of the tree
		
		//gets the indexes for the left and right subarray
		int left_end = mid; 
		int right_begin = mid + 1;



		IntBST t = new IntBST();
		Node<Integer> r = t.addRoot(tree_root); //adds root to tree

		//adds the leaf nodes to the root 
		if (mid ==1) {
			Node<Integer> lr = t.addLeft(r, a[0]);
			Node<Integer> rr = t.addRight(r, a[2]);
			
		}
		//otherwise add the subtrees to the root node
		else {
			//creates the subarrays for the left and right subtrees
			int [] left_subarray = subtreeArray(a, 0, left_end);
			int [] right_subarray = subtreeArray(a, right_begin, (a.length));

			//create the subtrees by calling the method again and using the arrays
			NodeBinaryTree<Integer> lt = makeBinaryTree(left_subarray);
			NodeBinaryTree<Integer> rt = makeBinaryTree(right_subarray);
	
			t.attach(r, lt, rt); //attaches the subtrees to the root
		}

		return t; //returns the tree
	}

}
