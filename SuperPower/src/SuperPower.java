
public class SuperPower {
	
	public static int calcPower(int num){
		
		for(int i = 1; i<=Math.sqrt(num); i++){
			
			for(int j=1; j<= Math.sqrt(i)+2; j++){
				if(Math.pow(i, j) > num)
					break;
				if(Math.pow(i, j) == num)
					return 1;
				
			}	
		}
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(SuperPower.calcPower(8));
	}

}
