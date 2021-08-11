package Juc.高级;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData//为何用Lock   Lock可以设置多把锁和多把钥匙 可以定向唤醒  Lockgood_demo
{
    private int number = 1;//a:1  b:2  c:3
    //synchronized重锁1.8前
    private Lock lock=new ReentrantLock();//ReentrantLock递归锁 非公平锁  新版的
    private Condition c1=lock.newCondition();//多把钥匙   多吧锁
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public  void printc1()throws Exception
    {
        lock.lock();
        try{
            //1.判断
            while (number!=1){c1.await();}//if可能有虚假唤醒
            //2.干活
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
            number=2;
            c2.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public  void printc2()throws Exception
    {
        lock.lock();
        try{
            //1.判断
            while (number!=2){c2.await();}//if可能有虚假唤醒
            //2.干活
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
            number=3;
            c3.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void printc3()throws Exception
    {
        lock.lock();
        try{
            //1.判断
            while (number!=3){c3.await();}//if可能有虚假唤醒
            //2.干活
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
            number=1;
            c1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}

class Aircondtion// 轻锁和重锁ifandwhile_lockandsy
{
    private int number = 0;
    //synchronized重锁1.8前
    private Lock lock=new ReentrantLock();//ReentrantLock递归锁 非公平锁  新版的
    private Condition condition=lock.newCondition();
//    不能用synchronized的this.wait();和this.notifyAll()

    public  void increment()throws Exception
    {
        lock.lock();
        try{
            //1.判断
            while (number!=0){condition.await();}//if可能有虚假唤醒
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
            //this.notifyAll();//通知其他的this.wait(); 停止等待再次判断
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public  void decrement()throws Exception
    {
        lock.lock();
        try{
            //1.判断
            while (number==0){condition.await();}//if可能有虚假唤醒
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
//        this.notifyAll();//通知其他的this.wait(); 停止等待再次判断
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


/*
    public synchronized void increment()throws Exception
    {
        //1.判断
        while (number!=0){this.wait();}//if可能有虚假唤醒
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3.通知
        this.notifyAll();//通知其他的this.wait(); 停止等待再次判断
    }

    public synchronized void decrement()throws Exception
    {
        //1.判断
        while (number==0){this.wait();}//if可能有虚假唤醒
        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3.通知
        this.notifyAll();//通知其他的this.wait(); 停止等待再次判断
    }

 */

}
/**
 * //多线程
 * JUC :java.util.concurrent（并发）   .atomic（原子）    .locks(锁)
 *   进程/线程         并发/并行
 *
 * 我们程序设计追求“高内聚，低耦合’
 * ➢高内聚:类的内部数据操作细节自己完成，不允许外部干涉;
 * ➢低耦合:仅对外暴露少量的方法用于使用。
 *   在高内聚    低耦合  的前提下    线程  操作（对外暴露的调用方法）  资源类
 *   （都可以用  不能修改）
 *
 * synchronized  不可中断  不公平锁
 * ReentrantLock 可中断  1.设置超时方法tryLock(long timeout,TimeUnit unit).
 *                      2.lockInterruptibly()放在代码块中，调用interrupt()方法中断
 *
 *  用Condition 可精准唤醒
 *
 */
public class LockAndsynchronized {

    public static void main(String[] args) {

    }



    private static void Lockgood_demo() {
        ShareData shareData=new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.printc1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.printc2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.printc3();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }   //精确唤醒某些线程  private Condition c1=lock.newCondition();//多把钥匙   多吧锁

    private static void ifandwhile_lockandsy() {
        Aircondtion aircondtion=new Aircondtion();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondtion.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondtion.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"b").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondtion.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"c").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    aircondtion.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"d").start();

    }   //if和while之间多线程要用while
}
