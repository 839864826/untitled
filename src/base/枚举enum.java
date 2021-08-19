package base;

import lombok.Getter;
/**
 * 类的对象只有有限个，确定的。举例如下:
 * ➢星期: Monday(星期- -)、....
 * Sunday(星期天)
 * ➢性别: Man(男)、Woman(女)
 * ➢季节: Spring(春 .....Winter(冬天)
 * ➢支付方式: Cash (现金)、WeChatPay (微信)、Alipay(支付宝)、 BankCard(银
 * 行) CreditCard(信用卡)
 * ➢就职状态: Busy、 Free、 Vocation、 Dimission
 * ➢订单状态: Nonpayment (未付款)、Paid (已付款)、Fulfilled (已配货)、
 * Delivered (已发货)、Return (退货)、Checked (已确认)
 * ➢线程状态:创建、就绪、运行、阻塞、死亡
 * ●当需要定义一组常量时，强烈建议使用枚举类
 */
public class 枚举enum {

    public static void main(String[] args) {
        /**
         * 二、如何定义枚举类
         * 方式一: jdk5.0之前，自定义枚举类
         * 方式二: jdk5.e, 可以使用enum关键字定义枚举类
         *
         *三、Enum类中的常用方法: .
         * values()方法:返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
         * value0f(string str):可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对像
         * toString():返回当前枚举类对象常量的名称
         *四、使用enum关键字定义的枚举类实现接口的情况
         * 情况一:实现接口，在enum类 中实现抽象方法
         * 情况二:让枚举类的对象分别实现接口中的抽象方法
         */
        Season autumn = Season.AUTUMN;
        System.out.println(autumn);

        Season1 season1 = Season1.WINTER;
        System.out.println(Season1.class.getSuperclass());//class java.lang.Enum
        System.out.println(season1);//WINTER
        System.out.println("values()方法:返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。");
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]+"     ");
        }//SPRING     SUMMER     AUTUMN     WINTER
        System.out.println();
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.print(values1[i] + "     ");
        }//NEW     RUNNABLE     BLOCKED     WAITING     TIMED_WAITING     TERMINATED
        System.out.println();
        System.out.println("value0f(String objName):返回枚举类中对象名是objName的对象");
        Season1 season11 = Season1.valueOf("WINTER");//找不到会抛异常
        System.out.println(season11);//WINTER
        season11.shows();//冬天来了
    }


    public enum CountryEnum{
        ONE(1,"齐国"),TWO(2,"楚国"),
        THREE(3,"燕国"),FOUR(4,"赵国"),
        FIVE(5,"魏国"),SIX(6,"韩国");
        @Getter private Integer retCode;
        @Getter private String retMessage;

        CountryEnum(Integer retCode,String retMessage) {
            this.retMessage = retMessage;
            this.retCode = retCode;
        }

        public static CountryEnum forEach_CountryEnum(int index){

            CountryEnum[] myArray = CountryEnum.values();
            for(CountryEnum countryEnum:myArray){
                if(index==countryEnum.getRetCode()){
                    return countryEnum;
                }
            }
            return null;
        }
    }  //枚举

}

class Season{
    //1.声明对象属性
    private final  String name;
    private final String desc;
    private Season(String name,String desc){
        this.name=name;
        this.desc=desc;
    }//2.私有化构造器

    //3.供当前枚举类的多个对象: public static final的
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

interface info{
    void show();
    void shows();
}
//使用 enum 关键字枚举  定义的枚举类默认继承于java. lang. Enum类
enum Season1 implements info{
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("春天来了");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天来了");
        }
    },
    AUTUMN("秋天","秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天来了");
        }
    },
    WINTER("冬天","冰天雪地"){
        @Override
        public void show() {
            System.out.println("冬天来了");
        }
    };
    @Override
    public void shows() {
        System.out.println(this.name+"来了");
    }//接口的show方法
    private final  String name;
    private final String desc;
    private Season1(String name,String desc){
        this.name=name;
        this.desc=desc;
    }//2.私有化构造器


} //默认有toString