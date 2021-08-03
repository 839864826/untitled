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
 */
public interface java8 {
    public static void method1(){
        System.out.println("定义静态方法");
    }
    public default void method2(){
        System.out.println("定义静态方法");
    }
    public static void method3(){
        System.out.println("定义静态方法");
    }
}
