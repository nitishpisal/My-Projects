package Package;

public class Validation {

		public boolean isInteger (String value){
			try {
				System.out.println("value inside validation " + value);
		        Long.parseLong(value);
		        System.out.println(Integer.parseInt(value));
		    } catch(NumberFormatException e) { 
		        System.out.println("number format exception " + e);
		    	return false; 
		    }
		    // only got here if we didn't return false
		    return true;
		}
		
		
		
}
