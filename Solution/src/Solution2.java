
public class Solution2 {
	
	public int isPangram(String n) {
			//n.toLowerCase()
		    int flag = 1;    
			for (char i = 97; i <= 122;i++){
		        
		            if ((n.indexOf(i) < 0)){
		            	
		            	if(n.indexOf(i - 32) < 0)
		            	
		            		flag = 0;
		            	}
			
			}
			return flag;
		    
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution2 s = new Solution2();
		System.out.println(s.isPangram("the quick brown fox jumps over the lazy dog"));

	}
	

}
