package base;

/**
 * 类的内部成员之五:内部类
 * 1. Java中 允许将一个类A声明在另一个类B中，则类A就是内部类，类B称为外部类
 * 2.内部类的分类:成员内部类vs局部内部类(方法内、代码块内、构造器内)
 *
 * 3.成员内部类
 *       一方面， 作为外部类的成员:
 *              >调用外部类的结构
 *              >可以被static修饰
 *              >可以被4种不同的权限修饰
 *
 *       另一方面，作为一个类:
 *              >类内可以定义属性、方法、构造器等
 *
 */
public class 内部类 {//一个Java文件只有主类可以用public修饰   其他副类

}
 class WW{

    public void gg(){

    }
    //非静态成员内部类
    class Bird{
        String name;
        public Bird(){

        }
        public void sing(){
            gg();//等同于↓
            WW.this.gg();
        }//调用外部类的结构
    }
    //静态成员内部类
    static class Dog{//可以static修饰内部类   按理说是不可以的

    }


    //局部内部类
    public void ZZ(){
        //局部内部类
        class NN{

        }
    }
    {
        //局部内部类
        class NN02{

        }
    }
    public WW(){
        //局部内部类
        class  NN03{

        }
    }
}