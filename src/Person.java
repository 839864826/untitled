
public class Person {


    public String a=null;
    public Person(String abc) {
        this.a=abc;
    }
    public Person() {
//        空参默认有su
    }

    public void setPersonName(String xxx) {
        this.a=xxx;
    }

    public String getPersonName() {
        return this.a;
    }
}
