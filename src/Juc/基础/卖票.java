package Juc.基础;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@FunctionalInterface
interface  Foo {
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

class Ticket {
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

}//资源类      =实例+方法

public class 卖票 {
    public static void main(String[] args) {
        lambda();
    }
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

        /*
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

}