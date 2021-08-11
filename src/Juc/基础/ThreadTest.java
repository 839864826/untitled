package Juc.基础;


/**
 * 多线程的创建，方式一 :继承于Thread类
 * 1.创建一个继承于Thread类的于类
 * 2.重写Thread 类的run()
 * 3.创建Thread类的子类的对象
 * 4.通过此对象调用start()
 *
 *
 */
class OushuThread1 extends Thread{//继承类   Thread implements Runnable
    private static int a=100;//线程不安全
    private static Object obj=new Object();//线程不安全
    @Override
    public void run() {
        while (true){
//            synchronized (this){
            synchronized (ThreadTest.class){//类也是对象   类只加载一次  所以唯一
//            synchronized (obj){
                if(a>0){
                    System.out.println(Thread.currentThread().getName()+"："+a--);
                }else {
                    break;
                }
            }
        }
    }
}

class OushuThread3 extends Thread{//继承类   Thread implements Runnable
    private static int a=100;//线程不安全
    private static Object obj=new Object();//线程不安全
    @Override
    public void run() {
        while (show()){
        }
    }

    //synchronized  没static相当于synchronized (this){
    //有的话static synchronized  相当于synchronized (RunnableTest.class){
    private static synchronized boolean show(){//由于extends Thread  要创建多个对象 所以static
        if(a>0){
            System.out.println(Thread.currentThread().getName()+"："+a--);
            return true;
        }else {
            return false;
        }
    }
}
public class ThreadTest {
    public static void main(String[] args) {
//        OushuThread1 ou = new OushuThread1();
//        OushuThread1 ou1 = new OushuThread1();//创建了多个对象
        OushuThread3 ou = new OushuThread3();
        OushuThread3 ou1 = new OushuThread3();//创建了多个对象
//        ou.run();//只是一个方法   还是在main线程中执行
        ou.start();//多线程  一个new只能调用一次  启动当前线程   调用run方法
        ou1.start();//输出两个0-19  因为父类是Thread   new了俩个  不是私有的 相互不干扰
        //Thread implements Runnable   继承了 Runnable  就等于两个不同的类

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
