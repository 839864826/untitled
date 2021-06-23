package base;

//这就是一个javaBeen    有空参   有gat  set方法

/**
 * this调用构造器
 * ④我们在类的构造器中，可以显式的使用"this (形参列表) "方式，调用本类中指定的其他构造器
 * ②构造器中不能通过"this (形参列表) "方式调用自己
 * ③如果一个类中有n个构造器，则最多有n - 1构造器中使用了"this (形参列表)
 * ④规定: "this (形参列表) "必须声明在当前构造器的首行//所以⑤
 * ⑤构造器内部，最多只能声明一个"this (形参列表)"，用来调用其他的构造器
 *
 *
 *
 * 所谓JavaBean，是指符合如下标准的Java类:
 * >类是公共的
 * >一个无参的公共的构造器
 * >属性，且对应的get、 set方法
 *
 *
 */
public class JavaBeenDemo {
    private int a;
    private String b;

    public JavaBeenDemo(int a, String b) {
        this.a = a;
        this.b = b;
    }

    public JavaBeenDemo() {
        this(1,"s");//调用了自己类的方法//bi'xi'fang'zai
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
