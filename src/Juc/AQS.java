package Juc;

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
}
