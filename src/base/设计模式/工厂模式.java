package base.设计模式;

public class 工厂模式 {
    public static void main(String[] args) {
        无工厂();
        简单工厂();
        工厂方法();


    }

    private static void 工厂方法() {
        Car a = new AudiFactory().getCar();
        Car b = new BydFactory().getCar();
        a.run();
        b.run();
    }

    private static void 简单工厂() {
        Car a = CarFactory.getCar("奥迪");
        a.run();
        Car b = CarFactory.getCar("比亚迪");
        b.run();
    }

    private static void 无工厂() {
        Car a = new Audi();
        Car b = new BYD();
        a.run();
        b.run();
    }
}

interface Car{
    void run();
}
class Audi implements Car{
    public void run() {
        System.out.println("奥迪在跑");
    }
}
class BYD implements Car{
    public void run() {
        System.out.println("比亚迪在跑");
    }
}
//工厂类
class CarFactory {
    //方式一
    public static Car getCar(String type) {
        if ("奥迪".equals(type)) {
            return new Audi();
        } else if ("比亚迪".equals(type)) {
            return new BYD();
        } else {
            return null;
        }
    }
//方式二
// public static Car getAudi() {
// return new Audi();
// }
//
// public static Car getByd() {
// return new BYD();
// }
}  //工厂类

//工厂接口
interface Factory{
    Car getCar();
}
//两个工厂类
class AudiFactory implements Factory{
    public Audi getCar(){
        return new Audi();
    }
}
class BydFactory implements Factory{
    public BYD getCar(){
        return new BYD();
    }
}



class XxxFactory{//XxxFactory   表示Xxx的工厂

}
