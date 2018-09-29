package com.example.demo.otherstool;

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
import java.util.*;

/**
 * @ClassName: ExcelSheetUtil
 * @Description: excel 工具类（带Sheet）
 * @author: Chen Xinjie
 * @date: 2018年8月23日 下午1:45:07
 */
@Slf4j
public final class ExcelSheetUtil {

	private ExcelSheetUtil() {
		throw new IllegalStateException("Utility class");
	}
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	private static final String CONTENT_DIS = "Content-disposition";
	private static final String CONTENT_SRT = "attachment;filename=";
	private static final String GBK = "GBK";
	private static final String ISO = "ISO8859-1";
	private static final String XLS = ".xls";
	private static final String XLSX = ".xlsx";
	private static final String POINT = ".";
	private static final String E = "E";
	private static final String MSG = "Excel中不可以存在科学计数法！";

	/**
	 * @Description: 读取excel（带Sheet）
	 * @param: @param file
	 * @param: @return
	 * @return: Map<String,List<List<String>>>
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午1:58:18
	 * @throws
	 */
	public static Map<String, List<List<String>>> parseSheet(MultipartFile file) {
		Map<String, List<List<String>>> result = new HashMap<>();
		if (null == file) {
			return result;
		}
		InputStream fis = null;
		try {
			fis = file.getInputStream();
			if (file.getOriginalFilename().indexOf(".xlsx") > -1) {
				result = parseSheetXLSX(fis);
			} else {
				result = parseSheetXLS(fis);
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
	 * @Description: 解析XLS（带Sheet）
	 * @param: @param fis
	 * @param: @return
	 * @return: Map<String,List<List<String>>>
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午1:59:01
	 * @throws
	 */
	private static Map<String, List<List<String>>> parseSheetXLS(InputStream fis) {
		Map<String, List<List<String>>> result = null;
		HSSFWorkbook book = null;
		HSSFSheet sheet = null;
		try {
			book = new HSSFWorkbook(fis);
			// 获取 Sheet 数量；
			List<String> data;
			List<List<String>> datas;
			result = new HashMap<>();
			for (int sheetAct = 0; sheetAct < book.getNumberOfSheets(); sheetAct++) {
				sheet = book.getSheetAt(sheetAct);
				if (sheet != null) {
					datas = new ArrayList<>();
					// 除去表头和第一行
					for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) {
						data = new ArrayList<>();
						for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
							data.add(getCellValue(sheet.getRow(i).getCell(j)));
						}
						if (isNotEmpty(data)) {
							datas.add(data);
						}
					}
					result.put(sheet.getSheetName(), datas);
				}
			}
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		} finally {
			bookClose(book);
			fisClose(fis);
		}
		return result;
	}

	/**
	 * @Description: 解析XLSX（带Sheet）
	 * @param: @param fis
	 * @param: @return
	 * @return: Map<String,List<List<String>>>
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午1:59:21
	 * @throws
	 */
	private static Map<String, List<List<String>>> parseSheetXLSX(InputStream fis) {
		Map<String, List<List<String>>> result = new HashMap<>();
		List<List<String>> datas = null;
		List<String> data = null;
		XSSFWorkbook book = null;
		XSSFSheet sheet = null;
		try {
			book = new XSSFWorkbook(fis);
			for (int sheetAct = 0; sheetAct < book.getNumberOfSheets(); sheetAct++) {
				sheet = book.getSheetAt(sheetAct);
				if (sheet != null) {
					datas = new ArrayList<>();
					// 获取最大列
					int lastCellNum = sheet.getRow(0).getLastCellNum();
					// 除去表头和第一行
					for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) {
						data = new ArrayList<>();
						XSSFRow row = sheet.getRow(i);
						for (int j = 0; j < lastCellNum; j++) {
							data.add(getCellValue(row.getCell(j)));
						}
						if (isNotEmpty(data)) {
							datas.add(data);
						}
					}
					result.put(sheet.getSheetName(), datas);
				}
			}
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		} finally {
			bookClose(book);
			fisClose(fis);
		}
		return result;
	}
	
	/**
	 * @Description: book close
	 * @param: @param book
	 * @return: void
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:19:23
	 * @throws
	 */
	private static void bookClose(HSSFWorkbook book) {
		if (book != null) {
			try {
				book.close();
			} catch (IOException e) {
				String fullStackTrace = ExceptionUtils.getStackTrace(e);
				log.error(fullStackTrace);
			}
		}
	}
	private static void bookClose(XSSFWorkbook book) {
		if (book != null) {
	        try {
	          book.close();
	        } catch (IOException e) {
	          String fullStackTrace = ExceptionUtils.getStackTrace(e);
	          log.error(fullStackTrace);
	        }
	      }
	}
	
	/**
	 * @Description: fis close
	 * @param: @param fis
	 * @return: void
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:20:25
	 * @throws
	 */
	private static void fisClose(InputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				String fullStackTrace = ExceptionUtils.getStackTrace(e);
				log.error(fullStackTrace);
			}
		}
	}
	
	/**
	 * @Description: 校验空行
	 * @param: @param data
	 * @param: @return
	 * @return: Boolean
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:17:08
	 * @throws
	 */
	private static Boolean isNotEmpty(List<String> data) {
		Boolean flag = false;
		if (!data.isEmpty()) {
			for (String str : data) {
				if (StringUtils.isNotBlank(str)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	
	/**
	 * @Description: 校验数字类型
	 * @param: @param value
	 * @return: void
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午2:51:35
	 * @throws
	 */
	private static void checkDouble(final String value) {
		if (null != value && value.indexOf(POINT) != -1 && value.indexOf(E) != -1) {
			throw new RuntimeException(MSG);
		}
	}
	
	/**
	 * @Description: 解析cell
	 * @param: @param cell
	 * @param: @return
	 * @return: String
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:12:58
	 * @throws
	 */
	private static String getCellValue(HSSFCell cell) {
		String cellValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
					cellValue = "";
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				// 数字格式
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					java.text.DecimalFormat formatter = new java.text.DecimalFormat("########.####");
					cellValue = formatter.format(cell.getNumericCellValue());
					checkDouble(cellValue);
					BigDecimal b = BigDecimal.valueOf(Double.valueOf(cellValue));
					cellValue = formatter.format(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cellValue = String.valueOf(cell.getNumericCellValue());
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
		return cellValue;
	}
	
	private static String getCellValue(XSSFCell cell) {
		String cellValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
					cellValue = "";
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				// 数字格式
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					java.text.DecimalFormat formatter = new java.text.DecimalFormat("########.####");
					cellValue = formatter.format(cell.getNumericCellValue());
					checkDouble(cellValue);
					BigDecimal b = BigDecimal.valueOf(Double.valueOf(cellValue));
					cellValue = formatter.format(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cellValue = String.valueOf(cell.getNumericCellValue());
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
		return cellValue;
	}

	/**
	 * @Description: 导出XLS（带Sheet）
	 * @param: @param response
	 * @param: @param fileName
	 * @param: @param tableName
	 * @param: @param data
	 * @return: void
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:36:09
	 * @throws
	 */
	public static void downloadSheetXLS(HttpServletResponse response, String fileName, Map<String, String[]> tableNames, Map<String, List<List<String>>> map) {
		OutputStream out = null;
		HSSFWorkbook book = null;
		try {
			book = generateXLS(map, tableNames);
			response.setContentType(CONTENT_TYPE);
			response.addHeader(CONTENT_DIS, CONTENT_SRT + new String(fileName.getBytes(GBK), ISO) + XLS);
			out = response.getOutputStream();
			book.write(out);
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		} finally {
			outClose(out);
			bookClose(book);
		}
	}

	/**
	 * @Description: 导出XLSX（带Sheet）
	 * @param: @param response
	 * @param: @param fileName
	 * @param: @param tableName
	 * @param: @param data
	 * @return: void
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:36:59
	 * @throws
	 */
	public static void downloadXLSX(HttpServletResponse response, String fileName, Map<String, String[]> tableNames, Map<String, List<List<String>>> map) {
		OutputStream out = null;
		XSSFWorkbook book = null;
	    try {
	    	book = generateXLSX(map, tableNames);
	      response.setContentType(CONTENT_TYPE);
	      response.addHeader(CONTENT_DIS, CONTENT_SRT + new String(fileName.getBytes(GBK),ISO) + XLSX);
	      out = response.getOutputStream();
	      book.write(out);
	    } catch (Exception e) {
	      String fullStackTrace = ExceptionUtils.getStackTrace(e);
	      log.error(fullStackTrace);
	    } finally {
	    	outClose(out);
	    	bookClose(book);
	    }
	}
	
	/**
	 * @Description: 生成XLSX（带Sheet）
	 * @param: @param map
	 * @param: @param tableNames
	 * @param: @return
	 * @return: XSSFWorkbook
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午4:14:00
	 * @throws
	 */
	private static XSSFWorkbook generateXLSX(Map<String, List<List<String>>> map, Map<String, String[]> tableNames) {
		XSSFWorkbook book = null;
		try {
			if (map != null && !map.isEmpty() && tableNames != null && !tableNames.isEmpty()) {
				book = new XSSFWorkbook();
				XSSFSheet sheet = null;
				for (Map.Entry<String, List<List<String>>> entry : map.entrySet()) {
					sheet = book.createSheet(entry.getKey());
					sheet.autoSizeColumn(1, true);// 自适应列宽度
					// 填充表头标题
					XSSFRow firstRow = sheet.createRow(0);// 第几行（从0开始）
					for (int i = 0; i < tableNames.get(entry.getKey()).length; i++) {
						firstRow.createCell(i).setCellValue(tableNames.get(entry.getKey())[i]);
					}
					// 填充表格内容
					if (entry.getValue() != null && !entry.getValue().isEmpty()) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							XSSFRow row2 = sheet.createRow(i + 1);// index：第几行
							List<String> data = entry.getValue().get(i);
							for (int j = 0; j < data.size(); j++) {
								XSSFCell cell = row2.createCell(j);// 第几列：从0开始
								cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置单元格内容为字符串型
								cell.setCellValue(data.get(j));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		}
		return book;
	  }

	/**
	 * @Description: 生成XLS（带Sheet）
	 * @param: @param map
	 * @param: @param tableName
	 * @param: @return
	 * @return: HSSFWorkbook
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午4:03:22
	 * @throws
	 */
	private static HSSFWorkbook generateXLS(Map<String, List<List<String>>> map, Map<String, String[]> tableNames) {
		HSSFWorkbook book = null;
		try {
			if (map != null && !map.isEmpty() && tableNames != null && !tableNames.isEmpty()) {
				book = new HSSFWorkbook();
				HSSFSheet sheet = null;
				for (Map.Entry<String, List<List<String>>> entry : map.entrySet()) {
					sheet = book.createSheet(entry.getKey());
					sheet.autoSizeColumn(1, true);// 自适应列宽度
					// 填充表头标题
					HSSFRow firstRow = sheet.createRow(0);// 第几行（从0开始）
					for (int i = 0; i < tableNames.get(entry.getKey()).length; i++) {
						firstRow.createCell(i).setCellValue(tableNames.get(entry.getKey())[i]);
					}
					// 填充表格内容
					if (entry.getValue() != null && !entry.getValue().isEmpty()) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							HSSFRow row2 = sheet.createRow(i + 1);// index：第几行
							List<String> data = entry.getValue().get(i);
							for (int j = 0; j < data.size(); j++) {
								HSSFCell cell = row2.createCell(j);// 第几列：从0开始
								cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置单元格内容为字符串型
								cell.setCellValue(data.get(j));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			String fullStackTrace = ExceptionUtils.getStackTrace(e);
			log.error(fullStackTrace);
		}
		return book;
	}
	
	
	
	/**
	 * @Description: out close
	 * @param: @param out
	 * @return: void
	 * @author:  Chen Xinjie
	 * @date:   2018年8月23日 下午3:41:26
	 * @throws
	 */
	private static void outClose(OutputStream out) {
		if (out != null) {
			try {
				out.flush();
			} catch (IOException e) {
				String fullStackTrace = ExceptionUtils.getStackTrace(e);
				log.error(fullStackTrace);
			}
			try {
				out.close();
			} catch (IOException e) {
				String fullStackTrace = ExceptionUtils.getStackTrace(e);
				log.error(fullStackTrace);
			}
		}
	}
}
