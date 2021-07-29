package base;

/**
 * 设计模式
 * 创建型模式，共5种:工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
 * 结构型模式，共7种:适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
 * 行为型模式，共11种:策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 *
 *
 *
 */

/**
 * 单例设计模式
 * 所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对
 * 某个类只能存在一个对象实例，并且该类只提供一-个取得其对象实例的方法。
 * 如果我们要让类在一个虚拟机中只能产生-一个对象，我们首先必须将类的构
 * 造器的访问权限设置为private，这样，就不能用new操作符在类的外部产生
 * 类的对象了，但在类内部仍可以产生该类的对象。因为在类的外部开始还无
 * 法得到类的对象，只能调用该类的某个静态方法以返回类内部创建的对象，
 * 静态方法只能访问类中的静态成员变量，所以，指向类内部产生的该类对象
 * 的变量也必须定义成静态的。
 *
 * 懒汉式和饿汉式
 *     饿汉式：坏处：对象加载时间过长
 *           好处：线程安全的
 *     懒汉式：好处：延迟对象创建
 *           坏处：目前写法不安全
 *
 *
 *
 * 单例设计模式   优点
 *      由于单例模式只生成一个实例，减少了系统性能开销，当一个对象的
 *      产生需要比较多的资源时，如读取配置、产生其他依赖对象时，则可
 *      以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方
 *      式来解决。
 *
 */
public class SingLetonDemo {
    public static void main(String[] args) {
        //单例设计模式一饿汉式   线程安全的
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        System.out.println(bank1 == bank2);//true

        //单例设计模式二懒汉式
        Banks banks1 = Banks.getInstance();
        Banks banks2 = Banks.getInstance();
        System.out.println(banks1 == banks2);//true
    }
}

class Bank{
    private Bank(){
    }
    private static Bank instance = new Bank();
    public static Bank getInstance(){
        return instance;
    }
} //饿汉式

class Banks{

    private Banks(){
    }//1.私有化类的构造器
    private static Banks instance = null;

    public static Banks getInstance() {
        if(instance == null){
            instance = new Banks();
        }//线程不安全的
        return instance;
    }

} //懒汉式
