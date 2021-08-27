package IO流;

import java.io.*;

/**
 * 处理流之二：转换流的使用
 * 1.转换流：属于字符流
 *   InputStreamReader：将一个字节的输入流转换为字符的输入流
 *   OutputStreamWriter：将一个字符的输出流转换为字节的输出流
 *
 * 2.作用：提供字节流与字符流之间的转换
 *
 * 3. 解码：字节、字节数组  --->字符数组、字符串
 *    编码：字符数组、字符串 ---> 字节、字节数组
 *
 * 4.字符集
 *  ASCII：美国标准信息交换码。
 *  用一个字节的7位可以表示。
 *  ISO8859-1：拉丁码表。欧洲码表
 *  用一个字节的8位表示。
 *  GB2312：中国的中文编码表。最多两个字节编码所有字符
 *  GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
 *  Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
 *  UTF-8：变长的编码方式，可用1-4个字节来表示一个字符。
 *
 *
 * ● Unicode不完美， 这里就有三个问题，一个是，我们已经知道， 英文字母只用
 * 一个字节表示就够了，第二个问题是如何才能区别Unicode利ASCII?计算机
 * 怎么知道两个字节表示-一个符号，而不是分别表示两个符号呢?第三个，如果
 * 和GBK等双字节编码方式-一样，用最高位是1或0表示两个字节和一个字节，
 * 就少了很多值无法用于表示字符，不够表示所有字符。Unicode在很长一-段时
 * 间内无法推广，直到互联网的出现。I
 * ●面向传输的众多UTF (UCS Transfer Format) 标准出现了，顾名思义，UTF
 * 8就是每次8个位传输数据，而UTF-16就是每次16个位。这是为传输而设计的
 * 编码，并使编码无国界，这样就可以显示全世界上所有文化的字符了。
 * ●Unicode只是定义了一个庞大的、全球通用的字符集，并为每个字符规定了唯
 * 一确定的编号，具体存储成什么样的字节流，取决于字符编码方案。推荐的
 * Unicode编码是UTF 8和UTF-16。
 *
 */
public class 转换流 {
    //E:\\xunlian\\untitled\\File\\hello.txt
    public static void main(String[] args) {
        转换流();
    }

    private static void 转换流() {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            FileInputStream fis = new FileInputStream("File\\hello.txt");
            FileOutputStream fos = new FileOutputStream("File\\hello_gbk.txt");

//            InputStreamReader isr = new InputStreamReader(fis);//默认 idea设置的
            //File->Settings->Editor->File Encodings
            isr = new InputStreamReader(fis,"UTF-8");//utf-8
            osw = new OutputStreamWriter(fos,"gbk");

            char[] chars = new char[20];
            int len;
            while ((len = isr.read(chars))!=-1){
                osw.write(chars,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
