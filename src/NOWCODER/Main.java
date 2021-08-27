package NOWCODER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {

        addNum(6);
        addNum(10);
        addNum(2);
        addNum(6);
        addNum(5);
        addNum(0);
        addNum(6);
        addNum(3);
        addNum(1);
        addNum(0);
        addNum(0);
    }

    private static void addNum(int num) {
        if(list.size()==0){list.add(num);return;}
        int q=0,h=list.size()-1,z=0;
        if(list.get(list.size()-1)<num){
            list.add(num);
            System.out.println(list);
            return;
        }
        do{
            z=q+(h-q)/2;
            if(list.get(z)>num){
                h=z-1;
            }else if(list.get(z)<num){
                q=z+1;
            }else{
                list.add(z,num);
                return;
            }
        }while(q<=h);
//        if(list.get(q)<num){
//            list.add(q+1,num);
//        }else {
//            list.add(q,num);
//        }

        list.add(q,num);


        System.out.println(q +"  "+ h);
        System.out.println(list);
    }

    private static double findMedian() {
        int h=list.size(),q=h-1;
        q/=2;h/=2;
        if(q==h){
            return (double) list.get(q);
        }else{
            return ((double)list.get(q)+(double)list.get(h))/2;
        }
    }
    private static List<List<String>> partition(String s) {
        List<List<String>> list= new ArrayList<List<String>>();
        List<String> list1= new ArrayList<>();

        return list;
    }

    private static void dfs(String s,int index,List<List<String>> list,List<String> list1){

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
