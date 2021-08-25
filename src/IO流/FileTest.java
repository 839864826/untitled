package IO流;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File类的使用
 *
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 *
 *
 *
 *
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        创建硬盘中对应的文件目录();
    }


    private static void 创建File类的实例(){
        /**
         *  1.如何创建File类的实例
         *         File(String filePath)
         *         File(String parentPath,String childPath)
         *         File(File parentFile,String childPath)
         *  2.
         *     相对路径：相较于某个路径下，指明的路径。
         *     绝对路径：包含盘符在内的文件或文件目录的路径
         *
         *  3.路径分隔符
         *      windows:\\
         *      unix:/
         */
        //构造器1
        File file1 = new File("hello.txt");//相对于当前module
        File file2 =  new File("E:\\xunlian\\untitled\\hello.txt");

        System.out.println(file1);
        System.out.println(file2);

        //构造器2：
        File file3 = new File("E:\\xunlian","untitled");
        System.out.println(file3);

        //构造器3：
        File file4 = new File(file3,"hello.txt");
        System.out.println(file4);
    }

    public static void File的常用方法(){

        /**
         * public String getAbsolutePath()：获取绝对路径
         * public String getPath() ：获取路径
         * public String getName() ：获取名称
         * public String getParent()：获取上层文件目录路径。若无，返回null
         * public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
         * public long lastModified() ：获取最后一次的修改时间，毫秒值
         *
         */
        File file1 = new File("hello.txt");
        //E:\\xunlian\\untitled\\hello.txt  其实应该是一个'\'  只是在注释中  不能有一个'\'  容易出现 非法的Unicode 转义问题 的错误
//        File file1 =  new File("E:\\xunlian\\untitled\\hello.txt");
        System.out.println("获取绝对路径:"+file1.getAbsolutePath());//"E:\\xunlian\\untitled\\hello.txt" 获取绝对路径
        System.out.println("获取路径:"+file1.getPath());//hello.txt 获取路径 返回
        System.out.println("获取名称:"+file1.getName());//hello.txt 获取名称
        System.out.println("获取上层文件目录路径:"+file1.getParent());//null
        //获取上层文件目录路径。若无，返回null 若用的是相对路径 极有可能返回null

        System.out.println("获取文件长度:"+file1.length());//获取文件长度（即：字节数）

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//格式化时间指定格式
        Date date = new Date(file1.lastModified());////获取最后一次的修改时间，毫秒值
        String format = sdf.format(date);//格式化
        System.out.println("最后一次的修改时间:"+format);//获取最后一次的修改时间，毫秒值

        System.out.println("************绝对路径********************************");
        File file2 =  new File("E:\\xunlian\\untitled\\hello.txt");
        System.out.println("获取绝对路径:"+file2.getAbsolutePath());//E:\\xunlian\\untitled\\hello.txt
        System.out.println("获取路径:"+file2.getPath());//E:\\xunlian\\untitled\\hello.txt
        System.out.println("获取名称:"+file2.getName());//hello.txt
        System.out.println("获取上层文件目录路径:"+file2.getParent());//E:\\xunlian\\untitled
    }

    public static void 获得目录信息(){
        /**
         * 如下的两个方法适用于文件目录：
         * public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
         * public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
         *
         */
        File file = new File("E:\\xunlian\\untitled");

        String[] list = file.list();//返回file目录下的所有文件名字
        for(String s : list){
            System.out.println(s);
        }

        System.out.println();

        File[] files = file.listFiles();////返回file目录下的所有文件路径
        for(File f : files){
            System.out.println(f);
        }

    }

    public static void 文件移动(){
        /**
         *  public boolean renameTo(File dest):把文件重命名为指定的文件路径
         *      比如：file1.renameTo(file2)为例：
         *         要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
         */
        File file1 = new File("hello.txt");
        File file2 = new File("hi.txt");

        boolean renameTo = file1.renameTo(file2);//将file1的文件放到file2的位置上并重命名
        //（保证file1的位置上有这个文件） （保证file2的位置上没有这个文件）
        System.out.println(renameTo);


    }


    public static void 文件状态判断(){
        /**
         * public boolean isDirectory()：判断是否是文件目录
         * public boolean isFile() ：判断是否是文件
         * public boolean exists() ：判断是否存在
         * public boolean canRead() ：判断是否可读
         * public boolean canWrite() ：判断是否可写
         * public boolean isHidden() ：判断是否隐藏
         *
         * 默认为false
         *
         */
        File file1 = new File("hello.txt");
//        file1 = new File("hello1.txt");

        System.out.println("是否是文件目录"+file1.isDirectory());
        System.out.println("是否是文件"+file1.isFile());
        System.out.println("是否存在"+file1.exists());
        System.out.println("是否可读"+file1.canRead());
        System.out.println("是否可写"+file1.canWrite());
        System.out.println("是否隐藏"+file1.isHidden());

        System.out.println();

        File file2 = new File("E:\\xunlian\\untitled");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }

    public static void 创建硬盘中对应的文件() throws IOException {
        /**
         *     创建硬盘中对应的文件或文件目录
         * public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
         * public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
         * public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建
         *
         *     删除磁盘中的文件或文件目录
         * public boolean delete()：删除文件或者文件夹
         *     删除注意事项：Java中的删除不走回收站。
         */
        File file1 = new File("hi.txt");
        if(!file1.exists()){
            //文件的创建
            file1.createNewFile();//创建file1文件
            System.out.println("创建成功");
        }else{//文件存在
            file1.delete();//删除file1文件
            System.out.println("删除成功");
        }
    }
    public static void 创建硬盘中对应的文件目录(){
        /**
         * public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
         * public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建
         *
         */
        //文件目录的创建
        File file1 = new File("d:\\file\\file1\\file11");

        boolean mkdir = file1.mkdir();//只能创建
        if(mkdir){
            System.out.println("创建成功1");
        }

        File file2 = new File("d:\\file\\file1\\file12");

        boolean mkdir1 = file2.mkdirs();
        if(mkdir1){
            System.out.println("创建成功2");
        }
        //要想删除成功，io4文件目录下不能有子目录或文件
        File file3 = new File("D:\\io\\io1\\io4");
        file3 = new File("D:\\io\\io1");
        System.out.println(file3.delete());
    }
}
