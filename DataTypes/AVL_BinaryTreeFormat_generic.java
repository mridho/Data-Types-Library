import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class AVL_BinaryTreeFormat_generic {

	static class Node<T> {
		T data;
		int ht;
		Node<T> left;
		Node<T> right;
		public Node(){
			this.data = null;
			this.ht = 0;
		}
		
		public Node(T value){
			this.data = value;
			this.ht = 0;
		}
		
	}

	static class BinaryTree <T extends Comparable<T>> {
		Node<T> root;
		int n;

		public BinaryTree() {
			this.root = null;
			this.n = 0;
		}

		public int height(Node<T> N){
	        if (N == null)
	            return -1;
	        return N.ht;	
		}
		
	    // A utility function to get maximum of two integers
	    public int max(int a, int b) {
	        return (a > b) ? a : b;
	    }
	    
		// A utility function to right rotate subtree rooted with y
	    public Node<T> rightRotate(Node<T> y) {
	        Node<T> x = y.left;
	        Node<T> T2 = x.right;
	 
	        // Perform rotation
	        x.right = y;
	        y.left = T2;
	 
	        // Update heights
	        y.ht = max(height(y.left), height(y.right)) + 1;
	        x.ht = max(height(x.left), height(x.right)) + 1;
	 
	        // Return new root
	        return x;
	    }
	 
	    // A utility function to left rotate subtree rooted with x
	    public Node<T> leftRotate(Node<T> x) {
	        Node<T> y = x.right;
	        Node<T> T2 = y.left;
	 
	        // Perform rotation
	        y.left = x;
	        x.right = T2;
	 
	        //  Update heights
	        x.ht = max(height(x.left), height(x.right)) + 1;
	        y.ht = max(height(y.left), height(y.right)) + 1;
	 
	        // Return new root
	        return y;
	    }
		
		public void insert(T val) {
			n++;
			this.root = insert(this.root, val);
		}
		
		//insert val to a node
		public Node<T> insert(Node<T> roots, T value) {
			if(roots==null){
				roots = new Node<T>();
				roots.data = value;
				return roots;
			}else{
				if(value.compareTo(roots.data) < 0){
					roots.left = insert(roots.left, value);
				}else if(value.compareTo(roots.data) > 0){
					roots.right = insert(roots.right, value);
				}else{
					//handling for when the number is the same
					
				}
			}
			int left_ht = (roots.left==null)? -1:roots.left.ht;
			int right_ht = (roots.right==null)? -1:roots.right.ht;
			
			roots.ht = 1+max(left_ht, right_ht);
			//balancing
			roots = Balancing(roots, left_ht, right_ht);
			return roots;
		}
		
		public Node<T> Balancing(Node<T> roots, int left_ht, int right_ht){
			
			if(Math.abs(left_ht - right_ht) > 1){
				if(left_ht - right_ht >= 2){
					Node<T> l_child = roots.left;
					int l_child_lht = (l_child.left==null)? -1:l_child.left.ht;
					int l_child_rht = (l_child.right==null)? -1:l_child.right.ht;
					
					if(l_child_lht - l_child_rht >= 1){
						//LL case
						return rightRotate(roots);
						
					}else{
						//LR case
						roots.left = leftRotate(roots.left);
						return rightRotate(roots);
		
					}
				}else{
					Node<T> r_child = roots.right;
					int r_child_lht = (r_child.left==null)? -1:r_child.left.ht;
					int r_child_rht = (r_child.right==null)? -1:r_child.right.ht;
					
					if(r_child_lht - r_child_rht >= 1){
						//RL case
						roots.right = rightRotate(roots.right);
						return leftRotate(roots);
						
					}else{
						//RR case
						return leftRotate(roots);
					}
				}
				
				//left_ht = (roots.left==null)? -1:roots.left.ht;
				//right_ht = (roots.right==null)? -1:roots.right.ht;
				//roots.ht = 1+max(left_ht, right_ht);
			}

			return roots;
		}
		
		public void printAll() {	
			Queue<Node<T>> q = new LinkedList<Node<T>>();
			if(this.root != null) {
				q.add(this.root);
			}
			
			while(!q.isEmpty()){
				Node<T> temp = q.poll();
				if(temp.left != null) {
					q.add(temp.left);
				}
				if(temp.right != null) {
					q.add(temp.right);
				}
				
				System.out.println(temp.data+" -h-> "+temp.ht);
			}
		}

	}

	/**VVVVVVVVVVVVVVVVVVVVVVVV**/
	//main to test the AVL tree
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//String customTypes = sc.next();
		
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		for (int n = 0; n < N; n++) {
			Integer i = sc.nextInt();
			tree.insert(i);
		}
		sc.close();
		System.out.println("height is : "+height(tree.root)+" -> from : "+tree.n);
		System.out.println("=====");
		tree.printAll();
		
	}

	//recursive method to get height
	@SuppressWarnings("rawtypes")
	static int height(Node root) {
		if (root == null) {
			return -1;
		}
		return 1 + (Math.max(height(root.left), height(root.right)));
	}

}