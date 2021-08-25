package IO流;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
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
 *
 *
 */
public class FileReaderWriterTest { //流一定要关闭
    //E:\\xunlian\\untitled\\File\\hello.txt
    public static void main(String[] args)  {
        File file = new File("File\\hello.txt");
//        System.out.println(file.getAbsoluteFile());//获得绝对路径
//        System.out.println(file.length());//获得绝对路径

        FileReader fR = null;
        try {
            fR = new FileReader(file);
            //可能出现异常 但是这时fR还没有开启流 所以fR.close();
            // 要判断一下防止没有流  没法关闭  出现异常

            int data = fR.read();//读取第一个字符
            while (data!=-1){
                System.out.print((char) data);
                data = fR.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fR!=null) fR.close();//流关闭   不关闭造成资源浪费
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
