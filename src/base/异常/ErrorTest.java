package base.异常;

/**
 * ➢Error: Java 虚拟机无法解决的严重问题。如: JVM系统内部错误、资源
 *  *          耗尽等严重情况。比如: StackOverflowError和OOM。 一般不编写针对性
 *  *          的代码进行处理。|
 */
public class ErrorTest {
    public static void main(String[] args) {
//        main(args);//栈溢出Exception in thread "main" java.lang.StackOverflowError
//        Integer[] arr = new Integer[1024*1024*1024];//堆溢出OOM Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
