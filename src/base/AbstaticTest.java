package base;

import java.util.Map;

/**
 * abstract关键字的使用
 *
 * 1. abstract :抽象的
 * 2. abstract可以用来修饰的结构:类、方法
 * 3. abstract修饰类:抽象类
 *      >此类不能实例化//不能new
 *      >抽象类一定有构造器，便于子类实例化调用
 *      >开发中，都会提供抽象类的子类，让子类对象实例化，完成相关的操作
 *
 * 4. abstract修饰方法:抽象方法
 *      >抽象方法只有方法的声明，没有方法体
 *      >包含抽象方法的类，一 定是一个抽象类。反之，抽象类中可以没有抽象方法的。
 *      >若子类重写了父类中的所有的抽象方法后，此子类方可实例化
 *      >若子类没有重写父类中的所有的抽象方法，则此子类也是一个抽象类， 需要使用abstract修饰
 *
 *
 *
 */

/**
 * abstract不能使用
 * abstract使用上的注意点:
 * 1. abstract不能用来修饰:属性、构造器等结构
 * 2. abstract不能用来修饰私有方法//abstract必须重写，但是私有的不可被调用 无法重写
 *                      静态方法,
 *                      final的方法、final的类//final不可重写继承 abstract必须重写继承
 */
public class AbstaticTest {
    public static void main(String[] args) {


        //Static001  抽象了   不可实例化
//        Static001 s01=new Static001();

        //创建了一匿名类的对象: static001
        Static001 static001=new Static001() {
            @Override
            public void eats() {
                System.out.println("匿名类的吃");
            }
        };//说明  Static001有eats的抽象方法

        qw(new Static002());//匿名对象
        Static002 static002=new Static002();
        qw(static002);//非匿名的类非匿名的对象

        qw(new Static002());//非匿名的类匿名的对象

        er(static001);
        er(new Static001() {
            @Override
            public void eats() {
                System.out.println("匿名子类的匿名对象\n");
            }
        });
//        new Static001(()->{System.out.println("匿名子类的匿名对象\n");});

    }
    public static void qw(Static002 static002){
        static002.eats();

    }
    public static void er(Static001 static001){
        static001.eats();
    }
}
abstract class Static001{
    String name;
    int age;

    public Static001(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Static001() {
    }
    public void walk() {
        System.out.println("走");
    }

    public abstract void eats();//他的非抽象子类  必须写eats方法
    public void eat() {
        System.out.println("吃");
    }
}

class Static002 extends Static001{
    public Static002(String name, int age) {
        super(name, age);
    }

    public Static002() {
    }

    public void eats() {
        System.out.println("吃s");
    }
}