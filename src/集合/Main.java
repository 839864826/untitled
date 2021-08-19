package 集合;

/**
 * 集合框架的概述
 * ●一方面，面向对象语言对事物的体现都是以对象的形式，为了方便对多个对象
 * 的操作，就要对对象进行存储。另一方面，使用Array存储对象方面具有- -些弊
 * 端，而Java集合就像一种容器，可以动态地把多个对象|的引用放入容器中。
 *
 * 1.集合、数组都是对多个数据进行存储操作的结构，简称Java容器。
 *   说明:此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储(.txt   数据库   .jpg等)
 * ➢数组在内存存储方面的特点:
 *     >数组初始化以后，长度就确定了。
 *     >数组声明的类型，就决定了进行元素初始化时的类型
 * ➢数组在存储数据方面的弊端:
 *      >数组初始化以后，长度就不可变了，不便于扩展
 *      >数组中提供的属性和方法少，不便于进行添加、删除、插入等操作，且效率不高。
 *      >同时无法直接获取存储元素的个数
 *      >数组存储的数据是有序的、可以重复的。--->存储数据的特点单一
 * ●Java集合类可以用于存储数量不等的多个对象，还可用于保存具有映射关系的
 * 关联数组。
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Java集合可分为Collection和Map两种体系
 * ➢Collection接口:单列数据，定义了存取一组对象的方法的集合
 *      List接口:元素有序、可重复的集合  --》"动态"数组
 *          方法：ArrayList、LinkedList、Vector
 *      Set接口:元素无序、不可重复的集合
 *          方法：HashSet、LinkedHashSet、TreeSet
 * ➢Map接口:双列数据，保存具有映射关系“key-value对”的集合
 *          方法：HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 */
public class Main {
    public static void main(String[] args) {

    }
}
