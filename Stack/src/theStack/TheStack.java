package theStack;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TheStack {
	
	private String[] stackArray;
	private int stackSize;
	private int topOfStack = -1; //symboize empty
	
	TheStack(int size) {
		// TODO Auto-generated constructor stub
		stackSize = size;
		stackArray = new String[size];
		  // Assigns the value of -1 to every value in the array
		  // so I control what gets printed to screen
		
		Arrays.fill(stackArray,"-1");
		
	}
	
	public void push(String input){
		if(topOfStack+ 1 < stackSize){
			topOfStack ++;
			stackArray[topOfStack] = input;
			
		}else{
			System.out.println("The stack is full cannot insert item");
		}
		displayTheStack();
	}
	
	public String pop(){
		if(topOfStack >= 0){
			
			displayTheStack();
			System.out.println("POP " + stackArray[topOfStack] + " Was Removed From the Stack\n");
			
			stackArray[topOfStack] = "-1";
			return stackArray[topOfStack--];
		}
		else{
			displayTheStack();
			System.out.println("Nothing is in there in the stack");
			return "-1";
		}
	}
	
	public String peek(){
        
        displayTheStack();
         
        System.out.println("PEEK " + stackArray[topOfStack] + " Is at the Top of the Stack\n");
         
        return stackArray[topOfStack];
         
    }

	
	
	public void displayTheStack(){
	     
        for(int n = 0; n < 61; n++)System.out.print("-");
         
        System.out.println();
         
        for(int n = 0; n < stackSize; n++){
             
            System.out.format("| %2s "+ " ", n);
             
        }
         
        System.out.println("|");
         
        for(int n = 0; n < 61; n++)System.out.print("-");
         
        System.out.println();
         
        for(int n = 0; n < stackSize; n++){
             
             
             
            if(stackArray[n].equals("-1")) System.out.print("|     ");
             
            else System.out.print(String.format("| %2s "+ " ", stackArray[n]));
             
        }
         
        System.out.println("|");
         
        for(int n = 0; n < 61; n++)System.out.print("-");
         
        System.out.println();
     
	}
 
	public void pushMany(String multiValues){
		
		String[] temp = multiValues.split(" ");
		for (int i=0; i<temp.length; i++){
			push(temp[i]);
		}
	}
	
	
	public void popAll(){
        
        for(int i = topOfStack; i >= 0; i--){
             
            pop();
             
        }
         
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TheStack stack = new TheStack(10);
		stack.push("10");
		stack.push("15");
		stack.peek();
		stack.pop();
		stack.pushMany("20 11 23 4");
		stack.displayTheStack();

	}

}
