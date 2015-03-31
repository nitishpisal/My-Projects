
public class CountDown extends Thread{


	public static boolean finished = false;
	public void run(){
		for(int i=0; i<10000000; i++){
			if(finished) break;
			System.out.println("countdown:"+ i);
			
		}
	}
	
}
