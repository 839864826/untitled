package base;

/**
 * 修饰符     类内部   同一个包   不同包的子类   同一个工程
 * private   Yes
 * (缺省)     Yes      Yes
 * protected Yes      Yes       Yes
 * public    Yes      Yes       Yes          Yes
 *
 * 修饰类   只能  （缺省）和public
<<<<<<< HEAD
=======
 * 可以修饰    属性，方法，构造器，内部类
 *  修饰类：   缺省，public
>>>>>>> 学习资料
 *
 */
public class AnimalDemo {
    private int a;
    protected int b;
    public int c;

    public static void main(String[] args) {

<<<<<<< HEAD
        Animal animal=new Animal();
=======
//        throw new RuntimeException("主动抛出异常");
        Animal animal=new Animal();//构造器
>>>>>>> 学习资料
        animal.name="aa";
//        animal.lengs=1; //private   私有的 不可在外调用
    }
}
class Animal{
    String name;
    int age;
    private int legs;

    public void setLegs(int l){
        legs=l;
    }
}
