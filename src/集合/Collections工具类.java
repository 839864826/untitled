package 集合;

import java.util.*;

/**
 * Collections用来操作Collection和Map的工具类
 *
 */
public class Collections工具类 {

    public static void main(String[] args) {
        Collections.synchronizedList(new ArrayList<>());//返回的就是线程安全的
        Collections.synchronizedSet(new HashSet<>());//返回的就是线程安全的
        Collections.synchronizedMap(new HashMap<>());//返回的就是线程安全的
    }

    private static void Collections基础方法() {
        /**
         * 方法
         * reverse(List)：反转 List 中元素的顺序
         * shuffle(List)：对 List 集合元素进行随机排序
         * sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
         * sort(List，Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
         * swap(List，int， int)：将指定 list 集合中的 i 处元素和 j 处元素进行交换
         *
         * Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
         * Object max(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素
         * Object min(Collection)
         * Object min(Collection，Comparator)
         * int frequency(Collection，Object)：返回指定集合中指定元素的出现次数
         * void copy(List dest,List src)：将src中的内容复制到dest中
         * boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值
         *
         */
        List list=new ArrayList<>();
        list.add(5465);
        list.add(45);
        list.add(5463565);
        list.add(11);
        list.add(-5465);
        List dest=new ArrayList<>();

//        if (srcSize > dest.size()) //dest的大小<list的大小
//            throw new IndexOutOfBoundsException("Source does not fit in dest");
//        java.lang.IndexOutOfBoundsException: Source does not fit in dest
        dest= Arrays.asList(new Object[list.size()]);//给dest一个大小
        Collections.copy(dest,list);
        System.out.println(dest);
    }
}
