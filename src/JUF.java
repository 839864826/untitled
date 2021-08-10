import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
class User
{
    private Integer id;
    private String  username;
    private int     age;
}

class MyTask extends RecursiveTask<Integer>
{

    public static final Integer ADJUST_VALUE = 10;
    private  int begin;
    private  int end;
    private  int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute(){
        if(end - begin<=ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result+=i;
            }
        }else{
            int middle=(end+begin)/2;
            MyTask task01=new MyTask(begin,middle);
            MyTask task02=new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result=task01.join()+task02.join();
        }
        return result;
    }
}//把大问题   分割成一堆小问题


class Mythrend2 implements Callable<Integer>
{
    AtomicInteger atomicInteger=new AtomicInteger(10);//原子性的int
    @Override
    public Integer call() throws Exception//会抛异常
    {
        System.out.println("****come in call method()");
        TimeUnit.SECONDS.sleep(1);
        return 1024;
    }//只会执行一次
}  //Callable<Integer>的高手有返回值


class HoldLockThread implements Runnable
{
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
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockB + "\t尝试获得" + lockA);

            }
        }
    }
}  //产生死锁


/**
 *
 * Stream自己不会存储
 * Stream  流式计算 延迟执行
 * 集合讲的是数据   流讲的是计算
 *
 *
 * ForkJoinPool
 * ForkJoinTask
 *
 *
 */
public class JUF {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallableDemo();

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

    private static void CompletableFutureDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值");
        });
        completableFuture.get();


        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有  返  回");
            //int age=10/0;
            return 1024;
        });
        System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("****t" + t);
            System.out.println("****u" + u);
        }).exceptionally(f -> {
            System.out.println("excpyion" + f.getMessage());
            return 4444;
        }).get());
    }

    private static void ForkJoin_Demo() throws InterruptedException, ExecutionException {

        MyTask myTask=new MyTask(0,100);

        ForkJoinPool forkJoinPool =new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        System.out.println(forkJoinTask.get());
    }
    //分支合并框架

    private static void CallableDemo() throws InterruptedException, ExecutionException {
        /**
         * 第二种  多线程  有返回值！！！！   只能在一个线程跑   要摸在new另一个
         *class Mythrend2 implements Callable<Integer>
         * {
         *     @Override
         *     public Integer call() throws Exception
         *     {
         *         System.out.println("****come in call method()");
         *         return 1024;
         *     }
         * }
         *
         */

        /*
        FutureTask<Integer> futureTask1 = new FutureTask(new Mythrend2());//只能执行一次
        FutureTask<Integer> futureTask2 = new FutureTask(new Mythrend2());

        new Thread(futureTask1,"A").start();
        new Thread(futureTask1,"B").start();
        new Thread(futureTask2,"C").start();


        System.out.println(Thread.currentThread().getName()+"计算完毕");
        Integer result =futureTask1.get();
        System.out.println(result);
        Integer result2 =futureTask2.get();
        System.out.println(result2);


         */

        FutureTask<Integer> futureTask0 = new FutureTask(new Mythrend2());

        new Thread(futureTask0,"0").start();
        new Thread(futureTask0,"1").start();

        System.out.println(Thread.currentThread().getName() + "**************************");
        int a=100;

        while (!futureTask0.isDone())//是否结束
        {

        }
        int aa=futureTask0.get();
        System.out.println("*****result" + (a + aa));


    }
    //Callable<Integer>  的调用   另一种多线程

    private static void Streamlanmuda() {

        User u1=new User(11,"a",23);
        User u2=new User(12,"b",24);
        User u3=new User(13,"c",22);
        User u4=new User(14,"d",28);
        User u5=new User(16,"e",26);

        List<User> list=Arrays.asList(u1,u2,u3,u4,u5);

        list.stream().filter(t->t.getId()%2==0
        ).filter(t->t.getAge()>24
        ).map(x-> x.getUsername().toUpperCase() //转大写
        ).sorted((o1, o2) ->  o2.compareTo(o1)
        ).limit(1).forEach(System.out::println);

        /**
        list.stream().filter(t->{
            return t.getId()%2==0;
        }).filter(t->{return t.getAge()>24;
        }).map(x->{
            return x.getUsername().toUpperCase();
        }).sorted((o1, o2) -> {return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);
         */

        List<Integer> list1=Arrays.asList(1,2,3);
        list1=list1.stream().map(x->{return x*2;}).collect(Collectors.toList());
        for (Integer e:list1){
            System.out.println(e);
        }
    }  //流式接口

    private static void InterfaceDemo() {

        /**
         * 四大函数式接口
         *
         */

        Function<String,Integer> function=s->{return  s.length();}; //函数型接口
        System.out.println(function.apply("abc"));

        Predicate<String> predicate=s->{return s.isEmpty();}; //bool型接口
        System.out.println(predicate.test("hh"));

        Consumer<String> consumer=s->{System.out.println(s);}; //消费性接口
        consumer.accept("傻逼");

        Supplier<String> supplier=()->{return "你猜";}; //供给型接口
        System.out.println(supplier.get());

    }  //四大函数式接口

}
interface Myinterface
{
    public int myInt(int x);
    public String myString (String s);
}
