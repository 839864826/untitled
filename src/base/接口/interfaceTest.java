package base.接口;


/**
 * 接口
 * 一方面，有时必须从几个类中派生出一个子类，继承它们所有的属性和方
 * 法。但是，Java不支持多重继承。有了接口，就可以得到多重继承的效果。
 *
 * 接口的使用
 * 1.接口使用interface来定义
 * 2.Java中，接口和类是并列的两个结构
 * 3.如何定义接口:定义接口中的成员
 *      3.1 JDK7及以前:只能定义全局常量和抽象方法  interface
 *          >全局常量: public static final的  可以省略
 *          >抽象方法: public abstract的  可以省略
 *      3.2 JDK8: 除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法
 * 4.接口中不能定义构造器的!意味着接口不可以实例化
 * 5.Java开发中，接口通过让类去实现( implements )的方式来使用
 *   如果实现类覆盖了接口中的所有抽象方法，则此实现类就可以实例化
 *   如果实现类没有覆盖接口中所有的抽象方法，则此实现类仍为一个抽象类
 *
 * 6.Java类可以实现多个接口--- >弥补了Java单继承性的局限性
 *      格式: class AA extends BB implements CC,DD,EE
 * 接口和接口之间可以
 * 7.接口与接口之间可以继承，而且可以多继承
 *
 * 8.接口的具体使用，体现多态性
 * 9.接口，实际上可以看做是一种规范
 *
 *
 */
public class interfaceTest {
    public static void main(String[] args) {
        Pland pland = new Pland();
        pland.fly();
        System.out.println("**********************");

    }
}

interface  Flyable{

    //全局常量
    public static final int MAX_SPEED = 7900;
    int MIN_SPEED = 1;// 省略了public static final


    //抽象方法
    public  abstract void fly();
    void stop();//省略了public  abstract

    //Interface abstract methods cannot have body接口抽象方法不能有正文
//    public  Flyable(){
//
//    }


} //接口 飞

interface Attackable{
    void attack();//省略了public  abstract
}


class Pland implements Flyable{

    @Override
    public void fly() {
        System.out.println("通过引擎飞行");
    }

    @Override
    public void stop() {
        System.out.println( "驾驶员减速停止") ;
    }
}

abstract class Kite implements Flyable{

    @Override
    public void fly() {

    }
}

class Bullet implements Flyable,Attackable{


    @Override
    public void fly() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void attack() {

    }
}

interface AA{
    int x=0;// 省略了public static final
    void play();
     default void B1(){
        System.out.println("B2");
    }
}
interface BB{
    void play();
}

interface CC extends AA,BB{

}//接口可一多继承
class B{
    int x=1;
    public static void B1(){
        System.out.println("B1");
    }
}


class C extends B implements CC{
    public void PX(){
        B1();//因为类优先于接口  输出B1
        System.out.println(super.x);//super 父类
        System.out.println(AA.x);// 省略了public static final  所以他是全局的
//        System.out.println(x);// 不明确   不知道是B的还是AA的
    }

    public static void main(String[] args) {
        new C().PX();
    }

    @Override
    public void play() {
    }
}
