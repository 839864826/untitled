package base;

import java.util.Scanner;

public class jd {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int m= cin.nextInt();
        int a[]=new int[m+3];
        int dp[]=new int[m+3];
        for (int i = 0; i < m; i++) {
            a[i+3]= cin.nextInt();
        }

        for (int i = 0; i < m; i++) {
            dp[i+3]=Math.max(dp[i+1],dp[i])+a[i+3];
        }
        System.out.println(Math.max(dp[m+2],dp[m+1]));
    }
}
