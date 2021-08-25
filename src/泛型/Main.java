package 泛型;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 泛型 jdk1.5  新增
 * 泛型只能是引用数据类型
 *　
 * 如果定义了泛型，最好最好用到泛型
 *
 * 子类在继承带泛型的父类时，指明了泛型类型。则实例化子类对象时，不再需要指明泛型。
 * 1.泛型类可能有多个参数，此时应将多个参数一 起放在尖括号内。比如: <E1, E2,E3>
 * 2.泛型类的"构造器"如下: public GenericClass(){}。
 *   而下面是错误的: public GenericClass<E>(){}
 * 3.实例化后，操作原来泛型位置的结构必须与指定的泛型类型--致。
 * 4.泛型不同的引用不能相互赋值。
 *  >尽管在编译时ArrayList<String>和ArrayList<Integer>是两种类型，但是，在运行时只有
 *   一个ArrayList被加载到JVM中。
 * 5.泛型如果不指定，将被擦除，泛型对应的类型均按照Object处理，"但不等价"
 * 于Object。经验:泛型要使用一路都用。要不用，一路都不要用。
 * 6.如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。
 * 7. jdk1.7，泛型的简化操作: ArrayList<Fruit> flist = new ArrayList<>();
 * 8.泛型的指定中不能使用基本数据类型，可以使用包装类替换。
 * 9.在类/接口.上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、
 *   非静态方法的参数类型、非静态方法的返回值类型。但在"静态方法中不能使用类的泛型"。
 *   因为静态是在类加载前加载的，但类的泛型实在类加载时给的。
 * 10.   异常类不能是泛型的
 * 11.不能使用new E[]但是可以: E[] elements = (E[])new Object[capacity];
 * 参考: ArrayList源码中声明: Object] elementData，而非泛型参数类型数组。
 * 结论:子类必须是“富二代”，子类除了指定或保留父类的泛型，还可以增加自
 * 己的泛型
 */

/**
 *  * 12.父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型:
 *  * ●子类不保留父类的泛型:按需实现
 *  * ➢没有类型擦除
 *  * ➢具体类型
 *  * ●子类保留父类的泛型:泛型子类
 *  * ➢全部保留
 *  * ➢部分保留
 * class Father<T1, T2> {
 * //子类不保留父类的泛型
 * // 1)没有类型擦除
 * class Son<A, B> extends Father{//等价于class Son extends Father<Object , 0bject>{
 * // 2)具体类型
 * class Son2<A, B> extends Father<Integer, String> {
 * //子类保留父类的泛型
 * // 1)全部保留
 * class Son3<T1, T2, A, B> extends Father<T1, T2> {
 * }
 * // 2)部分保留
 * class Son4<T2, A, B> extends Father<Integer, T2> {
 */


public class Main {
    public static void main(String[] args) {
        有限制条件的通配符();


    }

    private static void 通配符() {
        /**
         * 通配符 ？
         * 类A是类B的父类，G<A> 和G<B>是没有关系的，二者共同的父类是: G<?>
         */
        List<Object> list = null;
        List<String> list1 = new ArrayList<>();
        list1.add("AA");
        list1.add("BB");
        list1.add("CC");
        List<?> list2 = null;//?是根父类
        list2 = list;//可以
        list2 = list1;//可以
        list2.add(null);//除了null外   不能添加数据  因为?你不知道是什么类型

        System.out.println(list2.get(2));//可以读


    }

    private static void 有限制条件的通配符() {
        /**
         * 有限制条件的通配符的使用
         * 假设Object是最大的  即  父 大于 子
         * ? extends Order//(-无穷 - Order]  即Order  和他的子类都可以赋值给他
         * ? super Order//[Order - 无穷)     即Order  和他的父类都可以赋值给他
         */
        List<? extends Order> listEx = new ArrayList<>();
        List<? super Order> listSup =new ArrayList<>();

        List<Student> listSty = new ArrayList<>();
        List<Order> listOr = new ArrayList<>();
        List<Object> listObj = new ArrayList<>();

        listEx = listSty;//可以赋值
        listEx = listOr;//可以赋值
//        listEx = listObj;//不行

//        listSup = listSty;//不行
        listSup = listOr;//可以赋值
        listSup = listObj;//可以赋值

        listEx =listSty;
        Order order = listEx.get(0);//读  只能用最大的类型接受
//        Student order1 = listEx.get(0);//编译不通过

        //写  只能用类型最小的写入
        // 而? extends Order是(-无穷 - Order] 没最小，所以不能单独写入
//        listEx.add(new Student());//报错
//        listEx.add(new Order());//报错
//        listEx.add(new Object());//报错

        //写  只能用类型最小的写入
        // 而? super Order是[Order - 无穷) 只要是Order或它的子类都可以
        listSup.add(new Order());
        listSup.add(new Student());
//        listSup.add(new Object());//报错
    }

    private static void 泛型在继承方面的体现() {
        /**
         * 泛型在继承方面的体现
         * 类A是类B的父类，G<A>和G<B>二者不具备子父类关系，二者是并列关系。
         * 补充:类A是类的父类，A<G> 是 B<G>的父类
         */
        Object obj = null;
        String str = null;
        obj = str;

        Object[] objs = null;
        String[] strs = null;
        objs = strs;

        List<Object> list = null;
        List<String> list1 = null;
//        list=list1;//报错  不具备子父类关系  不可以直接赋值
        list= Collections.singletonList(list1);//这中可以

        List<String> list2 =null;
        ArrayList<String> list3 = null;
        list2=list3;//可以
    }

    private static void 测试泛型方法() {
        //测试泛型方法
        Integer[] integers = new Integer[]{1,2,3,4,5,6,7,8,9};
        Main main =new Main();
        System.out.println(integers);
        //泛型方法在调用时，指明泛型参数的类型。  所以可以静态
        List<Integer> cp = main.cp(integers);
        System.out.println(cp);
    }

    private static  <E> List<E> cp(E[] arr){
        /**
         * 泛型方法
         * 在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
         * 泛型方法所属的类是不是泛型类都没有关系。
         *
         */
        ArrayList<E> list = new ArrayList<>();
        for (E e:arr){
            list.add(e);
        }
        return list;
    } //泛型方法

    private static void 小小的测试() {
        ArrayList<Integer> list = new ArrayList<>();//jdk7新特性:类型推断
        list.add(55);
        list.add(69);
        list.add(46);
        list.add(88);
        list.add(93);
        list.add(60);

//        编译时就会进行类型检查
//        list.add("Tom");//报错

        for(Integer i : list){
            System.out.println(i);
        }
        Order order = new Order();//没指明泛型类型   默认Object
        order.setT(1);
        order.setT("hello");
        order.setT('w');

        Order<Boolean> order1 = new Order<>();
        order1.setT(false);
//        order1.setT(1);//报错 类型不一样

    }
}
