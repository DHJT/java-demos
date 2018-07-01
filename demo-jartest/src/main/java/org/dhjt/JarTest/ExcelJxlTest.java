package org.dhjt.JarTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhjt.util.Util;

import jxl.Cell;
import jxl.CellView;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Jxl生成Excel
 * @author DHJT 2018年6月25日 下午3:00:47
 *
 */
public class ExcelJxlTest {

    /**
     * 报表Excel生成
     * @edit slh 2018年4月16日-下午5:22:56
     * @param tList
     * @param BQid
     * @param systemBQ
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public Map<String, Object> BQExcel(List tList, String BQid) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        WritableWorkbook book = null;
        Map<String, Object> result = new HashMap<String, Object>();

        String headerStart = "0"; // 表头起始行
        String headerEnd = "1"; // 表头结束行
        String cycleStart = "3";    // 循环起始行
        String cycleEnd = "8";  // 循环结束行
        String strfile = null;
        try {
            strfile = Util.fileCopy(BQid, "");
            // Excel获得文件
            Workbook wb = Workbook.getWorkbook(new File(strfile));
            // 打开一个文件的副本，并且指定数据写回到原文件
            book = Workbook.createWorkbook(new File(strfile), wb);
            // 添加一个工作表
            WritableSheet sheet = book.getSheet(0);
            CellView fristView = sheet.getRowView(Integer.parseInt(cycleStart)-1);
            int colnum = sheet.getColumns();
            int row = sheet.getRows();// 获取行数
            SheetSettings setting = sheet.getSettings(); // 获取excel设置
            if (Util.notNull(headerEnd)) {
                setting.setPrintTitles(0, Integer.parseInt(headerEnd) - 1, colnum, colnum);// 插入复制表头
            }
            if (Util.notNull(headerEnd)) {
                int heend = Integer.parseInt(headerEnd);
                // 先生成报表头
                for (int line = 0; line < heend; line++) {
                    for (int j = 0; j < colnum; j++) {
                        Cell c = sheet.getCell(j, line);// 获得单元数据
                        Boolean b = Util.isLetterDigit(c.getContents().toString());
                        if (b) {
                            if (Util.notNull(c.getContents().toString())) {
                                String se = "get" + c.getContents().toString();
                                int num = line;
                                for (int i = 0; i < tList.size(); i++) {
                                    String cellStr = null;
                                    try {
                                        cellStr = (String) tList.get(i).getClass().getMethod(se).invoke(tList.get(i)); // 获取值
                                    } catch (Exception e) {
                                        e.toString();
                                    }
                                    if (Util.isNull(cellStr)) {
                                        cellStr = " ";
                                    }
                                    sheet.addCell(new Label(j, num, cellStr, c.getCellFormat()));
                                }
                            }
                        }

                    }
                }
            }
            if (Util.notNull(cycleStart)) {
                // 开始生成数据 endedit :2016年7月5日10:38:18 by ：LJB
                int cystart = Integer.parseInt(cycleStart);
                for (int line = cystart - 1; line < cystart; line++) {
                    int num = line;
                    int jnum = 0;
                    int cycend = 0; // 表体循环次数 补空格数
                    if (Util.notNull(cycleEnd)) {
                        int lxnum = tList.size() / Integer.parseInt(cycleEnd);
                        if (lxnum != 0) {
                            cycend = Integer.parseInt(cycleEnd) - (tList.size() / Integer.parseInt(cycleEnd));
                        }
                    }
                    if(cycend<0){//判断cycend 是否为正数
                        cycend = 0-cycend;
                    }
                    Cell[] c1 = sheet.getRow(line); // 获取列行 集合

                    for (int i = 0; i < tList.size() +cycend; i++) {
                        for (int j = jnum; j < colnum; j++) {
                            if (j >= c1.length) {
                                break;
                            }
                            Cell c = c1[j];
                            if (Util.notNull(c.getContents().toString())) {
                                String se = "get" + c.getContents().toString();
                                if (i >= tList.size()) {
                                    sheet.addCell(new Label(j, num, "  ", c.getCellFormat())); // 给表格赋值
                                } else {
                                    String cellStr = (String) tList.get(i).getClass().getMethod(se).invoke(tList.get(i)); // 获取值
                                    if ("getBoxNumber".equals(se)) {
                                        cellStr = cellStr.substring(cellStr.length()-4, cellStr.length());
                                    } else if ("getTitle".equals(se)) {
                                        cellStr = "　　" + cellStr;
                                    }
                                    sheet.addCell(new Label(j, num, cellStr, c.getCellFormat()));
                                }
                                jnum++;
                            }
                             sheet.setRowView(num, fristView);
                        }
                        num++;
                        jnum = 0;
                    }
                }
            }
            book.write();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (BiffException be) {
            be.printStackTrace();
        } catch (WriteException we) {
            we.printStackTrace();
        } finally {
            try {
                if (book != null) {
                    book.close();
                    result.put("success", true);
                    String[] spstr = strfile.split("\\\\");
                    result.put("path", spstr[spstr.length - 2] + "\\" + spstr[spstr.length - 1]);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (WriteException we) {
                we.printStackTrace();
            }
        }
        return result;
    }
}
