package base.常用类;

public class SystemTest {
    public static void main(String[] args) {
        /**
         * 相当于工具类
         */

//        该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算杭时
//        间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。
        System.currentTimeMillis();

        System.gc();//请求垃圾回收

        /**
         * ➢String getProperty(String key):
         * 该方法的作用是获得系统中属性名为key的属性对应的值。系统中常y
         * 的属性名以及属性的作用如下表所示:
         * 属性名.                  属性说明
         * java.version             Java运行时环境版本
         * java.home                 Java安装目录
         * os.name                 操作系统的名称
         * os.version           操作系统的版本
         * user.name               用户的账户名称
         * user.home                用户的主目录
         * user.dir            用户的当前工作目录
         */
        System.out.println(System.getProperty("java.version"));

        System.exit(0);//退出程序   0表示正常退出  非0异常退出
    }
}
