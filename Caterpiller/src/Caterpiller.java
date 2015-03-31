import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Caterpiller {

	public int unEatenLeaves(int leaves, int cat, int[] catArr){
		int n = 0;
		Set<Integer> mySet = new HashSet<Integer>();
		for (int k=1; k<=leaves; k++)
			mySet.add(k);
		
		for(int i=1; i<= leaves/catArr[0];i++){
			
			for(int j=0; j<catArr.length; j++){
				if(catArr[j]*i > leaves)
					continue;
				if(mySet.contains(catArr[j]*i)){
					mySet.remove(i*catArr[j]);
				}
			}
		}
		n =mySet.size();
		return n;
	}
	
	static int countUneatenLeaves(int N, int[] A) {
	     //  int [] array = new int[N+1];
	       ArrayList arr = new ArrayList();
	        int i;
	        for(i = 1; i <= N;i++)
	               arr.add(i, 1);
	        int ans = 0;
	        for(i = 0; i < A.length;i++)
	        {
	               if((int)arr.get(A[i])==0)
	                      continue;
	               for(int j = 1;j*A[i]<=N;j++)
	               {       
	                      //arr[A[i]*j]=0;
	                      arr.add(A[i]*j, 0);
	                      
	               }
	       }
	        for(int k=0;k<arr.size();k++){
	            if((int)arr.get(k) == 1)
	                ans++;
	        }
	       return ans;
	    }
	
	
	/*static int countUneatenLeaves(int N, int[] A) {
	       int [] array = new int[N+1];
	        int i;
	        for(i = 1; i <= N;i++)
	               array[i] = 1;
	        int ans = 0;
	        for(i = 0; i < A.length;i++)
	        {
	               if(array[A[i]]==0)
	                      continue;
	               for(int j = 1;j*A[i]<=N;j++)
	               {       
	                      array[A[i]*j]=0;
	                      
	               }
	       }
	        for(int k=0;k<array.length;k++){
	            if(array[k] == 1)
	                ans++;
	        }
	       return ans;
	    }*/
	
	
	
	
	
	
	
	
	
	
	public static void main(String ... args){
		int l = 10;
		int cat = 3;
		int[] arr = {2,4,5};
		Caterpiller c = new Caterpiller();
		System.out.println(c.unEatenLeaves(l, cat, arr));
		
	}
	
	
}
