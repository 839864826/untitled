

class AA{
    public AA(){
        System.out.println("AA构 造 方 法11111");
    }
    {
        System.out.println("AA构  造  块22222");
    }
    static {
        System.out.println("AA静态代码块33333:");
    }
}

public class 加载顺序 {
    {
        System.out.println("BB构  造  块44444");
    }
    static {
        System.out.println("BB静态代码块55555:");
    }
    public 加载顺序(){
        System.out.println("BB构 造 方 法66666");
    }

    public static void main(String[] args) {


        System.out.println("起始点=========================");
        new AA();
        System.out.println("-----------------------");
        new AA();
        System.out.println("-----------------------");
        new 加载顺序();
    }
}
