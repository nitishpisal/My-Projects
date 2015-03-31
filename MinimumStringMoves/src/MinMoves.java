public class MinMoves {

	public static int FindPos(char letter,char[] str) {
		int retPos=0;
		for(int i=0;i<=str.length-1;i++){
			if(str[i]==letter){
				retPos=i;
				break;
			}
		}
		return(retPos);
	}
	public static char[] MoveRight(char[] str,int pos,int npos)
	{
		char[] retArray;
		char chTemp;
		retArray=str.clone();
		for(int i=pos;i<npos;i=i+1){
			chTemp=retArray[i];
			retArray[i]=retArray[i+1];
			retArray[i+1]=chTemp;
		}
		return(retArray);
	}
	public static char[] MoveLeft(char[] str,int pos,int npos)
	{
		char[] retArray;
		char chTemp;
		retArray=str.clone();
		for(int i=pos;i>npos;i=i-1){
			chTemp=retArray[i];
			retArray[i]=retArray[i-1];
			retArray[i-1]=chTemp;
		}
		return(retArray);
	}
	public static void main(String[] args) {
		String A = "ababa";
        String B = "aaabb";
        System.out.println("started");
        char[] aChar = A.toCharArray();
        char[] bChar = B.toCharArray();
        System.out.println("A: " + String.valueOf(aChar));
        System.out.println("B: " + String.valueOf(bChar));
        int numMoves = 0;
        int numPos=0;
        for(int i=0;i<aChar.length;i++)
        {
        	if(aChar[i]!=bChar[i]){
        		numPos=FindPos(aChar[i],bChar);
        		if(numPos<i){
        			bChar=MoveRight(bChar,numPos,i);
        		}
        		if(numPos>i){
        			bChar=MoveLeft(bChar,numPos,i);
        		}
        		numMoves=numMoves+(numPos);
        	}
        }
        System.out.println("-----------------------------");
        System.out.println("A: " + String.valueOf(aChar));
        System.out.println("B: " + String.valueOf(bChar));
        System.out.println("Number of swaps: " + numMoves);
	}
}