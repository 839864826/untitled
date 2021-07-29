package redisDemo;

import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        //redis默认端口 6879

        
        int a=1,b=1;
        for (int i = 0; i < 10; i++) {
            a*=2;
            b<<=1;
        }
        System.out.println(a);
        System.out.println(b);
    }
}
