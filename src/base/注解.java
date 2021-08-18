package base;


import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.ElementType.*;

/**
 * ●从JDK5.0开始，Java增加了对元数据(MetaData)的支持,也就是
 * Annotation(注解)
 * ●Annotation其实就是代码里的特殊标记,这些标记可以在编译,类加
 * 载,运行时被读取,并执行相应的处理。通过使用Annotation,程序员
 * 可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息I代
 * 码分析工具、开发工具和部署工具可以通过这些补充信息进行验证
 * 或者进行部署
 * ●Annotation可以像修饰符一样被使用,可用于修饰包，类,构造器，方
 * 法，成员变量，参数,局部变量的声明,这些信息被保存在Annotation
 * 的"name=value"对中。
 *
 *
 * ●在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，
 * 忽略警告等。在JavaEE/Android中注解 占据了更重要的角色，例如
 * 用来配置应用程序的任何切面，代替JavaEEI旧版中所遗留的繁冗
 * 代码和XML配置等。
 * ●未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以
 * 上都是基于注解的，Hibernate3.x以后也是基于注解的，现在的
 * Struts2有一部分也是基于注解的了，注解是一种趋势，一定程度上
 * 可以说:框架=注解+反射+设计模式。
 */

/**
 * ●示例一:生成文档相关的注解
 * @author 标明开发该类模块的作者，多个作者之间使用,分割
 * @version 标明该类模块的版本
 * @see 注解   参考转向，也就是相关主题
 * @since 从哪个版本开始增加的
 * @param对方法中某参数的说明，如果没有参数就不能写
 * @return 对方法返回值的说明，如果方法的返回值类型是void就不能写
 * @exception对方法可能抛出的异常进行说明，如果方法没有用throws显式抛出的异常就不能写
 * 其中
 * @param @return 和@exception 这三个标记都是只用于方法的。
 * @param的格式要求: @param 形参名形参类型形参说明
 * @return 的格式要求: @return 返回值类型返回值说明
 * @exception的格式要求: @exception 异常类型异常说明
 * @param和@exception 可以并列多个
 *
 *
 * ➢@Override:限定重写父类方法，该注解只能用于方法
 * ➢@Deprecated:用于表示所修饰的元素(类,方法等)已过时。通常是因为
 * 所修饰的结构危险或存在更好的选择
 * ➢@SuppressWarnings:抑制编译器警告
 *
 * @WebServlet("/login")
 */



public class 注解 {
    private String param;
    /**
     *
     * @param args
     * @throws Exception
     * @exception Exception
     */
    public static void main(String[] args) throws Exception{
        Class<自定义注解> bClass = 自定义注解.class;//获得这个注解有哪些注解  反射
        Annotation[] annotations = bClass.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
//        Class bClass1 = b.class;//获得这个注解有哪些注解
//        Annotation[] annotations1 = bClass1.getAnnotations();
//        for (int i = 0; i < annotations1.length; i++) {
//            System.out.println(annotations1[i]);
//        }
    }

    @SuppressWarnings({"unused"})//加个这  就不会灰色了
    private static void 自带注解() {
        b b1=new b();
        b1.AA();//表示过时了

        @SuppressWarnings({"unused"})//加个这  就不会灰色了
        int num = 10;//没用到  num是灰的
        @SuppressWarnings({"unused"})//加个这  就不会灰色了
        List list=new ArrayList();
    }
}
@自定义注解(value = "66",value1 = "966")
abstract class Aa{
    abstract void AA();
}
interface a{
    void aa();
}
@自定义注解(value = "66",value1 = "966")
@自定义注解(value = "66",value1 = "966")
class b extends Aa implements a{
    @Override//限定重写父类方法，该注解只能用于方法
    public void aa() {
    }
    @Override
    @Deprecated//用于表示所修饰的元素(类,方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
    void AA() {

    }
}
/**
 * 3.如何自定义注解:参照@SuppressWarnings定义
 * *①注解声明为: @interface
 * *②内部定义成员，通常使用value表示
 * *③可以指定成员的默认值，使用default定义
 * *④如果自定义注解没有成员，表明是一个标识作用。
 *
 *如果注解有成员，在使用注解时，需要指明成员的值。
 * 自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
 * 自定义注解通过都会指明两个元注解: Retention、 Target
 *
 * 4. jdk提供的4种元注解
 * |元注解: 对现有的注解进行解释说明的注解
 * Retention:指定所修饰的Annotation的生命周期: SOURCE\CLASS (默认行为) \RUNTIME
 * 只有声明为RUNTIME生命周期的注解，力 能通过反射获取。
 * Target:用于指定被修饰的Annotation能用于修饰哪些程序元素
 * Documented:表示所修饰的注解在javadoc解析时   保留下来（api）
 * Inherited:被它修饰的注解  将具有继承性
 *
 *
 */
@Inherited//被它修饰的注解  将具有继承性
@Repeatable(重复自定义注解.class)//让注解可以写多个 jdk8 新增
@Target(value={TYPE_USE,TYPE_PARAMETER,CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})//指明这个注解可以修饰神魔
//   TYPE_USE           TYPE_PARAMETER   可以修饰泛型
@Retention(RetentionPolicy.CLASS)//默认行为  不写也行   SOURCE
//@Retention(RetentionPolicy.RUNTIME)//只有声明为RUNTIME生命周期的注解，才能通过反射获取。
@interface 自定义注解{//之后反射在讲真正用处

    String value();
    String value1()default "hello";//默认hello
}
@Inherited//被它修饰的注解  将具有继承性
@Target(value={TYPE_PARAMETER,CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})//指明这个注解可以修饰神魔
@Retention(RetentionPolicy.CLASS)//默认行为  不写也行   SOURCE
@interface 重复自定义注解{//之后反射在讲真正用处
    自定义注解[] value();
}
/**
 * jdk1.8新注解
 * 6. jdk 8中注解的新特性:可重复注解、类型注解
 * 6.1可重复注解:①在  自定义注解.上声明@Repeatable, 成员值为  重复自定义注解.class
 * ②自定义注解的Target和Retention等元注解和 重复自定义注解 相同。
 * 6.2类型注霹:
 * ElementType. TYPE_ PARAMETER 表示该注解能写在类型变量的声明语句中(如:泛型声明)。
 * ElementType. TYPE_ USE表示该注解能写在使相类型的任何语句中。
 */

class Good<@自定义注解(value = "66",value1 = "966") T>{
    public void show()throws @自定义注解(value = "66",value1 = "966")RuntimeException{
        ArrayList<@自定义注解(value = "66",value1 = "966") String> list = new ArrayList<>();

    }

}
