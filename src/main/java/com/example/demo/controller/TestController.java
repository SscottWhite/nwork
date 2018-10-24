package com.example.demo.controller;

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

    //一个循环
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
    }
}
