package base.常用类;

import java.time.*;
import java.util.Date;

/**
 * java.time
 * 他获得类不是new 类  而是   类.now
 *
 */
@Deprecated//表示已废弃
public class Data时间 {
    public static void main(String[] args) {



    }



    private static void jdk8新时间api的一部分原因() {
        /**
         * 一-个java.util.Date类，但是它的大多数方法已经在JDK 1.1
         * 引入Calendar类之后被弃用了。而Calendar并不比Date好多少。
         * 它们面临的问题是:
         *      可变性:像日期和时间这样的类应该是不可变的
         *      偏移性: Date中的年份是从1900开始的，而月份都从0开始。
         *      格式化:格式化只对Date有用，Calendar则不行。
         *      此外，它们也不是线程安全的;不能处理闰秒等。
         *      总结:对日期和时间的操作一直是Java程序 员最痛苦的地方之一。
         */
        Date date=new Date(5,5,5);//有偏移量
        System.out.println(date);//Mon Jun 05 00:00:00 CST 1905
        /**
         * As of JDK version 1.1,
         * replaced by Calendar.set(year + 1900, month, date) or
         * GregorianCalendar(year + 1900, month, date).
         */}

    private static void Data的相互转换() {
        //java.util.Date extends java.util.Date
        long time = System.currentTimeMillis();//获取系统当前按时间
        System.out.println(time);//格式1629096065408毫秒数
        Date date = new Date();//创建一个当前的时间的对象
        System.out.println(date.toString());//格式Mon Aug 16 14:41:05 CST 2021
        System.out.println(date.getTime());//获得对象的毫秒数
        Date date1 = new Date(2629095316368L);//创建一个制定毫秒数的指定对象
        System.out.println(date1);//Thu Apr 24 16:15:16 CST 2053
        java.sql.Date date2 = new java.sql.Date(1629095316368L);//根据毫秒数创一个java.sql.Date对象
        System.out.println(date2);//格式2021-08-16

        //java.util.Date和java.sql.Date转换
        java.sql.Date date3 = new java.sql.Date(date.getTime());//date.getTime()获得对象的毫秒数
        //强转只能子类强转成父类
        java.sql.Date date4 = new java.sql.Date(1629095316368L);
        Date date5=new Date();
        Date date6=date4;
//        java.sql.Date date7= (java.sql.Date) date5;//报错java.lang.ClassCastException类型转换异常
    }
}
