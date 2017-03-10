import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BinaryTreeFormat {

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
		
	    // A utility function to get maximum of two integers
	    public int max(int a, int b) {
	        return (a > b) ? a : b;
	    }
		
		// A utility function to right rotate subtree rooted with y
	    // See the diagram given above.
	    public Node rightRotate(Node y) {
	        Node x = y.left;
	        Node T2 = x.right;
	 
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
	    // See the diagram given above.
	    public Node leftRotate(Node x) {
	        Node y = x.right;
	        Node T2 = y.left;
	 
	        // Perform rotation
	        y.left = x;
	        x.right = T2;
	 
	        //  Update heights
	        x.ht = max(height(x.left), height(x.right)) + 1;
	        y.ht = max(height(y.left), height(y.right)) + 1;
	 
	        // Return new root
	        return y;
	    }
		
		public void insert(int val) {
			n++;
			this.root = insert(this.root, val);
		}
		
		//insert val to a node
		public Node insert(Node roots, int val) {
			if(roots==null){
				roots = new Node();
				roots.val = val;
				return roots;
			}else{
				if(val < roots.val){
					roots.left = insert(roots.left, val);
				}else if(val > roots.val){
					roots.right = insert(roots.right, val);
				}else{
					//handling for when the number is the same
					
				}
			}
			int left_ht = (roots.left==null)? -1:roots.left.ht;
			int right_ht = (roots.right==null)? -1:roots.right.ht;
			
			roots.ht = 1+Math.max(left_ht, right_ht);
			//balancing
			roots = Balancing(roots, left_ht, right_ht);
			return roots;
		}
		
		public Node Balancing(Node roots, int left_ht, int right_ht){
			
			if(Math.abs(left_ht - right_ht) > 1){
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
				}else{
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
				
				//left_ht = (roots.left==null)? -1:roots.left.ht;
				//right_ht = (roots.right==null)? -1:roots.right.ht;
				//roots.ht = 1+Math.max(left_ht, right_ht);
			}

			return roots;
		}
		
		public void printAll() {	
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		BinaryTree tree = new BinaryTree();

		for (int n = 0; n < N; n++) {
			int i = sc.nextInt();
			tree.insert(i);
		}
		sc.close();
		//System.out.println("height is : "+height(tree.root)+" -> from : "+tree.n);
		System.out.println("=====");
		tree.printAll();
		
	}

	static int height(Node root) {
		if (root == null) {
			return -1;
		}
		return 1 + (Math.max(height(root.left), height(root.right)));
	}

	static Node[] get3Child(Node root){
		Node[] ans = new Node[3];
		Queue<Node> q = new LinkedList<Node>();
		if(root != null) {
			q.add(root);
		}
		int i=0;
		while(!q.isEmpty()){
			Node temp = q.poll();
			if(temp.left != null) {
				q.add(temp.left);
			}
			if(temp.right != null) {
				q.add(temp.right);
			}
			ans[i] = temp;
			i++;
			
			for(int j=i; j>0; j--){
				if(ans[j].val < ans[j-1].val){
					Node temp1 = ans[j];
					ans[j] = ans[j-1];
					ans[j-1] = temp1;  
				}
			}
		}
		
		return ans;
	}

}
