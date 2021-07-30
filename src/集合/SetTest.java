package 集合;


import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {

        Set<Integer> treeSet=new TreeSet<>();

        treeSet.add(5);
        treeSet.add(6);
        treeSet.add(4);
        System.out.println(treeSet);
        /**
         * 存储的数据为int类型的时候，treeset是可以自动进行排序的；
         * 存储的数据为char类型的时候可见这是按照字母的顺序去排列的；
         * 可见对于String类型来说，也是按照字母的顺序来排列的；
         */
    }
}
