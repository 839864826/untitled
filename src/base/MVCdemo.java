package base;

import java.util.Date;


import static java.lang.System.*;

/**
 * MVC是常用的设计模式之一，将整个程序分为三个层次:视图模型层，控制器层，与
 * 数据模型层。这种将程序输入输出、数据处理，以及数据的展示分离开来的设计模式
 * 使程序结构变的灵活而且清晰，同时也描述了程序各个对象间的通信方式，降低了程
 * 序的耦合性。
 * 模型层 model 主要处理数据                      控制层controller 处理业务逻辑
 * >数据对象封装  model.bean/domain              >应用界面相关controller.activity
 * >数据库操作类  model.dao                      >存放fragment contrller.fragment
 * >数据库       model.db                       >显示列表的适配器controller.adapter
 *                                             >服务相关的  controller.service
 *                                             >抽取的基类  controller.base
 * 视图层
 * view显示数据
 * >相关工具类    view.utils
 * >自定义view   view.ui
 */
/**
 * 一、package关键字的使用
 * 1.为了更好的实现项目中类的管理，提供包的概念
 * 2.使用package声明类或接口所属的包，声明在源文件的首行
 * 3.包，属于标识符，遵循标识符的命名规则、规范( xxxyyyzzz)、“见名知意”
 * 4.每"."-次，就代表一层文件目录。
 * 补充:同一个包下，不能命名同名的接口、类。
 *     不同的包下，可以命名同名的接口、类。
 * 二、import关键字的使用
 * import:导入
 * 1.在源文件中显式的使用import结构导入指定包下的类、接口
 * 2.声明在包的声明和类的声明之间
 * 3.如果需要导入多个结构，则并列写出即可
 * 4.可以使用"xxx. *"的方式，表示可以导入XXX包下的所有结构
 * 5.如果使用的类或接口是java.lang包下定义的，则可以省略import结构
 * 6.如果使用的类或接口是本包下定义的，则可以省略import结构
 * 7.如果在源文件中，使用了不同包下的同名的类，则必须至少有一个类需要以全类名的方式显示。
 * 8.使用"XXX.*"方式表明可以调用xxx包下的所有结构。但是如果使用的是xxx子包下的结构，则仍需要导入
 * 9. import static :导入指定类或接口中的静态结构:属性或方法。
 *
 */
public class MVCdemo {
    public static void main(String[] args) {
        java.util.Date data=new Date();
        java.sql.Date date0=new java.sql.Date(4545L);

        System.out.println("666");
        out.println("777");//import static java.lang.System.*;   导入静态资源可以省略System
        //9. import static:导入指定类或接口中的静态结构，
    }
}
