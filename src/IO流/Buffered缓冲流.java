package IO流;

import java.io.*;

/**
 * 处理流之一:缓冲流的使用
 * 1.缓冲流:
 * BufferedInputStream      byte[8192]
 * BufferedOutputStream     byte[8192]
 * BufferedReader           char[8192]
 * BufferedWriter           char[8192]
 * 2.作用:提供流的读取、写入的速度
 *      提高速度的原因   他创了一个缓冲区  大小为8192
 */
public class Buffered缓冲流 {
//    //E:\\xunlian\\untitled\\File\\hello.txt
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            //this(in, defaultCharBufferSize);
//            br = new BufferedReader(new FileReader(new File("File\\hello.txt")));
//            bw = new BufferedWriter(new FileWriter(new File("File\\hello1.txt")));
            br = new BufferedReader(new FileReader("File\\hello.txt"));//new File  可以省略
            bw = new BufferedWriter(new FileWriter("File\\hello1.txt"));
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1){
//                bw.write(cbuf,0,len);
//                //由于汉字等某些字符  一个字符占不等的 字节 所以有汉字的用字符流好
//            }
            String data;
            while ((data = br.readLine())!=null){//一次读一行 字符流才有
                bw.write(data);//不包括换行符

                bw.newLine();//添加换行 导致  最后一行必定是空的
//                bw.flush();//刷新缓冲区
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //用缓冲流速度比不用缓冲流快
    private static void 缓冲字节流的复制() {
        //造文件
        String cpFile0 = "D:\\第一部\\短信\\华为手机助手导出的短信2020-01-23_175631263.csv";
        String cpFile1 = "D:\\第一部\\短信\\华为手机短信.csv";
        int sd = 1024;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(cpFile0);
            File file1 = new File(cpFile1);
            //造节点流
            FileInputStream fI = new FileInputStream(file);
            FileOutputStream fO = new FileOutputStream(file1,false);//覆盖  没有创建
//            fO = new FileOutputStream(file1,true);//向后添加  没有创建

            //造缓冲流
            bis = new BufferedInputStream(fI);//this(in, DEFAULT_BUFFER_SIZE);DEFAULT_BUFFER_SIZE=8192
            bos = new BufferedOutputStream(fO);//this(out, 8192);


            byte[] bytes = new byte[sd];//
            int len;
            while ((len = bis.read(bytes)) != -1){
                bos.write(bytes,0,len);
                //由于汉字等某些字符  一个字符占不等的 字节 所以有汉字的用字符流好

//                bos.flush();//刷新缓冲区

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //4.资源关闭
            //要求:先关闭外层的流，再关闭内层的流
            //但是  关外层时  内层也会自动关闭   所以可以不用 fI.close(); fO.close();

            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
