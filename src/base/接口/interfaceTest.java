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
    public default void B1(){
        System.out.println("接口B2");
    }
    static void B2(){
        System.out.println("接口BB2");
    }
    default void pl(){
        System.out.println("接口plAA");
    }
}
interface BB{
    void play();
    default void pl(){
        System.out.println("接口plBB");
    }
}

interface CC extends AA,BB{
    default void pl(){
        System.out.println("接口plCC");

    }
}//接口可一多继承
class B{
    int x=1;
    public  void B1(){
        System.out.println("父类B1");
    }
    public static void B2(){
        System.out.println("父类BB1");
    }
    public void pl(){
        System.out.println("父类plB");

    }
}


class C extends B implements AA,BB{//CC{
    public void PX(){
        //知识点3:如果子类(或实现类)继承的父类和实现的接口中声明了同名同参数的方法，
        //那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数的方法。-- >类优先原则
        B1();//==super.B1();//因为类优先于接口  输出B1   相当于class中的B1  重写了接口的B1
        //把类class B 中的B1删了  就输出接口中的B1

        B2();//静态不可重写 class B 中的B2删了  报错↓
        //知识点1:接口中定义的静态方法，只能通过接口来调用。
        AA.B2();//静态不可重写         所以用 接口名.方法名

        //知识点4:如果实现类实现了多个接口，而这多个接口中定义了同名同参数的默认方法，
        //那么在实现类没有重写此方法的情况下，报错。-- >接口冲突。
        //这就需要我们必须在实现类中重写此方法
        pl();
        super.pl();//父类
        AA.super.pl();
        BB.super.pl();


        System.out.println(super.x);//super 父类
        System.out.println(AA.x);// 省略了public static final  所以他是全局的
//        System.out.println(x);// 不明确   不知道是B的还是AA的

    }

    public static void main(String[] args) {
        new C().PX();
    }


    @Override
    public void play() {
        System.out.println("重写playC");
    }

    @Override
    public void pl() {
        System.out.println("重写plC");
    }

}
