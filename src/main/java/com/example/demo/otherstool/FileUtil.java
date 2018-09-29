package com.example.demo.otherstool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.*;

@Slf4j
public class FileUtil {
	
  public final static String gif = "GIF";
  public final static String jpg = "JPG";
  public final static String png = "PNG";
  public final static String bmp = "BMP";
  public final static String txt = "txt";
  public final static String docx = "DOCX";
  public final static String xlsx = "XLSX";
  public final static String jpeg = "JPEG";
  public final static Long fileSize = 20 * 1024 * 1024L;
  public final static Long imageSize = 5 * 1024 * 1024L;
	
  // 图片 JPEG、JPG、PNG、BMP
  public static final String JPG = "FFD8FF";
  public static final String WEIXINJPG = "EFBFBDEF";
  public static final String JPEG = "FFD8FF";
  public static final String PNG = "89504E47";
  public static final String BMP = "424D";
  public static final String BMP2 = "424D9E5F";
  
  
  // 附件 DOC、DOCX、XLS、XLSX、PDF、TXT、RAR、ZIP、JPG、GIF、JPEG、PNG
  public static final String DOC = "D0CF11E0";
  public static final String DOCX = "D0CF11E0";
  public static final String XLS = "D0CF11E0";
  public static final String XLSX = "D0CF11E0";
  public static final String PDF = "25504446";
  public static final String RAR = "52617221";
  public static final String ZIP = "504B0304";
  public static final String GIF = "47494638";
  
  /**
   * 根据文件名获取文件后缀
   * 
   * @author tianzhiqiang
   * @param fileName
   * @return
   */
  public static String getFileSuffix(String fileName) {
    String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
    fileSuffix = fileSuffix.toLowerCase();
    return fileSuffix;
  }

  /**
   * 根据文件名称获取前缀
   * 
   * @author tianzhiqiang
   * @param fileName
   * @return
   */
  public static String getFilePrefixName(String fileName) {
    String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
    return prefixName;
  }

  /**
   * 校验图片格式为JPG、JPEG、PNG、BMP、WEIXINJPG
   * 
   * @param fileName
   */
  public static boolean checkPhotoType(byte[] bytes) {
    if (bytes == null || bytes.length < 4) {
      return false;
    }

    byte[] b = new byte[4];
    System.arraycopy(bytes, 0, b, 0, 4);
    String type = bytesToHexString(b).toUpperCase();

    if (type.contains(JPG) || type.contains(PNG) || type.contains(BMP) || type.contains(BMP2) || type.contains(WEIXINJPG)) {
      return true;
    }
    return false;
  }

  /**
   * 返回图片类型 JPG、JPEG、PNG、BMP
   * 
   * @param bytes
   * @return
   */
  public static String getPhotoType(byte[] bytes) {
    if (bytes == null || bytes.length < 4) {
      return null;
    }
    byte[] b = new byte[4];
    System.arraycopy(bytes, 0, b, 0, 4);
    String type = bytesToHexString(b).toUpperCase();
    if (type.contains(JPG)) {
      return jpg;
    } else if (type.contains(PNG)) {
      return png;
    } else if (type.contains(BMP)) {
      return bmp;
    }else if (type.contains(BMP2)) {
        return bmp;
    }else if (type.contains(WEIXINJPG)) {
        return jpg;
    } else {
      return null;
    }
  }

  // public static boolean checkPNGPhotoType(byte[] bytes) {
  // if (bytes == null || bytes.length < 4) {
  // return false;
  // }
  // byte[] b = new byte[4];
  // System.arraycopy(bytes, 0, b, 0, 4);
  // String type = bytesToHexString(b).toUpperCase();
  // if (type.contains(PNG) || type.contains(GIF)) {
  // return true;
  // }
  // return false;
  //
  // }

  /**
   * 校验文件格式为DOC、DOCX、XLS、XLSX、PDF、TXT、RAR、ZIP、JPG、GIF、JPEG、PNG
   */
  public static boolean checkFileType(byte[] bytes, String fileName) throws IOException {

    // txt文件特殊校验方式，判断后缀后续有好方法请补充
    String lowerCase = fileName.toLowerCase();
    String stuffix = getFileSuffix(lowerCase);
    if (stuffix.equals(txt)) {
      return true;
    }

    if (bytes == null || bytes.length < 4) {
      return false;
    }

    byte[] b = new byte[4];
    System.arraycopy(bytes, 0, b, 0, 4);
    String type = bytesToHexString(b).toUpperCase();

    if (type.contains(DOC) || type.contains(DOCX) || type.contains(XLS) || type.contains(XLSX) || type.contains(PDF)
        || type.contains(RAR) || type.contains(ZIP) || 
        type.contains(BMP2) || 
        type.contains(JPG) || type.contains(PNG) ||  type.contains(WEIXINJPG)) {
      return true;
    }
    return false;
  }

  /**
   * 校验文件格式为DOC、DOCX、XLS、XLSX、PDF、TXT、JPG、GIF、JPEG、PNG、WEIXINJPG
   */
  public static boolean checkFileType2(byte[] bytes, String fileName) throws IOException {

    // txt文件特殊校验方式，判断后缀后续有好方法请补充
    String lowerCase = fileName.toLowerCase();
    String stuffix = getFileSuffix(lowerCase);
    if (stuffix.equals(txt)) {
      return true;
    }

    if (bytes == null || bytes.length < 4) {
      return false;
    }

    byte[] b = new byte[4];
    System.arraycopy(bytes, 0, b, 0, 4);
    String type = bytesToHexString(b).toUpperCase();

    if (type.contains(DOC) || type.contains(DOCX) || type.contains(XLS) || type.contains(XLSX) || type.contains(PDF)
        || type.contains(JPEG) 
        || type.contains(BMP2) 
        || type.contains(JPG) || type.contains(PNG) || type.contains(WEIXINJPG)) {
      return true;
    }
    // xlsx docx 其实是zip的头
    if ((stuffix.equals(xlsx) || stuffix.equals(docx)) && type.contains(ZIP)) {
      return true;
    }

    return false;
  }

  /**
   * 二进制文件转成字符串格式标记
   * 
   * @param src
   * @return
   */
  public static String bytesToHexString(byte[] b) {
    StringBuilder stringBuilder = new StringBuilder();
    if (b == null || b.length <= 0) {
      return null;
    }
    for (int i = 0; i < b.length; i++) {
      int v = b[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }

  /**
   * 检查文件是否大于20M
   * 
   * @author tianzhiqiang
   * @param fileSize
   */
  public static boolean checkFileSize(Long fSize) {
    if (fSize < fileSize) {
      return true;
    }
    return false;
  }
  
 
  /**
   * 检查图片是否大于5M
   * 
   * @param imgSize
   * @return
   */
  public static boolean checkImageSize(Long imgSize) {
    if (imgSize < imageSize) {
      return true;
    }
    return false;
  }

  /**
   * 校验文件格式(动态)
   * 
   * @param bytes
   * @param fileTypeList
   * @author zhoujun-a
   */
  public static boolean checkPhotoTypeDynamic(byte[] bytes, String[] fileTypeList) {
    if (bytes == null || bytes.length < 4 || fileTypeList == null || fileTypeList.length < 1) {
      return false;
    }

    byte[] b = new byte[4];
    System.arraycopy(bytes, 0, b, 0, 4);
    String type = bytesToHexString(b).toUpperCase();

    for (String fileType : fileTypeList) {
      if (type.contains(fileType)) {
        return true;
      }
    }
    return false;
  }

  
  /**
   * 通过字节流保存文件
   * @param b
   * @param outputFile
   * @return
   */
  public static Boolean saveFileFromBytes(byte[] b, String outputFile) {
    return saveFileFromBytes(b, outputFile, Boolean.FALSE);
  }
  
  /**
   * 通过字节流保存文件
   * @param b
   * @param outputFile
   * @param isCover 是否覆盖原文件
   * @return
   */
  public static Boolean saveFileFromBytes(byte[] b, String outputFile, Boolean isCover) {
    BufferedOutputStream stream = null;
    try {
      File file = new File(outputFile);
      if (file.exists() && !isCover) {
        return Boolean.FALSE; // 源文件已经存在
      }
      FileOutputStream out = new FileOutputStream(file);
      stream = new BufferedOutputStream(out);
      stream.write(b);
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
      return Boolean.FALSE;
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
          return Boolean.FALSE;
        }
      }
    }
    return Boolean.TRUE;
  }
  
  
  public static String readTxt(String filePath) {

	  StringBuilder sb = new StringBuilder();
	  try {
	    File file = new File(filePath);
	    if(file.isFile() && file.exists()) {
	      InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
	      BufferedReader br = new BufferedReader(isr);
	      String lineTxt = null;
	      while ((lineTxt = br.readLine()) != null) {
	    	  sb.append(lineTxt);
	    	  //System.out.println(lineTxt);
	      }
	      br.close();
	    } else {
	      System.out.println("文件不存在!");
	    }
	  } catch (Exception e) {
	    System.out.println("文件读取错误!");
	  }
	  return sb.toString();
  }
  
}
