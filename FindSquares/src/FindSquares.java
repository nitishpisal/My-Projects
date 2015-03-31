import java.util.Scanner;

public class FindSquares {
    public static int square(int minNum, int maxNum){
			int count = 0;
			int o1 = (int)Math.sqrt(minNum);
			int o2 = (int)Math.sqrt(maxNum);
            count = o2-o1;
            if(o1*o1 == minNum)
                count++;
			return count;
		}
    
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int numCase;
			int minNum = 0;
			int maxNum = 0;
			Scanner in = new Scanner(System.in);
			
			int test = in.nextInt();
          //  String test2 = in.nextLine();
             in.nextLine();
          //  System.out.println(test);
            
			String input = "";
			String[] num = new String[2];
			int count=0;
			int[] numArr = new int[2*test];
			for(int i=0; i<test; i++){
			//	Scanner inp = new Scanner(System.in);
				
				input = in.nextLine();
                //System.out.println(input);
				num = input.split(" ");
				minNum = Integer.parseInt(num[0]);
				maxNum = Integer.parseInt(num[1]);
				numArr[count] = minNum;
				numArr[++count] = maxNum;
				count++;
			}
			for(int j=0; j<numArr.length; j+= 2){
				System.out.println(FindSquares.square(numArr[j], numArr[j+1]));
			}
        
        
        
    }
}