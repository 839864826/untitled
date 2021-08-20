package 集合.CollectionTest;

import java.util.*;

/**
 * ➢Collection接口:单列数据，定义了存取一组对象的方法的集合
 *  *      List接口:元素有序、可重复的集合  --》"动态"数组  jdk1.2
 *  *          方法：ArrayList、LinkedList、Vector
 *
 *
 *  都实现了List接口素有序、可重复的集合
 *
 *  ArrayList： jdk1.2
 *      线程不安全，效率高  底层使用Object[] elementData存储
 *  LinkedList：jdk1.2
 *      底层使用双向链表   对于频繁的"插入、删除"操作，使用此类效率比Arraylist高;
 *  Vector：    jdk1.0     作为List接口的古老实现类
 *      线程安全，效率低 synchronized  Object[] elementData存储
 *
 */
public  class ListTest {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);//[1, 2]  由于重写了方法  如果不是对象则认为是第几个
        System.out.println(list);
        list.remove(new Integer(2));//[1]   想要删除固定的值   要自己装箱
        System.out.println(list);


    }

    private static void List的方法() {
        /**
         * List的方法  不同于Collection
         *
         * 总结:常用方法
         * 增: add(object obj)
         * 删: remove(int index) / remove(Object obj)
         * 改: set(int index, object ele)
         * 查: get(int index)
         * 插: add(int index, object ele)
         * 长度: size()
         * 遍历: ①Iterator迭代器方式
         *      ②增强for循环
         *      ③普通的循环
         */
        List list=new ArrayList();
        list.add(5);
        list.add(2.2);
        list.add('a');
        list.add("hello");
        list.add(5);
        System.out.println(list);//[5, 2.2, a, hello, 5]

        list.add(1,"hh");//在第一个后面插入一个
        System.out.println(list);//[5, hh, 2.2, a, hello, 5]

        System.out.println(list.get(1));//hh   获取角标为1的元素  并返回

        System.out.println(list.remove(2));//删除角标为2的元素
        System.out.println(list);//[5, hh, a, hello, 5]

        System.out.println(list.indexOf(5));//1 返回这个元素在集合中首次出现的角标
        System.out.println(list.lastIndexOf(5));//4 返回这个元素在集合中末次出现的角标

        list.set(0,555);//把角标为0的元素修改成555
        System.out.println(list);//[555, hh, a, hello, 5]

        List list1 = list.subList(1, 5);//返回[1,5)  的list集合  右边的数大了  不影响最多输出完
        System.out.println(list1);//[hh, a, hello, 5]
        System.out.println("********************遍历*****************************");
        Iterator iterator = list1.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+"        ");
        }
        System.out.println();
        for (Object o :list1) {
            System.out.print(o+"        ");
        }
    }

    private static void VectorTest() {
        Vector vector = new Vector();
        //默认大小10  扩容为原来的2倍  其他和Arraylist一样
    }

    private static void LinkedList双向链表() {
        /**
         * LinkedList  双向链表
         * LinkedList linkedList=new LinkedList();
         * 内部声明了 Node<E> first;
         *          Node<E> last;
         *
         *
         * transient关键字的作用是：
         * 被transient修饰的变量不参与序列化和反序列化。
         * 当一个对象被序列化的时候，
         * transient型变量的值不包括在序列化的表示中，
         * 然而非transient型的变量是被包括进去的。
         *
         */
        LinkedList linkedList=new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.remove(2);// 0删除第一个   默认为0
        System.out.println(linkedList);
    }

    private static void ArrayListTest() {
        /**
         *
         * jdk7  new ArrayList();//类似于单例模式的饿汉式
         *          底层创建了长度是10的Object[]数组elementData
         * jdk8：new ArrayList();  {}//类似于单例模式的懒汉式   延迟了创建
         *         没创建在第一次add中创建长度是10的Object[]数组elementData
         *
         *
         *         int oldCapacity = elementData.length;
         *         int newCapacity = oldCapacity + (oldCapacity >> 1);扩容为原来的1.5倍
         *         if (newCapacity - minCapacity < 0)
         *             newCapacity = minCapacity;
         *         if (newCapacity - MAX_ARRAY_SIZE > 0)
         *             newCapacity = hugeCapacity(minCapacity);
         *         elementData = Arrays.copyOf(elementData, newCapacity);把原有数组的数据复制到新的数组中
         *
         */
        ArrayList arrayList = new ArrayList();
        arrayList.add(5);
    }

    private static void list的sort排序() {
        List<Integer> list=new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(4);
        System.out.println(list);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        System.out.println(list);
    }
}
