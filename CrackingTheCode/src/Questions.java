public class Questions {

/**
 *Question 1:
 *Implement an algorithm to determine if a string has all the unique
 *characters. What if you cannot use additional data structures.	
 */
  public boolean checkDuplicate(String str){
	if(str.length()> 256)
		return false;
	boolean[] charFlag = new boolean[256];
	//char[] sArr = str.toCharArray();
	
	for(int i=0; i < str.length(); i++){
		//char val = (char)sArr[i];
		//int ascii = (int) sArr[i];
		int ascii = (int)str.charAt(i);
		if(charFlag[ascii]){
			return false;
		}
		else{
			charFlag[ascii] = true;
		}
	}
	
	  return true;
  }
  
  //***********************************************************//
  
  /**
   * Question 2:
   * Write a program to reverse a string
   * 
   */
  public void stringReversal(String s){
	  
	  char[] cArr = s.toCharArray();
	  for(int i = 0, j=cArr.length-1; i<=cArr.length/2; i++, j--){
		  if(i>=j)
			  break;
		  //Swap values
		  char a = cArr[i];
		  cArr[i] = cArr[j];
		  cArr[j] = a;

	  }
	  s = String.valueOf(cArr);
	  System.out.println("The string becomes "+ s);
  }

  /**
   * Question 3:
   * Implement a method to perform basic string compression using the counts of
		repeated characters. For example, the string aabcccccaaa would become
		a2blc5a3. If the "compressed" string would not become smaller than the original
		string, your method should return the original string.
   */
  
  public void stringCompress(String str){
	  int count = 1;
	  String compressed = "";
	  char[] charS = str.toCharArray();
	  for(int i = 0; i<charS.length-1; i++){
		  char iVal = charS[i];
		  char nextVal = charS[i+1];
		  if (iVal == nextVal){
			  count++;
			  
		  }
		  else{
			  if(count > 1){
				  compressed = compressed + iVal + "" + count;
				  count = 1;
				  
			  }
			  else{
				  compressed = compressed + nextVal + "";
			  }
		  }
	  }
	  System.out.println("compressed String is "+ compressed);
	  
  }
  
  /**
   * Question 4:
   * 
   * Write a program to check if one string is permutation of the other
   *Approach 1:
   *1. Check if the length is same if not they cannot be permutation.
   *2. create an array of [256] and increment the index count for one list.
   *3. Loop over to the second array and decrement the index count.
   *4. If count is < 0 any where in the array-- it means string doesnot match.
   *
   *Approach 2:
   *
   *We can sort the string and then compare if they are equal after sorting.
   */
  
  public boolean checkPermutation(String s1, String s2){
	   if(s1.length() != s2.length())
		   return false;
	   int[] arr = new int[256];
	   int loc = 0;
	   //Step 2 and 3
	   for(int i=0; i<s1.length(); i++){
		   loc = (int)s1.charAt(i);
		   arr[loc]++;
	   }
	   for(int i=0; i<s2.length();i++){
		   loc = (int)s2.charAt(i);
		   arr[loc]--;
		   if(arr[loc]-- < 0)
			   return false;
	   }
	  
	  return true;
  }
  
  /**
   * Question 5:
   * Write a method to replace all spaces in a String with %20
   * Approach:
   * 1. count the spaces
   * I'll create a new character Array with extra size i.e. 3 chars for %20 
   */
  
   public void spaceWith20(String str){
	   char[] arr = str.toCharArray();
	   int spaces = 0;
	   int indexOfi = 0, indexOfj = 0;
	   for(int i=0; i< str.length(); i++){
		   if (Character.isWhitespace(str.charAt(i)))
			   spaces++;
	   }
	   char[] arr2 = new char[str.length() + spaces*3];
	   for (int i=0, j=0; i<arr.length; i++){
		   if(!Character.isWhitespace(arr[i])){
			   arr2[j] = arr[i];
			   j++;
		   }
		   else{
			   arr2[j] = '%';
			   arr2[j+1] = '2';
			   arr2[j+2] = '0';
			   j += 3;
		   }
	   }
	   System.out.println(String.valueOf(arr2));
	   
   }

}
