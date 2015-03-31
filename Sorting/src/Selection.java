
public class Selection {

	public static void selectionSort(int A[], int n){
		for (int i=0; i< n; i++){
			int iMin = i;
			for(int j=i+1; j<n; j++){
				if (A[j] < A[iMin]){
					iMin = j;
				}
			}
			int temp = A[i];
			A[i] = A[iMin];
			A[iMin] = temp;
			System.out.println(A[i]);
		}
		
	
	
	}

}
