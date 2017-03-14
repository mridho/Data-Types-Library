import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class AVL_BinaryTreeFormat_generics {

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
		
	    // Function to get maximum of two integers
	    public int max(int int1, int int2) {
	        return (int1 > int2) ? int1 : int2;
	    }
	    
		// Function to right rotate subtree rooted with main
	    public Node<T> rightRotate(Node<T> main) {
	        Node<T> brach = main.left;
	        Node<T> leaf = brach.right;
	 
	        // Perform rotation
	        brach.right = main;
	        main.left = leaf;
	 
	        // Update heights
	        main.ht = max(height(main.left), height(main.right)) + 1;
	        brach.ht = max(height(brach.left), height(brach.right)) + 1;
	 
	        // Return new root
	        return brach;
	    }
	 
	    // Function to left rotate subtree rooted with main
	    public Node<T> leftRotate(Node<T> main) {
	        Node<T> brach = main.right;
	        Node<T> leaf = brach.left;
	 
	        // Perform rotation
	        brach.left = main;
	        main.right = leaf;
	 
	        //  Update heights
	        main.ht = max(height(main.left), height(main.right)) + 1;
	        brach.ht = max(height(brach.left), height(brach.right)) + 1;
	 
	        // Return new root
	        return brach;
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
			
			//balancing
			roots = Balancing(roots, left_ht, right_ht);
			
			roots.ht = 1+max(left_ht, right_ht);
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
			}

			return roots;
		}
		
		public void printAll() {	//print all element with Level-Order traversal method
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
				
				System.out.println(temp.data+" ,h-> "+temp.ht);
			}
		}

	}

	//Main to test the AVL tree
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

	//Recursive method to get height
	@SuppressWarnings("rawtypes")
	static int height(Node root) {
		if (root == null) {
			return -1;
		}
		return 1 + (Math.max(height(root.left), height(root.right)));
	}

}