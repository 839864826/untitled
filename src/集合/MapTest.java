package 集合;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ➢Map接口:双列数据，保存具有映射关系“key-value对”的集合JDK1.2
 *          方法：HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 * HashMap    //JDK1.2          线程不安全  速度快  可存储null的key-value
 *      LinkedHashMap//JDK1.4  遍历比HashMap快
 *      可保证顺序遍历  在HashMap的基础上  加了一对指针 指向前一个和后一个元素
 *
 * Hashtable//JDK1.0古老的实现类  线程安全  速度慢    不可存储null的key-value
 *      Properties//常用来处理配置文件   key-value都是String
 *
 * TreeMap//JDK1.2 保证按照添加的key-value对进行排序，实现排序遍历。 用key进行排序的
 *      //二叉树存储   红黑树 特点:有序，
 *
 */
public class MapTest {
    /**
     * 二、Map 结构的理解:
     * Map中的key:无序的、不可重复的，使用Set 存储所有的key //重写hashCode()和equals方法
     * Map中的value:无序的、可重复的，使用Collection存 储所有的value//重写equals方法
     * -个键值对: key-value 构成了一个Entry对象 。
     * Map中的entry:无序的、不可重复的，使用Set 存储所有的entry
     */
    public static void main(String[] args) {
        LinkedHashMap重点();

    }

    private static void LinkedHashMap重点() {
        Map map = new LinkedHashMap();//LinkedHashMap中的内部类: Entry数组
        /*
        重写了new Node数组 把node数组改了 弄成了双线链表
            static class Entry<K,V> extends HashMap.Node<K,V> {
                Entry<K,V> before, after;
                Entry(int hash, K key, V value, Node<K,V> next) {
                    super(hash, key, value, next);
                }
            }
         */
        map.put(541,"wdaws");
        map.put(7864,"wdgfhaws");
        map.put(5434531,"wdartryws");
        map.put(52.31341,"gdfg");
        System.out.println(map);
    }

    private static void HashMap的底层实现原理() {
        /**
         * HashMap 底层原理
         * 数组+链表 (jdk7及之前)
         *      底层直接创建了长度为16的一位数组Entry[] table
         * 数组+链表+红黑树(jdk 8)
         * //当某一个数组索引位置上的元素以链表形式存储的个数>8 && 且当前数组的长度> 64时，
         *      底层在put之后创建了长度为16的一位数组Node[] table
         *
         * 三、HashMap的底层实现原理?以jdk7为例说明:
         *  HashMap map = new HashMap():
         *  在实例化以后，底层创建了长度是16的一维数组Entry[] table.
         *  ...可能已经执行过多次put...
         *  map. put(key1, value1):
         *  首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组 中的存放位置。
         *  如果此位置上的数据为空，此时的key1-value1 添加成功。---- 情况1
         *  如果此位置上的数据不为空，(意味着此位置上存在一个或多 个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据
         *  的哈希值:
         *          如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1 添加成功。----情况2
         *          如果key1的哈希值和已经存在的某一个数据(key2-value2) 的哈希值相同，继续比较:调用key1 所在类的equals(key2)
         *                  如果equals()返回faLse:此时key1-value1添加成功。----情况3
         *                  如果equals().返回true:使用value1替换value2。
         *      补充:关于情况2和情况3:此时key1-value1 和原来的数据以链表的方式存储。
         *
         *  在不断的添加过程中，会涉及到扩容问题，默认的扩容方式:
         *      扩容为原来容量的2倍，并将原有的数据复制过来。
         */

        /**
         * HashMap的默认特殊值
         * 默认加载因子 0.75// 即 如原来有16个数组  当存了12个（16*0.75）
         *     第13个时要扩容（如果第13个存的地方原来是空的话  不扩容）
         *     扩容后  要重新算一次哈希 重新把原来的一个一个添加到新的里
         * 他创建数组的大小  必定是2的幂
         *
         * 如果 链表连的长度大于等于8时 但数组的长度《= 64  他会扩容
         * DEFAULT_ INITIAL_ CAPACITY : HashMap 的默认容量，16
         * DEFAULT LOAD FACTOR: HashMap的默认加载因子: 0.75
         * threshold:扩容的临界值，=容量*填充因子: 16 *
         * 0.75 => 12
         * TREEIFY_THRESHOLD: Bucket 中链表长度大于该默认值，转化为红黑树:8
         * MIN_TREEIFY_CAPACITY: 桶中的Node 被树化时最小的hash表容量: 64
         *
         *
         *
         */
        HashMap map=new HashMap<>();//底层创建了长度为16的一位数组Entry[] table

        map.put(1,5);//根据key的哈希值经过某种运算存入
        // 如果没 直接添加到Entry[] table数组上  若果有 先比较哈希值不一样 链表直接添加
        //比较哈希值一样  在key的equals方法比较之后
        //没相等的 链表连起来   有的话  用新的替换旧的
    }
}
