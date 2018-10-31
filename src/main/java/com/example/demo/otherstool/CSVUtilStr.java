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
 *
 * CSV文件导出工具类
 *
 * Created on 2014-08-07
 * @author
 * @reviewer
 */
@Slf4j
public class CSVUtilStr {

    /**
     * CSV文件生成方法
     * @param head
     * @param dataList
     * @param outPutPath
     * @param filename
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename, HttpServletResponse response) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            Long start = new Date().getTime();
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // csvWtriter = new BufferedWriter(new OutputStreamWriter(baos, "gb2312"));
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
            Long start2 = new Date().getTime();
            log.debug("csv生成耗时:"+String.valueOf(start2 - start));


            File zipFile = new File(outPutPath + File.separator + filename + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOut.putNextEntry(new ZipEntry(csvFile.getName())) ;
            InputStream input = new FileInputStream(csvFile) ;
            int temp = 0 ;
            while((temp=input.read())!=-1){	// 读取内容
                zipOut.write(temp) ;	// 压缩输出
            }
            input.close() ;	// 关闭输入流
            zipOut.close() ;

            log.debug("zip生成耗时:"+String.valueOf(new Date().getTime() - start2));

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ".zip\"; filename*=utf-8''" + filename+".zip");
            FileCopyUtils.copy(new FileInputStream(zipFile), response.getOutputStream());
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

    public static void main(String[] args) {
        String str = "abcdefg";
        String ste1 = str.substring(str.length() - 4);
        System.out.println(ste1);
    }

}
