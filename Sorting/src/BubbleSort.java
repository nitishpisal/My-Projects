
public class BubbleSort {

	public static void sort(int A[]){
		int n = A.length;
		int k =n-1;
		int temp;
		int flag = 0;
		for (int i =0; i< n-1; i++){
			for (int j=0; j< k; j++){
				if (A[j] > A[j+1]){
				temp = A[j];
				A[j] = A[j+1];
				A[j+1] = temp;
				flag = 1; // we are setting the flag to 1 if there is any swapping done
				}
				
			}
			if(flag == 0) break; //this means that no swapping is conducted in the inner loop because the array input is already sorted.
		
		}
		for (int i=0; i<n;i++ ){
			System.out.println(A[i]);
		}
	}
	
}
