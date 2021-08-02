package NOWCODER;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        int k;
        long n,nn,max=0;
        k=cin.nextInt();
        nn=n=cin.nextLong();
        while (nn!=0){
            long mx=max;
            boolean b=true;
            while (mx!=0){
                if(mx%10==k){
                    b=false;
                }
                mx/=10;
            }
            if(b){
                nn--;
            }
            max++;
        }
        System.out.println(max-1);
        //qqq(k, n, nn, max);
    }

    private static void qqq(int k, long n, long nn, long max) {
        int w=0,ww;
        while (nn !=0){
            nn /=10;
            w++;
        }
        nn =1;
        ww=w;
        while (--w!=0){
            nn *=10;
        }
        System.out.println(nn);
        while(n !=0){
            long g= n;
            if(nn >=10){
                for (int i = 0; i < ww; i++) {
                    g=g/10*9;
                }
            }
            int i;
            for(i=0;i<=9;i++){
                if(i== k){
                    continue;
                }
                if(n >=g){
                    n -=g;
                }else{
                    break;
                }
            }
            max = max *10+--i;
            nn /=10;
        }

        System.out.println(max);
    }
}
