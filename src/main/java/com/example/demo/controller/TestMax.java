package com.example.demo.controller;

public class TestMax {

    /**
     * 大整数相加
     * @return
     */
    public static String bigNumberSum(String bigNumberA,String bigNumberB){
        int maxLength = bigNumberA.length() > bigNumberB.length()
                ? bigNumberA.length()
                : bigNumberB.length();
        //取第一个字符段组数
        int[] arrayA = new int[maxLength+1];
        for (int i = 0; i < bigNumberA.length(); i++) {
            arrayA[i] = bigNumberA.charAt(bigNumberA.length()-1-i)-'0';
        }
        //取第二个字符段做数组
        int[] arrayB = new int[maxLength+1];
        for (int i = 0; i < bigNumberB.length(); i++) {
            arrayB[i] = bigNumberB.charAt(bigNumberB.length()-1-i)-'0';
        }
        //整合数组相加
        int[] result = new int[maxLength];
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];

            if (temp >= 10){
                temp = temp -10;
                result[i+1] = 1;
            }
            result[i] = temp;
        }
        //然后把这个数组合并成字符
        StringBuilder sb = new StringBuilder();
        boolean findFirst = false;
        for (int i = result.length - 1; i >0; i--) {
            if(!findFirst){
                if(result[i] == 0){
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(bigNumberSum("4444444444445555444","88555555555555"));
    }
}
