package IO流;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        随机流();
    }

    private static void 随机流() {
        /**
         * RandomAccessFile的使用
         * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
         * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
         *
         * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
         *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
         *
         * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
         *
         * ●创建RandomAccessFile类实例需要指定一个mode参数，该参数指
         * 定RandomAccessFile的访问模式:
         * ➢r:以只读方式打开
         * ➢rw:打开以便读取和写入
         * ➢rwd:打开以便读取和写入;同步文件内容的更新
         * ➢rws:打开以便读取和写入;同步文件内容和元数据的更新
         * ●如果模式为只读r。则不会创建文件，而是会去读取一一个已经存在的文件,
         * 如果读取的文件不存在则会出现异常。如果模式为rw读写。如果文件不
         * 存在则会去创建文件，如果存在则不会创建。
         *
         */
        /**
         * ●RandomAccessFile类支持“随机访问”的方式，程序可以直接跳到文件的任意
         * 地方来读、写文件
         * ➢支持只访问文件的部分内容
         * ➢可以向已存在的文件后追加内容
         * ●RandomAccessFile对象包含-一个记录指针，用以标示当前读写处的位置。
         * RandomAccessFile类对象可以自由移动记录指针:
         * ➢long getFilePointer():获取文件记录指针的当前位置
         * ➢void seek(jong pos):将文件记录指针定位到pos位置
         */
        RandomAccessFile raf0 = null;
        RandomAccessFile raf1 = null;
        try {
            raf0 = new RandomAccessFile(new File("File\\cout.txt"),"r");
            raf1 = new RandomAccessFile(new File("File\\cin.txt"),"rw");
            byte[] buffer = new byte[5];
//            StringBuilder sb = new StringBuilder();// 由于字节与String的字符冲突  中文等  会出现乱码
            ByteArrayOutputStream sb = new ByteArrayOutputStream();//这个不会出现乱码
            int len;
            raf1.seek(5);//指针位置调到5  开始覆盖或读取   从0开始
            while((len = raf1.read(buffer)) != -1){
//               sb.append(new String(buffer,0,len));
                sb.write(buffer,0,len);
            }
            System.out.println(sb);
            raf1.seek(5);//指针位置调到5  开始覆盖或读取   从0开始
            raf1.write("666".getBytes());
//            raf1.write(sb.toString().getBytes());//对原有文件进行覆盖
            raf1.write(sb.toByteArray());//对原有文件进行覆盖

            //原有文件存在   不会删除原有文件  只是从指针位置（默认从0开始）开始覆盖


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf0.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void 对象流序列化() {
        /**
         * 处理流:对象流
         * ●ObjectInputStream和ObjectOutputSteam
         * ➢用于存储和读取基木数据类型数据或对象的处理流。它的强大之处就是可
         *   以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
         * ●序列化: 用ObjectOutputStream类 保存基本类型数据或对象的机制
         * ●反序列化:用ObjectInputStream类 读取基本类型数据或对象的机制
         * ●ObjectOutputStream和ObjectInputStream不能序
         *  列化static和transient修饰的成员变量
         *
         * ●对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从
         *  而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流传
         *  输到另一个网络节点。
         *  当其它程序获取了这种二进制流，就可以恢复成原来的Java对象
         * ●序列化的好处在于可将任何实现了Serializable接口的对象转化为字节数据,
         *  使其在保存和传输时可被还原
         * ●序列化是RMI ( Remote Method Invoke -远程方法调用)过程的参数和返
         *  回值都必须实现的机制，而RMI是JavaEE的基础。因此序列化机制是
         *  JavaEE平台的基础
         * ●"如果需要让某个对象支持序列化机制"，则必须让对象所属的类及其属性是可
         *  序列化的，为了让某个类是可序列化的，该类必须实现如下两个接口之一。
         *  否则，会抛出NotSerializableException异常
         * ➢Serializable
         * ➢Externalizable
         *
         */

        ObjectOutputStream oos = null;//写
        ObjectInputStream ois = null;//读
        try {
            oos = new ObjectOutputStream(new FileOutputStream("File\\hello1.txt"));
            ois = new ObjectInputStream(new FileInputStream("File\\hello1.txt"));

            oos.writeObject("我爱中国");
            oos.flush();
            oos.writeObject(new User(1,22,"张蛟龙"));
            oos.flush();


            System.out.println(ois.readObject());

            System.out.println(ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
