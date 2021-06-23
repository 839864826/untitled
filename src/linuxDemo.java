

/**
 * sudo su//进入root权限  283036
 * top//查看linux具体信息
 * uptime//精简版top
 * history//查看以前代码历史
 *
 * vmstat -n 2 3//查看CPU信息
 * mpstat -P ALL 2//查看所有CPU核信息
 *
 * ps -ef|grep java//查看java和相关的进程
 * pidstat -u 1 -p 进程编号//每个进程使用cpu的用量分解信息
 *
 * free//(自由)查看内存  -g//按G算。。。   -m//按MB算
 * pidstat -p 进程号 -r  采样秒数
 *
 * df -h//查看每个盘的信息
 *
 * iostat -xdk 2 3               pidstat -d 采样秒数 -p  进程号
 *      磁盘块设备分布
 *      rkB/s每秒读取数据量kB;
 *      wkB/s每秒写入数据量kB;
 *      svctm /O请求的平均服务时间，单位毫秒; .
 *      await I/O请求的平均等待时间，单位亳秒;值越小，性能越好;
 *      util一秒中有百分几的时间用于I/O操作。接近100%时，表示磁盘带宽跑满，需要优化程序或者增加磁盘;
 *      rkB/s、wkB/s根 据系统应用不同会有不同的值，但有规律遵循:长期、 超大数据读写，肯定不正常，需要优化程序读取。
 *      svctm的值与await的值很接近，表示几乎没有I/O等待，磁盘性能好，
 *      如果await的值远高于svctm的值，则表示I/O队列等待太长，需要优化程序或更换更快磁盘。
 *
 * ifstat  //还要下载
 *
 * ps -ef|grep java|grep -V grep
 *
 * top//查看linux具体信息
 * ps -ef|grep java//查看java和相关的进程
 * ps -mp 进程号 -o THREAD.tid.time//查看进程号  的具体线程
 *     -m//显示所有的线程   -p//pid进程使用的CPU的时间  -o//该参数后是用户自定义格式
 * 4.将需要的线程ID转换为16进制格式(英文小写格式)
 *
 * jstack 进程号| grep （2进制的线程） -A60//能看到   具体哪一行在占用CPU
 *
 *
 * in：name,
 * github
 *    关键词  stars:>=5000   100..200(之间)   表示点赞》=5000
 *    关键词  forks:>=5000         表示下载》=5000
 *
 *   awesome 关键词//表示要学习这个关键词
 *   地址#L行数   高亮显示那一行
 *   #L行号-L行号      高亮显示范围
 *   t
 *   location:城市拼音 language:语言
 *
 *
 */

/**
 * 命令
 * dir查看当前目录文件
 * md 文件名//创建文件。
 * cd 文件目录//进入
 * cd..//返回上一个目录
 * cd\//回到最开始目录
 * del 名字//删除
 * del 文件名//删除文件下的所有东西
 * del *.后缀名//删除全部
 * rd 文件名//删除空文件
 *
 */
public class linuxDemo {
    public static void main(String[] args) {

    }
}
