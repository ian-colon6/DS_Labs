import java.util.EmptyStackException;
import java.util.Iterator;

import org.w3c.dom.css.Counter;


public class Lab07P2Wrapper {

	public static interface Stack<E> {
		public void push(E newEntry);
		public E pop();
		public E top();
		public boolean isEmpty();
		public int size(); 
		public void clear();
	}

	public static class SinglyLinkedStack<E> implements Stack<E> {

		private static class Node<E> {
			private E element; 
			private Node<E> next; 

			public Node(E elm, Node<E> next) { 
				this.element = elm; 
				this.next = next; 
			}

			public Node(E data) { 
				this(data, null);
			}

			public Node() { 
				this(null, null);
			}

			public E getElement() {
				return element;
			}

			public Node<E> getNext() {
				return next;
			}


			public void setElement(E elm) {
				this.element = elm;
			}

			public void setNext(Node<E> next) {
				this.next = next;
			}

			public void clear() {  // no need to return data
					element = null; 
					next = null; 
			}

		}

		// instance variables for the stack object

		private Node<E> header; //Note that this is NOT a dummy header 
		private int currentSize; 

		public SinglyLinkedStack() { 
			header = new Node<>(); 
			currentSize = 0; 
		}

		@Override
		public void push(E newEntry) {
			Node<E> nNode = new Node<>(newEntry, header.getNext()); 
			header.setNext(nNode); 
			currentSize++; 
		}

		@Override
		public E pop() {
			E etr = top(); 
			Node<E> ntr = header.getNext(); 
			header.setNext(ntr.getNext()); 
			currentSize--; 
			ntr.clear();
			ntr = null;
			return etr;
		}

		@Override
		public E top() {
			if (isEmpty()) 
				throw new EmptyStackException(); 
			return header.getNext().getElement();
		}

		@Override
		public boolean isEmpty() {
			return size() == 0;
		}

		@Override
		public int size() {
			return currentSize;
		}

		@Override
		public void clear() {
			while (size() > 0) pop(); 
		}

	}

	/**
	 * Implement a non-member method for the Stack ADT called stackSort. 
	 * The function takes as a parameter an array of integers and returns the array sorted in increasing order.
	 * 
	 * For example consider we have an array A = {9, 11, 15, 11, 1, -1, 3, 11}
	 * In order to sort values, we will use two stacks which will be called the left and right stacks. 
	 * The values in the stacks will be sorted (in non-descending order) and the values in the left stack 
	 * will all be less than or equal to the values in the right stack. 
	 * 
	 * The following example illustrates a possible state for our two stacks:
	 * 
	 * 				left		right
	 * 				[  ]		[  ]
	 * 				[  ]		[ 9]
	 * 				[ 3]		[11]
	 * 				[ 1]		[11]
	 * 				[-1]		[15]
	 * 
	 * Notice that the values in the left stack are sorted so that the smallest value is at the bottom of the stack. 
	 * The values in the right stack are sorted so that the smallest value is at the top of the stack. 
	 * If we read the values up the left stack and then down the right stack, we get:
	 * 			A = {-1, 1, 3, 9, 11, 11, 11, 15}
	 * which is in sorted order.
	 * 
	 * 
	 * Consider the following cases, using the example shown above as a point of reference, to help you design your algorithm:
	 * 		1) If we were to insert the value 5, it could be added on top of either stack and the collection would remain sorted. 
	 * 		   What other values, besides 5, could be inserted in the  example without having to move any values?
	 * 
	 * 		2) If we were to insert the value 0, some values must be moved from the left stack to the right stack before we could actually insert 0. 
	 * 		   How many values must actually be moved?
	 * 
	 *		3) If we were to insert the value 11, first some values must be moved from the right stack to the left stack. 
	 *		   How many values must actually be moved?
	 *		   What condition should we use to determine if enough values have been moved in either of the previous two cases?
	 *		   
	 * YOU MUST USE TWO STACKS, IMPLEMENTATIONS THAT USE Arrays.sort(); 
	 * OR ANY SORTING ALGORITHM (BubbleSort, SelectionSort, etc.) WILL NOT BE GIVEN CREDIT
	 * 
	 * @param array
	 * @return Sorted array using two stacks
	 */
	public static int[] stackSort(int[] array) {
		/*ADD YOUR CODE HERE*/
		int[] sorted = new int[array.length];
		int temp;
		SinglyLinkedStack<Integer> left = new SinglyLinkedStack<>();
		SinglyLinkedStack<Integer> right = new SinglyLinkedStack<>();

		for(int i = 0; i < array.length; i++){
			left.push(array[i]);
		}

		while(!left.isEmpty()){

			temp = left.top();
			left.pop();

			while(!right.isEmpty() && temp > right.top() ){

				left.push(right.top());
				right.pop();

			}
			right.push(temp);
		}

		int count = 0;
		while(!right.isEmpty()){
			sorted[count] = right.top();
			right.pop();
			count++;
		}

		
		return sorted;
	}

	public static void main(String args[]){

		int[] nums = {0, 4, 5, 3};

		System.out.println("Unsorted");

		for(int i = 0; i < nums.length; i++){
			System.out.println(nums[i]);
		}

		int[] sorted = stackSort(nums);

		System.out.println("Sorted");

		for(int i = 0; i < sorted.length; i++){
			System.out.println(sorted[i]);
		}
	}
}


