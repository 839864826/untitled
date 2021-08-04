package base.异常;

/**
 * ➢Exception:其它因编程错误或偶然的外在因素导致的一-般性问题，可以使
 *  *          用针对性的代码进行处理。例如:
 *  *              r空指针访问
 *  *              r试图读取不存在的文件
 *  *              r网络连接中断
 *  *              r数组角标越界
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 */
public class ExceptionTest {
    public static void main(String[] args) {


    }

    //******************以下是编译时异常*******************************************

    private static void 编译时异常01() throws IOException {
        //如果没有   throws IOException  就回爆红  也可以用
        //try{}catch{}
        File file=new File("helle.txt");
        FileInputStream fis = new FileInputStream(file);

        int data = fis.read();
        while (data != -1){
            System.out.println();
        }
        fis.close();
    }

    //******************以下是运行时异常*******************************************
    private static void ArithmeticExceptionTest() {
        System.out.println(5/0);
        //Exception in thread "main" java.lang.ArithmeticException
    } //算数异常

    private static void InputMismatchExceptionTest() {
        Scanner cin=new Scanner(System.in);
        int a=cin.nextInt();
        //Exception in thread "main" java.util.InputMismatchException
        System.out.println(a);
        cin.close();//主动关闭
    } //输入异常

    private static void ClassCastExceptionTest() {
        Object o=new Date();
        String s=(String) o;
        //Exception in thread "main" java.lang.ClassCastException
    } //类型转换异常

    private static void NumberFormatExceptionTest() {
        String s="123";
        s="adf";
        int num=Integer.parseInt(s);
        //Exception in thread "main" java.lang.NumberFormatException
    } //数字转换异常

    private static void NullPointerExceptionTest() {
        String str="null";
        str = null;
        System.out.println(str.charAt(0));
        //Exception in thread "main" java.lang.NullPointerException
    } //空指针异常

    private static void IndexOutOfBoundsExceptionTest() {
        //角标越界
//        int[] a=new int[3];
//        System.out.println(a[3]);
//        //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException

        String a="aaa";
        System.out.println(a.charAt(3));
        //Exception in thread "main" java.lang.StringIndexOutOfBoundsException

    } //角标越界
}
