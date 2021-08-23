package base;
/**
 * 对象比较
 * Integer i=new Integer(50);
 * Integer j=new Integer(50);
 * System.out.println(i==j);  //运行的结果是false
 * Integer i=new Integer.valueOf(100);
 * Integer j=new Integer.valueOf(100);
 * System.out.println(i==j);  //运行的结果是true
 * Integer i=new Integer.valueOf(400);
 * Integer j=new Integer.valueOf(400);
 * System.out.println(i==j);//运行结果是false
 * Integer i=100;
 * Integer j=100;
 * System.out.println(i==j);//运行结果是true
 * 然后再用400这个数试一试，通过实验运行的结果是false，
 *public static Integer valueOf(int i){
 * if(i>=-128&&i<=IntegerCache.high)
 * return IntegerCache.cache[i+128];
 * else return new Integer(i);
 * }
 * 通过看源码能够知道整数类型在-128~127之间时，会使用缓存。
 * 造成的效果就是，如果已经创建了一个相同的整数，
 * 使用valueOf创建第二次时，不会使用new 关键字，
 * 而是用已经缓存的对象。所以使用valueOf方法创建两次对象，
 * 若对应数值相同，且数值在-128~127之间时，两个对象指向同一个地址。
 * 使用Integer i=400这样的方法创建Integer对象与使用valueOf方法的效果是一样的,
 * 若要比较，使用compareTo或者equals方法是更好的
 */
/**
    单个 Java 文件长度限制
    一般的，一个 Java 类可以写多少行，几乎没人知道，因为没试验过。

实际上，Java 对单个类文件时有长度限制的。单个 Java 文件常量个数上限是 65536。
 超过这个数字会报：
 Too many constants, the constant pool for XXX would exceed 65536 entries。

这是因为.class文件头 4 个字节的模数（magic number）是小写的
 0xca,0xfe,0xba,0xbe。0xCA,0xFE,0xBA,0xBE连起来就是
 cafebabe，这就是 Class 文件的魔数。
 */

/**
 * String 的长度是有限制的。
 * 编译期的限制：字符串的UTF8编码值的字节数不能超过65535，字符串的长度不能超过65534。
 * 运行时限制：字符串的长度不能超过2^31-1，占用的内存数不能超过虚拟机能够提供的最大值。
 * 另外，本文的理论是基于 Java8 的。JDK9 以后对 String 的存储进行了优化。
 * 底层不再使用 char 数组存储字符串，而是使用 byte 数组。
 * 这样对于 LATIN1 字符的字符串可以节省一倍的内存空间。
 */

/**
 * 方法参数不能超过 255 个
 */
public class 奇奇怪怪的知识 {
    public static final String a;
    public static final String b;
    public static final String aa="123";
    public static final String bb="456";
    public static  String aaa="123";
    public static  String bbb="456";

    static {
        a = "123";
        b = "456";
    }

    public static void main(String[] args)
    {
        String str1=new StringBuilder("j").append("ava").toString();
        System.out.println(str1 == str1.intern());//false  除了java都是true
        String str2=new StringBuilder("jee").append("ava").toString();
        System.out.println(str2 == str2.intern());//true
    }

    private static void 汉字的字节大小() {
        String s = "你";
        System.out.println(s.getBytes().length);//utf-8  3       utf-16  4
        System.out.println(s.codePoints().count());
        System.out.println(s.length());
        System.out.println(s.codePointCount(0, s.length()));
    }

    private static void NaN冷知识() {
        /**
         * 4.如何判断一个数是否是NaN？
         * 答案：使用Double.isNaN(double)或Float.isNaN(float)。其实现是一个数不等于自己，就是NAN：
         *
         * public static boolean isNaN(double v) {
         *     return (v != v);
         * }
         */
        double d1 = Double.NaN;
        double d3 = Double.NaN;//唯一一个  自己不等于自己的
        System.out.println("d3 == d1 : " + (d3 == d1));//d3 == d1 : false
    }

    private static void 字符串连接() {
        String c = "123456";
        String d = a + b;
        System.out.println((c == d)+"\t"+d);//false	123456

        String e=aa+bb;
        System.out.println((c == e)+"\t"+e);//true	123456

        String f=aaa+bbb;
        System.out.println((c == f)+"\t"+f);//false	123456
    }
}
