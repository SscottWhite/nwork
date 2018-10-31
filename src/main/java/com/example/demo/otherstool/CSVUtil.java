package com.example.demo.otherstool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * CSV文件导出工具类
 */
@Slf4j
public class CSVUtil {

   /* public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename, HttpServletResponse response) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            csvFile = new File(outPutPath + File.separator + filename + ".csv");

            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(baos, "gb2312"));  //new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ".csv\"; filename*=utf-8''" + filename+".csv");
            FileCopyUtils.copy(baos.toByteArray(), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    */
   /*
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }*/

    public static File createCSVFile(final List<Object> head, List<List<Object>> dataList,
                                     final String outPutPath,final String filename) {

        File csvFile = null;
        File zipFile = null;
        BufferedWriter csvWtriter = null;
        //File.separator类似于分隔符
        final String fileName = outPutPath + File.separator + filename;

        try {
            zipFile = new File(fileName+".zip");
            final ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOut.setLevel(0);//压缩等级,数字越高文件越小 0-9
            //zipOut.


            csvFile = new File(fileName + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile)
                    , "GB2312"), 1024);
            writeRow(head, csvWtriter);
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
            zipOut.putNextEntry(new ZipEntry(csvFile.getName())) ;
            InputStream input = new FileInputStream(csvFile) ;
            int temp = 0 ;
            while((temp=input.read())!=-1){	// 读取内容
                zipOut.write(temp) ;	// 压缩输出
            }
            input.close() ;	// 关闭输入流
            zipOut.close() ;
            csvWtriter.close();

            csvFile.delete();//多一个文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return zipFile;
    }



    private static void doZip(Integer index,ZipOutputStream zipOut,String filename,List<Object> head, List<List<Object>> dataList){
        try{
            log.debug("开始压缩线程"+index);
            zipOut.setLevel(0);
            log.debug(FastJsonUtil.toJsonString(zipOut));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            File csvFile = new File(filename+index+".csv");
            Long start = new Date().getTime();
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            log.debug("创建csv");
            BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            writeRow(head, csvWtriter);
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
            Long start2 = new Date().getTime();
            log.debug("开始压缩csv");


            zipOut.putNextEntry(new ZipEntry(filename+index+".csv")) ;
            InputStream input = new FileInputStream(csvFile) ;
            int temp = 0 ;
            while((temp=input.read())!=-1){	// 读取内容
                zipOut.write(temp) ;	// 压缩输出
            }
            input.close() ;	// 关闭输入流
            zipOut.close() ;
            csvWtriter.close();
            log.debug("zip生成耗时:"+String.valueOf(new Date().getTime() - start2));
        }catch (Exception e){
            log.debug(StringUtil.getExecptionMessage(e));
        }
    }

    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}
