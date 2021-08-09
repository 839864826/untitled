package Juc;

/**
 * 程序进程线程
 * ●程序(program)是为完成特定任务、用某种语言编写的一组指令的集合。即指一
 *      段静态的代码，静态对象。
 * ●进程(process)是程序的一次执行过程，或是正在运行的一个程序。是一个动态
 *      的过程:有它自身的产生、存在和消亡的过程。生命周期
 * ➢如:运行中的QQ，运行中的MP3播放器
 * ➢程序是静态的，进程是动态的
 * ➢进程作为资源分配的单位，系统在运行时会为每个进程分配不同的内存区域
 * ●线程(thread),进程可进一步细化为线程，是一个程序内部的一条执行路径。
 * ➢若一个进程同一时间并行执行多个线程，就是支持多线程的
 * ➢线程作为调度和执行的单位，每个线程拥有独立的运行栈和程序计数器(pc)，线程切换的开销小
 * ➢一个进程中的多个线程共享相同的内存单元/内存地址空间>它们从同一堆中分配对象，可以
 *    访问相同的变量和对象。这就使得线程间通信更简便、高效。但多个线程操作共享的系统资
 *    源可能就会带来安全的隐患。
 */

/**
 * ●单核CPU和多核CPU的理解
 * ➢单核CPU，其实是-种假的多线程，因为在-一个时间单元内，也只能执行一个线程
 *  的任务。例如:虽然有多车道，但是收费站只有一一个工作人员在收费，只有收了费
 *  才能通过，那么CPU就好比收费人员。如果有某个人不想交钱，那么收费人员可以
 *  把他“挂起”(晾着他， 等他想通了，准备好了钱，再去收费)。但是因为CPU时
 *  间单元特别短，因此感觉不出来。
 * ➢如果是多核的话， 才能更好的发挥多线程的效率。(现在的服务 器都是多核的)
 * ➢一个Java应用程序java.exe，其实至少有三个线程: main()主线程， gc()
 *  垃圾回收线程，异常处理线程。当然如果发生异常，会影响主线程。
 * ●并行与并发
 * ➢并行:多个CPU同时执行多个任务。比如:多个人同时做不同的事。
 * ➢并发: 一个CPU(采用时间片)同时执行多个任务。比如:秒杀、多个人做同一件事.
 */
public class Main {
    public static void main(String[] args) {

    }
}
