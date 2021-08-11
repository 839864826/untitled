package Juc.高级;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AQS {
    public static void main(String[] args) {
        /**
         * AQS
         * 是用来构建锁或者其它同步器组件的重量级基础框架及整个JUC体系的基石，
         * 通过内置的FIFO队列来完成资源获取线程的排队工作，并通过一个int类型变量
         * 表示持有锁的状态
         *
         *
         *
         * ReentrantLock
         * CountDownLatch
         * ReentrantReadWriteLock
         * Semaphore
         * 都继承于AQS
         *
         *
         */
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        lock.unlock();

    }
    private  static void LockSupportDemo() {
        /**
         * LockSupport//  不需要锁块
         * park()//阻塞队列        //wait()   和 await()     升级版   //
         * unpark()//关闭阻塞队列   //notify() 和 signal()    升级版 //
         *
         * wait()一定在notify()前面执行
         * unpark()可以在park()前执行   等于提前唤醒//native方法
         * unpark()发放许可证 用一次+1 但是最大为一
         * park()消耗许可证   为0消耗不了被堵塞  直到再发许可证
         *
         */

        Thread a = new Thread(()->{
            LockSupport.park();
        },"a");
        a.start();

        Thread b = new Thread(()->{
            LockSupport.unpark(a);
        },"b");
        b.start();

    }

}
