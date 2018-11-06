package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

public abstract class MapService {

    public static Map<Integer,String> map = new HashMap<>();

    static{
        map.put(1,"你好");
        map.put(2,"hello");
        map.put(3,"connijiwa");
    }
    public String getKey(Integer it){
        return map.get(it);
    }

}
