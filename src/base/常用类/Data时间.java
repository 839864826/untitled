package base.常用类;

import java.util.Date;

/**
 *
 */
@Deprecated//表示已废弃
public class Data时间 {
    public static void main(String[] args) {
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
        java.sql.Date date3 = new java.sql.Date(date.getTime());
        //强转只能子类强转成父类
        java.sql.Date date4 = new java.sql.Date(1629095316368L);
        Date date5=new Date();
        Date date6=date4;
//        java.sql.Date date7= (java.sql.Date) date5;//报错java.lang.ClassCastException类型转换异常

    }
}
