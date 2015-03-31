import java.util.HashSet;


public class Password {
	
	public static boolean passwordValidate(String str){
		str = str.toLowerCase();
		char temp;
		HashSet<Character> vowel = new HashSet<Character>();
		vowel.add('a');
		vowel.add('e');
		vowel.add('i');
		vowel.add('o');
		vowel.add('u');
		char one, two, three;
		int loop = str.length()-3;
		for(int i=0, j=0; i<= loop; i++, j++)
		{
			temp = str.charAt(i);
			if( (i<= loop)  ){
					one = str.charAt(i);
					two = str.charAt(i+1);
					three = str.charAt(i+2);
					if(vowel.contains(one) && vowel.contains(str.charAt(two)) && (vowel.contains(three))){
						return false;
					}
					else{
						if(!vowel.contains(one)){
							j=i;
						}
						else if(!vowel.contains(two)){
							j=i+1;
						}
						else{
							j=i+2;
						}
						if(j+2 <= loop && !(vowel.contains(j) && vowel.contains(str.charAt(j+1)) && (vowel.contains(j+2)))){
							return false;
						}
						
					}
					
				}
			}
			

		
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.err.println(Password.passwordValidate("aasbhut"));

	}

}
