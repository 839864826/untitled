package base.常用类;

/**
 * String的特性
 * ●String类:代表字符串。Java 程序中的所有字符串字面值(如"abc" )都作
 * 为此类的实例实现。
 * ●String是一个final类，代表不可变的字符序列。不可被继承
 * ●字符串是常量，用双引号引起来表示。它们的值在创建之后不能更改。
 * ●String对象的字符内容是存储在- -个零符数组value[]中的。
 * ●String实现了 Serializable接口:表示字符串是支持序列化的。//IO流在讲
 *        实现了Comparable  接口  ：表示String可以比较大小
 * ●String内部定义了final char[] value  用于存储字符串数据
 * ●String:代表不可变的字符序列。简称:不可变性。
 *  体现: 1.当对字符事重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
 *      2.当对视有的字符事进行连按操作时，也需要里新指定内存区娱眠值，不能使用原有tyalwe进行赋值,|
 *
 * int length(): 返回字符串的长度: return value.length
 * char charAt(int index):返 回某索引处的字符return value[index]
 * boolean isEmpty():判断是否是空字符串: return value.length == 0
 * String toLowerCase():使用默认语言环境，将String中的所有字符转换为小写|
 * String toUppTCase():使用默认语言环境，将String 中的所有字符转换为大写
 * String trim():返回字符串的副本，忽略前导空白和尾部空白
 * boolean equals(Object obj):比较字符串的内容是否相同
 * boolean equalsIgnoreCase(String anotherString): ljequals方法类似， 忽略大
 * 小写
 * String concat(String str):将指定字符串连接到此字符串的结尾。等价 于用“+”
 * int compare To(String anotherString):比较两个字符串的大小|
 * String substring(int beginIndex): 返回一个新的字符串，它是此字符串的从
 * beginIndex开始截取到最后的一个子字符串。
 * String substring(int beginIndex, int endIndex) :返回个新字符串， 它是此字
 * 符串从beginIndex开始截取到endIndex(不包含)的个 子字符串。
 *
 * boolean endsWith(String suffix):测试此字符串是否以指定的后缀结束
 * boolean startsWith(String prefix): 测试此字符串是否以指定的前缀开始
 * boolean startsWith(String prefix, int toffset): 测试此字符串从指定索引开始的
 * 子字符串是否以指定放缀开始
 *
 */

/**
  * String搜索
 * ●boolean contains(CharSequence S):当且仅当此字符串包含指定的char值序列
 * 时，返回true
 * ●int indexOf(String str):返回指定子字符串在此字符串中第次一出现处的索引
 * int indexOf(String str, int fromIndex):返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
 * int lastIndexOf(String str): 返回指定子字符串在此字符串中最右边出现处的索引  反向搜索
 * int lastIndexOf(String str, int fromIndex):返回指定子字符串在此字符串中最后
 *      一次出现处的索引，从指定的索引开始反向搜索
 *      注: indexOf和lastIndexOf方法 如果未找到都是返回-1
 *
 *
 */

/**
 * String替换
 * CharSequence可以用String代替
 * ●String replace(char oldChar, char newChar):返回一个新的字符串，它是
 * 通过用newChar替换此字符串中出现的所有oldChar得到的。
 * ●String replace(CharSequence target, CharSequence replacement):
 * 使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
 * ●String replaceAll(String regex, String replacement) :使用给定的
 * replacement
 * 替换此字符串所有匹配给定的正则表达式的子字符串。
 * ●String replaceFirst(String regex, String replacement): 使用给定的
 * replacement替换此字符串匹配给定的正则表达式的第一一个子字符串。
 */

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * String正则
 * ●boolean matches(String regex):
 * 告知此字符串是否匹配给定的正则表达式。
 *
 * String[] split(String regex):根据给定正则表达式的匹配拆分此字符串。
 * String[] split(String regex, int limit):根据匹配给定的正则表达式来拆分此
 * 字符串，最多不超过limit个， 如果超过了，剩下的全部都放到最后一个元素中
 */
public class StringTest {


    public static void main(String[] args) throws UnsupportedEncodingException {
        final Person s=new Person("aaa",55);
        final Person s2=new Person("aaa",55);
        System.out.println(s==s2);


    }

    private static void 类型转换() {
        String str = "123";
        int num=Integer.parseInt(str);//str转数字
        String str1=String.valueOf(num);
        System.out.println(str == str1);//false

        char[] chars=str.toCharArray();//将String转换成char[]
        for (char a:chars){
            System.out.println(a);
        }
        String str2=new String(chars);//char[]转换成String
        System.out.println(str2);
    }

    private static void byte与String的转换() throws UnsupportedEncodingException{
        String str="123abc张蛟龙哈";//UTF-8默认方法一个中文在UTF-8由3个ASCII组成
        byte[] bytes=str.getBytes();//byte是-128到127  他将每个字符转换成ASCII了
        System.out.println(Arrays.toString(bytes));

        byte[] bytes1=str.getBytes("gbk");//一个中文在gbk由2个ASCII组成
        System.out.println(Arrays.toString(bytes1));

        String str1=new String(bytes,"gbk");//字节默认方法转String
        System.out.println(str1);

        String str2=new String(bytes1,"gbk");//用gbk解码
        System.out.println(str2);
        /**
         * 编码   字符串--》字节(二进制)
         * 解码    字节(二进制)--》字符串
         */
    }

    private static void 正则() {
        String str="12hello34word56java789mysql";
        String[] strings= str.split("5");
        System.out.println(strings[0]);
        System.out.println(strings[1]);
        String str1=str.replaceAll("\\d+",",");
        //  \\d数字   +一个或多个前面的
        System.out.println(str1);
    }

    //    String str = new String("good");
    String str001 = "good";
    char[] ch = {'t','e','s','t'};
    public void change(String str, char c[]){//方法传的是地址
        str="sss";
        c[0]='b';
    }
    private static void String4() {
        StringTest et=new StringTest();
        et.change(et.str001, et.ch);//方法传的是地址
        System.out.println(et.str001);//good
        System.out.println(et.ch);// best
    }


    private static void String1() {
        //String在方法区中的字符串常量池中   只能放相同的一个串
        String s1 = "abc";//没有创建一个
        String s2 = "abc";//发现有  复用第一个
        String s111=s1;

        //保存在堆空间中  堆在指向字符串常量池  比较不相等  比较的是地址
        String s11=new String("abc");
        String s12=new String("abc");
        System.out.println(s1 == s2);//相等
        System.out.println(s1 == s11);//不相等
        System.out.println(s12 == s11);//不相等
        System.out.println(s1 == s111);//相等
        s1="hello";//重新创建一个新的数组  并给它
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("********************************");
        s1+=" word";//新造一个
        System.out.println(s1);
        System.out.println("********************************");
        String s4 = "abca";
        String s5 = s4.replace("a","A");//s4不改==不新创业个   会返回一个修改后的String
        System.out.println(s4);//abca
        System.out.println(s5);//AbcA
    }

    private static void String2() {
        Person p0=new Person("Tom",12);
        //里面的name是name=“” 所以在字符串常量池中加载的  所以p1.name==p0.name
        Person p1=new Person("Tom",12);
        System.out.println(p1.equals(p0));//false
        System.out.println(p1==p0);//false
        System.out.println(p1.name.equals(p0.name));//true
        System.out.println(p1.name==p0.name);//true
    }

    private static void String3() {
        String s1="a";//加final  就相当于 这个s1是一个常量
        String s2="b";

        String s3 = "ab" ;//只有常量 存在字符串常量池中存储
        String s4="a"+"b";//只有常量 存在字符串常量池中存储
        String s5 =s1+"b";//有变量名字要在堆里存储   相等于new
        String s6 ="a"+s2;//有变量名字要在堆里存储   相等于new
        String s7 =s1 +s2;//有变量名字要在堆里存储   相等于new
        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s5 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s7);//false
        System.out.println("****************************");
        String s8=s5.intern();//返回与s5相等的 字符串常量池的 String  没有在字符串常量池中造一个
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s8);//ture
    }




}
