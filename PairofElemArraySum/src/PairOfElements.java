import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;




public class PairOfElements {
	
	int ele1;
	int ele2;
	public int getEle1() {
		return ele1;
	}


	public void setEle1(int ele1) {
		this.ele1 = ele1;
	}


	public int getEle2() {
		return ele2;
	}


	public void setEle2(int ele2) {
		this.ele2 = ele2;
	}

	
	
	static List<PairOfElements> getPair(int[] arr, int checkVal){
		//int[] pair = new int[2];
		Integer temp;
		List<PairOfElements> rList = new ArrayList<PairOfElements>();
		HashMap<Integer, Integer> valueHash = new HashMap<Integer, Integer>();
		for(int i = 0; i< arr.length; i++){
			valueHash.put(arr[i], i);
		}
		for(int j=0; j<arr.length; j++){
			temp = valueHash.get(checkVal - arr[j]);
			if(temp != null && temp != j){
				PairOfElements p = new PairOfElements();
				p.setEle1(valueHash.get(arr[j]));
				p.setEle2(temp);
				/*System.out.println(valueHash.get(arr[j]) + " " + valueHash.get(checkVal - arr[j]) 
						+ " = " + checkVal
						);*/
				rList.add(p);		
			}
			
		}
		
		
		return rList;
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {2, 7, 15, 6, 5, 9, 12, 14, 3};
		PairOfElements p = new PairOfElements();
		List<PairOfElements> rList = PairOfElements.getPair(arr, 12);
		ListIterator it = rList.listIterator();
		while(it.hasNext()){
			p = (PairOfElements)it.next();
			System.out.println(p.getEle1() + " " + p.getEle2());
		}

	}

}
