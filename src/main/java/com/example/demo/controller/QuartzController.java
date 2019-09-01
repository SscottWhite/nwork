package com.example.demo.controller;

import com.example.demo.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/thread")
    public void testThread(){
        threadService.testThread();
    }


}
