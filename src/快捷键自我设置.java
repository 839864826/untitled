import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class 快捷键自我设置 {
    public static void main(String[] args) {
        Thread.currentThread().getName();//tname  获取当前线程名字
        UUID.randomUUID().toString().substring(0, 8);//获取8为随机数  8为以上有问题
        try {TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) {e.printStackTrace();}
        /*
         * 睡眠
         * NANOSECONDS       纳秒
         * MICROSECONDS      微妙
         * MILLISECONDS      毫秒
         * SECONDS            秒
         * MINUTES           分钟
         * HOURS             小时
         * DAYS               天
         */


    }
}
