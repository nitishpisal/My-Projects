
public class CallingClass {

	public void swap(int a, int b){
		int temp =a;
		a=b;
		b= temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Selection s = new Selection();
		int A[] = {10,2,1,7,5,8,9,6,13};
		//s.selectionSort(A,10);
		int n = A.length;
		BubbleSort B = new BubbleSort();
		//B.sort(A);
		
		InsertionSort I = new InsertionSort();
		//I.insertionSort(A);
		
		MergeSort m = new MergeSort();
		m.mergeSort(A,n);
	}

}
