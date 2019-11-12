package com.zhao.demo.common.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LocalDateTimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 转换时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime 时间
     * @return 时间字符串
     */
    public static String formatDate(LocalDateTime dateTime) {
        return FORMATTER.format(dateTime);
    }

    /**
     * 时间字符串转时间
     * @param dateTime 时间字符串
     * @return 时间对象
     */
    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime,FORMATTER);
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
     * @return 时间对象 LocalDateTime
     */
    public static LocalDateTime firstDayTimeOfMonth() {
        LocalDate localDate = firstDayOfMonth();
        return localDate.atStartOfDay();
    }

    /**
     * 获取当月月末时间
     * @return 时间对象 LocalDate
     */
    public static LocalDate lastDayOfMonth() {
        LocalDate now = LocalDate.now();
        return now.with(TemporalAdjusters.lastDayOfMonth());
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                try {
                    System.out.println(formatDate(LocalDateTime.now()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
