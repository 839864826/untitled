package base.异常;

/**
 *
 * 异常:在Java语言中，将程序执行中发生的不正常情况称为“异常
 * (开发过程中的语法错误和逻辑错误不是异常)
 *
 * ●Java程序在执行过程中所发生的异常事件可分为两类:
 *      ➢Error: Java 虚拟机无法解决的严重问题。如: JVM系统内部错误、资源
 *          耗尽等严重情况。比如: StackOverflowError和OOM。 一般不编写针对性
 *          的代码进行处理。|
 *      ➢Exception:其它因编程错误或偶然的外在因素导致的一-般性问题，可以使
 *          用针对性的代码进行处理。例如:
 *              r空指针访问
 *              r试图读取不存在的文件
 *              r网络连接中断
 *              r数组角标越界
 */

/**
 * 异常结构体系
 *
 * java.lang.Throwable
 *      |-----java.lang.Error:般不编写针对性的代码进行处理。
 *      |-----java.lang.Exception:可以进行异常的处理
 *          |----- -编译时异常(checked)
 *          |----- -运行时异常(unchecked)
 *
 *
 */
public class Main {
    public static void main(String[] args) {

    }
}
