import java.util.Scanner;

public class QueueFormat {

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
	
	static class Queue{
		Node head;
		Node tail;
		int n;
		
		public Queue(){
			n = 0;
		}
		
		public boolean isEmpty(){
			if(n<=0) return true;
			else return false;
		}
		
		public void enqueue(int val){
			n++;
			Node temp = new Node(val);
			
			if(n<=1){
				this.head = temp;
				this.tail = temp;
				return;
			}else{
				this.tail.next = temp;
				this.tail = temp;
			}
		}
		
		public int dequeue(){
			Node temp = this.head;
			if(n>0){
				n--;
				this.head = temp.next;
				temp.next = null;
			}
			return temp.data;
		}
		
		public void peek(){
			System.out.println(this.head.data);
		}
		
		public void peekTail(){
			System.out.println(this.tail.data);
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
		Queue que = new Queue();
		
		for(int q=0; q<Q; q++){
			int s = sc.nextInt();
			if(s==1){ 												// 1 => enqueue a value
				int val = sc.nextInt();
				que.enqueue(val);
			}else if(s==5){											// 5 => check number of queue element
				System.out.println("amount : "+que.n);
			}else{
				if(que.isEmpty()){
					System.out.println("empty queue !!!");
				}else{
					if(s==2){ 										// 2 => dequeue, return value
						System.out.println("Poll : "+que.dequeue());
					}else if(s==3){ 								// 3 => peek the head value
						que.peek();
					}else if(s==4){ 								// 4 => print all queue element
						que.printAll();
					}
				}
			}
				
			
		}
		sc.close();
		
	}
	
}
