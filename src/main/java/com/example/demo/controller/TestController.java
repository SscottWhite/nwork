package com.example.demo.controller;

import com.example.demo.utils.ExcelUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestController {

    /*public static void main(String[] args) {
        int array[] = new int[]{2,3,41,4,21,1,20};
       // System.out.println(array.length);//5

        //最后一个保持不变,每次后比前少一位,都是从0开始,比大小,大的往后挪,所以最后一位肯定是最大了,不需要再动
        for(int i = 0 ; i < array.length ; i++){//i最大4
            for(int j= 0 ; j < array.length-1-i ; j++){ //j最大是3,所以i+1=4肯定有值
                int temp = 0;
                if(array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }*/

    /*//一个循环
    public static void main(String[] args) {
        int array[] = new int[]{2,3,41,4,21,1,20};
        //System.out.println(array.length);

        int len = array.length-1;
        int temp = 0;
        for (int i = 0;i < len ; i++){
            if(array[i] > array[i+1]){
                temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
            //其实原理和上面一样,保证从0开始,长度比较少一位
            if (i == len - 1) {
                i = -1;//有i++,保证从0 开始
                len--;
            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }*/

    /*public static void main(String[] args) {
        //插入排序
        int array[] = new int[]{49,38,65,97,76,13,27,49,78,34,12,64,1};
        for(int i = 1; i<array.length ; i++){
            int temp = array[i];
            int j;
            for(j = i-1;j>=0;j--){
                if(array[j] > temp){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1] = temp;
        }
        for (int i : array) {
            System.out.println(i);
        }
    }*/

   // public static void main(String[] args) {
        /*List<List<String>> data = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("网点编号");
        list.add("货号");
        list.add("色号");
        data.add(list);
        list.clear();
        list.add("ZB92");
        list.add("X80130006B");
        list.add("1132");
        data.add(list);

       // ExcelUtil.downloadXLS();
     }*/
        /*Map map = Collections.synchronizedMap(new LinkedHashMap<>());//Link不安全,可以用同步包
        //Map map1 = Collections.singletonMap(new HashMap<>())
        Map map2 = new ConcurrentHashMap();

        Double x = Math.ceil(1040/500);
        System.out.println(x);*/
        /*int i = 8;
        List<Integer> list = Arrays.asList(1,2,3,4);
        if(list.contains(i)){
            System.out.println("good");
        }else{
            System.out.println("nogood");
        }*/
   // }


   /* public static void main(String[] args) throws InterruptedException {
        long timeB = System.currentTimeMillis();
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,6,7,8,8,9);
        List<List<Integer>> list1 = Lists.partition(list,3);
        System.out.println(list1);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (final List<Integer> it : list1) {
            final Object lock = new Object();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (Integer its : it) {
                        synchronized (lock){
                            System.out.println(its);
                        }
                    }
                }
            };
            exec.submit(task);
        }
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.MINUTES);
        long timeA = System.currentTimeMillis();
        System.out.println(timeA-timeB);
        *//*Map<String,Object> map = new HashMap();
        map.put(null,null);
        System.out.println(map.containsKey(null));
        System.out.println(map.size());*//*




    }*/

    public static void main(String[] args) {
       // System.out.println(((true && true) || (true || false)) && (true && true));
        int i ;
        int w = 0;
        for( i = 0; i<7 ;i++){
            i = i+2;
            w = i % 2;
        }
        System.out.println(i+","+w);
    }
}
