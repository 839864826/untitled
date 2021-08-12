package Juc.高级;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteDemo  //把写锁了  写完  在读
{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "正在写"+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) {e.printStackTrace();}
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
public class 读写锁 {
    public static void main(String[] args) {
        ReadWriteLockDemo();
    }

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

}
