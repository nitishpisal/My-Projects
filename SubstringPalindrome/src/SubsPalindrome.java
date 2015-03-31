import java.util.HashSet;
import java.util.Set;


public class SubsPalindrome {
	
	public static Set<String> output = new HashSet<>();
	
	public static Set<String> palindromes(String input) {
	     for (int i = 0; i < input.length(); i++) {
	         // Check for the even palindromes
	         addPal(input,i,i+1);
	         // Check for the odd Palindromes
	         addPal(input,i,i);
	     } 
	     System.out.println(output.size());
	     return output;
	  }

	  public static void addPal(String s, int i, int j) {
		  //Starting from i and j expand in both the directions untill no match is found.
		  //System.out.println(s.charAt(i) + s.charAt(j));
	      while (i >= 0 && j < s.length() && s.charAt(i)== s.charAt(j)) {
	            output.add(s.substring(i,j+1));
	            i--; j++;
	      }
	  }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(SubsPalindrome.palindromes("aabaacaad"));

	}

}
