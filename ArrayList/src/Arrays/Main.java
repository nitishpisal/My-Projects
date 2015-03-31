package Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeeksForGeeks g = new GeeksForGeeks();
		
		/**
		 * Solution 1:
		 */
		 /* int[] arr;
		arr = g.generateRandomArray(10);
		g.print(arr);
		System.out.println(g.duplicateValue(arr, 3));*/
		
		/**
		 * Solution 2:
		 */
		/*String[][] sArr;
		sArr = g.generateRandomMultiArray(6, 6);
		g.print(sArr);
		g.replaceOwithX(sArr);
		System.out.println();
		g.print(sArr);*/
		/**
		 * Solution 3:
		 */
		/*String binary = "100100110";
		String result[] = g.stringStartEndwith1(binary);
		g.print(result);*/
		
		/**
		 * Solution 4: And Quicksort
		 * 
		 */
		
		/*int[] arr =  g.generateRandomArray(10);
		MergeSort m = new MergeSort();
		m.mergeSort(arr, arr.length);
		g.print(arr);
		System.out.println();
		g.quickSort(arr, 0, arr.length-1);
		g.print(arr);
		//g.waveArray(arr);
*/		
		/**
		 * Solution 5:
		 */
		
		/*int[][] arr;
		arr = g.generateRandomMultiArrayInt(5, 6);
		g.print(arr);
		g.fillColor(arr, 3, 2, 5);
		System.out.println();
		g.print(arr);*/
		
		/**
		 * Solution 6:
		 */
		/*int[] arr =  g.generateRandomArray(10);
		g.quickSelect(arr, 0, arr.length-1, 3);
		g.print(arr);*/
		
		/**
		 * Solution 7:
		 */
		/*
		int[] arr = {1,1,0,0,1,1,1,0,1,1,0,1,1,1,0,1};
		System.out.println(g.positionOfZero(arr));*/
		String one = "/home/alice/shared/file1.txt";
		String[] arr = one.split("/");
		for(int i=0; i<arr.length;i++){
			System.out.println(arr.length);
			System.out.println(arr[i]);
		}
		
		
	}

}
