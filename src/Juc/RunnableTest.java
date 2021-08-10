package Juc;

/**
 * 创建多线程的方式二：实现Runnable接口
 * 1.创建一个实现了Runnable接口的类
 * 2.实现类去实现Runnable中的抽象方法: run()
 * 3.创建实现类的对象
 * 4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5，通过Thread类的对象 调用start()
 *
 * 最好用实现Runnable接口
 *      1.因为接口可以多继承
 *      2.实现的方式更适合处理共享数据的方式
 *
 *
 * Thread implements Runnable
 * 都必须重写run方法
 *
 */
class MyRunnable1 implements Runnable {//继承接口  Thread implements Runnable
    private int a=0;//线程不安全
    @Override
    public void run()
    {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName()+":  "+a);
            a++;
        }
    }
}


public class RunnableTest {
    public static void main(String[] args) {
        MyRunnable1 myRunnable1=new MyRunnable1();
        Thread thread=new Thread(myRunnable1);//new Thread 才能创建线程
        Thread thread1=new Thread(myRunnable1);//new Thread 才能创建线程
        //Thread(myRunnable1)   把Runnable放了进去  是同一个把Runnable放了进去 a累加 1-39
        thread.start();
        thread1.start();

        //new Thread(new MyRunnable1()).start();
    }
}

