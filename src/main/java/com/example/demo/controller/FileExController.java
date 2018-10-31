package com.example.demo.controller;

import com.example.demo.otherstool.CSVUtil;
import com.example.demo.otherstool.CSVUtilStr;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileExController {

    @RequestMapping("/ex")
    public void fileExchange(HttpServletResponse response){
        List<Object> lsit = Arrays.asList("吃饭","树胶");
        List<Object> listS = Arrays.asList("1","2");
        List<List<Object>> listSS = new ArrayList<>();
        listSS.add(listS);
        CSVUtil.createCSVFile(lsit,listSS,"D:\\","ss");
    }

    public static void main(String[] args) {
       /* FileOutputStream fos = null;

        try{
            File file = new File("D:\\twt.txt");
            fos = new FileOutputStream(file,true);//true 表示不覆盖原有内容
            if(!file.exists()){
                file.mkdirs();
            }
            String str = ",试试1";
            byte by[] = str.getBytes();
            fos.write(by);

        }catch (Exception e){

        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        FileInputStream fis = null;

        try{
            File file = new File("D:\\twt.txt");
            if (!file.exists()) {
                return;
            }
            fis = new FileInputStream(file);
            byte by[] = new byte[1024];
            int i = 0;
            while((i=fis.read(by)) != -1){
                String str = new String(by,0,i);
                System.out.println(str);
            }

        }catch (Exception e){

        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
