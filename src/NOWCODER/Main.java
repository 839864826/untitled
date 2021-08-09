package NOWCODER;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] graph ={{2,4}, {2},{0,1},{4},{3,0}};
        int[][] graph1 ={{1,2,3,4}, {0,3},{0},{0,1},{0}};
        System.out.println(qqqqqq(graph1));
    }

    private static int  qqqqqq(int[][] graph) {
        int m=graph.length,max=0,d=0,min1=12,min2=12,min3=0,dd=0,max1=0;
        int qq=0;
        int[] q=new int[12];
        Arrays.fill(q,1);
        if(m<=3){
            return m-1;
        }
        // for(int i=0;i<12;i++){
        //     Arrays.fill(gra[0],0);
        //     for(int k:graph[i]){
        //         gra[i][k]=1;
        //     }
        // }
        for(int i=0;i<m;i++){
            if(graph[i].length>1){
                max++;
                if(graph[i].length==2&&q[i]==1){
                    q[i]=0;
                    int ii=graph[i][0],iii=graph[i][1];
                    while(graph[ii].length==2&&q[ii]==1){
                        q[ii]=0;
                        ii=ii==graph[ii][0]?graph[ii][1]:graph[ii][0];
                    }
                    while(graph[iii].length==2&&q[iii]==1){
                        q[iii]=0;
                        iii=iii==graph[iii][0]?graph[iii][1]:graph[iii][0];
                    }
                    if(ii==iii){
                        max1++;
                    }
                }
            }else{

                int ii=i,iii=i,iiii=i;
                if(d==0){
                    min1=0;
                    min1++;

                    // while(graph[graph[ii][0]].length==2){
                    //     min1++;
                    //     ii=ii==graph[ii][0]?graph[ii][1]:graph[ii][0];
                    // }
                    ii=graph[ii][0];
                    while(graph[ii].length==2){
                        min1++;
                        iiii=ii;
                        ii=iii==graph[ii][0]?graph[ii][1]:graph[ii][0];
                        iii=iiii;
                    }
                    // if(graph[graph[ii][0]].length==2){
                    //     min1++;

                    // }
                }else if(d==1){
                    min2=0;
                    min2++;
                    // if(graph[graph[ii][0]].length==2){
                    //     min2++;
                    ii=graph[ii][0];
                    while(graph[ii].length==2){
                        min2++;
                        iiii=ii;
                        ii=iii==graph[ii][0]?graph[ii][1]:graph[ii][0];
                        iii=iiii;
                    }
                    // }

                }else{
                    min3=0;
                    min3++;
                    // while(graph[graph[ii][0]].length==2){
                    //     min3++;
                    //     ii=ii==graph[ii][0]?graph[ii][1]:graph[ii][0];
                    // }
                    // if(graph[graph[ii][0]].length==2){
                    //     min3++;
                    ii=graph[ii][0];
                    while(graph[ii].length==2){
                        min3++;
                        iiii=ii;
                        ii=iii==graph[ii][0]?graph[ii][1]:graph[ii][0];
                        iii=iiii;
                    }
                    //}

                    if(min3>min1){
                        dd+=min1;
                        min1=min3;
                    }else{
                        dd+=min3;
                    }
                }
                if(min1>min2){
                    min3=min1;
                    min1=min2;
                    min2=min3;
                }
                d++;
            }
        }
        // return dd;
        if(d<=1){
            max1=0;
        }
        if(d<=2){
            return max+d-1+max1;
        }else{
            return max+dd-1+d+max1;
        }
    }

    private static void qq() {
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
