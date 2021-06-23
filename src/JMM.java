
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * 1.volatile  是Java虚拟机提供的轻量级的同步机制
 *     1.1：保证可见性     :   所有线程有自己的工作内存  操作main内存时 先复制再操作 再写入  通知其他线程
 *     1.2：不保证原子性   :   先复制再操作 再写入 中间有时间差 可能会有别的线程操作过了
 *     1.3：不允许重排列   :   在volatile变量进行写操作时加入store屏障指令   StoreStore  volatile写       StoreLoad
 *                          在volatile变量进行读操作时加入Load屏障指令      volatile读   LoadLoad      LoadStore
 *
 * 2.JMM   java内存模型//一种规范
 *    2.1：可见性        线程结束之前 ，必须刷新到主内存
 *    2.2：原子性        线程加锁之前，必须重新读取主内存到自己的工作内存
 *
 *    2.3：有序性
 *
 *
 *
 *
 *
 *
 *
 */

class mydata {//MyData.java->MyData.class->jvm字节码
    volatile int number=0;
    public void add(){this.number=60;}
    public  void addplus(){ ++number;}
    AtomicInteger atomicInteger=new AtomicInteger();//原子性的int
    public  void atomic(){atomicInteger.getAndIncrement();}//i++
    //凭啥能不加锁实现原子性   它使用unsafe（系统原语  不可能被打断） 也用到了CAS（比较并交换）
}

class ReSortSeqDemo{
    int a=0;
//    AtomicInteger a=new AtomicInteger(0);//原子性的int
    boolean flag =false;
    public void me1(){
//        a.compareAndSet(a.get(),1);
        a=1;
        flag=true;
    }
    public void me2(){
        if(flag){
            a+=5;
//            a.getAndAdd(5);
            System.out.println(a);
        }
    }
}

public class JMM {    //java内存模型  //主类

    public static void main(String[] args) {


        AtomicStampedReference_ABA();
    }

    private static void AtomicStampedReference_ABA() {
        AtomicReference<Integer> A=new AtomicReference<>(1); //原子引用
        AtomicStampedReference<Integer> a=new AtomicStampedReference<>(1,1);
        System.out.println("=============以下是ABA问题的产生=========");

        new Thread(()->{
            A.compareAndSet(1,2);
            A.compareAndSet(2,1);
        },"t1").start();
        new Thread(()->{
            try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}
            System.out.println(A.compareAndSet(1, 11)+"\t"+A.get());
        },"t2").start();
        try{TimeUnit.SECONDS.sleep(2);}catch (InterruptedException e){e.printStackTrace();}

        System.out.println("=============以下是ABA问题的解决=========");

        new Thread(()->{
            int stamp = a.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号"+stamp+"\t第一次的值"+a.getReference());
            try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}

            a.compareAndSet(1,2,a.getStamp(),a.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号"+a.getStamp()+"\t第二次的值"+a.getReference());
            a.compareAndSet(2,1,a.getStamp(),a.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本号"+a.getStamp()+"\t第三次的值"+a.getReference());
            },"t3").start();
        new Thread(()->{
            int stamp = a.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号"+stamp+"\t第一次的值"+a.getReference());
            try{TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e){e.printStackTrace();}
            boolean k=a.compareAndSet(1,3,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改"+k+"当前版本号"+a.getStamp()+"\t当前值"+a.getReference());
            System.out.println(Thread.currentThread().getName()+"当前值"+a.getReference());
            },"t4").start();
    }

    private static void compareAndSet_CAS_Demo() {
        AtomicReference<User> userAtomicReference=new AtomicReference<>(); //原子引用
        /**
         *   compareAndSet（CAS）比较并交换   用的是Unsafe原语 不可被打断
         *   public final int getAndAddInt(Object o, long offset, int delta) {
         *         int v;
         *         do {
         *             v = getIntVolatile(o, offset);
         *         } while (!weakCompareAndSetInt(o, offset, v, v + delta));
         *         return v;
         *     }
         *
         *     用do{}while();自旋锁
         *     CAS缺点:
         *         如果CAS一直不成功  循环时间长  会让cpu开销大
         *         只能保证一个变量
         *         会引发ABA问题:
         *
         *
         */
        AtomicInteger atomicInteger=new AtomicInteger(5);
        atomicInteger.getAndIncrement();//凭啥能不加锁实现原子性   它使用unsafe（系统原语  不可能被打断） 也用到了CAS
        System.out.println(atomicInteger.compareAndSet(5, 55)+"变成"+atomicInteger);//比较  如果比较相等就交换
    }

    private static volatile JMM instance = null; //加volatile禁止指令重排
    private JMM() {
        System.out.println(Thread.currentThread().getName() + "\t我是构造方法");

    }
    public static JMM getInstance() {
        if(instance == null){
            synchronized (JMM.class){   //锁加在这里  在多线程下有可能指令重排 会先执行return
                instance = new JMM();
            }
        }
        return instance;
    }
    private static void 构造函数_的多线程应用() {
        System.out.println(JMM.getInstance() == JMM.getInstance());
        System.out.println(JMM.getInstance() == JMM.getInstance());
        System.out.println(JMM.getInstance() == JMM.getInstance());

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(JMM.getInstance());
            },String.valueOf(i+1)).start();
        }
    }

    private static void volatile_sort_demo() {
        int x=11;   //1
        int y=12;   //2
        y+=x*x;      //3
        x=x+5;      //4
        //有可能1234     1243   21**
        System.out.println(x+"       "+y);


        ReSortSeqDemo reSortSeqDemo=new ReSortSeqDemo();
        for (int i = 0; i < 100; i++) {

            new Thread(()->{
                reSortSeqDemo.me1();
                reSortSeqDemo.me2();
            },String.valueOf(i+1)).start();
        }
    }

    private static void AtomDemo() {
        mydata my =new mydata();

        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                for (int j = 1; j <= 1000; j++) {
                    my.addplus();my.atomic();
                }
                },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){ Thread.yield();}//线程个数

        System.out.println(Thread.currentThread().getName()+"\t        int     "+my.number);

        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger  "+my.atomicInteger);
    }        //volatile  不保证原子性

    private static void volatile_lookx() {
        mydata my=new mydata();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){ e.printStackTrace();}
            my.add();
            System.out.println(Thread.currentThread().getName()+"\t come in"+my.number);
        },"A").start();

        while (my.number==0){}
        System.out.println(Thread.currentThread().getName()+"\t over   "+my.number);
    }  //volatile  保证可见性

}
