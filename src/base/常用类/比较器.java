package base.常用类;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 比较
 * 1.Java中的对象，正常情况下，只能进行比较: ==或!=。不能使用〉或<的
 *  但是在开发场景中，我们需要对多个对象进行排序，言外之意， 就需要比较对象的大小。
 *  如何实现?使用两个接口中的任何一个: Comparable 或Comparator
 *
 */
public class 比较器 {
    public static void main(String[] args) {

        Comparator定制排序();

    }

    private static void Comparator定制排序() {
        /**
         * Comparator接口的使用:定制排序  用一次就没了
         * 1.背景:
         * 当元素的类型没有实现java. lang. comparable接口而又不方便修改代码，
         * 或者实现了java. lang. Comparable接口的排序规则不适合当前的操作，十
         * 那么可以考虑使用Comparator 的对象来排序
         * 2.重写compare(Object 01, object o2) 方法，比较o1和o2的大小:
         * 如果方法返回正整数，则表示01大于o2;
         * 如果返回8，表示相等;
         * 返回负整数，表示o1小于o2。
         *
         */
        String[] strings = new String[]{"AA","BB","CC","DD","AB"};
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(strings));
    }

    private static void Comparable自然排序() {
        /**
         * Comparable接口//自然排序 重写了  可一直用
         * 1.像String、包装类等实现了Comparable接口，
         *      重写了compareTo()方法，给出了比较两个对象大小//默认从小到大
         * 2.像String、包装类重写compareTo()方法以后，进行了从小到大的排列
         * 2.重写compareTo(obj)规则
         *      如果当前对象this大于形参对象obj,则返耳正整数，
         *      如果当前对象this小于形参对象obj,则返巴负整数,
         *      如果当前对象this等于形参对象obj,则返回零。
         * 4.对于自定义类类，要排序  我们要让自定义类实现Comparable接口
         *      重写compareTo()方法，在compareTo()里写排序方法
         */
        String[] strings = new String[]{"AA","BB","CC","DD","AB"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
        Goods[] arr = new Goods[4];//重写了compareTo()方法
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("del1Mouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

class Goods implements Comparable{

    private String name;
    private double price;

    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods = (Goods) o;
            if(this.price>goods.price){
                return 1;
            }else if(this.price<goods.price){
                return -1;
            }else {
                return 0;
            }
//            return Double.compare(this.price,goods.price);//或者
        }
        throw new RuntimeException("传入数据表类型不一样");
    } //指明判断大小方式

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
