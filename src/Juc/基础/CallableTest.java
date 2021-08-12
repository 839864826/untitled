package Juc.基础;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
//JDK  5.0新增
class Mythrend2 implements Callable<Integer> {
    AtomicInteger atomicInteger=new AtomicInteger(10);//原子性的int
    @Override
    public Integer call() {
        System.out.println("线程"+Thread.currentThread().getName()+"        call() 的执行！！！！！");
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        return 1024;
    }//只会执行一次
}  //Callable<Integer>的高手有返回值

/**
 * Callable和Runnable异同点
 * Callable
 *      1.可以抛异常     V call() throws Exception;
 *      2.V     有返回值
 *      3.需要FutureTask这个中间商  再new Thread（FutureTask只能执行一次）
 * Runnable
 *      1.每异常       public abstract void run();
 *      2.void  无返回值
 *      3.直接可以  new Thread
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo();
    }

    private static void CallableDemo() throws ExecutionException, InterruptedException {
         //第二种  多线程  有返回值！！！！
        // 一个对象只能执行一次  等于说👇
        // 只能在一个线程跑   要摸在new另一个继承Callable的对象

        FutureTask<Integer> futureTask1 = new FutureTask(new Mythrend2());//只能执行一次
        FutureTask<Integer> futureTask2 = new FutureTask(new Mythrend2());//FutureTask只能执行一次

        new Thread(futureTask1,"A").start();
        new Thread(futureTask1,"B").start();//由于线程A执行过了  一个new Callable只能执行一次
        new Thread(futureTask2,"C").start();


        System.out.println(Thread.currentThread().getName()+"计算完毕");
        Integer result =futureTask1.get();
        System.out.println(result);
        Integer result2 =futureTask2.get();
        System.out.println(result2);
        System.out.println("*************************************************");


        FutureTask<Integer> futureTask0 = new FutureTask(new Mythrend2());

        new Thread(futureTask0,"0").start();
        new Thread(futureTask0,"1").start();//只执行一个

        System.out.println(Thread.currentThread().getName() + "**************************");
        int a=100;
        while (!futureTask0.isDone()) {//是否结束
            // 因为一个FutureTask 对象只执行一次  所以可以只看FutureTask是否结束
        }
        int aa = futureTask0.get();//获取返回值
        System.out.println("*****result" + (a + aa));


    }
    //Callable<Integer>  的调用   另一种多线程
}
