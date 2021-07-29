package BNB;

public class Main {
    public static void main(String[] args) {

//        Mains.main(new String[100]);
        for (String a : args) {
            System.out.println(a);
        }

    }

    private static void appendDemo() {
        String str1=new StringBuilder("j").append("ava").toString();
        System.out.println(str1 == str1.intern());//除了java都是thue
        String str2=new StringBuilder("jee").append("ava").toString();
        System.out.println(str2 == str2.intern());
    }
}
class Mains{
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = "args_"+i;
            System.out.println(args[i]);
        }

//        for (String a : args) {
//            System.out.println(a);
//        }
    }
}
