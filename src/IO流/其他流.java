package IO流;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 其他流的使用
 * 1.标准的输入、输出流
 * 2.打印流
 * 3.数据流
 */
public class 其他流 {
    public static void main(String[] args) {
//
//        String str = "ad";
//        str.chars().filter(x->x>3).forEach(x-> System.out.println(x));
//
//        Arrays.asList(4,5,9,10).parallelStream()
//                .filter(m->{
//                    System.out.println(m);
//                    return (m>1);
//                }).map(m -> m+1)
//                .collect(Collectors.groupingBy(x ->x%10))
//                .forEach((x,y)-> System.out.println(y));
//

        Map<Integer,Integer> map = new HashMap<>();
        map.put(5,6);
        System.out.println(map.get(4));

    }

    private static void 数据流() {
        /**
         *  数据流
         *    DataInputStream 和 DataOutputStream
         *    作用：用于读取或写出基本数据类型的变量或字符串
         *
         *     练习：将内存中的字符串、基本数据类型的变量写出到文件中。
         *
         */
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("File\\cin.txt"));
            dis = new DataInputStream(new FileInputStream("File\\cin.txt"));
            dos.writeUTF("张蛟龙");
//            dos.flush();//刷新操作，将内存中的数据写入文件

            dos.writeInt(23);
//            dos.flush();
            dos.writeBoolean(true);
//            dos.flush();

            /**
             * 读取的顺序要与存入的顺序一致   因为他读取的字节有变动
             */
            System.out.println(dis.readUTF());
            System.out.println(dis.readInt());
            System.out.println(dis.readBoolean());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } //可以保存类型

    private static void 打印流() {
        /**
         * 打印流
         * ●实现将基本数据类型的数据格式转化为字符串输出
         * ●打印流: PrintStream 和PrintWriter
         * ➢提供了一系列重载的print()和println()方法，用于多种数据类型的输出
         * ➢PrintStream和PrintWriter的输出不会抛出IOException异常
         * ➢PrintStream 和PrintWriter有自动flush功能
         * ➢PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。
         * 在需要写入字符而不是写入字节的情况下，应该使用PrintWriter 类。
         * ➢System.out返回的是PrintStream的实例
         */


        FileOutputStream  fos = null;
        try {
            fos = new FileOutputStream("File\\cin.txt");
            //创建打印输出流，设置为自动刷新模式(写入换行符或字节'\n' 时都会刷新输出缓冲区)
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream ps = new PrintStream(fos);
//        PrintWriter pw = new PrintWriter(fos);
        if(ps != null){
            System.setOut(ps);//把标准输出流(控制台输出)改成文件
        }
        for (int i = 0; i < 256; i++) {
            System.out.print((char) i);
            if(i%50==49){
                System.out.println();
            }
        }
    }

    private static void 标准的输入输出流() {
        /**
         * 标准的输入流
         *
         * 标准的输出流
         *
         * ●System.in和System.out分 别代表了系统标准的输入和输出设备
         * ●默认输入设备是:键盘，输出设备是:显示器
         * ●System.in的类 型是InputStream
         * ●System.out的类 型是PrintStream,其是OutputStream的子类
         * FilterOutputStream的子类
         * ●重定向: 通过System类的setlIn, setOut方法对默认设备进行改变。
         * ➢public static void setIn(InputStream in)
         * ➢public static void setOut(PrintStream out)
         *
         * 方法一：使用Scanner实现，调用next()返回一个字符串
         * 方法二：使用System.in实现。
         *      System.in  --->  转换流 ---> BufferedReader的readLine()
         *
         */
//        Scanner cin = new Scanner(System.in);
//        String next = cin.next();
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true){
                System.out.println("请输入字符串");

                String data = br.readLine();


                if("e".equalsIgnoreCase(data)||"exit".equalsIgnoreCase(data)){
                    System.out.println("程序结束");
                    break;
                }else {
                    String s = data.toUpperCase();
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
