package JavaN.Java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 *
 */
public class Java8_DateTime {
    public static void main(String[] args) {
        SimpleDateFormatDemo();
    }

    private static void DateTimeFormatter格式转换() {
        //格式转换
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime ldt = LocalDateTime.now();//获得当前时间
        System.out.println(ldt);
        //日期--》字符串
        String format = dtf.format(ldt);
        System.out.println(format);

        //字符串--》日期
        TemporalAccessor parse = dtf.parse("2021-08-08T16:26:13.137");
        System.out.println(parse.toString());

        System.out.println("*****************************");
        //格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String str = dateTimeFormatter.format(ldt);
        System.out.println(str);

        //自定义格式化      重点
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
        String format1 = dateTimeFormatter1.format(LocalDateTime.now());
        System.out.println(format1);

        //解析
        TemporalAccessor parse1 = dateTimeFormatter1.parse("2021年08月17日 04:46:53");
        System.out.println(parse1);
    }

    private static void Instant创建本初子母线时间() {
        Instant instant =Instant.now();//本初子母线时间  标准建筑 伦敦
        System.out.println(instant);//咱属于东八区  小时+8

        //添加偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //获取自1970年1月1日0时0分0秒（UTC） 开始的毫秒数
        long mi=instant.toEpochMilli();//System.currentTimeMillis()一样
        System.out.println(mi);

        Instant instant1=Instant.ofEpochMilli(1629188242558L);
        System.out.println(instant1);
    }

    private static void java点time001类() {
        /**
         * Java 8吸收了Joda-Time的精华，
         * 以一个新的开始为Java创建优秀的API。
         * 新的java.time中包含了所有关于本地日期(LocalDate)、
         * 本地时间(LocalTime)、本地日期时间(LocalDateTime) 、
         * 时区( ZonedDate Time )和持续时间( Duration) 的类。
         * 历史悠久的Date类新增了toInstant()方法，
         * 用于把Date转换成新的表示形式。这些新增的本地化时间口期API大大简
         * 化了日期时间和本地化的管理。
         */

        LocalDate localDate=LocalDate.now();//获取当前日期
        LocalTime localTime = LocalTime.now();//获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();//获取当前日期时间
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //没有偏移量  获取指定时间
        LocalDateTime of = LocalDateTime.of(2021, 2, 1, 11, 11, 11, 111);
        System.out.println("******************************************");
        System.out.println(localDateTime.getDayOfMonth());//当月第几天
        System.out.println(localDateTime.getNano());//当前纳秒为基础   0.0-1.0秒之间如134000000
        System.out.println(localDateTime.getDayOfWeek());//今天星期几
        System.out.println(localDateTime.getHour());//现在几点
        System.out.println(localDateTime.getMinute());
        ;//现在几分
        System.out.println(localDateTime.getSecond());
        ;//现在几秒
        System.out.println(of.getDayOfYear());//今天是今年的第几天

        //不可变性   返回修改后的时间
        localDateTime.withHour(5);//把时间改成5点   返回修改后的时间  原时间不变

        localDateTime.plusDays(5);//把时间加5天   返回修改后的时间  原时间不变

        localDateTime.minusDays(6);//把时间减5天   返回修改后的时间  原时间不变
    } //jdk8

    private static void Calendar日历类() {
        /**
         * jdk8之前
         * 日历类 Calendar abstract抽象类不可实例化
         *
         */
        //创建子类对象
        Calendar calendar=Calendar.getInstance();

        //class java.util.GregorianCalendar
        System.out.println(calendar.getClass());

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.DAY_OF_MONTH,22);//修改calendar这个对象

        calendar.add(Calendar.DAY_OF_MONTH,3);//增加  负数减少


        Date time = calendar.getTime();//日历类-->java.util.Date

        calendar.setTime(time);//java.util.Date-->日历类
    }//jdk8之前

    private static void SimpleDateFormatDemo() {
        //        SimpleDateFormat sdf=new SimpleDateFormat();//格式化时间默认格式：21-8-17 上午10:47
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//格式化时间指定格式

        Date date = new Date();//创建一个当前电脑的时间的对象
//        System.out.println(date.toString());
        String format= sdf.format(date);//格式化
        System.out.println(format);//

        String str = "2015-05-25";
//        str="21-8-17 上午10:44";
        str="2021-8-17 10:44:55";
        Date date1;
        try {
           date1=sdf.parse(str);//格式必须是和SimpleDateFormat要求格式一模一样
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }//jdk8之前
}
