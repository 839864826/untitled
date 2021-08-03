package JavaN;

/**
 *  *       3.1 JDK7及以前:只能定义全局常量和抽象方法  interface
 *  *          >全局常量: public static final的  可以省略
 *  *          >抽象方法: public abstract的  可以省略
 *  *      3.2 JDK8: 除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法
 *  *
 *   default是在java8中引入的关键字  可在interface中添加方法的具体实现代码
 *                                   interface按理说只能定义方法名
 *   因为类优先于接口
 *
 *
 */
public interface Java8 {
    public static void method1(){//使用方式和工具类一样
        System.out.println("定义静态方法");
    }

    //默认方法
    public default void method2(){
        System.out.println("定义/默认方法02");
    }
     default void method3(){
        System.out.println("定义/默认方法03");
    }
}
class JK implements Java8{

}
class defaults{
    public static void main(String[] args) {
        //详情  见接口的 interfaceTest
        Java8 jk=new JK();


        //知识点1:接口中定义的静态方法，只能通过接口来调用。
        Java8.method1();

        //知识点2:通过实现类的对象，可以调用接口中的默认方法。
        //如果实现类重写了接口中的默认方法，调用时，仍然调用的是重写的方法
        jk.method2();

        jk.method3();

    }
}
