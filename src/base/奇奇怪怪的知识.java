package base;

public class 奇奇怪怪的知识 {
    public static final String a;
    public static final String b;
    public static final String aa="123";
    public static final String bb="456";

    static {
        a = "123";
        b = "456";
    }
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
    public static void main(String[] args)
    {
        String c = "123456";
        String d = a + b;
        System.out.println(c == d);//false

        String e=aa+bb;
        System.out.println(c == e);//true
    }
}
