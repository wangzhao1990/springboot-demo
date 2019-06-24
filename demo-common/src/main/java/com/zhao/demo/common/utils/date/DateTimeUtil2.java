package com.zhao.demo.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 时间处理工具
 *
 * @author wangz
 * @create 2019/3/29
 */
public class DateTimeUtil2 {

//    /**
//     * 使用ThreadLocal保证每个线程的simpleDateFormat对象是单独的
//     */
//    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化时间：yyyy-MM-dd HH:mm:ss
     * @param date 时间
     * @return 格式化时间字符串
     */
    public static String format(Date date){
        return sdf.format(date);
    }

    /**
     * 转换时间
     * @param dateTime  时间字符串
     * @return Date
     * @throws ParseException 转换异常
     */
    public static Date parse(String dateTime) throws ParseException {
        return sdf.parse(dateTime);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                try {
//                    System.out.println(format(new Date()));
                    System.out.println(parse("2019-03-29 16:46:00"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

}
