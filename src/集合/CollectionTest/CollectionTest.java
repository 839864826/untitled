package 集合.CollectionTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
        Collection collection = new ArrayList();
        collection.add("AA");//集合添加
        collection.add('v');
        collection.add(123);
        collection.add(15.6);
        collection.add(new Date());

        System.out.println(collection.size());//5   个数
        collection.addAll(collection);//添加一个和自己同类型的所有
        System.out.println(collection.size());//10    个数

        collection.clear();//清空集合元素
        System.out.println(collection);//[]
        System.out.println(collection.isEmpty());//判断是否为空


    }
}
