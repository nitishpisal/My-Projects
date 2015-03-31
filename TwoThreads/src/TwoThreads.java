
public class TwoThreads extends Thread{
	
	public boolean finished = false;
	public void run(){
		for(int i=0; i<10000000; i++){
			if(finished) break;
			System.out.println("countup:"+ i);
			
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoThreads sam = new TwoThreads();
		Thread c = new CountDown();
		sam.start();
		c.start();
		try{
			Thread.sleep(5000);
		}catch(Exception ex){
			System.out.println("Error " + ex);
		}
		sam.finished = true;
		CountDown.finished = true;

	}

}
