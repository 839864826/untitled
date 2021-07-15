package base;

/**
 * 对象比较
 * Integer i=new Integer(50);
 * Integer j=new Integer(50);
 * System.out.println(i==j);  //运行的结果是false
 * Integer i=new Integer.valueOf(100);
 * Integer j=new Integer.valueOf(100);
 * System.out.println(i==j);  //运行的结果是true
 * Integer i=new Integer.valueOf(400);
 * Integer j=new Integer.valueOf(400);
 * System.out.println(i==j);//运行结果是false
 * Integer i=100;
 * Integer j=100;
 * System.out.println(i==j);//运行结果是true
 * 然后再用400这个数试一试，通过实验运行的结果是false，
 *public static Integer valueOf(int i){
 * if(i>=-128&&i<=IntegerCache.high)
 * return IntegerCache.cache[i+128];
 * else return new Integer(i);
 * }
 * 通过看源码能够知道整数类型在-128~127之间时，会使用缓存。
 * 造成的效果就是，如果已经创建了一个相同的整数，
 * 使用valueOf创建第二次时，不会使用new 关键字，
 * 而是用已经缓存的对象。所以使用valueOf方法创建两次对象，
 * 若对应数值相同，且数值在-128~127之间时，两个对象指向同一个地址。
 * 使用Integer i=400这样的方法创建Integer对象与使用valueOf方法的效果是一样的,
 * 若要比较，使用compareTo或者equals方法是更好的
 */

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 包装类的使用
 * 强转   必须有字符类关系
 *
 * 1.java提供了8种基本数据类型对应的包装类，使得基本数据类型的变量具有类的特征
 *
 * 2.掌握的:基本数据类型、包装类、String三者 之间的相互转换
 *
 */
public class WrapperDemo {



    public static void main(String[] args) {

        Order a=new Order();
        Or b= new Or();
        if(b instanceof Order){
            System.out.println("b instanceof Order");
        }
        if(a instanceof Order){
            System.out.println("a instanceof Order");
        }
        if(a instanceof Or){
            System.out.println("a instanceof Or");
        }

    }

    private static void String_int() {
        //String类型--- >基本数据类型、包装类:调用包装类的parseXxx ()
        //基本数据类型--- >String类型
        int num1 = 1 ;
        String str1 = num1 + "";
        str1 = String.valueOf(num1);//转换
        System.out.println(str1);
        String str2 = "123";
        str1.equals(str2);
        int num2 = Integer.parseInt(str2);
        System.out.println(num2);
    }

    private static void Auto_boxDemo() {
        //包装类---》基本数据类型：调用包装类的xxxValue()可以返回他的原基本数据类型
        int a=1;
        Integer b = new Integer(a);//按理说
        Integer c = a;//但是自动装箱
        int a1 = c.intValue();//按理说
        int a2 = b;//自动拆箱
    }//自动装箱

    private static void BooleanDemo() {
        int num01 = 10;
        Integer num1 = new Integer(10);
        Order order = new Order();
        System.out.println(order.isMale);//false   默认值
        System.out.println(order.isFemale);//null
    }
}
class Order{
    boolean isMale;
    Boolean isFemale;
}
class Or extends Order{
    int a=0;
}
