package 集合;

import 集合.CollectionTest.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
    public static void main(String[] args)  {
        Properties常用来处理配置文件();

    }

    private static void Properties常用来处理配置文件() {
        Properties properties = new Properties();
        //Hashtable的子类 常用来处理配置文件   key-value都是String
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("jdbc.properties");
            properties.load(fis);//加载对应的文件
            //如果有中文  可能有乱码 File->Settings->Editor->File Encodings->
            //Properties Files (*. properties)
            //Default encoding for properties files: UTF-8 √ Transparent native -to-asci conversion
            //要勾上  这个时专门搞properties配置文件的  而且要删了重新搞  否则他之前加载过
            String name = properties.getProperty("name");
            String age = properties.getProperty("age");
            System.out.println(name);
            System.out.println(age);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();//关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void TreeMapTest() {
        /**
         * TreeMap
         * 向TreeMap中添加key-value,要求key必须是由同一个类创建的对象
         * 因为要按照key进行排序:自然排序、
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
        TreeMap map = new TreeMap(comparator);
        User user1 = new User("aa",1005);
        User user2 = new User("bb",1003);
        User user3 = new User("dd",1008);
        User user4 = new User("dd",1002);
        map.put(user1,99);
        map.put(user2,23);
        map.put(user3,786);
        map.put(user4,95679);


        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + "----->" + entry.getValue());
        } //map最优遍历方法
    }

    private static void Map的遍历() {
        Map map = new HashMap();

        map.put(1,"AA");
        map.put(2,"bb");
        map.put("CC",3);
        map.put(2,"BB");//2 这个key相同  把新的value修改成新的
        System.out.println(map);//{CC=3, 1=AA, 2=BB}

        System.out.println("******************把Map的Key-value提取出来*****************************");
        Set set = map.keySet();//把Map的key转换成Set
        System.out.println(set);//[CC, 1, 2]
        Collection values = map.values();//把Map的value转换成Collection集合
        System.out.println(values);//[3, AA, BB]

        Set entrySet = map.entrySet();//返回的类型  Set<Map.Entry<K, V>>
        System.out.println(entrySet);//[CC=3, 1=AA, 2=BB]
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            Map.Entry entry = (Map.Entry) obj;//Map.Entry 内部接口
            System.out.println(entry.getKey()+"\t"+entry.getValue());//内部接口的方法（）
        } //这种遍历方式 速度快 效率高
    }

    private static void Map的普通方法2() {
        Map map = new HashMap();

        map.put(1,"AA");
        map.put(2,"bb");
        map.put("CC",3);
        map.put(2,"BB");//2 这个key相同  把新的value修改成新的
        System.out.println(map);//{CC=3, 1=AA, 2=BB}

        System.out.println(map.get(2));//BB  根据key  返回对应的value

        System.out.println("*********是否包含指定的Key或Value*******************************");
        System.out.println(map.containsKey(3));//false
        System.out.println(map.containsValue(3));//true
        System.out.println(map.equals(map));//true  判断map是否相等
        System.out.println(map.isEmpty());//false 判断map是否为空
    }

    private static void Map的普通方法() {
        Map map = new HashMap();

        map.put(1,"AA");
        map.put(2,"bb");
        map.put("CC",3);
        map.put(2,"BB");//2 这个key相同  把新的value修改成新的
        System.out.println(map);//{CC=3, 1=AA, 2=BB}

        Map map1 = new HashMap();
        map1.put("dd",8);
        map1.put("90",56);

        map.putAll(map1);//合集  把map1的Map全添加到map里
        System.out.println(map);//{CC=3, dd=8, 1=AA, 2=BB, 90=56}

        System.out.println("***********remove**删除*************************");
        Object value = map.remove("dd");//删除key为dd的key  并返回value的值
        System.out.println(value);//8   没有返回null
        System.out.println(map);//{CC=3, 1=AA, 2=BB, 90=56}
        System.out.println("***********clear**清空*************************");
        map.clear();//清空map
        System.out.println(map.size());//0   获取map的对数
        System.out.println(map);//{}
    }

    private static void LinkedHashMap重点() {
        LinkedHashMap map = new LinkedHashMap();//LinkedHashMap中的内部类: Entry数组
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
        System.out.println(map);//可以i根据添加的顺序  遍历
        //{541=wdaws, 7864=wdgfhaws, 5434531=wdartryws, 52.31341=gdfg}
    }

    private static void HashMap的底层实现原理() {
        /**
         * HashMap 底层原理
         * 数组+链表 (jdk7及之前)
         *      底层直接创建了长度为16的一位数组Entry[] table
         * 数组+链表+红黑树(jdk 8)
         * //当某一个数组索引位置上的元素以链表形式存储的个数>8 && 且当前数组的长度> 64时，
         *      底层在put之后创建了长度为16的一位数组Node[] table
         * //红黑树  排序二叉树
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
