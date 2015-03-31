
public class Recursion {
	void myMethod( int counter)
	{
	if(counter == 0)
	     return;
	else
	       {
	       System.out.println("hello" + counter);
	       myMethod(--counter);
	       System.out.println(""+counter);
	       return;
	       }
	} 
	/**
	 * steps:
	 * 
	 * 1. Write if -- works in most of the cases
	 * 2. Handle the simplest case(s):  base case - where no recursion is called
	 * 3. Write a recursive call to the function -- to the next simpler step--may be store result in a variable
	 * 4. Assume the recursive call works
	 * @param n
	 * @return
	 */
	public static int fact(int n){
		if (n == 0){
			return 1;
		}
		else{
			int result = n * fact(n-1);
			return result;
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recursion r = new Recursion();
		//r.myMethod(4);
		int re = fact(5);
		System.out.println(re);
	}

}
