package JavaN.Java8;

import java.util.ArrayList;
import java.util.Collection;

public class 集合 {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add("AA");//集合添加
        coll.add('v');
        coll.add(123);
        coll.add(15.6);

        for (Object o:coll){//内部调用的也是迭代器
            System.out.println(o);
        }
        coll.forEach(System.out::println);
        //同上  也是遍历 JKD8新特性
    }
}
