package Juc.基础;

/**
 * Thread常用方法
 *
 * 1. start():启动当前线程并执行run方法
 * 2. run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法牛
 * 3. currentThread(): 静态方法，返回执行当前代码的线程
 * 4. getName():获取当前线程的名字
 * 5. setName():设置当前线程的名字
 * 6. yield():释放当前cpu执行权//但是也可能自己再抢到
 * 7. join():在线程a中调用线程b的join(),此时线程a就进,入阻塞状态，
 *          直到线程b完全执行完以后，线程a才结束阻塞状甜
 * 8.stop():过时了  强制关闭当前线程
 * 9.sleep():睡眠
 * 10.isAlive():判断线程是否死亡 true存货  false死亡
 *
 * 线程优先级： 越大越牛  高只是概率高点并不是一定靠前
 *      MIN_PRIORITY = 1
 *      NORM_PRIORITY = 5//默认优先级
 *      MAX_PRIORITY = 10
 *2.如何获取和设置当前线程的优先级:
 *      getPriority():获取线程的优先级
 *      setPriority(int p): 设置线程的优先级
 *
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isAlive());//判断线程是否死亡 true存货  false死亡
        System.out.println(Thread.currentThread());//获取当前线程
        System.out.println(Thread.currentThread().getName());//获取当前线程名字
        System.out.println(Thread.currentThread().getPriority());//返回优先级等级  1-10
    }
}
