package Juc.基础;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run()
    {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockA + "\t尝试获得" + lockB);
            try{ TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}
            //睡1秒
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockB + "\t尝试获得" + lockA);

            }
        }
    }
}  //产生死锁

/**
 * 演示线程的死锁问题
 * 1.死锁的理解:不同的线程分别占用对方需要的同步资源不放弃,
 * 都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 * 2.说明:
 * 1)出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2)我们使用同步时，要避免出现死锁。
 *
 */
public class 死锁 {
    public static void main(String[] args) {

        jps_jstack();

    }
    private static void jps_jstack() {
        /**
         * jps -l查看java的线程
         * jstack 11320（线程编号）   查看死锁原因
         *
         */
        String lockA="lockA";
        String lockB="lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBB").start();
    }  //死锁原因
}
