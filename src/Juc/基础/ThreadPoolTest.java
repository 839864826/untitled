package Juc.基础;

import java.util.concurrent.*;

/**
 * 背景:经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，
 * 对性能影响很大。
 * 思路:提前创建好多个线程，放入线程池中，使用时直接获取，使用完
 * 放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交
 * 通工具。
 * ●好处:
 *      ➢提高响应速度(减少了创建新线程的时间)
 *      ➢降低资源消耗(重复利用线程池中线程，不需要每次都创建)
 *      ➢便于线程管理
 */
public class ThreadPoolTest {
    public static void main(String[] args) {

        ewFixedThreadPool_demo();
    }

    private static void ewFixedThreadPool_demo() {

        /**
         * 线程池参数
         * 1：return new ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>());
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
         * 系统给的线程池
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
                2,//核心线程数 == 固定线程数
                5,//最大线程数

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

                2L,//线程存活时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingDeque<>(3), //阻塞队列
                Executors.defaultThreadFactory(),//表示生成线程池中工作线程的线程工厂  用于创建线程  一般默认
                new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略  当阻塞队列满了  工作线程大于等于最大线程数时触发
        );



                /*
                    new ThreadPoolExecutor.DiscardPolicy()//抛弃执行不了的
                    new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃等待最久的
                    new ThreadPoolExecutor.CallerRunsPolicy()// 会退回原有的线程
                    new ThreadPoolExecutor.AbortPolicy()  不够会抛异常 默认的
                */  //处理结果

        //java.util.concurrent.RejectedExecutionException   太多了  最多maximumPoolSize+queue
        //                      拒绝执行

//        threadPool.execute(new MyRunnable3());//适用于Runnable
//        threadPool.submit(new MyCallable2());//适用于Callable
        //不用一个一个new Thread()了   直接放在threadPool里  他给你new Thread


//        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) threadPool;//强转一下   就可以用一些函数了
//        System.out.println(threadPoolExecutor.getPoolSize());//当前使用线程数
//        System.out.println(threadPoolExecutor.getCorePoolSize());//当前已有线程
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
            threadPool.shutdown();//关闭线程池
        }
    }  //线程池   nTreads  几个线程  固定  线程  池

    private static void BlockingQueue_demoqueue() throws InterruptedException {


        /**
         * 线程池用的阻塞队列
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

}
