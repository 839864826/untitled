package IO流;

import java.io.*;

/**
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）         缓冲流（处理流的一种）
 * InputStream     FileInputStream        BufferedInputStream
 * OutputStream    FileOutputStream       BufferedOutputStream
 * Reader          FileReader             BufferedReader
 * Writer          FileWriter             BufferedWriter
 *
 */
public class FileReaderWriter字符流 { //流一定要关闭   字符流 处理"字符"的 char
    //E:\\xunlian\\untitled\\File\\hello.txt
    public static void main(String[] args)  { //一般用在文本文件
        readTest();
    }

    private static void writeTest() {
        File file = new File("File\\hello.txt");
        FileWriter fW = null;
        /**
         * 说明
         * 1.write操作  file可以不存在  new FileWriter(file,false/true);
         *      如果不存在，在输出的过程中，会自动创建此文件。
         *      如果存在:  默认为false   对原有文件的覆盖
         *                  true  不会对原有文件覆盖，而是在原有文件基础上追加内容
         */
        try {
            //文件覆盖  默认为false    true是在原有文件后添加
            fW = new FileWriter(file,true);

            fW.write("毛纪");
//            fW.write("毛纪",0,85);//从 0写入  写入85个  报错  因为前面字符串没85个
            fW.append("s");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fW.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } //写出  把内容写道文件里

    private static void readTest() {
        File file = new File("File\\hello.txt");
//        System.out.println(file.getAbsoluteFile());//获得绝对路径
//        System.out.println(file.length());//获得绝对路径

        /**
         *  说明点：
         *     1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
         *     2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
         *     3. 读入的文件一定要存在，否则就会报FileNotFoundException。
         *
         */
        FileReader fR = null;
        try {
            fR = new FileReader(file);
            //可能出现异常 但是这时fR还没有开启流 所以fR.close();
            // 要判断一下防止没有流  没法关闭  出现异常

            //读入
            int data = fR.read();//读取第一个字符
            while (data!=-1){
                System.out.print((char) data);
                data = fR.read();//读完了  返回-1
            }

//            //批量查询
//            char[] cbuf = new char[5];//fR.read(cbuf)
//            // 他的读取只是覆盖原来的数组 如果原本有值且没有全部覆盖，后几个char就是上次的char
//            System.out.println(fR.read(cbuf));//读到几个返回多少  没有返回-1

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fR!=null) fR.close();//流资源关闭   不关闭造成资源浪费
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } //读入  把文件能容弄到java里

}
