import java.util.Iterator;

public class LinkedListWrapper {
	public static interface List<E>{

		/*METHODS SPECIFIED IN SECTION 2.4*/
		public void add(E elm);
		public void add(E elm, int index);
		public boolean remove(int index);
		public E get(int index);
		public E set(E elm, int index);
		public int size();


		/*METHODS TODO FOR THIS EXERCISE*/
		public boolean remove(E elm);
		public int removeAll(E elm);
		public void clear();
		public boolean contains(E elm);
		public E first();
		public E last();
		public int firstIndex(E elm);
		public int lastIndex(E elm);
		public boolean isEmpty();


	}

	public static class LinkedList<E> implements List<E>{
		
/////////////////*DO NOT ALTER THIS CODE//////////////////////////////////////
		private Node<E> head;
		private int currentSize;


		private class Node<E>{
			private E element;
			private Node<E> next;
			
			public Node(E elm, Node<E> next) {
				this.element = elm;
				this.next = next;
			}
			
			public Node(E elm) {
				this(elm, null);
			}
			
			public Node() {
				this(null, null);
			}

			public E getElement() {
				return element;
			}

			public void setElement(E element) {
				this.element = element;
			}

			public Node<E> getNext() {
				return next;
			}

			public void setNext(Node<E> next) {
				this.next = next;
			}
			
			public void clear() {
				element = null;
				next = null;
			}	
		} //End of Node class
		
		public LinkedList() {
			currentSize = 0;
		}		

		@Override
		public void add(E elm) {
			// just add at the end of the list. 
			add(elm, currentSize);
		}

		@Override
		public void add(E elm, int index) {
			if (index < 0 || index > currentSize)
				throw new IndexOutOfBoundsException("Invalid index for ADD operation, index = " + index); 

			// if here, then the index is valid. 
			Node<E> newNode = new Node<>(elm);      // the new node to be linked to the LL
			if (index == 0) {              // if true, then the new node shall become the new first
				newNode.setNext(head);  // Notice that the previous condition is also true if size==0. Why?
				head = newNode; 
			}
			else {                                  // index > 0
				Node<E> prev = findNode(index-1);    // find node preceding location for insertion of new node
				newNode.setNext(prev.getNext());   // properly inserting the new node between prev and its next
				prev.setNext(newNode);             // properly inserting the new node between prev and its next
			} 

			currentSize++; 

		}

		@Override
		public boolean remove(int index) {
			if (index < 0 || index >= currentSize)
				throw new IndexOutOfBoundsException("Invalid index for REMOVE operation, index = " + index); 

			// if here, then the index is valid. 
			Node<E> ntr = null;       // node to remove
			if (index == 0) {      // need to remove the first node and return its element.  
				ntr = head; 
				head = head.getNext(); 
			} 
			else { 
				Node<E> prev = findNode(index-1);   // node preceding the one to remove, why index - 1?
				ntr = prev.getNext();               // node to remove, notice that we find the predecessor node to the one we want to remove  
				prev.setNext(ntr.getNext());        // disconnect the node to remove from the List by deviating the predecessor node's next
				// to the node to remove's next node
			}
			currentSize--;  //Decrement currentSize
			ntr.clear();    // Help the GC  
			return true;	//return element in the node (see clear() in Node)

		}


		@Override
		public E get(int index) {
			if (index < 0 || index >= currentSize)
				throw new IndexOutOfBoundsException("Invalid index for GET operation, index = " + index); 

			// if here, then the index is valid. 
			Node<E> targetNode = findNode(index); 
			return targetNode.getElement();
		}

		@Override
		public E set(E elm, int index) {
			if (index < 0 || index >= currentSize)
				throw new IndexOutOfBoundsException("Invalid index for SET operation, index = " + index); 

			// if here, then the index is valid. 
			Node<E> targetNode = findNode(index);   // node whose element is to be replaced
			E etr = targetNode.getElement();        // element to be replaced
			targetNode.setElement(elm);               // replace current element by e
			return etr;                             // return the replaced element
		}

		@Override
		public int size() {
			return currentSize;
		}
		
		private Node<E> findNode(int index) { 
			// pre: index is valid; that is: index >= 0 and index < size. 
			Node<E> target = head; 
			for (int i=0; i<index; i++)
				target = target.getNext(); 

			return target;    // node representing position index in the list
		}
		
//////////*TODO ADD YOUR CODE HERE TO EXTEND IMPLEMENTATION*//////////////////////

		/**
		 * Method that removes the first copy of the given element on the list
		 * 
		 * @param elm - element to search for and remove
		 * @return true if element was found and removed, false otherwise
		 */
		@Override
		public boolean remove(E elm) {
            for(int i = 0; i < this.size() - 1; i++){
                
                if(this.get(i).equals(elm)){
                    this.remove(i);
                    return true;
                }

            }
			return false;
		}
		
		/**
		 * Method that removes all copies of a given element
		 * @param elm - Element to remove
		 * @return the number of copies it removed
		 */
		@Override
		public int removeAll(E elm) {
			/* We used the following in ArrayList, and it would also work here,
			 * but it would have running time of O(n^2).
			 * 
			 * while (remove(obj))
			 * 		counter++;
			 * 
			 * DO NOT DO THIS IMPLEMENTATION, make it O(n)
			 */
			int copies = 0;
			Node<E> previous = new Node<E>();
			Node<E> current = this.head;

			while(current != null){
				
				if(current.getElement().equals(elm)){
					previous.setNext(current.getNext());
					previous.setElement(current.getNext().getElement());
					current = current.getNext();
					copies++;
				}else{
					current = current.getNext();
				}
			}

			return copies;
		}

		/**
		 * Method that empties the list and resets currentSize to 0
		 */
		@Override
		public void clear() {
            while(this.size() > 0){
                this.remove(0);
            }
		}

		/**
		 * Method that return true if and only if the given element is in the list
		 * This method CANNOT alter the contents of the list, nor move them out of order.
		 * 
		 * @param elm - Element to search for
		 * @return True if element is in the list, false otherwise
		 */
		@Override
		public boolean contains(E elm) {
            for(int i = 0; i < this.size() - 1; i++){
                if(this.get(i).equals(elm)){
                    return true;
                }
            }
			return false;
		}

		/**
		 * Method that return the first element in the list 
		 * @return First element in the list
		 */
		@Override
		public E first() {
			return this.head.element;
		}

		/**
		 * Method that return the last element in the list 
		 * @return Last element in the list
		 */
		@Override
		public E last() {
			return this.get(this.size() - 1);
		}

		/**
		 * Method that return the index of the first occurrence 
		 * of the element given as parameter in the list.
		 * 
		 *  If the element is not present, return -1.
		 *  
		 * @param elm - Element to search for
		 * @return  Index of the first occurrence of element in the list
		 */
		@Override
		public int firstIndex(E elm) {	
            for(int i = 0; i < this.size() - 1; i++){
                if(this.get(i).equals(elm)){
                    return i;
                }
            }
			return -1;
		}
		
		/**
		 * Method that return the index of the last occurrence 
		 * of the element given as parameter in the list.
		 *
		 *  If the element is not present, return -1.
		 *  
		 * @param elm - Element to search for
		 * @return  Index of the last occurrence of element in the list
		 */

		@Override
		public int lastIndex(E elm) {
			for(int i = this.size() - 1; i >= 0; i--){
                if(this.get(i).equals(elm)){
                    return i;
                }
            }
			return -1;
		}

		/**
		 * Method that returns true if there are no elements added in the list
		 * @return True if list is empty
		 */
		@Override
		public boolean isEmpty() {

			return (this.size() == 0);
		}
	}

	public static void main(String args[]){

		LinkedList<Integer> nums = new LinkedList<Integer>();

		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(3);
		nums.add(4);
		nums.add(5);

		nums.removeAll(3);

		System.out.println(nums);
	}
}
