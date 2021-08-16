package base.常用类;

/**
 * 关于String
 * 底层使用char[]存储
 * String：       不可变的字符序列    jdk1.0
 * StringBuffer： 可变的字符序列     jdk 1.0  线程安全     效率低
 * StringBuilder：可变的字符序列     jdk5.0   线程不安全   效率高
 *
 */
public class StringBufferBuilderTest {
    public static void main(String[] args) {

        执行时间();
    }

    private static void 执行时间() {
        /**
         * 执行时间
         * String>StringBuffer>StringBuilder
         */
        //初始数据
        long startTime =0L;
        long endTime = 0L;
        String str = "";
        StringBuffer buffer=new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer执行时间"+(endTime-startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder"+(endTime-startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            str=str + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String执行时间"+(endTime-startTime));
    }

    private static void 一部分方法() {
        /**
         * StringBuffer append(xxx): 提供了很多的append()方法，用于进行字符串拼接
         * StringBuffer delete(int start, int end): 删除指定位置的内容
         * StringBuffer replace(int start, int end, String str): 把[start, end)位置替换为str
         * StringBuffer insert(int offset, xxx): 在指定位置插xxx
         * StringBuffer reverse() :把当前字符序列逆转
         * public int index0f(String str)
         * public String substring(int start, int end)
         * public int length()
         * public char charAt(int n )
         * public void setCharAt(int n , char ch)
         */
        StringBuffer sbf = new StringBuffer();
        sbf.append("abc");
        sbf.append(1);//添加
        System.out.println(sbf);//abc1
        sbf.delete(2,3);//删除
        System.out.println(sbf);//ab1
        sbf.replace(2,5646,"cdef");//修改
        //如果end大于length相当于把后面的end=length  但是start 也就是第一个数字 必须>=0否则报错
        System.out.println(sbf);//abcdef
        sbf.insert(1,"A");//在第一个字符后插入
        System.out.println(sbf);//aAbcdef
        System.out.println(sbf.length());//7
        sbf.reverse();//反转
        System.out.println(sbf);//fedcbAa
        sbf.setCharAt(0,'M');//将下标为0的字符改成M
        System.out.println(sbf);//MedcbAa
        String sbf1 = sbf.substring(0,1);//返回下标为0到下标不为1的字符串
        System.out.println(sbf1);//M
    }

    private static void new创建() {
        String str0 = new String();//相当于new char[0]
        String str1 = new String("abc");//相当于new char[]{'a','b','c'};

        StringBuffer sbf0 = new StringBuffer();//相当于char[] value=new char[16]//默认16个
        System.out.println(sbf0.length());//0   //重写了
        sbf0.append('a');//value[0] = 'a';
        sbf0.append('b');//value[1] = 'b';
        sbf0.append('c');//value[2] = 'c';
        /**
         * 扩容
         * 默认情况下(value.length << 1) + 2;  扩到原来的两倍+2
         * if (newCapacity - minCapacity < 0) {
         *     newCapacity = minCapacity;
         * }//如果还是不行  新加的字符太长了   扩到添加的字符那麽多
         *
         */
        StringBuffer sbf1 = new StringBuffer("abc");//相当于new char["abc".length()+16];
        System.out.println(sbf0 == sbf1);
        sbf1.setCharAt(0,'m');
        System.out.println(sbf0+"***"+sbf1);//abc***mbc
    }
}
