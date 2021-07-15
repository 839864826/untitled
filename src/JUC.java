
//import com.sun.istack.internal.localization.NullLocalizable;


import lombok.Getter;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.*;


class Ticket//资源类      =实例+方法
{
    private  int number = 30 ; //volatile  在不加锁的时候可保证唯一性
    Lock lock=new ReentrantLock();//可重入锁
    public void sale()//高内聚 资源类自带的方法     synchronized重锁   lock轻锁   //函数式接口可用
    {
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"张票"+"还剩下："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

//    public void sale()//高内聚 资源类自带的方法     synchronized重锁   lock轻锁   //函数式接口可用
//    {
//        if(number>0){
//            System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"张票"+"还剩下："+number);
//        }
//    }
}

@FunctionalInterface
interface  Foo
{
    //public void sayHello();
    public int add(int x,int y);//函数式接口可用

    public default int mul(int x,int y)
    {
        return x+y;
    }

    public static int div(int x,int y)
    {
        return x/y;
    }

}

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

class ReadWriteDemo  //把写锁了  写完  在读
{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "正在写"+key);
            try {TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写了了");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "正在读");
            try {TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) {e.printStackTrace();}
            Object res =map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完了"+res);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}

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

    public  static  void main(String[] args) throws Exception {//main线程   一切程序入口

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

        System.out.println(Runtime.getRuntime().availableProcessors());//查看核数
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

        JUC juc=new JUC();
        new Thread(()->{
            juc.myLock();
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace(); }
            juc.myUnlock();
        },"AA").start();

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace(); }

        new Thread(()->{
            juc.myLock();
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace(); }
            juc.myUnlock();
        },"BB").start();
    }

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

    private static void ewFixedThreadPool_demo() {

        /**
         *
         * 1、ArrayBlockingQueue：由数组结构组成的有界阻塞队列
         *
         * 2、LinkedBlockingQueue：由链表组成的有界(但大小默认值为Integer.MAX_Value)
         *
         * 3、PriorityBlockingQueue：支持优先级排序的无界阻塞队列
         *
         * 4、DelayQueue：使用优先级队列实现的延迟无界阻塞队列
         *
         * 5、SynchronizedQueue：不存储元素的阻塞队列，也即单个元素的队列
         *
         * 6、LinkedTransferQueue：由链表结构组成的无界阻塞队列
         *
         * 7、LinkedBlockingDeque：由链表结构组成的双向阻塞队列
         *
         * 其中橘色的三种是常用的。其中 LinkedBlockingQueue 和 SynchronizedQueue 是两个极端，SynchronizedQueue 是没有容量的阻塞队列，而 LinkedBlockingQueue 在未指定容量时可以看作是容量无穷大的阻塞队列。
         *
         *
         * 1：return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
         *
         *
         *
         *
         * 2： return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         *
         * 3：ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *  60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
         *
         *
         *  public ThreadPoolExecutor(int corePoolSize,          //线程池中常驻核心线程数
         *                               int maximumPoolSize,    //线程池中最多同时容纳最大线程数   必须大于0
         *                               long keepAliveTime,     //线程存活时间
         *                               TimeUnit unit,          //时间单位
         *                               BlockingQueue<Runnable> workQueue,  //阻塞队列  任务队列 被提交但尚未被执行的任务
         *                               ThreadFactory threadFactory,    //表示生成线程池中工作线程的线程工厂  用于创建线程  一般默认
         *                               RejectedExecutionHandler handler) {  //RejectedExecutionHandle 拒绝策略  当队列满了  工作线程大于等于最大线程数   拒绝再来的
         *         if (corePoolSize < 0 ||
         *             maximumPoolSize <= 0 ||
         *             maximumPoolSize < corePoolSize ||
         *             keepAliveTime < 0)
         *             throw new IllegalArgumentException();
         *         if (workQueue == null || threadFactory == null || handler == null)
         *             throw new NullPointerException();
         *         this.acc = System.getSecurityManager() == null ?
         *                 null :
         *                 AccessController.getContext();
         *         this.corePoolSize = corePoolSize;
         *         this.maximumPoolSize = maximumPoolSize;
         *         this.workQueue = workQueue;
         *         this.keepAliveTime = unit.toNanos(keepAliveTime);
         *         this.threadFactory = threadFactory;
         *         this.handler = handler;
         *     }
         *
         *
         */

        /**
         * 都不用   容易OOM
         *
        //ExecutorService threadPool= Executors.newFixedThreadPool(5);
        //线程池   nTreads  几个线程  类似银行多个窗口        固定  线程  池

        //ExecutorService threadPool= Executors.newSingleThreadExecutor();
        //线程池  一个  线程   类似一个窗口
        ExecutorService threadPool= Executors.newCachedThreadPool();
        //          自动扩容线程 不够就添加

         */


        ExecutorService threadPool= new ThreadPoolExecutor(
                2,
                5,

                /**
                 * 1、对于CPU密集型，也就是代码大部分操作都是CPU去执行计算处理的，
                 *                 不需要创建过多的线程，所以可以设置为 CPU核数+1
                 * 2、对于IO密集型，因为IO操作往往伴随着线程的线程的使用，
                 *                所以应该设置大一些，所以可以设置为 CPU核数*2
                 *                或者是  CPU核数/（1-阻塞系数）    阻塞系数一般是0.8-0.9
                 * System.out.println(Runtime.getRuntime().availableProcessors());//看你几核处理器
                 *
                 * 当LinkedBlockingDeque队列  满了 加到maximumPoolSize
                 * 当在自己定的时间 内  没事干了  会销毁多的
                 *
                 */

                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

                /*
                    new ThreadPoolExecutor.DiscardPolicy()//抛弃执行不了的
                    new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃等待最久的
                    new ThreadPoolExecutor.CallerRunsPolicy()// 会退回原有的线程
                    new ThreadPoolExecutor.AbortPolicy()  不够会抛异常 默认的
                */  //处理结果

          //java.util.concurrent.RejectedExecutionException   太多了  最多maximumPoolSize+queue
          //                      拒绝执行

        try{
            for (int i = 0; i < 10; i++) {
                final int k=i+1;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t干活"+k);
                });
                //做完了  腾出位置 可以在做
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }  //线程池   nTreads  几个线程  固定  线程  池

    private static void BlockingQueue_demoqueue() throws InterruptedException {

        /**
         * 1、ArrayBlockingQueue：由数组结构组成的有界阻塞队列  默认10
         *
         * 2、LinkedBlockingQueue：由链表组成的有界(但大小默认值为Integer.MAX_Value（21亿多)
         *
         * 3、PriorityBlockingQueue：支持优先级排序的无界阻塞队列
         *
         * 4、DelayQueue：使用优先级队列实现的延迟无界阻塞队列
         *
         * 5、SynchronizedQueue：不存储元素的阻塞队列，也即单个元素的队列
         *
         * 6、LinkedTransferQueue：由链表结构组成的无界阻塞队列
         *
         * 7、LinkedBlockingDeque：由链表结构组成的双向阻塞队列
         *
         * 其中橘色的三种是常用的。其中 LinkedBlockingQueue 和
         * SynchronizedQueue 是两个极端，
         * SynchronizedQueue 是没有容量的阻塞队列，
         * 而 LinkedBlockingQueue 在未指定容量时可以看作是容量无穷大的阻塞队列。
         *
         *
         */
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(1);

        /*
            System.out.println(blockingQueue.add("A"));
            //多于3个java.lang.IllegalStateException
            System.out.println(blockingQueue.remove());
            //把值取出来并删除 java.util.NoSuchElementException  没了还删  没法删异常
            System.out.println(blockingQueue.element());
            //把值取出来不删除 java.util.NoSuchElementException  没了还取  没法取异常

         */



        System.out.println(blockingQueue.offer("B0"));
        System.out.println(blockingQueue.offer("B1"));
        //添加  多于1个 返回false 不添加.
        System.out.println(blockingQueue.peek());//查看队头元素   不删除 没有返回null
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll());
        //删除  没有 返回false 不删除


        System.out.println(blockingQueue.offer("B",3L,TimeUnit.SECONDS));//等一定时间 在时间内没位置等这添加  到时间后结束
        System.out.println(blockingQueue.offer("B",3L,TimeUnit.SECONDS));


        blockingQueue.put("傻逼");//如果有位置  就添加  如果没 等着直到有
        blockingQueue.take();//取值  没了一直等着取
    }  //队列

    private static void ReadWriteLockDemo() {
        ReadWriteDemo readWriteDemo=new ReadWriteDemo();
        for (int i = 0; i < 10; i++) {
            final int temp=i+1;
            new Thread(()->{
                readWriteDemo.put(Thread.currentThread().getName(),temp+"");
            },String.valueOf(i+1)).start();
        }
        for (int i = 0; i < 10; i++) {
            final int temp=i+1;
            new Thread(()->{ //在Lambda表达式中  类型要用final
                readWriteDemo.get(Thread.currentThread().getName());
            },String.valueOf(i+1)).start();
        }
    }  //ReadWriteLock  //把写锁了  写完  在读

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

    private static void CountDownLatchDemo() throws InterruptedException {
        //      倒计时锁  计数  向下 门闩
        CountDownLatch countDownLatch=new CountDownLatch(6);//6次结束
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println("第" + Thread.currentThread().getName() + "走了");
                countDownLatch.countDown();//每做一次记录一次
            },CountryEnum.forEach_CountryEnum(i+1).getRetMessage()).start();//String.valueOf(i+1)
        }     //           枚举
        countDownLatch.await();//没结束等待
        System.out.println(Thread.currentThread().getName() + "关门");
    }//CountDownLatch 倒计时锁  控制线程 做完后在接下来运行

    private static void lambda() {
         /*
        Foo foo=new Foo() {
            @Override
            public void sayHello() {

            }
        };
        foo.sayHello();
         Foo foo=(int x,int y) -> {System.out.println("hello");return x+y;};

        */  //只能有一个函数式函数   lanmuda表达式

         Ticket ticket=new Ticket();
         new Thread(() ->{for(int i=0;i<40;i++)ticket.sale();},"A").start();
         new Thread(() ->{for(int i=0;i<40;i++)ticket.sale();},"B").start();
         new Thread(() ->{for(int i=0;i<40;i++)ticket.sale();},"C").start();
         //升级版

        /**
         new Thread(new Runnable() {
        @Override
        public void run() {
        for (int i = 0; i < 40; i++) {
        ticket.sale();
        }
        }
        }, "A").start();
         new Thread(new Runnable() {
        @Override
        public void run() {
        for (int i = 0; i < 40; i++) {
        ticket.sale();
        }
        }
        }, "B").start();
         new Thread(new Runnable() {
        @Override
        public void run() {
        for (int i = 0; i < 40; i++) {
        ticket.sale();
        }
        }
        }, "C").start();
         */  //低配版

    }   //一种 //在高内聚低耦合的前提下  线程  操作  资源类  // 资源类自带的方法     synchronized重锁   lock轻锁   //函数式接口可用

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
