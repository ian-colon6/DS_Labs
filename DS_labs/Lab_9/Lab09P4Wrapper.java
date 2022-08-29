import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class Lab09P4Wrapper {
	
	public static interface TreeNode<E> {
		public E getValue();
	}

	public static class BinaryTreeNode<E> implements TreeNode<E> {

		private E value;
		private BinaryTreeNode<E> leftChild;
		private BinaryTreeNode<E> rightChild;
		private BinaryTreeNode<E> parent;

		
		public BinaryTreeNode(E value) {
			super();
			this.value = value;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;

		}

		
		public BinaryTreeNode(E value, BinaryTreeNode<E> parent, BinaryTreeNode<E> leftChild, BinaryTreeNode<E> rightChild) {
			super();
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
		}

		@Override
		public E getValue() {
			return this.value;

		}


		public BinaryTreeNode<E> getLeftChild() {
			return leftChild;
		}


		public void setLeftChild(BinaryTreeNode<E> leftChild) {
			this.leftChild = leftChild;
		}


		public BinaryTreeNode<E> getRightChild() {
			return rightChild;
		}


		public void setRightChild(BinaryTreeNode<E> rightChild) {
			this.rightChild = rightChild;
		}


		public void setValue(E value) {
			this.value = value;
		}


		public BinaryTreeNode<E> getParent() {
			return parent;
		}


		public void setParent(BinaryTreeNode<E> parent) {
			this.parent = parent;
		}

	}

	public static interface Tree<E> {
		public TreeNode<E> root();
		public TreeNode<E> left(TreeNode<E> p);
		public TreeNode<E> right(TreeNode<E> p);
		public TreeNode<E> sibling(TreeNode<E> p); 
		public boolean isEmpty();
		public int size();
		public boolean verifyCousins(E e1, E e2);
	}	

	public static class BinaryTree<E> implements Tree<E> {
		
		private BinaryTreeNode<E> root;
		

		
		public BinaryTree(BinaryTreeNode<E> root) {
			super();
			this.root = root;
		}
		
		public BinaryTree(BinaryTreeNode<E> root, BinaryTree<E> T1, BinaryTree<E> T2) {
			super();
			this.root = root;
			if (T1 != null) {
				BinaryTreeNode<E> temp = (BinaryTreeNode<E>)T1.root();
				this.root.setLeftChild(temp);
				temp.setParent(this.root);
				
			}
			if (T2 != null) {
				BinaryTreeNode<E> temp = (BinaryTreeNode<E>)T2.root();

				this.root.setRightChild(temp);
				temp.setParent(this.root);

			}

		}



		@Override
		public TreeNode<E> root() {
			return this.root;
		}


		private void check(TreeNode<E> p) {
			if (p==null) {
				throw new IllegalArgumentException();
			}
		}
		@Override
		public TreeNode<E> left(TreeNode<E> p) {
			this.check(p);
			BinaryTreeNode<E> temp = (BinaryTreeNode<E>)p;
			return temp.getLeftChild();
		}


		@Override
		public TreeNode<E> right(TreeNode<E> p) {
			this.check(p);
			BinaryTreeNode<E> temp = (BinaryTreeNode<E>)p;
			return temp.getRightChild();

		}

		@Override
		public TreeNode<E> sibling(TreeNode<E> p) {
			this.check(p);
			BinaryTreeNode<E> temp = (BinaryTreeNode<E>)p;
			if (temp.getParent().getLeftChild() != temp) {
				return temp.getRightChild();
			}
			else {
				return temp.getLeftChild();
			}

		}
		
		@Override
		public boolean isEmpty() {
			return this.size() == 0;
		}

		@Override
		public int size() {
			return this.sizeAux(this.root);
		}

		private int sizeAux(BinaryTreeNode<E> N) {
			if (N == null) {
				return 0;
			}
			else {
				return 1 + this.sizeAux(N.getLeftChild()) + this.sizeAux(N.getRightChild());
			}
			
		}

		
		public void print() {
			this.printAux(this.root, 0);
		}

		private void printAux(BinaryTreeNode<E> N, int i) {
			if (N != null) {
				this.printAux(N.getRightChild(), i + 4);
				for (int j=0; j < i; ++j) {
					System.out.print(" ");
				}
				System.out.println(N.getValue());
				this.printAux(N.getLeftChild(), i + 4);
			}
			
		}
		
		private BinaryTreeNode<E> find(E e, BinaryTreeNode<E> N) {
			if (N == null) {
				return null;
			}
			else if (N.getValue().equals(e)) {
				return N;
			}
			else {
				BinaryTreeNode<E> temp = find(e, N.getLeftChild());
				if (temp != null) {
					return temp;
				}
				else {
					return find(e, N.getRightChild());
				}
	 		}
		}


		@Override
		public boolean verifyCousins(E e1, E e2) {
			/*ADD YOUR CODE HERE*/
			BinaryTreeNode<E> temp1 = find(e1, this.root);
			BinaryTreeNode<E> temp2 = find(e2, this.root);

			if( temp1.getParent().equals(temp2.getParent()) ){ return true; }

			else if(this.Depth(e1, this.root) == this.Depth(e2, this.root)){ return true; }

			else{ return false; }
		}

		public int Depth(E e, BinaryTreeNode<E> root){
			
			if( root == null ){ return 0; }

			else if( root.getLeftChild() == null && root.getRightChild() == null ){ return 0; }

			else if( root.getValue().equals(e) ){ return 1; }

			else if( root.getLeftChild().getValue().equals(e) || root.getRightChild().getValue().equals(e) ){ return 1; }

			else{ return this.Depth(e, root.getLeftChild()) + 1; }
			
		}

		
		public static void main(String[] args) {

			BinaryTreeNode<String> root = new BinaryTreeNode<String>("Yu");

			BinaryTreeNode<String> left = new BinaryTreeNode<String>("Margaret");
			BinaryTreeNode<String> right = new BinaryTreeNode<String>("Yukiko");

			BinaryTreeNode<String> left1 = new BinaryTreeNode<String>("Chie");
			BinaryTreeNode<String> left2 = new BinaryTreeNode<String>("Rise");
			BinaryTreeNode<String> right1 = new BinaryTreeNode<String>("Naoto");
			BinaryTreeNode<String> right2 = new BinaryTreeNode<String>("Yosuke");

			
			
			BinaryTree<String> name = new BinaryTree<String>(left);
			BinaryTree<String> name1 = new BinaryTree<String>(right);
			BinaryTree<String> name2 = new BinaryTree<String>(left1);
			BinaryTree<String> name3 = new BinaryTree<String>(right1);
			BinaryTree<String> name4 = new BinaryTree<String>(left2);
			BinaryTree<String> name5 = new BinaryTree<String>(right2);

			BinaryTree<String> names = new BinaryTree<String>(root, name, name1);
			BinaryTree<String> names1 = new BinaryTree<String>(left, name2, name3);
			BinaryTree<String> names2 = new BinaryTree<String>(right, name4, name5);

			System.out.println(names.verifyCousins("Chie", "Rise"));
			System.out.println(names.verifyCousins("Margaret", "Yukiko"));
			System.out.println(names.verifyCousins("Naoto", "Yu"));
			System.out.println(names.verifyCousins("Yosuke", "Yukiko"));
			System.out.println(names.verifyCousins("Chie", "Yukiko"));
			System.out.println(names.verifyCousins("Margaret", "Naoto"));

		}
	}

}
