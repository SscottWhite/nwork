package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class JedisService {

    public void get() throws InterruptedException {
        /*ExecutorService exec = Executors.newFixedThreadPool(100);
        final Object obt = new Object();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                synchronized(obt){

                }
            }
        };
        exec.submit(task);
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.MILLISECONDS);*/

        /*ExecutorService exec = Executors.newCachedThreadPool();
        Runnable task = new Runnable() {
            @Override
            public void run() {

            }
        };*/

        ExecutorService exec = Executors.newScheduledThreadPool(5);
        ((ScheduledExecutorService) exec).schedule(new Runnable() {
            @Override
            public void run() {

            }
        },1,TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newScheduledThreadPool(6);
        ((ScheduledExecutorService) exec).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        },5,TimeUnit.SECONDS);
    }
}
