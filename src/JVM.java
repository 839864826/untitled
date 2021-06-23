//import com.sun.jdi.connect.Connector;
//import sun.tools.jps.Arguments;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.*;
//import net.sf.cglib.proxy.*;
/**
 *  1.JVM系统架构图(Java Virtual Machine)
 *
 * 2.类装载器   classLoader   (负责)cafe babe
 * 虚拟机自带加载器：
 * 2.1启动类加载器 （Bootstrap）  c++（根加载器）
 * 2.2扩展类加载器（Extension)   java  PlatformClassLoader
 * 2.3应用程序类加载器 （AppClassLoader）
 *
 * 用户自定义加载类
 * Java.lang.ClassLoader 的子类
 *
 * 2.4.1   有哪几种累加载器  如上
 * 2.4.2   双亲委派机制  如java.lang.String   先从启动类加载器 （Bootstrap）->扩展类加载器（Extension)->
 * 应用程序类加载器 （AppClassLoader）
 * 2.4.3   沙箱安全机制
 *
 * 3.Native 方法表示本地方法库 调用c/c++的程序  但现在越来越少 NativeThread
 *    3.1  Native是关键字
 *    3.2   有声明  没实现  调用c/c++的程序  但现在越来越少 NativeThread
 *
 *
 *
 * 4.PC寄存器   程序计数器     native 计数器为零   所需的内存很少  线程私有  无垃圾回收
 *    4.1  记录了方法之间的调用和执行情况  类似排版值日表
 *    用来存储下一条指令的地址   也是即将要执行的指令代码
 *    他是当前线程所执行的字节码的行号指示器
 *
 * 5. 方法区  线程共享  有垃圾回收
 *      5.1 他存储了每一个类的结构信息
 *      5.2 方法去是规范 在不同虚拟机里头实现是不一样的 ，
 *      最典型的是永久代（PermGen  space）和元空间(mentaspace)
 *
 *
 * *********************************
 * 程序 =算法+数据结构
 * 程序 = 框架+业务逻辑
 * 队列（FIFO）先进先出
 * 栈（FILO）先进后出
 * java方法   叫栈帧
 * **********************************
 * 6. stack
 *    6.1 栈（stack）管运行  //内存小  线程 无垃圾回收
 *        堆(heap)管存储    //内存大  有垃圾回收
 *    6.2 栈保存的：
 *        8种基本类型的变量  +对象的引用变量   +实例方法都是在函数的栈
 *    6.3  Exception in thread "main" java.lang.StackOverflowError  //Error 错误不是异常
 *          栈内存有限  递归死循环内存撑爆了           堆    溢出    错误
 *                     <
 *                    |  1/3 1: 新生区(Young)           new    Eden ：GC=YGC=轻GC  满了
 *                    |       8份   1.1:  伊甸区（Eden Space）满了 （死了一大部分（活得年龄+1））->
 *                    |       1份   1.2:  幸存者0区(from) （每一次GC 谁存活放在from） ->
 *                    |       1份   1.3:  幸存者1区(to（谁空谁是to）)   （年龄为15的） ->养老区
 *      7 堆 heap    <   2/3 2：养老区(old) （在满了）   Full GC=FGC  （筛选(全筛选包括young)）     一直满    实在没办法
 *                    |            出现OOM异常(java.lang.OutOfMemoryError)堆内存不足
 *                    |
 *                    |     3：永久代 /元空间（其实在heap 但他自己不认为） //java8 以后原空间不在jvm了  在使用本机物理机
 *                    <
 *     8.heap-->对象的生命周期---》OOM(java.lang.OutOfMemoryError) 内存不足错误
 *        更改java的虚拟机默认最大内存 -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 *        GC算法：
 *              1.引用计数法          //不咋用  缺点 每次赋值都要引用计数起麻烦    无法循环
 *              2.复制算法（Copying） //常用算法   是年轻代用的垃圾回收算法    优点：不会产生内存碎片    缺点：费空间
 *                 2.1  55行 可以调年龄（默认15）
 *              3.标记清除（Mark-Sweep）  //用在老年代    优点：节约空间    缺点：产生内存碎片  扫描2次  耗时长
 *              4.标记压缩（标记整理）（Mark-Compact）//  老年代（多次清除时在压缩）  缺点：耗时最长（标记清除+整理）
 *
 *
 *
 *
 */

/**
 *  GC
 *  怎样确定垃圾
 * 怎样确定他是垃圾 :内存已经不再使用
 *              1.引用计数法 用到它+1   不用-1   到0是垃圾
 *              2.从GC Roots  为起点  引用不可达  则为垃圾    应用可达   不是垃圾
 *
 * 谁可以成为GC Roots:   1.虚拟机栈
 *                     2.方法区中的类静态属性引用的对象
 *                     3.方法区中常量引用的对象
 *                     4.本地方法栈JNI（Native方法）引用的对象
 *
 * 4种垃圾回收器
 *     1.Serial（串行）//它为单线程环境设计且只使用一个线程进行垃圾回收，会暂停所有的用户线程。所以不适合服务器环境
 *        -XX:+UseSerialGC
 *     2.Parallel（并行）//多个垃圾收集线程并行工作，此时用户线程是暂停的，适用于科学计算/大数据处理首台处理等弱交互场景
 *        -XX:+UseParallelGC
 *     3.CMS（并发）//用户线程和垃圾收集线程同时执行(不一定是并行，可能交替执行)，不需要停顿用户线程
 *                 //互联网公司多用它，适用对响应时间有要求的场景
 *         -XX:+UseConcMarkSweepGC
 *         1.初始标记(CMS initial mark)
 *         2.并发标记(CMS concurrent mark)和用户线程一起
 *         3.重新标记(CMS remark)
 *         4.并发清除(CMS concurrent sweep)和用户线程一起
 *         优点:并发收集底停顿
 *         缺点:对CPU的资源压力大，标记清除有大量碎片
 *         -XX:CMSFullGCsBeForeCompaction(默认0， 即每次都进行内存整理（标记压缩）)
 *     4.G1//将堆内存分割成不同区域  分别进行CMS（并发）
 *       XX:+UseG1GC
 *     5.ZGC(0..jdk11及以后）
 *
 * UseSeriaOldlGC//以前有  废弃了
 * UseParNewGC//并行年轻代GC
 * UseParallelOldGC//并行老年代GC
 *   -XX:+UseParallelGC===-XX:+UseParallelOldGC//互相激活
 *
 * java -XX:+PrintCommandLineFlags -version//查看默认设置
 *
 *
 * 7种垃圾回收器
 * Young Gen(新生代)：
 *      1.Serial Copying
 *          Serial(串行收集器)  //会暂停其他一切工作线程
 *          -XX:+UseSerialGC  //开启  自动激活Old的1
 *      2.Parallel Scavenge：-XX:+UseParallelGC
 *          自动激活 老年代2
 *      3.ParNew：-XX:+UseParNewGC
 *          自动激活Old的1   也会暂停其他一切工作线程 只是时间短
 *      4.G1
 * Old Gen(老年代)：
 *      1.Serial Msc（Serial Old）（Tenured）//以前有  废弃了
 *      2.Parallel Compacting（Parallel Old）//自动激活 新生代2
 *      3.CMS
 *      4.G1
 *
 *   G1:
 *      是一种面向服务端的垃圾收集器
 *      应对多处理器和大容量的内存       在实现高吞吐量的同时 尽可能的满足垃圾收集暂停时间的要球
 *      目的是取代CMS收集器
 *      G1是一个由整理内存过程的垃圾收集器不会产生很多内存碎片
 *      G1的Stop The  World(STW)更可控，G1在停顿时间上添加了预测机制，用户可以指定期望停顿时间。
 *
 *      1: G1能充分利用多CPU、多核环境硬件优势，尽量缩短STW。
 *      2: G1整体正采用标记-整理算法，局部是通过复制算法，不会产生内存碎片。
 *      3: 宏观上看G1之中不再区分年轻代和老年代。把内存划分成多个独立的子区域(Region), 可以近似理解为一个围棋的棋盘。
 *      4: G1收集器里面讲整个的内存区都混合在一起了，但其本身依然在小范围内要进行年轻代和老年代的区分，保留了新生代和老年代，
 *         但它们不再是物理隔离的，而是一部分Region的集 合且不需要Region是连续的，也就是说依然会采用不同的GC方式来处理不同的区域。
 *      5: G1虽然也是分代收集器，但整个内存分区不存在物理上的年轻代与老年代的区别，也不需要完全独立的survivor(to space)堆做复制
 *         准备。G1只有逻辑上的分代概念，或者说每个分区都可能随G1的运行在不同代之间前后切换;
 *
 *      -XX:G1HeapRegionSize=n可指定分区大小（1-32  MB）  但必须是2的幂
 *
 *      Region区域化垃圾收集器分Eden  Survivor  Old  Humongous(大对象)
 *
 *   G1:四部
 *         1.初始标记:只标记  GC Roots能 直接关联到的对象
 *         2.并发标记:进行    GC Roots Tracing的过程
 *         3.最终标记:修正并发标记期间，因程序运行导致标记发生变化的那一部分对象
 *         4.筛选回收:根据时间来进行价值最大化的回收
 *
 * -XX:+UseG1GC
 * -XX:G1HeapRegionSize=n:设置的G1区域的大小。值是2的幂，范围是1MB到32MB。目标是根据最小的Java堆大
 * -XX:MaxGCPauseMillis=n:最大Gc停顿时间，这是i个软目标，JVM将尽可能(但不保证)停顿小于这个时间
 * -XX:inititingHeapOccupancyPercent=n:堆占用了多少的时候就触发GC，默认为45
 * -XX:ConcGCThreads=n: 并发Gc使用的线程数
 * -XX:G1ReservePercent=n:设置作为空闲空间的预留内存百分比，以降低目标空间溢出的风险，默认值是10%
 *
 *
 *
 *
 * 32位Window操作系统，不论硬件如何都默认使用Client的JVM模式
 * 32位其它操作系统，2G内存同时有2个cpu以上用Server模式，低于该配置还是Client模式
 * 64位only server模式。
 * server模式:
 *
 *
 */

/**
 *  第一种擦看参数盘点家底
 * jps(查看java后台进程) -l
 * jinfo(查看java 具体进程详细信息) -flag  -flags(查看所有)
 *        PrintGCDetails        线程号//是否开启垃圾回收
 *        MetaspaceSize         线程号//原空间大小
 *        MaxTenuringThreshold  线程号//进入养老区计数多少
 * 更改java的虚拟机默认最大内存 -Xms1024m(初始堆内存) -Xmx1024m(最大堆内存) -XX:+PrintGCDetails（开启垃圾回收细节） -XX:MetaspaceSize=1024M(设置原空间大小)
 *   JVM参数类型:  1.标配参数 java     -version(java版本)和 -help
 *               2.x参数（不重要）  java
 *                           -Xint(解释执行)
 *                           -Xcomp(第一次使用就编译成本地代码)
 *                           -Xmixed(混合模式)
 *               3.xx参数
 *                   1.Boolean  -XX:+(表开启) -(表关闭)
 *  路径>jps -l
 * 11440 org.jetbrains.jps.cmdline.Launcher
 * 11456
 * 14644 JVM
 * 4900
 * 11672 org.jetbrains.jps.cmdline.Launcher
 * 14856
 * 6812 sun.tools.jps.Jps
 *
 * 路径>jinfo -flag PrintGCDetails 14644
 * -XX:+PrintGCDetails
 *
 *
 *
 *
 *
 *
 *
 *
 */

/**
 *  第二种查看参数盘点家底
 *  java -XX:+PrintFlagsInitial            //查看所有默认    =默认
 *  java -XX:+PrintFlagsFinal -version     //查看所有修改   :=是修改过的或者是自己修改过
 *  java -XX:+PrintCommandLineFlags -version  //查看默认的垃圾回收器
 *
 */

/**
 * 常用参数:

 * -Xms  等价于-XX:initialHeapSize //初始大小内存  默认为物理内存的1/64
 * -Xmx  等价于-XX:MaxHeapSize     //最大分配内存  默认为物理内存的1/4
 * -Xss  等价于-XX:ThreadStackSize //单个线程栈的大小  一般默认为513k-1024k
 *       默认显示0  大小：Linux/x64(64-bit):1024k  OS X(64-bit):1024k Oracle Solaris/x64(64-bit):1024k
 * -Xmn       //新生代大小
 * -XX:MetaspaceSize   //原空间大小  默认20多m
 *
 * -XX:+UseSerialGC     串行垃圾回收器//默认并行
 * -XX:+UseParallelGC   并行垃圾回收器
 * -XX:+PrintGCDetails  查看垃圾回收细节
 * -XX:SurvivorRatio   设置新生代中eden和S0/S1空间的比例
 *     默认-XX:SurvivorRatio=8,   Eden:S0:S1=8:1:1
 *        -XX:SurvivorRatio=4,   Eden:S0:S1=4:1:1
 *     SurvivorRatio得值是设置eden的比例
 * -XX:NewRatio    设置新生代和老年代的比例
 *     默认-XX:NewRatio=2      新生代:老年代=1:2
 *        -XX:NewRatio=4      新生代:老年代=1:4
 * -XX:MaxTenuringThreshold   设置最大年龄数
 *     默认-XX:MaxTenuringThreshold=15(必须0-15)
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSiz=8m//设置原空间大小
 * -XX:MaxDirectMemorySize=5m  //最大直接内存化   即最大使用的内存
 *
 */

/**
 * 强引用软引用弱引用虚引用
 * 强引用:就算出现OOM也不会进行垃圾回收
 * 软引用:内存不足才回收
 * 弱引用:只要GC就回收
 * 虚引用:形同虚设  必须和引用队列(ReferencesQueue)联合使用
 *       主要作用:跟踪对象被垃圾回收的状态
 *              仅仅提供了一种确保对象被finalize以后做的某些事情的机制
 *        get方法总是NULL
 */

/**
  * OOM 内存不足异常
 * java.lang.StackOverflowError//栈溢出
 * java.lang.OutOfMemoryError:Java heap space//堆溢出
 * java.lang.OutOfMemoryError: GC overhead limit exceeded//GC效率太低
 * java.lang.OutOfMemoryError:Direct buffer memory//直接缓冲区内存溢出
 * java.lang.OutOfMemoryError:unable to create new native thread//linux里  一个进程默认1024
 * java.lang.OutOfMemoryError:Metaspace//原空间溢出  spring重用
 *
 * java.lang.OutOfMemoryError: Overflow: String length out of range//字符串长度溢出
 *
 */



public class JVM {//java 虚拟机


    static class OOMTest{ }
    public static void main(String[] args)  {//main线程   一切程序入口  //默认有一个GC线程

        PhantomReferenceDemo();

    }

    private static void OOM_Java_heap_space() {
        String str = "java.lang.OutOfMemoryError : Java heap space";
        while (true){
            str+= str+new Random().nextInt(11111111)+new Random().nextInt(2222222);
            str.intern();
        }
    }

    private static void OutOfMemoryError_unable_to_create_new_native_thread() {
        /**
         * 在linux中才行
         * 一般关不了
         *
         * 1. ps -ef|grep java//查看Java进程
         * 2.kill -9 进程号//杀死进程
         *
         * 增加linux的进程默认线程数（1024）
         * 非root用户1024  root无上限
         * 1.u limit -u//查看默认进程默认线程数（1024）
         * 2.vim /etc/security/limits.d/90-nproc.conf//打开文件修改
         *
         *
         */
        for (int i = 0; ; i++) {
            System.out.println("*********i=" + i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }  //进程中线程太多 溢出

    private static void OutOfMemoryError_Direct_buffer_memory() {
        /**
         * JDK8  16没了
         */
        //System.out.println("查看配置的本地内存maxDirectMemory:"+(sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) +"MB");
        //停顿一下方便看效果
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //-XX:MaxDirectMemorySize=5m 本地内存配置的是5MB,这里实际使用的是6MB
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
        /**
         * -Xms10m -Xmx10m -XX:+PrintGCDetails  -XX:MaxDirectMemorySize=5m
         * 创建Buffer对象时，可以选择从JVM堆中分配内存，也可以OS本地内存中分配，
         * 由于本地缓冲区避免了缓冲区复制，在性能上相对堆缓冲区有一定优势，但同时也存在一些弊端。
         *
         * 两种缓冲区对应的API如下：
         *      JVM堆缓冲区：ByteBuffer.allocate(size)
         *      本地缓冲区：ByteBuffer.allocateDirect(size)
         * 从堆中分配的缓冲区为普通的Java对象，生命周期与普通的Java对象一样，
         * 当不再被引用 时，Buffer对象会被回收。
         * 而直接缓冲区（DirectBuffer）为本地内存，并不在Java堆中，也不能被JVM垃圾回收。
         * 由于直接缓冲区在 JVM里被包装进Java对象DirectByteBuffer中，
         * 当它的包装类被垃圾回收时，会调用相应的JNI方法释放本地内存，
         * 所以本地内存的释放 也依赖于JVM中DirectByteBuffer对象的回收。
         *
         *
         * 由于垃圾回收本身成本较高，一般JVM在堆内存未耗尽时，不会进行垃圾回收操作。
         * 我们知道在32位机器上，每个进程的最大可用内存为4G，
         * 用户可用 内存在大概为3G左右，如果为堆分配过大的内存时，
         * 本地内存可用空间就会相应减少。当我们为堆分配较多的内存时，
         * JVM可能会在相当长的时间内不会进行垃 圾回收操作，从而本地内存不断分配，
         * 无法释放，最终导致OutOfMemoryError。
         *
         *
         * 由此可见，在使用直接缓冲区之前，需要做出权衡：
         *      1.堆缓冲区的性能已经相当高，若无必要，使用堆缓冲区足矣。
         *        若确实有提升性能的必要时，再考虑使用本地缓冲区。
         *      2.为JVM分配堆内存时，并不是越大越好，堆内存越大，本地内存就越小，
         *        根据具体情况决定，主要针对32位机器，64位机器上不存在该问题。
         *
         */
//        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        //ByteBuffer buffer = ByteBuffer.allocateDirect( 6 * 1024 * 1024 );//jvm以外 分配内存（总的1/4）以内
//        ByteBuffer allocate = ByteBuffer.allocate();//jvm内
    } //直接缓冲区内存  JDK8会出现   16暂时不知道

    private static void OutOfMemoryError_GC_overhead_limit_exceeded() {
        /**
         * 配置
         * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC（并行垃圾回收）
         *  -XX:MaxDirectMemorySize=5m  //最大直接内存化   即最大使用的内存 可有可无
         *
         *  OutOfMemoryError是java.lang.VirtualMachineError的子类，
         *  当JVM资源利用出现问题时抛出，
         *  更具体地说，这个错误是由于JVM花费太长时间执行GC且只能回收很少的堆内存时抛出的。
         *  根据Oracle官方文档，默认情况下，
         *  如果Java进程花费98%以上的时间执行GC，
         *  并且每次只有不到2%的堆被恢复，
         *  则JVM抛出此错误。换句话说，这意味着我们的应用程序几乎耗尽了所有可用内存，
         *  垃圾收集器花了太长时间试图清理它，并多次失败。
         *
         *
         */
        int i=0;
        List<String> list=new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("**************");
            e.printStackTrace();//Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            throw  e;
        }
//        Map<Integer, String> dataMap = new HashMap<>();
//        Random r = new Random();
//        while (true) {
//            dataMap.put(r.nextInt(), String.valueOf(r.nextInt()));
//        }
    }//如果Java进程花费98%以上的时间执行GC，并且每次只有不到2%的堆被恢复，

    private static void OutOfMemoryError_Java_heap_space() {
        /**
         * 配置
         * -Xms10m -Xmx10m -XX:+PrintGCDetails
         */
//        String str = "java.lang.OutOfMemoryError";
//        while (true){
//            str+= str+new Random().nextInt(11111111)+new Random().nextInt(2222222);
//            str.intern();
//        }
        byte[] bytes=new byte[80*1024*1024];
    }  //堆溢出

    private static void stackOverflowError() {
        stackOverflowError();
    }//栈溢出错误

    private static void PhantomReferenceDemo() {
        Object o1=new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();//引用队列  在被GC垃圾回收后会有值
        WeakReference<Object> weakReference=new WeakReference<>(o1,referenceQueue);
        PhantomReference<Object> phantomReference=new PhantomReference<>(o1,referenceQueue);//虚引用
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("================");
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    } //虚引用

    private static void WeakHashMapDemo() {
        /**
         * 对象比较
         * Integer i=new Integer(50);
         * Integer j=new Integer(50);
         * System.out.println(i==j);  //运行的结果是false
         * Integer i=new Integer.valueOf(100);
         * Integer j=new Integer.valueOf(100);
         * System.out.println(i==j);  //运行的结果是true
         * Integer i=new Integer.valueOf(400);
         * Integer j=new Integer.valueOf(400);
         * System.out.println(i==j);//运行结果是false
         * Integer i=100;
         * Integer j=100;
         * System.out.println(i==j);//运行结果是true
         * 然后再用400这个数试一试，通过实验运行的结果是false，
         *public static Integer valueOf(int i){
         * if(i>=-128&&i<=IntegerCache.high)
         * return IntegerCache.cache[i+128];
         * else return new Integer(i);
         * }
         * 通过看源码能够知道整数类型在-128~127之间时，会使用缓存。
         * 造成的效果就是，如果已经创建了一个相同的整数，
         * 使用valueOf创建第二次时，不会使用new 关键字，
         * 而是用已经缓存的对象。所以使用valueOf方法创建两次对象，
         * 若对应数值相同，且数值在-128~127之间时，两个对象指向同一个地址。
         * 使用Integer i=400这样的方法创建Integer对象与使用valueOf方法的效果是一样的,
         * 若要比较，使用compareTo或者equals方法是更好的
         */
        System.out.println("=========HashMap============");
        HashMap<Integer,String> map=new HashMap<>();
        Integer key1 = new Integer(1);
        String value1 = "HashMap";
        map.put(key1,value1);
        System.out.println(map);
        key1=null;
        System.out.println(map);
        System.gc();
        System.out.println(map);

        System.out.println("=========WeakHashMap============");
        WeakHashMap<Integer,String> weakHashMap=new WeakHashMap<>();
        Integer key2 = new Integer(1);

        String  value2 = "WeakHashMap";
        weakHashMap.put(key2,value2);
        System.out.println(weakHashMap);
        key2=null;
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap);
    }    //弱引用 GC直接回收
    private static void WeakReferenceDemo() {
        Object o1=new Object();
        WeakReference<Object> weakReference=new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1=null;
        System.gc();
        System.out.println("================");
        System.out.println(o1);
        System.out.println(weakReference.get());
    }  //弱引用 GC直接回收

    private static void SoftReference_Enough(){
        /**
         * 软引用在内存足的时候不回收
         */
        Object o1=new Object();
        SoftReference<Object> softReference=new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    } //软引用在内存足的时候不回收
    private static void SoftReference_NotEnough() {
        /**
         * -Xms5m -Xmx5m -XX:+PrintGCDetails
         */
        Object o1=new Object();
        SoftReference<Object> softReference=new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1=null;
        System.gc();
        try {
            byte[] bytes=new byte[30*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }


    } //软引用在内存不足的时候回收

    private static void ReferenceDemo() {
        Object obj1=new Object();
        Object obj2=obj1;
        System.out.println(obj1);
        obj1=null;
        System.gc();
        System.out.println(obj1);
        System.out.println(obj2);
    }  //强引用

    private static void heap_xms_xmx() {
        System.out.println(Runtime.getRuntime().availableProcessors());//看你几核处理器
        long maxMemory=Runtime.getRuntime().maxMemory();//返回Java虚拟机默认试图使用的最大内存   物理内存的1/4
        long totalMemory =Runtime.getRuntime().totalMemory();//返回Java虚拟机总容量       物理内存的1/64
        System.out.println("Xmx:"+maxMemory+"     "+(maxMemory)/(double)1024/1024+"MB");
        System.out.println("Xms:"+totalMemory+"     "+(totalMemory)/(double)1024/1024+"MB");
    }   // 虚拟机默认最大内存

    public  void changeValue1(int  age){
        age=30;

    }        //传的是复制品
    public void changeValue2(Person person){
        person.setPersonName("xxx");

    }    //传的是地址
    public void changeValue3(String str){
        str="xxx";

    }       //String自动找新的空间
    private static void StackDrom() {
        JVM jvm=new JVM();

        int age=20;
        jvm.changeValue1(age);
        System.out.println(age);

        Person person =new Person("abc");
        jvm.changeValue2(person);
        System.out.println(person.getPersonName());

        String str="abc";
        jvm.changeValue3(str);
        System.out.println(str);
    }           //堆得分层

    public static void m1()
    {
        m1();
    }//Exception in thread "main" java.lang.StackOverflowError  //Error 错误不是异常栈内存有限  递归死循环内存撑爆了

    private static void NativeThread() {
        Thread ti=new Thread();
        ti.start();
        //ti.start();//java.lang.IllegalThreadStateException start两次不可以
    }//Native 方法表示本地方法库 调用c/c++的程序  但现在越来越少 NativeThread

    private static void classjzq() {
        Object object =new Object();
        //System.out.println(object.getClass().getClassLoader().getParent().getParent());
        //System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());//根加载器 无

        JVM jvm=new JVM();

        System.out.println(jvm.getClass().getClassLoader().getParent().getParent());//jvm的上上一个装载
        System.out.println(jvm.getClass().getClassLoader().getParent());//jvm的上一个装载器
        System.out.println(jvm.getClass().getClassLoader());//jvm是谁装载的
    } //类装载器

}
