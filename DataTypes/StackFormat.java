import java.util.Scanner;

public class StackFormat {

	static class Node{
		int data;
		Node next;
		
		public Node(){
			data = 0;
			next = null;
		}
		
		public Node(int val){
			data = val;
			next = null;
		}
	
	}
	
	static class Stack{
		Node head;
		int n;
		
		public Stack(){
			n = 0;
		}
		
		public boolean isEmpty(){
			if(n<=0) return true;
			else return false;
		}
		
		public void push(int val){
			n++;
			Node temp = new Node(val);
			
			if(n<=1){
				this.head = temp;
				return;
			}else{
				temp.next = this.head;
				this.head = temp;
			}
		}
		
		public int pop(){
			Node temp = this.head;
			if(n>0){
				n--;
				this.head = this.head.next;
				temp.next = null;
			}
			return temp.data;
		}
		
		public void peek(){
			System.out.println(this.head.data);
		}
		
		public void printAll(){
			Node temp = this.head;
			if(temp == null) return;
		    while(temp.next != null){
		    	System.out.print(temp.data+" -> ");
		        temp = temp.next;
		    }
		    System.out.println(temp.data);
			
		}
		
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int Q = sc.nextInt();
		Stack sta = new Stack();
		
		for(int q=0; q<Q; q++){
			int s = sc.nextInt();
			if(s==1){ 												// 1 => push a value
				int val = sc.nextInt();
				sta.push(val);
			}else if(s==5){											// 5 => check number of stack element
				System.out.println("amount : "+sta.n);
			}else{
				if(sta.isEmpty()){
					System.out.println("empty stack !!!");
				}else{
					if(s==2){ 										// 2 => pop, return value
						System.out.println("pop : "+sta.pop());
					}else if(s==3){ 								// 3 => peek the head value
						sta.peek();
					}else if(s==4){ 								// 4 => print all stack element
						sta.printAll();
					}
				}
			}
		}
		sc.close();
		
	}	
	
}
