package Juc.高级;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


class  MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产消费
    private AtomicInteger atomicInteger=new AtomicInteger();
    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd()throws Exception{
        String data=null;
        boolean retValue;
        while (FLAG){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            }else{
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
//            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板叫停 表示FLAG=false");

    }

    public void myConsumer()throws Exception{
        String result=null;
        while (FLAG){
            result=blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null==result||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println();
                System.out.println();
                System.out.println(Thread.currentThread().getName() + "\t超时2秒没取到推出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列" + result + "成功");

        }
    }

    public void stop()throws Exception{
        this.FLAG=false;
    }
}  //不用lock  生产者消费者
public class 不用锁完成线程同步 {

    public static void main(String[] args) {

    }
    AtomicReference<Thread> atomicReference=new AtomicReference<>();//原子引用线程
    public void myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t O(∩_∩)O");
        while (!atomicReference.compareAndSet(null,thread))
        {
            /**
             * 自旋锁不会阻塞，而是采用循环的方式尝试获得锁
             */
        }
    }
    public void myUnlock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\tmyUnlock");
    }
    private static void 自旋锁() {
        /**
         * 自旋锁不会阻塞，而是采用循环的方式尝试获得锁
         */

        不用锁完成线程同步 juc=new 不用锁完成线程同步();
        new Thread(()->{
            juc.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace(); }
            juc.myUnlock();
        },"AA").start();

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace(); }

        new Thread(()->{
            juc.myLock();
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace(); }
            juc.myUnlock();
        },"BB").start();
    }

    private static void NoLock_BlockingQueue() throws Exception {
        MyResource myResource=new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t生产线启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t消费线启动");

            try {
                myResource.myConsumer();
                System.out.println();System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费").start();
        TimeUnit.MILLISECONDS.sleep(2);
//        TimeUnit.SECONDS.sleep(1);
//        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒教廷");
        myResource.stop();
    }  //不用lock  生产者消费者

}
