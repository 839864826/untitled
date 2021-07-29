package base;

/**
 * final:最终的
 *
 * 1.final可以用来修饰的结构:类、方法、变量
 *
 * 2.final 修饰类   此类不能被继承
 *           比如String，StringBuffer，System
 * 3.final 修饰方法    此方法不可被重写
 *          比如Object类中的getClass
 * 4. final 用来修饰变量:此时的"变量"就称为是一个常量    一般用大写
 *      尤其是使用final修饰形参时，表明此形参是一个常量。
 *      当我们调用此方法时，给常量形参赋一个实参,一旦调用以后，
 *      就只能在方法体内使用此形参，但不能进行重新赋值。
 *
 * 5.static final 修饰属性，方法
 *        修饰属性：全局常量
 *        修饰方法：全局常量
 *
 *
 */
public class FinalTest {
    int k0;

    final int k=10;//final必须 初始化
    //        k=20;//final修饰的变量不可修改

    final int k1;//final必须 初始化
    {
        k1=10;
    }

    final int k2;//final必须 初始化
    public  FinalTest() {k2=5;}
    public  FinalTest(final int n) {k2=n;}//因为这个类只要new了   值就固定了 没有第二次赋值的机会
    public  FinalTest(final FinalTest n, int k2) {
        this.k2 = k2;
        n.k0=1;//表示FinalTest不可以修改了   但是FinalTest的属性可以修改
        //就想  你的生母永远是你生母   不可修改   但是你生母的某些属性  如年龄  会改变
    }
//    final int k3;//final必须 初始化
//    protected void setk3(int n) {
//        k3=n;//不可以   因为final不可修改   而这个方法可以多次调用 违背了不可多次修改
//    }

    public static void main(String[] args) {

        /**
         *
         */
        FinalTest finalTest=new FinalTest();


    }
}

/* final */class A{ //最终的   太监   无子类    不可以被继承

    public final void show(){

    }
}

class B extends A{
//    public final void show(){
// //'show()' cannot override 'show()' in 'base.A'; overridden method is final
//    }
}
