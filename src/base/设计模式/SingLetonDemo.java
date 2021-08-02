package base.设计模式;

/**
 * 设计模式
 * 创建型模式，共5种:工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
 * 结构型模式，共7种:适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
 * 行为型模式，共11种:策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、
 *                 备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 *
 *
 *
 */

/**
 * 代理模式
 *  接口的应用
 * 代理模式是Java开发中使用较多的一种设计模式。代理设计就是为其
 * 他对象提供一种代理以控制对这个对象的访问。
 */



import java.util.Calendar;


public class SingLetonDemo {
    public static void main(String[] args) {
       Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));//获得当前年份
        System.out.println(calendar.get(Calendar.MONTH));//获得当前月份
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//获得当前日份
    }



}
