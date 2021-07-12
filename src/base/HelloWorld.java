package base;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *对于整数，有四种表示方式:
 * >二进制(binary): 0,1，满2进1.以0b或0B开头。
 * >十进制(decimal): 0-9，满10进1。
 * >八进制(octal): 0-7，满8进1.以数字0开头表示。
 * >十六进制(hex): 0-9及A-F，满16进1.以0x或ex开头表示。此处的A-F不区分大小写。
 * 如: 0x21AF +1= 0X21B0
 *
 * javadoc -d 自己起的名字 -author -version HelloJava. java
 *
 *
 * << 空位补0，被移除的高位丢弃，空缺位补0。
 * >> 被移位的二进制最高位是0，右移后，空缺位补0; 最高位是1，空缺位补1。
 * >>>  被移位二进制最高位无论是0或者是1，空缺位都用0补。
 * & 二进制位进行&运算，只有1&1时结果是1，否则是0;
 * | 二进制位进行|运算，只有0 | 0时结果是0，否则是1;
 * ^ 相同二进制位进行^运算，结果是0; 1^1=0 ，0^0=0
 *  不相同二进制位^运算结果是1。1^0=1， 0^1=1
 * ~ 正数取反，各二进制码按补码各位取反
 *  负数取反，各二进制码按补码各位取反
 */

public class HelloWorld {
    public static void main(String[] args) {

//        Integer
        int d;

    }

    private static void ArraysDemo() {
        /**
         * 数组操作
         * a.equals(b)//a和b是否相等
         *
         */
        String[] a= new String[]{"AA","BB","CC","DD","EE"};
        String B="BB";
        Arrays.fill(a,"1");//数组遍历赋值
        System.out.println(Arrays.toString(a));//遍历

        for (int i = 0; i < a.length; i++) {
            if(a[i].equals(B)){//a和b是否相等   Arrays.equals(a[i],B)//int数组
                System.out.println("相等");
            }
        }
    }

    private static void Array02Demo() {
        int[][] arr = new int[][]{{0,0,0},{0,5},{1,1,1}};
        System.out.println(arr[1][1]);
        int[][] arr1 = new int[3][];
        arr1[1]=new int[5];//加个这不会异常
        System.out.println(arr1[1][1]);//空指针异常
        System.out.println(arr.length);
        System.out.println(arr[1].length);
        int[][] arr01 = new int[4][];
        System.out.println(arr01[0]);//空  只有当定义时  只有第一个中括号有数字 为空


    }

    private static void Array01Demo() {
        /**
         * 赋值和给数组个数
         * 只能用一个
         *
         * ⑤数组元素的默认初始化值
         * >数组元素是整型: 0
         * >数组元素是浮点型: 0.0
         * >数组元素是char型: 0或'u0000'，而非'0 '
         * >数组元素是boolean型: false
         *
         * >数组元素是引用数据类型: null
         *
         */
        int[] ids = new int[]{1,2,3,4};
        System.out.println(ids[1]);
        String[] name=new String[5];//数组大小为5
        System.out.println(ids.length);
        String[] arr=new String[5];
        arr[1]="111";
        arr = new String[2];//会重新定义   地址改变  之前的成为垃圾
        System.out.println(arr[1]);
    }

    private static void Sca(String[] args) {
        Scanner cin = new Scanner(System.in);
        String s="sasasa";
        char c=s.charAt(2);
        switch (c){
            default:
                System.out.println("结束");
            case 'a':
                System.out.println("A");
                break;
            case 'c':
                System.out.println("aaaa");

        }
        System.out.println(args.length);
        for (String a: args){
            System.out.println(a);
        }
    }
}
