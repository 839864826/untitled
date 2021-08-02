package base.接口;

/**
 * 接口的使用
 *
 * ●定义Java类的语法格式:先写extends， 后写implements
 *      ➢class SubClass extends SuperClass implements InterfaceA{ }
 * ●一个类可以实现多个接口，接口也可以继承其它接口。
 * ●实现接口的类中必须提供接口中所有方法的具体实现内容，
 *      方可实  例化。否则，仍为抽象类。
 * ●接口的主要用途就是被实现类实现。( 面向接口编程)
 * ●与继承关系类似，接口与实现类之，间存在多态性
 * ●接口和类是并列关系，或者可以理解为一种特殊的类。从本质上讲,
 *      接口是一种特殊的抽象类，这种抽象类中只包含常量和方法的定义
 *       (JDK7.0及之前)，而没有变量和方法的实现。
 *
 */
public class USBTest {
    public static void main(String[] args) {

        Computer computer=new Computer();
        Flash flash=new Flash();
        computer.transferData(flash);

        //2. 创建了接口的非匿名实现类的匿名对象
        computer.transferData(new Printer());

        //3. 创建了接口的匿名实现类的非匿名对象
        USB phone = new USB() {
            @Override
            public void start() {
                System.out.println("手机开始工作");
            }

            @Override
            public void stop() {
                System.out.println("手机结束工作");
            }
        };
        computer.transferData(phone);

        //4. 创建了接口的匿名实现类的匿名对象   computer.transferData(new USB(){...});
    }
}

class Computer{
    public void transferData(USB usb){
        usb.start();
        System.out.println("细节工作，具体的传输");
        usb.stop();
    }
}
interface USB{
    //常量:定义了长、宽、最大最小的传输速度等

    void start();//省略了public static final
    void stop();//省略了public static final
}

class Flash implements USB{

    @Override
    public void start() {
        System.out.println("U盘开始工作");
    }

    @Override
    public void stop() {
        System.out.println("U盘结束工作");
    }

}//U盘

class Printer implements USB{
    @Override
    public void start() {
        System.out.println("打印机开始工作");
    }

    @Override
    public void stop() {
        System.out.println("打印机结束工作");
    }
} //打印机
