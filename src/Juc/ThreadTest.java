package Juc;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName;

/**
 * 多线程的创建，方式- :继承于Thread类
 * 1.创建一个继承于Thread类的于类
 * 2.重写Thread 类的run()
 * 3.创建Thread类的子类的对象
 * 4.通过此对象调用start()
 */
class OushuThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " invoked...");
    }
}
public class ThreadTest {
    public static void main(String[] args) {
        OushuThread ou = new OushuThread();
        ou.run();//只是一个方法   还是在main线程中执行
        ou.start();//多线程  一个new只能调用一次  启动当前线程   调用run方法
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
