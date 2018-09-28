package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ExcelUtil
 * @Description: TODO
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2016年12月15日 下午4:43:08
 * 
 */
@Slf4j
public class ExcelUtil {

  /**
   * 读取 excel 文件
   * 
   * @param file
   * @return
   */
  public static List<List<String>> parse(MultipartFile file) {
    List<List<String>> result = new ArrayList<List<String>>();
    if (null == file) {
      return result;
    }
    InputStream fis = null;
    try {
      fis = file.getInputStream();
      if (file.getOriginalFilename().indexOf(".xlsx") > -1) {
        result = parseXLSX(fis);
      } else {
        result = parseXLS(fis);
      }
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
      }
    }
    return result;
  }
  
  /**
   * 读取输入流并返回集合
   * 
   * @param fis
   * @return
   */
  private static List<List<String>> parseXLS(InputStream fis) {
    List<List<String>> datas = new ArrayList<List<String>>();
    HSSFWorkbook book = null;
    try {
      book = new HSSFWorkbook(fis);
      HSSFSheet sheet = book.getSheetAt(0);
      int firstRow = sheet.getFirstRowNum();
      int lastRow = sheet.getLastRowNum();
      String cellValue;
      log.debug("firstRow:" + firstRow + ",lastRow:" + lastRow);
      // 获取最大列 
      int lastCellNum = sheet.getRow(0).getLastCellNum();
      // 除去表头和第一行
      for (int i = firstRow; i < lastRow + 1; i++) {
        List<String> data = new ArrayList<String>();
        HSSFRow row = sheet.getRow(i);
        int firstCell = 0;
        for (int j = firstCell; j < lastCellNum; j++) {
          HSSFCell cell2 = row.getCell(j);
          cellValue = null;
          if (cell2 != null) {
            switch (cell2.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
              cellValue = cell2.getStringCellValue();
              if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
                cellValue = "";
              break;
            case HSSFCell.CELL_TYPE_NUMERIC:
              // TODO 数字格式
              if (HSSFDateUtil.isCellDateFormatted(cell2)) {
                Date date = cell2.getDateCellValue();
                cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
              } else {
                java.text.DecimalFormat formatter = new java.text.DecimalFormat("########.####");
                cellValue = formatter.format(cell2.getNumericCellValue());
                checkDouble(cellValue);
                BigDecimal b = new BigDecimal(Double.valueOf(cellValue));
                cellValue = formatter.format(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
              }
              break;
            case HSSFCell.CELL_TYPE_FORMULA:
              cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
              cellValue = String.valueOf(cell2.getNumericCellValue());
              break;
            case HSSFCell.CELL_TYPE_BLANK:
              cellValue = "";
              break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
              break;
            case HSSFCell.CELL_TYPE_ERROR:
              break;
            default:
              break;
            }
          }
          data.add(cellValue);
        }
        // TODO 解决空行的问题
        if (!data.isEmpty()) {
          Boolean flag = true;
          for (String str : data) {
            if (StringUtils.isNotBlank(str)) {
              flag = false;
              break;
            }
          }
          if (!flag) {
            datas.add(data);
          }
        }
      }
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    } finally {
      if (book != null) {
        try {
          book.close();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
      }
    }
    return datas;
  }
  
  /**
   * 读取输入流并返回集合
   * 
   * @param fis
   * @return
   */
  private static List<List<String>> parseXLSX(InputStream fis) {
    List<List<String>> datas = new ArrayList<List<String>>();
    XSSFWorkbook book = null;
    try {
      book = new XSSFWorkbook(fis);
      XSSFSheet sheet = book.getSheetAt(0);
      int firstRow = sheet.getFirstRowNum();
      int lastRow = sheet.getLastRowNum();
      String cellValue;
      log.debug("firstRow:" + firstRow + ",lastRow:" + lastRow);
      // 获取最大列 
      int lastCellNum = sheet.getRow(0).getLastCellNum();
      // 除去表头和第一行
      for (int i = firstRow; i < lastRow + 1; i++) {
        List<String> data = new ArrayList<String>();
        XSSFRow row = sheet.getRow(i);
        int firstCell = 0;
        for (int j = firstCell; j < lastCellNum; j++) {
          XSSFCell cell2 = row.getCell(j);
          cellValue = null;
          if (cell2 != null) {
            switch (cell2.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
              cellValue = cell2.getStringCellValue();
              if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
                cellValue = "";
              break;
            case XSSFCell.CELL_TYPE_NUMERIC:
              // TODO 数字格式
              if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell2)) {
                Date date = cell2.getDateCellValue();
                cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
              } else {
                java.text.DecimalFormat formatter = new java.text.DecimalFormat("########.####");
                cellValue = formatter.format(cell2.getNumericCellValue());
                checkDouble(cellValue);
                BigDecimal b = new BigDecimal(Double.valueOf(cellValue));
                cellValue = formatter.format(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
              }
              break;
            case XSSFCell.CELL_TYPE_FORMULA:
              cell2.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
              cellValue = String.valueOf(cell2.getNumericCellValue());
              break;
            case XSSFCell.CELL_TYPE_BLANK:
              cellValue = "";
              break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
              break;
            case XSSFCell.CELL_TYPE_ERROR:
              break;
            default:
              break;
            }
          }
          data.add(cellValue);
        }
        // TODO 解决空行的问题
        if (!data.isEmpty()) {
          Boolean flag = true;
          for (String str : data) {
            if (StringUtils.isNotBlank(str)) {
              flag = false;
              break;
            }
          }
          if (!flag) {
            datas.add(data);
          }
        }
      }
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    } finally {
      if (book != null) {
        try {
          book.close();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
      }
    }
    return datas;
  }
  
  /**
   * 检测值是否为科学计数法
   * 
   * @param value
   * @throws BusinessException
   */
  private static void checkDouble(final String value) {
    if (null != value && value.indexOf(".") != -1 && value.indexOf("E") != -1) {
      throw new RuntimeException("Excel中不可以存在科学计数法！");
    }
  }
  
  
  /**
   * 导出 Excel
   * 
   * @param response
   * @param fileName excel 名
   * @param sheetName 叶签名称
   * @param tableName 第一行
   * @param data	数据
   */
  public static void downloadXLS(HttpServletResponse response, String fileName, String sheetName, String[] tableName, List<List<String>> data) {
    OutputStream ouputStream = null;
    try {
      HSSFWorkbook workbook = generateXLS(data, sheetName, tableName);
      response.setContentType("application/vnd.ms-excel");
      response.addHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1") + ".xls");
      ouputStream = response.getOutputStream();
      workbook.write(ouputStream);
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    } finally {
      if (ouputStream != null) {
        try {
          ouputStream.flush();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
        try {
          ouputStream.close();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
      }
    }
  }
  
  public static void downloadXLSX(HttpServletResponse response, String fileName, String sheetName, String[] tableName, List<List<String>> data) {
    OutputStream ouputStream = null;
    try {
      XSSFWorkbook workbook = generateXLSX(data, sheetName, tableName);
      response.setContentType("application/vnd.ms-excel");
      response.addHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1") + ".xlsx");
      ouputStream = response.getOutputStream();
      workbook.write(ouputStream);
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    } finally {
      if (ouputStream != null) {
        try {
          ouputStream.flush();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
        try {
          ouputStream.close();
        } catch (IOException e) {
          String fullStackTrace = ExceptionUtils.getStackTrace(e);
          log.error(fullStackTrace);
        }
      }
    }
  }

  /**
   * 组装 Excel 对象
   * 
   * @param datas
   * @param sheetName
   * @param tableName
   * @return
   */
  private static HSSFWorkbook generateXLS(List<List<String>> datas, String sheetName, String[] tableName) {
    HSSFWorkbook book = new HSSFWorkbook();
    try {
      HSSFSheet sheet = book.createSheet(sheetName);
      sheet.autoSizeColumn(1, true);// 自适应列宽度

      // 填充表头标题
      HSSFRow firstRow = sheet.createRow(0);// 第几行（从0开始）
      for (int i = 0; i < tableName.length; i++) {
        firstRow.createCell(i).setCellValue(tableName[i]);
      }

      // 填充表格内容
      if (datas != null && !datas.isEmpty()) {
        for (int i = 0; i < datas.size(); i++) {
          HSSFRow row2 = sheet.createRow(i + 1);// index：第几行
          List<String> data = datas.get(i);
          for (int j = 0; j < data.size(); j++) {
            HSSFCell cell = row2.createCell(j);// 第几列：从0开始
            //设置单元格内容为字符串型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(data.get(j));
          }
        }
      }
      // book.write(fos);
      // fos.close();
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    }
    return book;
  }
  
  private static XSSFWorkbook generateXLSX(List<List<String>> datas, String sheetName, String[] tableName) {
    XSSFWorkbook book = new XSSFWorkbook();
    try {
      XSSFSheet sheet = book.createSheet(sheetName);
      sheet.autoSizeColumn(1, true);// 自适应列宽度

      // 填充表头标题
      XSSFRow firstRow = sheet.createRow(0);// 第几行（从0开始）
      for (int i = 0; i < tableName.length; i++) {
        firstRow.createCell(i).setCellValue(tableName[i]);
      }

      // 填充表格内容
      if (datas != null && !datas.isEmpty()) {
        for (int i = 0; i < datas.size(); i++) {
          XSSFRow row2 = sheet.createRow(i + 1);// index：第几行
          List<String> data = datas.get(i);
          for (int j = 0; j < data.size(); j++) {
            XSSFCell cell = row2.createCell(j);// 第几列：从0开始
            //设置单元格内容为字符串型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(data.get(j));
          }
        }
      }
      // book.write(fos);
      // fos.close();
    } catch (Exception e) {
      String fullStackTrace = ExceptionUtils.getStackTrace(e);
      log.error(fullStackTrace);
    }
    return book;
  }

  
	/**
	 * 导出 Excel
	 * @param fileName:excel 名
	 * @param sheetName:叶签名称
	 * @param tableName:第一行
	 * @param data:数据
	 */
	public static void downloadXLSList(HttpServletResponse response, String fileName, String sheetName, String[] tableName, List<List<String>> data,
                                       String sheetNameTwo, String[] tableNameTwo, List<List<String>> dataTwo) {
		OutputStream ouputStream = null;
		try {
			HSSFWorkbook workbook = generateXLSList(data, sheetName, tableName,dataTwo, sheetNameTwo, tableNameTwo);
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition","attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1") + ".xls");
			ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.flush();
				} catch (IOException e) {
					String fullStackTrace = ExceptionUtils.getStackTrace(e);
					log.error(fullStackTrace);
				}
				try {
					ouputStream.close();
				} catch (IOException e) {
					String fullStackTrace = ExceptionUtils.getStackTrace(e);
					log.error(fullStackTrace);
				}
			}
		}
	}

	/**
	 * 组装 Excel 对象
	 * @param datas
	 * @param sheetName
	 * @param tableName
	 */
	private static HSSFWorkbook generateXLSList(List<List<String>> datas, String sheetName, String[] tableName,
			List<List<String>> datasTwo, String sheetNameTwo, String[] tableNameTwo) {
		HSSFWorkbook book = new HSSFWorkbook();
		try {
			HSSFSheet sheet = book.createSheet(sheetName);
			sheet.autoSizeColumn(1, true);// 自适应列宽度
			// 填充表头标题
			HSSFRow firstRow = sheet.createRow(0);// 第几行（从0开始）
			for (int i = 0; i < tableName.length; i++) {
				firstRow.createCell(i).setCellValue(tableName[i]);
			}
			//填充表格内容
			if (datas != null && !datas.isEmpty()) {
				for (int i = 0; i < datas.size(); i++) {
					HSSFRow row2 = sheet.createRow(i + 1);// index：第几行
					List<String> data = datas.get(i);
					for (int j = 0; j < data.size(); j++) {
						HSSFCell cell = row2.createCell(j);// 第几列：从0开始
						// 设置单元格内容为字符串型
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(data.get(j));
					}
				}
			}
			
			//第二个叶签
			HSSFSheet sheetTwo = book.createSheet(sheetNameTwo);
			sheetTwo.autoSizeColumn(1, true);//自适应列宽度
			// 填充表头标题
			HSSFRow oneRow = sheetTwo.createRow(0);// 第几行（从0开始）
			for (int i = 0; i < tableNameTwo.length; i++) {
				oneRow.createCell(i).setCellValue(tableNameTwo[i]);
			}
			//填充表格内容
			if (datasTwo != null && !datasTwo.isEmpty()) {
				for (int i = 0; i < datasTwo.size(); i++) {
					HSSFRow row2 = sheetTwo.createRow(i + 1);// index：第几行
					List<String> data = datasTwo.get(i);
					for (int j = 0; j < data.size(); j++) {
						HSSFCell cell = row2.createCell(j);// 第几列：从0开始
						// 设置单元格内容为字符串型
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(data.get(j));
					}
				}
			}
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		}
		return book;
	}
  
}
