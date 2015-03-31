
public class InsertionSort {

	public void insertionSort(int A[]){
		int n = A.length;
		int value, hole;
		for (int i =1; i<n;i++){
			value = A[i];
			hole = i;
			while(hole > 0 && A[hole -1]>value){
				A[hole] = A[hole -1];
				hole = hole -1;
			}
			A[hole] = value;
		}
		for (int i =0; i<n;i++){
			System.out.println(A[i]);
		}
	}
}
