package com.umm.wfm.utils;

import com.umm.wfm.intf.constants.BizzEx;
import com.umm.wfm.intf.exception.BizzException;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel相关工具类
 *
 * @author dingxiangyong, zhangff
 */
public class ExcelUtils {

    private static final int MAX_IMPORT_COUNT = 1000;

    /**
     * 解析excel文件，文件不存在或解析失败将返回空集合
     *
     * @param inputStream excel流
     * @param ext         excel文件类型
     * @param startRow    读取起始行
     * @return 字符串列表格式
     */
    public static List<List<String>> readExcel(InputStream inputStream, String ext, int startRow) {
        try {
            if ("xlsx".equalsIgnoreCase(ext)) {
                return readExcel2007(inputStream, startRow);
            } else if ("xls".equalsIgnoreCase(ext)) {
                return readExcel2003(inputStream, startRow);
            } else {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "不支持的文件格式");
            }
        } catch (BizzException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "读取Excel文档失败." + ex.getMessage());
        }

    }

    private static List<List<String>> readExcel2003(InputStream inputStream, int startRow) throws IOException {
        List<List<String>> result = new ArrayList<>();
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fs);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > MAX_IMPORT_COUNT) {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "单次导入不能超过" + MAX_IMPORT_COUNT + "条");
            }

            // 循环输出表格中的内容(忽略表头)
            for (int i = sheet.getFirstRowNum() + startRow; i < totalRows; i++) {
                List<String> temp = new ArrayList<>();
                HSSFRow row = sheet.getRow(i);
                int totalCells = row.getPhysicalNumberOfCells();
                for (int j = row.getFirstCellNum(); j < totalCells; j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，全部trim去除多余空格
                    String cell = row.getCell((short) j).toString().trim();
                    temp.add(cell);
                }
                result.add(temp);
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

    private static List<List<String>> readExcel2007(InputStream inputStream, int startRow) throws IOException {
        List<List<String>> result = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = null;
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > MAX_IMPORT_COUNT) {
                throw new BizzException(BizzEx.COMM_EX_WRAP, "单次导入不能超过" + MAX_IMPORT_COUNT + "条");
            }

            // 循环输出表格中的内容(忽略表头)
            for (int i = sheet.getFirstRowNum() + startRow; i < totalRows; i++) {
                List<String> temp = new ArrayList<>();
                XSSFRow row = sheet.getRow(i);
                int totalCells = row.getPhysicalNumberOfCells();
                for (int j = row.getFirstCellNum(); j < totalCells; j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，全部trim去除多余空格
                    String cell = row.getCell((short) j).toString().trim();
                    temp.add(cell);
                }
                result.add(temp);
            }
        } finally {
            IOUtils.closeQuietly(xssfWorkbook);
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }


}
