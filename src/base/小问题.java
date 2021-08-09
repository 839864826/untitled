package base;

import java.util.Arrays;

public class 小问题 {
    public static void main(String[] args) {
        ArraysTest();
    }

    private static void ArraysTest() {
        int a;
        Object o=null;
        if(o instanceof Arrays){
            System.out.println("666");
        }


        Integer[] integer=new Integer[5];
        if(integer instanceof Integer[]){
            System.out.println("666");
        }
    }
}
