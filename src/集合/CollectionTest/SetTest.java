package 集合.CollectionTest;


import java.util.*;

/**
 *1. Set接口的框架:
 * /----Collection接口: 单列集合，用来存储一个一个的对象
 *      /----Set接口: 存储无序的、不可重复的数据 -->高中讲的“集合”
 *              /----HashSet:作为Set接口的主要实现类;线程不安全，可存储null
 *                  /----LinkedHashSet:是HashSet子类；
 *                      在HashSet加了一个链表；遍历内部数据时，可以按照添加顺序遍历
 *              /----TreeSet：可以按照添加对象的指定属性进行排序
 *
 * Set接口没有定义新的方法
 *      1.无序性：不等于随机性  不是按照添加顺序存储的，按照哈希值存储的
 *
 *      2.不可重复性：保证添加元素按照哈希值和equals()判断时，不能返回true，添加元素相同元素只能添加一次
 */

public class SetTest {
    public static void main(String[] args) {

        HashSet经典问题();

    }

    private static void HashSet经典问题() {
        HashSet set = new HashSet();
        User user1 = new User("aa",1001);
        User user2 = new User("bb",1002);
        set.add(user1);
        set.add(user2);
        System.out.println(set);
        //[User{name='bb', age=1002}, User{name='aa', age=1001}]

        user1.setName("cc");//把 aa 修改成 cc
        System.out.println(set);
        //[User{name='bb', age=1002}, User{name='cc', age=1001}]

        set.remove(user1);//删除 user1  但是删不了
        // 是因为 他删除的时候先看哈希值，但由于之前的哈希值是根据aa 1001
        // 计算的大概率不一样，所以发现没有 删除失败
        System.out.println(set);
        //[User{name='bb', age=1002}, User{name='cc', age=1001}]

        set.add(new User("cc",1001));//添加的时候是根据哈希值添加的  所以成功
        System.out.println(set);
        //[User{name='bb', age=1002}, User{name='cc', age=1001}, User{name='cc', age=1001}]

        set.add(new User("aa",1001));
        //虽然找到了之前的哈希表的位置 但是equals不相等  所以添加成功
        System.out.println(set);
        //[User{name='bb', age=1002}, User{name='cc', age=1001}, User{name='cc', age=1001}, User{name='aa', age=1001}]
    }

    private static void LinkedHashSetTest() {
        /**
         * LinkedHashSet
         * LinkedHashSet作为HashSet的子类，
         *      在添加数据的同时，每个数据还维护两个引用，记录此数据的前一个数据和后一个数据。
         * 优点:对于频繁的遍历操作，LinkedHashSet效率高于HashSet
         */
        Set set = new LinkedHashSet();
        set.add(545);
        set.add('w');
        set.add("hello");
        set.add(99L);
        set.add(2.5);
        for(Object o:set){
            System.out.println(o);
        } //有序遍历
        System.out.println(set);//有序
    }

    private static void HashSetTest() {
        /** 添加元素的过程:以HashSet 为例: //JDK8之前 起始大小16  JDK8后  在add之后才有大小
         *二、添加元素的过程:以HashSet为例:
         *      我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，
         *      计算元素的哈希值此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置(即为:索引位置)，
         *      判断数组此位置上是否已经有元素:
         *          如果此位置上没有其他元素，则元素a添加成功。
         *          如果此位置上有其他元對b(或以链表形式存在的多个元素)，
         *          则比较元素a与元素b的hash值:
         *              如果hash值不相同，则元素a添加成功。
         *              如果hash值相同，进而需要调用元素所在类的equals()方法:
         *                  equals()返回true,元素a添加失败
         *                  equals()返回false,则元素a添加成功。
         * 对于添加成功的情况2和情况3而言:元素a与已经存在指定索引位置上数据以链表的方式存储。
         *      jdk 7 :元素a放到数组中，指向原来的元素。.
         *      jdk 8 :原来的元素在数组中，指向元素d
         *
         * HashSet底层用的是数组加链表  底层是HashMap
         *  //自定义的对象要重写hashCode()方法和equals()方法
         *      也要保证一致性：相等的对象必须具有相等的散列码（最好用自己生成的）
         */
        Set set=new HashSet();
        set.add(111);
        set.add('w');
        set.add("qwe");
        set.add(false);
        set.add(new Date());
        for (Object o :set) {
            System.out.println(o);
        }
    }

    private static void TreeSetTest() {
        /**
         * TreeSet
         * //向TreeSet中添加的数据，要求是"相同类"的对象。
         * 存储的数据为int类型的时候，treeSet是可以自动进行排序的；
         * 存储的数据为char类型的时候可见这是按照字母的顺序去排列的；
         * 可见对于String类型来说，也是按照字母的顺序来排列的；
         *
         * TreeSet<>  自定义类是  他也会自动排序
         * implements Comparable  要继承Comparable
         *                         重写compareTo()方法
         *
         * 3.自然排序中，比较两个对象是否相同的标准为: compareTo() 返回e.不再是equals().
         *          继承Comparable接口//重写compareTo
         * 4.定制排序中，比较两个对象是否相同的标准为: compare() 返回e.不再是equals().
         *          Comparator comparator=new Comparator() {}
         */
        Set<Integer> treeSet=new TreeSet<>();
        //二叉树存储   红黑树 特点:有序，查询速度比List快


        treeSet.add(5);
        treeSet.add(6);
        treeSet.add(4);
        System.out.println(treeSet);

        Comparable comparable=new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
        //Collection
        /**
         * @FunctionalInterface
         * 1、该注解只能标记在"有且仅有一个抽象方法"的接口上。
         * 2、JDK8接口中的静态方法和默认方法，都不算是抽象方法。
         * @FunctionalInterface标记在接口上，
         *      “函数式接口”是指仅仅只包含一个抽象方法的接口。
         * 如果一个接口中包含不止一个抽象方法，
         *      那么不能使用@FunctionalInterface，编译会报错。
         *
         */
        Comparator comparator=new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }else {
                    throw new RuntimeException("类型不一样");
                }
            }
        };//定制排序
        Set<User> userSet = new TreeSet<>(comparator);
        userSet.add(new User("Tom",12));
        userSet.add(new User("Jerry",32));
        userSet.add(new User("jim",2));
        userSet.add(new User("Mike",65));
        userSet.add(new User("Jack",33));
        userSet.add(new User("Tom",12));

    }
}
