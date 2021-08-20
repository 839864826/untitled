
public class Person implements Comparable{


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

    @Override
    public int compareTo(Object o) {
        if( o instanceof Person){
            Person person = (Person) o;
            return this.a.compareTo(person.a);
        }else {
            throw  new RuntimeException("类型不匹配");
        }
    }
}
