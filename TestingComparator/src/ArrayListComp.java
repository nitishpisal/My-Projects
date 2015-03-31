import java.util.List;
import java.util.*;
import java.util.Comparator;


public class ArrayListComp {


	public static void main(String... args){
		List<Interval> arr = new ArrayList<Interval>();
		arr.add(new Interval(1,4));
		arr.add(new Interval(4.5f,6.5f));
		arr.add(new Interval(3, 5));
		arr.add(new Interval(7,9));
		Collections.sort(arr, new Interval());
		Interval next;
		
		List<Interval> l1 = Interval.overlap(arr);
		Iterator itr = l1.iterator();
		while(itr.hasNext()){
			next = (Interval)itr.next();
			System.out.println(" start : "+ next.start + "End :" + next.end);
		}
	}

}

class Interval implements Comparator<Interval> {
	
	float start;
	float end;
	
	public Interval(float min, float max){
		this.start = min;
		this.end = max;
	}
	public Interval(){
		this.start = 0.0f;
		this.end = 0.0f;
	}
	public static List<Interval> overlap(List<Interval> input){
		List<Interval> ol = new ArrayList<Interval>();
		Iterator it = input.iterator();
		float min = 0, max = 0;
		float previous = 0;
		Interval next;
		boolean breakPoint = false;
		while(it.hasNext()){
			next = (Interval)it.next();
			if((min == 0 && max == 0) || next.start >= previous){
				min = next.start;
				max = next.end;
				previous = next.end;
				continue;
			}
			
			if(next.start < previous){
				max = max > next.end ? max : next.end;
				previous = next.end;
				ol.add(new Interval(min, max));
			}
			
		}
				
		return ol;
	}
	
	public int compare(Interval o1, Interval o2) {
		// TODO Auto-generated method stub
		float lStart = o1.start;
		float l2Start = o2.start;
		
		if(lStart  == l2Start) return 0;
		else return (lStart > l2Start) ?1 : -1;
		
	}
	
}