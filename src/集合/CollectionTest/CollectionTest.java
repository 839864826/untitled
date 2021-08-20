package 集合.CollectionTest;

import java.util.*;

/**
 * Collection
 * ➢Collection接口:单列数据，定义了存取一组对象的方法的集合
 *  *      List接口:元素有序、可重复的集合  --》"动态"数组
 *  *          方法：ArrayList、LinkedList、Vector
 *  *      Set接口:元素无序、不可重复的集合
 *  *          方法：HashSet、LinkedHashSet、TreeSet
 *
 */
public class CollectionTest {
    public static void main(String[] args) {
        foreach增强for循环();

    }

    private static void foreach增强for循环() {
        /**
         * foreach
         * 遍历集合
         * //内部调用的也是迭代器
         *
         */
        Collection coll = new ArrayList();
        coll.add("AA");//集合添加
        coll.add('v');
        coll.add(123);
        coll.add(15.6);

        for (Object o:coll){//内部调用的也是迭代器
            System.out.println(o);
        }
        coll.forEach(System.out::println);//同上  也是遍历 JKD8新特性
    }

    private static void Iterator迭代器遍历() {
        /**
         * 使用Iterator接口遍历集合元素
         * ●Iterator对象称为迭代器(设计模式的- -种)， 主要用于遍历Collection集合中的元素。
         * ●GOF给迭代器模式的定义为:提供一种方法访问一个容器(container)对象中各个元
         * 素，而又不需暴露该对象的内部细节。迭代器模式，就是为容器而生。类似于“公.
         * 交车上的售票员”、“火车上的乘务员”、“空姐”
         * ●Collection接口继承了java.lang.Iterable接口，该接口有一个iterator()方法，
         * 那么所有实现了Collection接口的集合类都有一个iterator()方法，
         * 用以返回一个实现了Iterator接口的对象。
         * ●Iterator 仅用于遍历集合，Iterator 本身并不提供承装对象的能力。
         * 如果需要创建Iterator对象，则必须有一个被迭代的集合。
         * ●集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
         * 默认游标都在集合的第一个元素之前。
         * ●Iterator内部定义了remove(),可以在遍历的时候，删除集合中的元素。
         * 此方法不同于集直接调用remove()
         */
        Collection coll = new ArrayList();
        coll.add("AA");//集合添加
        coll.add('v');
        coll.add(123);
        coll.add(15.6);

        Iterator iterator = coll.iterator();//指针指向的是  第一个元素的上一个位置
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
////        System.out.println(iterator.next());//java.util.NoSuchElementException

        //hasNext():判断是否还有下一个元素
        while (iterator.hasNext()){//是否还有元素
            //next():①指针下移②将下移以后集合位置上的元素返回
//            System.out.println(iterator.next());//返回集合元素
            Object obj=iterator.next();
            Integer a=123;
            if(a.equals(obj)){//删除123这个元素
                iterator.remove();//删除当前指针指向的元素  不能连续remove两次  删没了 就没了
                // 不能刚开始就用  因为指针最开始指向的是  第一个元素的上一个位置 没有元素报异常
            }

        }
        iterator = coll.iterator();
        while (iterator.hasNext()){//是否还有元素
            System.out.println(iterator.next());//返回集合元素
        }
    }

    private static void 数组集合的转换() {
        Collection coll = new ArrayList();
        coll.add("AA");//集合添加
        coll.add('v');
        coll.add(123);
        coll.add(15.6);
        coll.add(new Date());


        System.out.println(coll.hashCode());//返回当前对象的哈希值
        System.out.println("********集合--》数组*******************");
        Object[] obj = coll.toArray();//集合--》数组
        for(Object o:obj){
            System.out.println(o);
        }

        System.out.println("********数组--》集合*******************");
        Collection a=Arrays.asList(obj);//数组--》集合
        System.out.println(a);

        Collection coll1 = Arrays.asList(new int[]{1336,545});
        System.out.println(coll1);//返回的是地址 如果是基本数据类型   它认为只有一个元素
        Collection coll2 = Arrays.asList(new Integer[]{1336,545});
        System.out.println(coll2);//包装后  就可以了
    }

    private static void 与要重写equals方法() {
        Collection coll = new ArrayList();
        coll.add("AA");//集合添加
        coll.add('v');
        coll.add(123);
        coll.add(15.6);
        coll.add(new Date());

        /**
         * 向Collection 接口的实现类的对象中添加数据obj时，
         * 要求obj所在类要重写equals()方法
         * contains()方法要用equals方法判断
         */
        //用的是equals方法判断   判断coll是否有此内容
        System.out.println(coll.contains("123"));//false   判断coll是否有此内容
        System.out.println(coll.contains(123));//true    判断coll是否有此内容
        System.out.println(coll.containsAll(coll));//判断是否包含所有元素 都有的话为true

        System.out.println("********* 删除指定元素*************差集**************");
        System.out.println(coll);
        System.out.print(coll.remove(123)+"         ");//  true  删除指定元素
        System.out.println(coll.remove(46563));//false  删除指定元素
        System.out.println(coll);//[AA, v, 15.6, Thu Aug 19 15:22:03 CST 2021]

        Collection coll1 = Arrays.asList('v',"aa");//差集
        System.out.println(coll.removeAll(coll1));//true 删除所有coll里coll1有的元素 只要有删除的就是true
        System.out.println(coll);//[AA, 15.6, Thu Aug 19 15:22:03 CST 2021]

        System.out.println("*********交集*****************");
        Collection coll2 = Arrays.asList("AA","aa",new Date());
        System.out.println(coll.retainAll(coll2));//交集  只要改变了coll就为true
        System.out.println(coll);//[AA]

        coll.equals(coll);//比较元素是否都相等  list  有序   顺序不对也是false  set无徐无关顺序


    }

    private static void 方法1() {
        Collection coll = new ArrayList();
        coll.add("AA");//集合添加
        coll.add('v');
        coll.add(123);
        coll.add(15.6);
        coll.add(new Date());

        System.out.println(coll.size());//5   个数
        coll.addAll(coll);//添加一个和自己同类型的所有
        System.out.println(coll.size());//10    个数

        coll.clear();//清空集合元素
        System.out.println(coll);//[]
        System.out.println(coll.isEmpty());//判断是否为空
    }
}
