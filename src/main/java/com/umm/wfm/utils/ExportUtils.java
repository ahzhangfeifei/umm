package com.umm.wfm.utils;

import com.umm.wfm.security.log.LogUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 导出工具类,
 * 支持excel，text，
 * Created by liuzhaoxiang on 2015/12/25.
 */
public class ExportUtils {
    private static final Logger LOGGER = LogUtil.getLogger(ExportUtils.class);

    private ExportUtils() {

    }

    /**
     * 导出CSV文件流。
     *
     * @param headers 标题数组
     * @param fields  字段名数组
     * @param data    数据
     * @return 文件流
     * @throws IOException
     */
    public static InputStream exportCsvIS(String[] headers, String[] fields, List<Map<String, Object>> data) throws IOException {

        // 检查参数
        if (headers.length != fields.length) {
            throw new IllegalArgumentException("参数有误!");
        }

        // 创建临时文件
        File xlfFile = new File(System.getProperty("java.io.tmpdir"), RandomStringUtils.randomAlphabetic(32));
        PrintWriter printw = new PrintWriter(xlfFile);

        StringBuilder sBuilder = new StringBuilder();

        for (int i = 0; i < headers.length; i++) {

            sBuilder.append(headers[i]);
            if (i != headers.length - 1) {
                sBuilder.append(",\t");
            }
        }
        sBuilder.append("\r\n");
        int count = 0;
        if (data != null && data.size() > 0) {
            for (Map<String, Object> rowMap : data) {
                for (int m = 0; m < fields.length; m++) {
                    count++;
                    sBuilder.append(getString(rowMap.get(fields[m])));

                    if (m != fields.length - 1) {
                        sBuilder.append(",\t");
                    }
                }
                sBuilder.append("\r\n");
            }

        }

        // 将内容写入文本中
        printw.write(sBuilder.toString());

        printw.flush();
        printw.close();

        // 输出流
        try {
            return FileUtils.openInputStream(xlfFile);
        } catch (IOException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("导出CSV文件流出现异常!", e);
            }

            throw e;
        }
    }

    private static String getString(Object o) {
        if (o == null) {
            return StringUtils.EMPTY;
        }
        return o.toString();
    }

    /**
     * 导出EXCEL文件流。
     *
     * @param headers 标题数组
     * @param data    数据
     * @return 文件流
     * @throws IOException
     */
    public static void exportExcelIS(String[] headers, List<Object[]> data, OutputStream outputStream) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet("Sheet1");

        // 头列表
        HSSFRow row1 = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row1.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int j = 1;
        int k = 1;
        int count = 2;
        HSSFRow row;
        HSSFCell cell;

        if (data != null && data.size() > 0) {

            for (Object[] rowArr : data) {

                row = sheet.createRow(j);

                for (int m = 0; m < headers.length; m++) {
                    cell = row.createCell(m);
                    cell.setCellValue(getString(rowArr[m]));
                }

                j = j + 1;
                k++;
                // 如果数据量大于5000,则分多个sheet导出
                if (k > 5000) {
                    sheet = workbook.createSheet("Sheet" + count);
                    HSSFRow row2 = sheet.createRow(0);
                    for (int i = 0; i < headers.length; i++) {
                        HSSFCell cell1 = row2.createCell(i);
                        cell1.setCellValue(headers[i]);
                    }
                    count++;
                    k = 1;
                    j = 1;
                }
            }
        }

        workbook.write(outputStream);
    }

    /**
     * 导出TXT文件流。
     *
     * @param headers 标题数组
     * @param fields  字段名数组
     * @param data    数据
     * @return 文件流
     * @throws IOException
     */
    public static InputStream exportTxtIS(String[] headers, String[] fields, List<Map<String, Object>> data) throws IOException {

        // 检查参数
        if (headers.length != fields.length) {
            throw new IllegalArgumentException("参数有误!");
        }

        // 创建临时文件
        File xlfFile = new File(System.getProperty("java.io.tmpdir"), RandomStringUtils.randomAlphabetic(32));
        System.out.println(xlfFile);
        FileWriter fw = new FileWriter(xlfFile);

        StringBuilder sBuilder = new StringBuilder();

        for (int i = 0; i < headers.length; i++) {
            sBuilder.append(headers[i]);
            if (i != headers.length - 1) {
                sBuilder.append(",\t");
            }

        }
        sBuilder.append("\r\n");

        if (data != null && data.size() > 0) {

            for (Map<String, Object> rowMap : data) {
                for (int m = 0; m < fields.length; m++) {
                    sBuilder.append(getString(rowMap.get(fields[m])));

                    if (m != fields.length - 1) {
                        sBuilder.append(",\t");
                    }
                }
                sBuilder.append("\r\n");
            }
        }

        // 将内容写入文本中
        fw.write(sBuilder.toString());
        fw.flush();
        fw.close();

        // 输出流
        try {
            return FileUtils.openInputStream(xlfFile);
        } catch (IOException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("导出TXT文件流出现异常!", e);
            }

            throw e;
        }
    }

}
