package com.zhao.demo.common.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
    private static final DateTimeFormatter FORMATTER_4 = DateTimeFormatter.ofPattern("yyyy-MM");
    private static final DateTimeFormatter FORMATTER_5 = DateTimeFormatter.ofPattern("MM月dd日 HH:mm");

    /**
     * 转换时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime 时间
     * @return 时间字符串
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return FORMATTER.format(dateTime);
    }
    /**
     * 转换时间格式：yyyy-MM-dd
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String formatDate(LocalDate date) {
        return FORMATTER_2.format(date);
    }
    /**
     * 转换时间格式：yyyy年MM月dd日
     *
     * @param dateTime 时间
     * @return 时间字符串
     */
    public static String formatDateTime1(LocalDateTime dateTime) {
        return FORMATTER_1.format(dateTime);
    }
    /**
     * 转换时间格式：yyyy年MM月dd日 HH:mm
     *
     * @param dateTime 时间
     * @return 时间字符串
     */
    public static String formatDateTime3(LocalDateTime dateTime) {
        return FORMATTER_3.format(dateTime);
    }
    /**
     * 转换时间格式：yyyy-MM
     *
     * @param dateTime 时间
     * @return 时间字符串
     */
    public static String formatDateTime4(LocalDateTime dateTime) {
        return FORMATTER_4.format(dateTime);
    }
    /**
     * 转换时间格式：MM月dd日 HH:mm
     *
     * @param dateTime 时间
     * @return 时间字符串
     */
    public static String formatDateTime5(LocalDateTime dateTime) {
        return FORMATTER_5.format(dateTime);
    }

    /**
     * 时间字符串转时间
     *
     * @param dateTime 时间字符串
     * @return 时间对象
     */
    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, FORMATTER);
    }

    /**
     * 获取当月月初时间
     * @return 时间对象 LocalDate
     */
    public static LocalDate firstDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取当月月初时间
     * @return 时间对象 LocalDate
     */
    public static LocalDate lastDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 计算两个时间点的天数差
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即要计算的天数差
     */
    public static int dateDiff(LocalDateTime dt1,LocalDateTime dt2){
        //获取第一个时间点的时间戳对应的秒数
        long t1 = dt1.toEpochSecond(ZoneOffset.ofHours(0));
        //获取第一个时间点在是1970年1月1日后的第几天
        long day1 = t1 /(60*60*24);
        //获取第二个时间点的时间戳对应的秒数
        long t2 = dt2.toEpochSecond(ZoneOffset.ofHours(0));
        //获取第二个时间点在是1970年1月1日后的第几天
        long day2 = t2/(60*60*24);
        //返回两个时间点的天数差
        return (int)(day2 - day1);
    }

    /**
     * 获取两个时间点的月份差
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即需求的月数差
     */
    public static int monthDiff(LocalDateTime dt1,LocalDateTime dt2){
        //获取第一个时间点的月份
        int month1 = dt1.getMonthValue();
        //获取第一个时间点的年份
        int year1 = dt1.getYear();
        //获取第一个时间点的月份
        int month2 = dt2.getMonthValue();
        //获取第一个时间点的年份
        int year2 = dt2.getYear();
        //返回两个时间点的月数差
        return (year2 - year1) *12 + (month2 - month1);
    }


    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        for (int i = 0; i < 20; i++) {
//            executorService.execute(() -> {
//                try {
//                    System.out.println(formatDate(LocalDateTime.now()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        executorService.shutdown();
//        executorService.awaitTermination(1, TimeUnit.DAYS);

        String s = LocalDateTimeUtil.formatDateTime5(LocalDateTime.now());
        System.out.println(s);
    }
}
