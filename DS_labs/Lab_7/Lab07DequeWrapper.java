public class Lab07DequeWrapper {
	
	public static interface Deque<E> {
		public void addFirst(E elm);
		public void addLast(E elm);
		public E removeFirst();
		public E removeLast();
		public E getFirst();
		public E getLast();
		public boolean removeFirstOccurrence(E elm);
		public boolean removeLastOccurrence(E elm);
		public int size();
		public boolean isEmpty();
		
		//DO NOT USE THIS IN EXERCISES
		public String[] toArray(); //DO NOT REMOVE, TEST WILL FAIL
	}

	/**
	 * Implementation of Deque ADT using a Circular Doubly-Linked Queue
	 * @author Fernando J. Bermudez & Juan O. Lopez Gerena
	 * @param <E>
	 */
	public static class CircularDoublyLinkedQueque<E> implements Deque<E>{

		private class Node<E>{
			private E element;
			private Node<E> next, prev;

			public Node(E elm, Node<E> next, Node<E> prev) {
				this.element = elm;
				this.next = next;
				this.prev = prev;
			}
			public Node(E elm, Node<E> next) {this(elm, next, null);}

			public Node(E elm) {this(elm, null, null);}

			public Node() {this(null, null, null);}
			
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
			public Node<E> getPrev() {
				return prev;
			}
			public void setPrev(Node<E> prev) {
				this.prev = prev;
			}
			public void clear() {
				next = null;
				prev = null;
				element = null;
			}
		} //End of Node Class
		
		

		private Node<E> header; 
		private int currentSize;

		public CircularDoublyLinkedQueque() {
			header = new Node<>(null, header, header);
			currentSize = 0;
		}

		@Override
		public void addFirst(E elm) {
			/*ADD YOUR CODE HERE*/
			Node<E> added = new Node<E>(elm, this.header.getNext(), this.header);

			this.header.getNext().setPrev(added);
			this.header.setNext(added);

			currentSize++;
		}

		@Override
		public void addLast(E elm) {
			/*ADD YOUR CODE HERE*/
			Node<E> added = new Node<E>(elm, this.header, this.header.getPrev());

			this.header.getPrev().setNext(added);
			this.header.setPrev(added);
			
			currentSize++;
		}

		@Override
		public E removeFirst() {	
			/*ADD YOUR CODE HERE*/

			Node<E> to_remove = this.header.getNext();

			this.header.setNext(to_remove.getNext());
			to_remove.getNext().setPrev(this.header);
			to_remove.setNext(null);
			to_remove.setPrev(null);
			currentSize--;
			return to_remove.getElement();
		}

		@Override
		public E removeLast() {
			/*ADD YOUR CODE HERE*/
			Node<E> to_remove = this.header.getPrev();

			this.header.setPrev(to_remove.getPrev());
			to_remove.getPrev().setNext(this.header);
			to_remove.setNext(null);
			to_remove.setPrev(null);
			currentSize--;
			return to_remove.getElement();
		}

		@Override
		public E getFirst() {
			/*ADD YOUR CODE HERE*/
			return this.header.getNext().getElement();
		}

		@Override
		public E getLast() {
			/*ADD YOUR CODE HERE*/
			return this.header.getPrev().getElement();
		}

		@Override
		public boolean removeFirstOccurrence(E elm) {
			/*ADD YOUR CODE HERE*/
			Node<E> curr = this.header.getNext();

			while( curr != null){

				if(curr.getElement().equals(elm)){
					curr.getPrev().setNext(curr.getNext());
					curr.getNext().setPrev(curr.getPrev());
					curr.clear();
					currentSize--;
					return true;

				}else{
					curr = curr.getNext();
				}
			}

			return false;
		}

		@Override
		public boolean removeLastOccurrence(E elm) {
			/*ADD YOUR CODE HERE*/
			Node<E> curr = this.header.getPrev();

			while(curr != null){

				if(curr.getElement().equals(elm)){
					curr.getPrev().setNext(curr.getNext());
					curr.getNext().setPrev(curr.getPrev());
					curr.clear();
					currentSize--;
					return true;
					
				}else{
					curr = curr.getPrev();
				}
			}

			return false;
		}

		@Override
		public int size() {
			/*ADD YOUR CODE HERE*/
			int counter = 0;
			Node<E> curr = this.header.getNext();

			while(curr != null){
				counter++;
				curr = curr.getNext();
			}

			return counter;
		}

		@Override
		public boolean isEmpty() {
			/*ADD YOUR CODE HERE*/
			if(this.header.getNext() != null){
				return true;
			}else{
				return false;
			}

		}

		//DO NOT USE THIS IN EXERCISES
		//DO NOT DELETE, TESTS WILL FAILS
		@Override
		public String[] toArray() {
			String[] arr = new String[size()];

			Node<E> curNode = header.getNext();
			for (int i = 0; curNode != header; curNode = curNode.getNext(), i++) {
				arr[i] = (String) curNode.getElement();
			}
			return arr;
		}
	}
}

