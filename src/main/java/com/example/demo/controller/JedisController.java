package com.example.demo.controller;

import com.example.demo.otherstool.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jedis")
public class JedisController {

    @RequestMapping(value = "/get")
    public void getJedis(){
        JedisUtil.set("key","112233");
        System.out.println(JedisUtil.get("key"));
    }


    @RequestMapping(value = "/d")
    public void get(){

    }


}
