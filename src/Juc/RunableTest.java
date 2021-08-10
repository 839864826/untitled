package Juc;


class Mythrend1 implements Runnable {//继承接口
    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName());
    }
}


public class RunableTest {
    public static void main(String[] args) {
        Mythrend1 mythrend1=new Mythrend1();
        Thread thread=new Thread(mythrend1);//new Thread 才能创建线程
        thread.start();

        new Thread(new Mythrend1()).start();
    }
}

