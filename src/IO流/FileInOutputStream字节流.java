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
public class FileInOutputStream字节流 { //可以复制文本文件  也不影响 但是不能读  注意位数
    public static void main(String[] args) {
//        File file = new File("File\\1.jpg");
//        File file1 = new File("File\\2.jpg");
        String cpFile0 = "D:\\第一部\\短信\\华为手机助手导出的短信2020-01-23_175631263.csv";
        String cpFile1 = "D:\\第一部\\短信\\华为手机短信.csv";
        int sd = 1024;
        字节流的复制(cpFile0, cpFile1, sd);
        //如果他是一个文件夹 java.io.FileNotFoundException异常  拒绝访问

    }

    private static void 字节流的复制(String cpFile0, String cpFile1, int sd) {
        File file = new File(cpFile0);
        File file1 = new File(cpFile1);
        FileInputStream fI = null;
        FileOutputStream fO = null;
        try {
            fI = new FileInputStream(file);
            fO = new FileOutputStream(file1,false);//覆盖  没有创建
//            fO = new FileOutputStream(file1,true);//向后添加  没有创建
            byte[] bytes = new byte[sd];//
            int len;
            while ((len = fI.read(bytes)) != -1){
//                String str = new String(bytes,0,len);
                fO.write(bytes,0,len);
                //由于汉字等某些字符  一个字符占不等的 字节 所以有汉字的用字符流好

//                System.out.print(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fI.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fO.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
