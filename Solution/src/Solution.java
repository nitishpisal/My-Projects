
public class Solution {
	
	public int getIntegerComplement(int n){
        int binaryArr[] = new int[50];
        int i = 0;
        String binaryNumber="";
        while(n > 0){
            binaryArr[i] = n%2;
            n = n/2;
            i = i+1;
        }
        for(int j = i -1 ;j >= 0;j--){
        	binaryNumber=binaryNumber+String.valueOf(binaryArr[j]);
           // System.out.print(binaryArr[j]);
            
        }
        /**
         * Temp code
         * 
         * int i = 0, temp[] = new int[25];
         * int toBinary[] = new int[25];
         *  while(n>0){
         *  	temp[i++] = n%2;
         *  	n = n/2;
         *  }
         *  for(int j = i-1, j>=0; j--){
         *  	toBinary[i++] = temp[j];
         *  }
         *  
         *  String binary  = String.valueOf(toBinary);
         * 	String compl = "";
         *  int tempNo = 0;
         * 	for(int x = 0; i<binary.length; x++){
         * 		tempNo = Integer.parseInt(binary.charAt(k));
         * 	if(tempNo == 1){
         * 	tempNo = 0;
         * 	}
         * 	else{
         * 	tempNo = 1;
         * 	}
         * 	compl = compl.append(String.valueOf(tempNo));
         *
         * }
         * return Integer.parseInt(compl, 2);
         * 
         */
        
      
    System.out.println(binaryNumber);
	
	
	String complement="";
	
	for(int k=0;k<binaryNumber.length();k++){
		
		int number=Integer.parseInt(Character.toString(binaryNumber.charAt(k)));
		//int number  = (int)binaryNumber.charAt(k);
		if(number==1){
			number=0;
		}else{
			number=1;
		}
		
		complement=complement+String.valueOf(number);
		
	}
	
	System.out.println(complement);
    int compl = module(complement);    
	return compl;
        
        
}
	public int module(String complement){
		int number;
		number = Integer.parseInt(complement, 2);
		return number;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s= new Solution();
		s.getIntegerComplement(50);

	}

}
