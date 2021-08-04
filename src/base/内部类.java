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
 *
 * 成员内部类和局部内部类，在编译以后，都会生成字节码文件。
 * 格式:成员内部类:  外部类$内部类名.class
 * 局部内部类:      外部类$数字内部类名.class//数字  若重名 按加载顺序1 2 3。。。
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
            WW.this.name="WW名字2";
            System.out.println(name+"鸟唱歌");
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
        final int age=5;//默认有  final（最后的） Java8可以不写 默认有
        /**
         * 为啥要final
         * 因为这个age是在方法里面定义的  作用域在方法里 覆盖了这个局部内部类
         * 但是局部内部类的class文件出了方法的结构  在另一个文件了
         * 加final 表示出了方法就失效了  还想用的话
         * 实际上传的是副本 改副本 也改不了原来的
         *
         *  Java7必须写final
         *  Java8可以不写 默认有final
         */
        //局部内部类
        class NN{
            public void show(){
//                age=5;//有final不可更改
                System.out.println(age);
            }
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

         int a=5;
        //创建一个实现了Comparable接口的类:局部内部类//方法二
         class MyComparable implements Comparable{

            @Override
            public int compareTo(Object o) {
                return a;
            }
            //内部类用到了 a就变成了final
        }
//        a=6;
        return new MyComparable();

    }

}