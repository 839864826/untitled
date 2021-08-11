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
    public  void printc1() {
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
    public  void printc2() {
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
    public  void printc3() {
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

class Aircondtion {
    private int number = 0;
    //synchronized重锁1.8前
    private Lock lock=new ReentrantLock();//ReentrantLock递归锁 非公平锁  新版的
    private Condition condition=lock.newCondition();
//    不能用synchronized的this.wait();和this.notifyAll()

    public  void increment(){
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

    public  void decrement() {
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

} // 轻锁和重锁ifandwhile_lockandsy

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

/**
 * 解决线程安全问题的方式三: Lock锁--- JDK5.0新增
 * 1. 面试题: synchronized 与Lock的异同?
 * 相同:二者都可以解决线程安全问题
 * 不同: synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
 * Lock需要手动的启动同步(Lock()) ，同时结束同步也需要手动的实现(unlock())
 *
 * 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性(提供更多的子类)
 *
 * *说明:
 * * 1.wait()， notify(), notifyALl()三个方法必须使用在同步代码块或同步方法中。
 * 2. wait(), notify(), notifyALl()三个方法的调用者必须是同步代码块或同步方法中的同步监视器
 * 否则，会出现IlLegaLMonitorStateException异常
 */
public class LockAndsynchronized {

    public static void main(String[] args) {
        ifandwhile_lockandsy();
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
