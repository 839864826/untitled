
//import com.sun.istack.internal.localization.NullLocalizable;


import lombok.Getter;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.*;

class Phone//static  锁的Class方法
{
    //锁的是资源类方法
    public  synchronized void sendEmail()throws Exception //static  全局锁已经定了 不可多线程同时
    {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("****sendEmail");
    }
    public  synchronized void sendSMS()throws Exception
    {
        System.out.println("****sendSMS");
    }
    public void sayhello()throws Exception
    {
        System.out.println("****hello");
    } //没加锁说明他不是内部的方法

}// //八锁的关系

class Phone2 implements Runnable
{
    //锁的是资源类方法
    public  synchronized void sendEmail()throws Exception //static  全局锁已经定了 不可多线程同时
    {
        System.out.println(Thread.currentThread().getId()+"****sendEmail");
        sendSMS();
    }
    public  synchronized void sendSMS()throws Exception
    {
        System.out.println(Thread.currentThread().getId()+"****sendSMS");
    }
    Lock lock=new ReentrantLock();
    @Override
    public void run(){
        get();
    }
    public void get(){
        /**
         * 线程可以进入任何-一个它已经拥有的锁
         * 所同步着的代码块
         */
        //lock.lock();
        lock.lock();  //可以加锁可多次  但是也要解锁几次
        try {
            System.out.println(Thread.currentThread().getId()+"****sendEmail");
            set();
        }finally {
            //lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"****sendEmail");
        }finally {
            lock.unlock();
        }
    }

}// //八锁的关系

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

public class JUC {//多线程
    private  int id;
    private String userName;
    private  int age;

    public  static  void main(String[] args) {//main线程   一切程序入口

//        System.out.println(Runtime.getRuntime().availableProcessors());//查看核数
    }

    private static void AQSTest() {
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

    public enum CountryEnum{
        ONE(1,"齐国"), TWO(2,"楚国"),
        THREE(3,"燕国"),FOUR(4,"赵国"),
        FIVE(5,"魏国"),SIX(6,"韩国");
        @Getter private Integer retCode;
        @Getter private String retMessage;

        CountryEnum(Integer retCode,String retMessage) {
            this.retMessage = retMessage;
            this.retCode = retCode;
        }

        public static CountryEnum forEach_CountryEnum(int index){

            CountryEnum[] myArray = CountryEnum.values();
            for(CountryEnum countryEnum:myArray){
                if(index==countryEnum.getRetCode()){
                    return countryEnum;
                }
            }
            return null;
        }
    }  //枚举

    private static void 可重入锁() {
        /**
         *    synchronized和ReentrantLock  可重入锁又名递归锁
         *     线程可以获得自己线程
         *
         *
         */
        Lock lock=new ReentrantLock();//默认 非公平锁 synchronized也是非公平锁
        Lock lock0=new ReentrantLock(false);//非公平锁  上来先尝试占有锁 如果尝试失败   就开始采取公平锁类似的方式
        Lock lock1=new ReentrantLock(true);
        //公平锁  每个线程先查看等待队列，如果为空或者是等待队列的第一个，就占有所，否则就会加入等待队列中  进行FIFO规则

        /**
         *   线程在外层方法获取锁的时候
         *   在进入内层方法会自动获取锁
         *
         *   线程可以进入任何一个它已经拥有的锁
         *   所同步着的代码块
         */
        Phone2 phone2=new Phone2();
        /*
        for (int i = 0; i < 1; i++) {
            new Thread(()->{

            },String.valueOf(i+1)).start();
        }

        */
        new Thread(()->{
            try {
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{

            try {
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        new Thread(phone2,"t3").start();
        new Thread(phone2,"t4").start();
    }

    private static void SemaphoreDemo() {
        Semaphore semaphore=new Semaphore(3);//总数1个
        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//获得       抢到
                    System.out.println(Thread.currentThread().getName() + "\t抢到了");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "\t离开了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放
                }
            },String.valueOf(i+1)).start();
        }
    }//Semaphore   限流

    private static void CyclicBarrierDemo() {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {//多余的话  会一直等待
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "课龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i+1)).start();
        }
    } //CyclicBarrier  控制线程 做完后在接下来运行(必须做完才结束)

    private static void CountDownLatchDemo() {
        //      倒计时锁  计数  向下 门闩
        CountDownLatch countDownLatch=new CountDownLatch(6);//6次结束
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println("第" + Thread.currentThread().getName() + "走了");
                countDownLatch.countDown();//每做一次记录一次
            },CountryEnum.forEach_CountryEnum(i+1).getRetMessage()).start();//String.valueOf(i+1)
        }     //           枚举
        try {
            countDownLatch.await();//没结束等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "关门");
    }//CountDownLatch 倒计时锁  控制线程 做完后在接下来运行

    private static void lockeitht() {
        Phone phone=new Phone();
        Phone phone2=new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"phone").start();
        new Thread(()->{
            try {
                phone.sendSMS();// 1静态 1动 一部两部手机都不影响 静态和动态分开了所以一部二部不影响 都动态
                //phone.sayhello();//不加锁完全不影响
                //phone2.sendSMS();
                // 动态 两部手机不影响（相当于造了两个函数）  静态 两部手机有影响(一共就一个对象 不得不争抢）
            }catch (Exception e){
                e.printStackTrace();
            }
        },"sms").start();
    }   //八锁的关系

    private static void mapNotsafe() {
        Map<String,String> map= new ConcurrentHashMap<>();
        //最好的解决方式  一种新的接口      并发    HashMap

        /**
         * HashMap k,v键值对
         * put方法其实是放到node<k,v>  节点上了
         */
        HashMap<String,String> m=new HashMap<>();
        for (int i = 0; i < 10; i++) {
            new  Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),"0");//随机产生8个字符
                System.out.println(map);
            },"a").start();
        }
    }  //map线程不安全

    private static void setNotsafe() {
        Set<String> set= new CopyOnWriteArraySet<>();

        //最好的解决方式  一种新的接口   复制写操作

        for (int i = 0; i < 10; i++) {
            new  Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));//随机产生8个字符
                System.out.println(set);
            },"a").start();
        }
    }    //set线程不安全

    private static void listNotsafe() {


        /**
         *
         * List<String> list=new ArrayList<>();
         * java.util.ConcurrentModificationException 会出现这种异常 并发异常
         *
         * List<String> list=new Vector<>(); //它自带锁synchronized   不会并发异常
         * List<String> list= Collections.synchronizedList(new ArrayList<>()); //强行加锁
         * new CopyOnWriteArrayList<>(); //最好的解决方式  一种新的接口
         * public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArr(ay);
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *         }
         *     }
         */

        List<String> list= new CopyOnWriteArrayList<>();//写实复制  读写分离思想
        //最好的解决方式  一种新的接口   复制写操作


        for (int i = 0; i < 10; i++) {
            new  Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));//随机产生8个字符System.out.println(list);
            },"a").start();
        }
        list.forEach(System.out::println);//循环输出
    }   //list线程不安全

}
