//package main
//注意不要添加包名称，否则会报错。

import java.io.*;
import java.util.*;
public class blf {
    public void as2(String str){
        str+="xxx";
    }
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int a;
        a=cin.nextInt();
        blf s=new blf();
        String str="abc";
        s.as2(str);
        System.out.println(str);
    }
}
                            