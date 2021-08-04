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
 *              >可以被final修饰， 表示此类不能被继承。言外之意，不使用final,就可以被继承
 *              >可以被abstract修饰
 *
 * 4.关注如下的3个问题
 *      4.1如何实例化成员内部类的对象
 *      4.2如何在成员内部类中区分调用外部类的结构
 *      4.3开发中局部内部类的使用
 *
 */
public class 内部类 {//一个Java文件只有主类可以用public修饰   其他副类

    public static void main(String[] args) {
        WW.Dog dog=new WW.Dog();//静态成员内部类
        dog.show();

        WW w=new WW();
        WW.Bird bird=w.new Bird();//非静态成员内部类
        bird.sing();
    }
}
 class WW{

    String name="WW名字";
    int age;
    public void eat(){
        System.out.println("eat");
        for (int i = 0; i < 10; i++) {
            continue;
        }
    }
    //非静态成员内部类
    class Bird{
        String name="Bird名字";
        int age;
        public Bird(){
//            System.out.println("Bird");
        }
        public void sing(){
            System.out.println("鸟唱歌");
            eat();//等同于↓
            WW.this.eat();
        }//调用外部类的结构
        public void display(String name){
            System.out.println(name);//方法的形参
            System.out.println(this.name);//内部类属性
            System.out.println(WW.this.name);//外部类属性
        }
    }
    //静态成员内部类
    static class Dog{//可以static修饰内部类   按理说是不可以的
        String name;
        int age;
        public void show(){
            System.out.println("狗");
        }
    }


    //局部内部类   很少见
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

    //返回一个实现了Comparable接口的类的对象
     public Comparable getComparable(){

        //方法一
//        return new Comparable() {
//            @Override
//            public int compareTo(Object o) {
//                return 0;
//            }
//        };

        //创建一个实现了Comparable接口的类:局部内部类//方法二
         class MyComparable implements Comparable{

            @Override
            public int compareTo(Object o) {
                return 0;
            }
        }
        return new MyComparable();

    }

}