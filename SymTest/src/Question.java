
public class Question {
    
    public static void main(String a[]) {
        Question q = new Question();
        q.method1();
    }

    public void method1() {
        Person p = new Person("John");
        method2(p);
        System.out.println(p);
    }

    public void method2(final Person p2) {
        //p2 = new Person("Mary");
        System.out.println(p2);
    }

}
