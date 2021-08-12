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
class User {
    private Integer id;
    private String  username;
    private int     age;
}

class MyTask extends RecursiveTask<Integer> {

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



    }


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

    }  //四大函数式接口  juf

}
