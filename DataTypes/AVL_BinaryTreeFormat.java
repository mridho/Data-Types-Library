import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class AVL_BinaryTreeFormat {

	static class Node {
		int val;
		int ht;
		Node left;
		Node right;
		public Node(){
			this.val = 0;
			this.ht = 0;
		}
		
		public Node(int val){
			this.val = val;
			this.ht = 0;
		}
	}

	static class BinaryTree {
		Node root;
		int n;

		public BinaryTree() {
			this.root = null;
			this.n = 0;
		}

		public int height(Node N){
	        if (N == null)
	            return -1;
	        return N.ht;	
		}
		
	    // Function to get maximum of two integers
	    public int max(int int1, int int2) {
	        return (int1 > int2) ? int1 : int2;
	    }
		
		// Function to right rotate subtree rooted with main
	    public Node rightRotate(Node main) {
	        Node branch = main.left;
	        Node leaf = branch.right;
	 
	        // Perform rotation
	        branch.right = main;
	        main.left = leaf;
	 
	        // Update heights
	        main.ht = max(height(main.left), height(main.right)) + 1;
	        branch.ht = max(height(branch.left), height(branch.right)) + 1;
	 
	        // Return new root
	        return branch;
	    }
	 
	    // Function to left rotate subtree rooted with main
	    public Node leftRotate(Node main) {
	        Node branch = main.right;
	        Node leaf = branch.left;
	 
	        // Perform rotation
	        branch.left = main;
	        main.right = leaf;
	 
	        //  Update heights
	        main.ht = max(height(main.left), height(main.right)) + 1;
	        branch.ht = max(height(branch.left), height(branch.right)) + 1;
	 
	        // Return new root
	        return branch;
	    }

	    //return the re-balanced Node
	    public Node getBalanced(Node roots){
			int left_ht = (roots.left==null)? -1:roots.left.ht;
			int right_ht = (roots.right==null)? -1:roots.right.ht;
			
			//Balancing
			roots = Balancing(roots, left_ht, right_ht);
			
			roots.ht = 1+max(left_ht, right_ht);
			return roots;
	    }
	    
	    //Tree balancing function
		public Node Balancing(Node roots, int left_ht, int right_ht){
			
			if(left_ht - right_ht >= 2){
				Node l_child = roots.left;
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
			}else if(left_ht - right_ht <= -2){
				Node r_child = roots.right;
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

			return roots;
		}
	    
		//insert value to the tree
		public void insert(int val) {
			n++;
			this.root = insert(this.root, val);
		}
		
		//insert value to a node
		public Node insert(Node roots, int value) {
			if(roots==null){
				roots = new Node(value);
				return roots;
			}else{
				if(value < roots.val){
					roots.left = insert(roots.left, value);
				}else if(value > roots.val){
					roots.right = insert(roots.right, value);
				}else{
					//handling for when the number is the same
					//no duplicate value is allowed
					n--;
				}
			}
			return getBalanced(roots);
		}
		
		public Node minValueNode(Node roots){
	        Node temp = roots;
	        
	        // loop down to find the leftmost leaf
	        while (temp.left != null){
	        	temp = temp.left;
	        }
	        return temp;
		}
		
		//delete value from the tree
		public void delete(int val){
			if(n<=0) return;
			this.root = delete(this.root, val);
		}
		
		//delete value from a node
		public Node delete(Node roots, int val){
			
	        //if tree is empty or value not found
			if (roots == null) return roots;
	 
	        if (val < roots.val){
	            roots.left = delete(roots.left, val);
	        }else if (val > roots.val){
	            roots.right = delete(roots.right, val);
			}
	        // value is equal with roots's val, node to be deleted found
	        else{
	        	n--;//target node found, decrease n
	        	
	            // node with only one child or no child
	            if ((roots.left == null) || (roots.right == null)){
	                Node temp = null;
	                if (roots.left == null){
	                    temp = roots.right;
	                }else{
	                    temp = roots.left;
	                }
	                // No child case
	                if (temp == null){
	                    roots = null;
	                }else{   
	                	// One child case
	                	roots = temp; // Copy the contents of the non-empty child
	                }                 
	            }else{
	                // node with two children
	                // get successor (smallest in the right subtree)
	                Node temp = minValueNode(roots.right);
	 
	                // Replace the successor's data with this node
	                roots.val = temp.val;
	 
	                // Delete the successor
	                n++;//add n to counter the recursive n decrement
	                roots.right = delete(roots.right, temp.val);
	            }
	        }
	 
	        // If the node is empty then return
	        if (roots == null) return roots;
			
	        //Return the re-balanced node
	        return getBalanced(roots);
		}
		
		public void printAll() {	//print all element with Level-Order traversal method	
			Queue<Node> q = new LinkedList<Node>();
			if(this.root != null) {
				q.add(this.root);
			}
			
			while(!q.isEmpty()){
				Node temp = q.poll();
				if(temp.left != null) {
					q.add(temp.left);
				}
				if(temp.right != null) {
					q.add(temp.right);
				}
				
				System.out.println(temp.val+" h-> "+temp.ht);
			}
		}

	}

	//Main to test the AVL tree
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input amount of tree elements to be inputted  : ");
		int N = sc.nextInt();
		BinaryTree tree = new BinaryTree();

		System.out.println("Input each of the "+N+" tree element : ");
		for (int n = 0; n < N; n++) {
			int i = sc.nextInt();
			tree.insert(i);
		}
		System.out.println("height is : "+height(tree.root)+" -> from total "+tree.n+" tree element");
		System.out.println("=====");
		tree.printAll();
		System.out.println("=====\n");
		
		System.out.println("Input amount of to be deleted tree element : ");
		int D = sc.nextInt();
		System.out.println("Input each of the "+D+" to be deleted tree element : ");
		for (int d = 0; d < D; d++) {
			int i = sc.nextInt();
			tree.delete(i);
			
			System.out.println("height is : "+height(tree.root)+" -> from total "+tree.n+" tree element");
			System.out.println("=====");
			tree.printAll();
			System.out.println("=====");
		}
		sc.close();
	}

	//Recursive method to get height
	static int height(Node root) {
		if (root == null) {
			return -1;
		}
		return 1 + (Math.max(height(root.left), height(root.right)));
	}

}
