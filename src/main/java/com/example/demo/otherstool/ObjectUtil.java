package com.example.demo.otherstool;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class ObjectUtil {

    /**
     * 判断对象是否空
     *
     * @param obj
     * @return
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List)) {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof Map)) {
            return ((Map) obj).size() == 0;
        }
        if ((obj instanceof BigDecimal)) {
            return ((BigDecimal) obj).compareTo(BigDecimal.ZERO) == 0;
        }
        if ((obj instanceof Integer)) {
            return ((Integer) obj) == 0;
        }
        if ((obj instanceof String)) {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 把列表按指定的长度拆分-->到集合
     * created by: wangzh 2018-09-22 09:02
     * @param list       列表
     * @param groupSize  页长度,如:100
     * @return
     */
    public static <T> List<List<T>> splitToList(List<T> list , int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        int num = ( length + groupSize - 1 )/groupSize ,fromIndex=0,toIndex=0;
        List<List<T>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            fromIndex = i * groupSize;
            // 结束位置
            toIndex = (i+1) * groupSize < length ? ( i+1 ) * groupSize : length ;
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }
    /**
     * 把列表按指定的长度拆分-->到MAP
     * created by: wangzh 2018-09-22 09:02
     * @param list       列表
     * @param groupSize  页长度,如:100
     * @return
     */
    public static <T> Map<Integer, List<T>> splitToMap(List<T> list, int groupSize) {
        Map<Integer, List<T>> map = new LinkedHashMap<>();
        int length = list.size();
        // 计算可以分成多少组
        int num = (length + groupSize - 1) / groupSize,fromIndex=0,toIndex=0;
        for (int i = 0; i < num; i++) {
            // 开始位置
            fromIndex = i * groupSize;
            // 结束位置
            toIndex = (i + 1) * groupSize < length ? (i + 1) * groupSize : length;
            map.put(i,list.subList(fromIndex, toIndex));
        }
        return map;
    }
}
