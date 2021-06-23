package base;

import java.io.PrintStream;

/**
 * POP面向过程//c
 * OOP面向对象//java
 * 基本类型赋值的变量是数据值  a=b   给的是值
 * 引用数据类型 赋值时是地址   a=b   给的是地址   需要new
 *
 * 如果变量是基本数据类型，此时赋值的是变量所保存的数据值。
 * 如果变量是引用数据类型，此时赋值的是变量所保存的数据的地址值。
 */
public class POP_OOP {
    public static void main(String[] args) {

        int[] a=new int[]{1,2,3};

        System.out.println(a);
        char[] c = new char[]{'a','v'};
        System.out.println(c);

    }

    private static void Arrays01() {
        int[][] a=new int[5][];
        System.out.println(a[0]);//null
        aw("1","1");
        aw();
    }

    public static void aw(String ... A){//可变参数的形参和String[] A  差不读一样 不可以重载

    }

    public static void method(int a,int b){
        PrintStream ps=new PrintStream(System.out){
            @Override
            public void println(String x){
                super.println(x);
            }
        };//重写System.out.println方法
    }
}
