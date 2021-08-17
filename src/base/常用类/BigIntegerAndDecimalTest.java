package base.常用类;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerAndDecimalTest {
    public static void main(String[] args) {
        /**
         * BigInteger的方法  高精度
         *●常用方法
         * ➢public BigInteger abs():返回此BigInteger的绝对值的BigInteger。
         * ➢BigInteger add(BigInteger val) :返回其值为(this + val)的BigInteger
         * ➢BigInteger subtract(BigInteger val):返回其值为(this - val)的BigInteger
         * ➢BigInteger multiply(BigInteger val) :返回其值为(this * val)的BigInteger
         * ➢BigInteger divide(BigInteger val) :返回其值为(this / val)的BigInteger。整数
         * 相除只保留整数部分。
         * ➢BigInteger remainder(BigInteger val):返回其值为(this % val)的BigInteger。
         * ➢BigInteger[] divideAndRemainder(BigInteger val):返回包含(this / val)后跟
         * (this % val)的两个BigInteger的数组。
         * ➢BigInteger pow(int exponent) :返回其值为(this 的exponent次方)的BigInteger。
         *
         */
        BigInteger a= new BigInteger("564444444444444444444444444444444444444444444444444444444444444444");
        System.out.println(a);

        /**
         * BigDecimal的方法 高精度
         * ●一般的Float类和Double类可以用来做科学计算或工程计算，但在商业计算中,
         * 要求数字精度比较高，故用到java.math.BigDecimal类。
         * ●BigDecimal类 支持不可变的、任意精度的有符号十进制定点数。
         * ●构造器
         * ➢public BigDecimal(double val)
         * ➢public BigDecimal(String val)
         * ●常用方法
         * ➢public BigDecimal add(BigDecimal augend)
         * ➢public BigDecimal subtract(BigDecimal subtrahend)
         * ➢public BigDecimal multiply(BigDecimal multiplicand)
         * ➢public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
         */
        BigDecimal bigDecimal = new BigDecimal("89465498654198498598459854986469849864651651.4345345874786374563452348366453245383453");
        System.out.println(bigDecimal);
        BigDecimal bd1 = new BigDecimal("54651.846451");
        BigDecimal bd2 = new BigDecimal("5.5");
        System.out.println(bd1.divide(bd2,25));
//        System.out.println(bd1.divide(bd2));//可能会报错   除不尽的话  会报错java.lang.ArithmeticException算数异常
        System.out.println(bd1.divide(bd2,BigDecimal.ROUND_HALF_DOWN));//BigDecimal.ROUND_HALF_DOWN 四舍五入默认长短和小数点多的一致
        System.out.println(bd1.divide(bd2,25,BigDecimal.ROUND_HALF_DOWN));
    }
}
