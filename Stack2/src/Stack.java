public class Stack {

	int stacksize = 100;
	int[] stack = new int[stacksize * 3];
	int[] top ={-1,-1,-1};
	
	public void push(int stackNo, int data){
		if(top[stackNo] + 1 > stacksize ){
			System.out.println("Cannot insert element as the stack is full");
		}
		else{
			++top[stackNo];
			int topLoc = topPointer(stackNo);
			stack[topLoc] = data;
		}
		
	}
	
	public int pop(int stackNo){
		if(top[stackNo] == -1){
			System.out.println("Stack is already Empty");
		}
		else{
			int value = stack[topPointer(stackNo)];
			stack[topPointer(stackNo)]  = 0;
			top[stackNo]--;
			return value;
			
		}
		
		return 0;
	}
	
	public int peek(int stackNo){
		if(top[stackNo] == -1){
			System.out.println("Stack is Empty");
		}
		else{
			int value = stack[topPointer(stackNo)];
			return value;
			
		}
		
		return 0;
	}
	
	public void display(int stackNo){
		int end = topPointer(stackNo);
		int start = stackNo * stacksize;
		
		for(int i = start; i<=end; i++){
			System.out.println(i + " -- " +stack[i] + " ");
		}
		
	}
	
	 public int topPointer(int stackNo){
		 
		 return stackNo * stacksize + top[stackNo];
	 }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stack s = new Stack();
		//push in stack 1
		s.push(0, 1);
		s.push(0, 2);
		s.push(0, 3);
		s.push(0, 4);
		//puch in second stack
		s.push(1, 10);
		s.push(1, 20);
		s.push(1, 30);
		s.push(1, 40);
		//push in third stack
		s.push(2, 100);
		s.push(2, 200);
		s.push(2, 300);
		s.push(2, 400);
		
		
		s.display(0);
	//	s.display(1);
		s.display(2);
		s.pop(2);
		System.out.println("");
		s.display(2);
		s.push(2, 50);
		s.display(2);
	}

}
