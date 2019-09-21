package com.zhao.demo.common.exercise.arithmetic;


import java.util.Random;

/**
 * 排序算法
 *
 * @author wangz
 * @create 2019/4/16
 */
public class SortArithmetic {

    public static int[] array;

    public static final int NUM =1000000;

    static {
        array = new int[NUM];
        for (int i = 0; i < NUM; i++) {
            array[i] = new Random().nextInt(NUM *10);
        }
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertSort(int[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }

    /**
     * 快速排序
     * @param array
     */
    public static void quickSort(int[] array){
        quickSort(array,0,array.length-1);
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        if (array.length <= 20) {
            insertSort(array);
            return;
        }

        int temp = array[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (array[j] >= temp && i < j) {
                j--;
            }
            array[i] = array[j];
            while (array[i] <= temp && i < j) {
                i++;
            }
            array[j] = array[i];
        }
        array[i] = temp;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static void print(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int a : array) {
            sb.append(a);
            sb.append(",");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public static void main(String[] args) {
//        print(array);
        System.out.println(SortArithmetic.NUM);
        long start = System.currentTimeMillis();


//        SortArithmetic.bubbleSort(array);
//        SortArithmetic.insertSort(array);
        SortArithmetic.quickSort(array);


        long useTime = System.currentTimeMillis() - start;
        System.out.println("用时:" + useTime + "ms");
//        print(array);
    }
}
