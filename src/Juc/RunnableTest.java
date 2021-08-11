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
    private  int a=100;//线程不安全
    @Override
    public void run()
    {
        while (true){
            if(a>0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"："+a--);
            }else {
                break;
            }
        }
    }
}

/**
 * 例子:创建三个窗口卖票，总票数为100张.
 * 1.问题:卖票过程中，出现了重票、错票-->出现了线程的安全问题
 * 2.问题出现的原因:当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票
 * 3.如何解决:当-个线程a在操作number的时候， 其他线程不能参与进来。直到线程a操作完number时
 * 线程才可以开始操作number。这种情况即使线程a出现了阻塞，也不能被改变。
 * 4.在Java中， 我们通过同步机制，来解决线程的安全问题。
 * 方式一:同步代码块
 * synchronized(同步监视器){
 *      // 需要被同步的代码
 * }
 *  说明:    1.操作共享数据的代码，即为需要被同步的代码
 *          2.共享数据:多个线程共同操作的变量。比如: ticket就是共享数据。.
 *          3.同步监视器，俗称:锁。一个类的对象都可以充当锁🔒
 *                  要求:多个线程必须要共用同一把锁。
 *5.同步的方式，解决了线程的安全问题。---好处
 * 操作同步代码时，只能有一 -个线程参与，其他线程等待。相当于是- -个单线程的过程，效率低。
 *
 * 方式二:同步方法
 *
 *
 */

class MyRunnable2 implements Runnable {//继承接口  Thread implements Runnable
    private  int a=100;//线程不安全
    Object obj=new Object();//多个线程必须要共用同一把锁。 也就是同一个对象
    @Override
    public void run()
    {
        while (true){
            synchronized (this){//两种都行
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

class MyRunnable3 implements Runnable {//继承接口  Thread implements Runnable
    private  int a=100;//线程不安全
    Object obj=new Object();//多个线程必须要共用同一把锁。 也就是同一个对象
    @Override
    public void run()
    {
        while (show()){
        }
    }

    //synchronized  没static相当于synchronized (this){
    //有的话static synchronized  相当于synchronized (RunnableTest.class){
    private  synchronized boolean show(){
        if(a>0){
            System.out.println(Thread.currentThread().getName()+"："+a--);
            return true;
        }else {
            return false;
        }
    }
}

public class RunnableTest {
    public static void main(String[] args) {
//        MyRunnable1 myRunnable=new MyRunnable1();
//        MyRunnable2 myRunnable=new MyRunnable2(); //只创建了一个对象
        MyRunnable3 myRunnable=new MyRunnable3(); //只创建了一个对象
        Thread thread=new Thread(myRunnable);//new Thread 才能创建线程
        Thread thread1=new Thread(myRunnable);//new Thread 才能创建线程
        //Thread(myRunnable1)   把Runnable放了进去  是同一个把Runnable放了进去 a累加 1-39
        thread.start();
        thread1.start();

        //new Thread(new MyRunnable1()).start();
    }
}

