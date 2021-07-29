/**
 *
 * 对属性可以赋值的位置:
 * ①默认初始化
 * ②显式初始化/⑤在代码块中赋值
 * ③构造器中初始化
 * ④有了对象以后，可以通过"对象。属性"或"对象.方法"的方式，进行赋值
 *
 * 执行的先后顺序:①-②/⑤-③-④
 */
class AA{
    int a=1;
    public AA(){
        System.out.println("AA构 造 方 法11111");
    }
    {
        a=2;//a在之前声明   a的值按照  后面的来
        b=2;//b在之后声明   b的值按照  后面的来
        System.out.println("AA构  造  块22222");
    }
    int b=1;
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

        /**
         * 代码块
         *
         * 1.代码块的作用:用来初始化类、对象
         * 2.代码块如果有修饰的话，只能使用static .
         * 3.分类:静态代码块  VS  非静态代码块
         *      静态代码块    可以有多个（没必要）
         *          >可以有输出语句
         *          >随着类的加载而执行 只执行一次
         *          >作用:初始化类的信息
         *
         *      非静态代码块
         *          >可以有输出语句
         *          >随着对像的创建而执行
         *          >每创建一次   就执行一次非静态代码块
         *          >作用:可以在创建对象时，对 对象的属性等进行初始化
         *
         */

        System.out.println("起始点=========================");
        new AA();//如过有父类   先静态后其他 先父在子
        System.out.println("-----------------------");
        new AA();
        System.out.println("-----------------------");
        new 加载顺序();
    }
}
