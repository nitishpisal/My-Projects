import java.util.Comparator;


public class Person implements Comparator{
    private String ssn;
    private String name;
    private int age;
    private String email;
    
    public Person(String n) {
        name = n;
    }
    // create toString method
    // create hashCode and equals
    
  /*  public String toString(){
       String details = this.name + " " + this.email + " " + this.age + " " + this.email; 
     return details;
    }*/

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Person p1 = (Person)o1;
		Person p2 = (Person)o2;
		String name1 = p1.name;
        String name2 = p2.name;
        
        return name1.compareTo(name2);
	}
    
}
