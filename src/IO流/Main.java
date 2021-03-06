package IO流;

import java.util.Arrays;

/**
 * Java I0原理
 * ●I/O是Input/Output的缩写， /O技 术是非常实用的技术，用于
 *  处理设备之间的数据传输。如读/写文件，网络通讯等。
 * ●Java程序中，对于数据的输入/输出操作以“流(stream)”的
 *  方式进行。
 * ●java.io包下提供了各种“流”类和接口，用以获取不同种类的
 *  数据，并通过标准的方法输入或输出数据。
 *
 * ●输入input:读取外部数据(磁盘、光盘等存储设备的数据)到程序(内存)中
 * ●输出output:将程序(内存)数据输出到磁盘、光盘等存储设备中。
 *
 * 流的分类
 * ●按操作数据单位不同分为:字节流(8 bit)，字符流(16 bit)
 * ●按数据流的流向不同分为:输入流，输出流
 * ●按流的角色的不同分为:节点流（直接作用在文件上的），处理流（作用在流上的）.
 * (抽象基类)   字节流             字符流
 * 输入流       InputStream      Reader
 * 输出流       OutputStream     Writer
 * 1. Java的I0流共涉及40多个类，实际上非常规则，都是从如下4个
 *    抽象基类派生的。
 * 2.由这四个类派生出来的子类名称都是以其父类名作为子类名后缀。
 * 结论:
 * 1.对于文本文件(. txt,.java,.c,.cpp),使用字符流处理
 * 2.对于非文本文件(.jpg,.mp3, .mp4, .avi, .doc,.ppt,...)，使用字节流处理
 */
public class Main {
    public static void main(String[] args) {

    }
}
