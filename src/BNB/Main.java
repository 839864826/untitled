package BNB;

public class Main {
    public static void main(String[] args) {
        String str1=new StringBuilder("j").append("ava").toString();
        System.out.println(str1 == str1.intern());
    }
}
