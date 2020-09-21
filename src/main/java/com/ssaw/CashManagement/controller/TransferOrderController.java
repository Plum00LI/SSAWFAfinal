package com.ssaw.CashManagement.controller;

import com.ssaw.BusinessDescription.entity.Account;
import com.ssaw.BusinessDescription.mapper.AccountMapper;
import com.ssaw.CashManagement.entity.TransferMoney;
import com.ssaw.CashManagement.mapper.TransferMoneyMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Eescription:生成excel的控制层
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-11
 */
@RestController
@RequestMapping("/transferOrderController")
public class TransferOrderController {
    @Resource
    TransferMoneyMapper transferMoneyMapper;
    @Resource
    AccountMapper accountMapper;

    @RequestMapping("/export")
        public String export1(HttpServletRequest request, HttpServletResponse response, TransferMoney transferMoney) throws Exception {
        System.out.println("excel===========================");
        System.err.println("transferMoney="+transferMoney);



        //头部
        String[] headers=new String[] {"到账日期：","付款账户名：","付款开户行：","付款账号：","付款金额（小写）及币种：","付款金额（大写）及币种：","收款账户名：","收款开户行：","收款账号：","付款金额（小写）及币种：","付款金额（大写）及币种：","划款用途：","备注："};
        TransferMoney transferMoney1=transferMoneyMapper.selectTransferMoneyByTransferMoneyId(transferMoney.getTransferMoneyId());

        System.err.println(transferMoney1.getInAccountId());
        System.err.println(transferMoney1.getOutAccount());
        Account inAccount=accountMapper.selectAccountById(transferMoney1.getInAccountId());
        Account outAccount=accountMapper.selectAccountById(transferMoney1.getOutAccount());
        List<Map<Integer, Object>> dataList=dataList(transferMoney1,inAccount,outAccount);
        Workbook wb=new HSSFWorkbook();
        fileExcelExport(dataList,wb,headers,"客户表",transferMoney);
        export(response, wb, "划款指令.xls");
        System.err.println(1111);
        return "html/transferOrder";
    }
    public static void fileExcelExport(List<Map<Integer, Object>> list, Workbook wb, String[] headers, String sheetName, TransferMoney transferMoney) {
        System.err.println(list);

        Sheet sheet=wb.createSheet(sheetName);
        HSSFPrintSetup printSetup = (HSSFPrintSetup) sheet.getPrintSetup();
        printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 纸张
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.2 ); // 上边距
        sheet.setMargin(HSSFSheet.BottomMargin,( double ) 0.2 ); // 下边距
        sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.2 ); // 左边距
        sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.2 ); // 右边距
        //表头字体
        Font headerFont = wb.createFont();
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        headerFont.setColor(HSSFColor.BLACK.index);
        //正文字体
        Font contextFont = wb.createFont();
        contextFont.setFontName("微软雅黑");
        contextFont.setFontHeightInPoints((short) 10);
        contextFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        contextFont.setColor(HSSFColor.BLACK.index);

        //表头样式，左右上下居中
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        headerStyle.setLocked(true);
        headerStyle.setWrapText(true);// 自动换行
        //单元格样式，左右上下居中 边框
        CellStyle commonStyle = wb.createCellStyle();
        commonStyle.setFont(contextFont);
        commonStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        commonStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        commonStyle.setLocked(true);
        commonStyle.setWrapText(true);// 自动换行
        //单元格样式，左右上下居中 边框
        CellStyle commonWrapStyle = wb.createCellStyle();
        commonWrapStyle.setFont(contextFont);
        commonWrapStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        commonWrapStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        commonWrapStyle.setLocked(true);
        commonWrapStyle.setWrapText(true);// 自动换行
        commonWrapStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        commonWrapStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        commonWrapStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        commonWrapStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        //单元格样式，竖向 边框
        CellStyle verticalStyle = wb.createCellStyle();
        verticalStyle.setFont(contextFont);
        verticalStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        verticalStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        verticalStyle.setRotation((short) 255);//竖向
        verticalStyle.setLocked(true);
        verticalStyle.setWrapText(false);// 自动换行
		/*verticalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		verticalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		verticalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		verticalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
*/
        //单元格样式，左右上下居中 无边框
        CellStyle commonStyleNoBorder = wb.createCellStyle();
        commonStyleNoBorder.setFont(contextFont);
        commonStyleNoBorder.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        commonStyleNoBorder.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        commonStyleNoBorder.setLocked(true);
        commonStyleNoBorder.setWrapText(false);// 自动换行
        //单元格样式，左对齐 边框
        CellStyle alignLeftStyle = wb.createCellStyle();
        alignLeftStyle.setFont(contextFont);
        alignLeftStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignLeftStyle.setLocked(true);
        alignLeftStyle.setWrapText(true);// 自动换行
        alignLeftStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        alignLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        alignLeftStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        alignLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        //单元格样式，左对齐 无边框
        CellStyle alignLeftNoBorderStyle = wb.createCellStyle();
        alignLeftNoBorderStyle.setFont(contextFont);
        alignLeftNoBorderStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftNoBorderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignLeftNoBorderStyle.setLocked(true);
        alignLeftNoBorderStyle.setWrapText(true);// 自动换行
        alignLeftNoBorderStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        //单元格样式，右对齐
        CellStyle alignRightStyle = wb.createCellStyle();
        alignRightStyle.setFont(contextFont);
        alignRightStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignRightStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignRightStyle.setLocked(true);
        alignRightStyle.setWrapText(false);// 自动换行
        alignRightStyle.setAlignment(CellStyle.ALIGN_RIGHT);// 左对齐

        //行号
        int rowNum = 0;
        int row=0;
        int rowIndex=0;
        //设置列宽
        for (int i = 0; i < 7; i++) {
            sheet.setColumnWidth(i, 6400);
        }

        //第一行
        Row r0 = sheet.createRow(rowNum++);
        r0.setHeight((short) 1000);
        Cell c00 = r0.createCell(0);
        HSSFRichTextString richString = new HSSFRichTextString("基金划款指令书"+"\r\n"+"嘉实基金管理有限公司_嘉实海外中国股票股票型证券投资基金_专用表");
        richString.applyFont(0, 7,headerFont);
        richString.applyFont(8, 40, contextFont);
        c00.setCellValue(richString);
        c00.setCellStyle(headerStyle);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(row++, rowIndex++, 0, 3));
        //第二行
        Row r1 = sheet.createRow(rowNum++);
        r1.setHeight((short) 500);
        Cell c01 = r1.createCell(0);
        for (Map<Integer, Object> rowMap : list) {
            c01.setCellValue("编号："+rowMap.get(0));
        }
        c01.setCellStyle(commonWrapStyle);
        for (int i = 1; i <=3; i++) {
            c01=r1.createCell(i);
            c01.setCellValue("");
            c01.setCellStyle(commonWrapStyle);
        }
        sheet.addMergedRegion(new CellRangeAddress(row++, rowIndex++, 0, 3));
        //第三行
        Row r2 = sheet.createRow(rowNum++);
        r2.setHeight((short) 500);
        Cell c02 = r2.createCell(0);
        for (Map<Integer, Object> rowMap : list) {
            c02.setCellValue("指令日期："+rowMap.get(1));
        }
        c02.setCellStyle(commonWrapStyle);
        for (int i = 1; i <=3; i++) {
            c02=r2.createCell(i);
            c02.setCellValue("");
            c02.setCellStyle(commonWrapStyle);
        }
        sheet.addMergedRegion(new CellRangeAddress(row++, rowIndex++, 0, 3));
        if(transferMoney.getOrderCheque()==1) {
            //第四行
            Row r3 = sheet.createRow(rowNum++);
            r3.setHeight((short) 500);
            Cell c03 = r3.createCell(0);
            c03.setCellValue(transferMoney.getHtitle());
            c03.setCellStyle(alignLeftStyle);
            for (int i = 1; i <=3; i++) {
                c03=r3.createCell(i);
                c03.setCellValue("");
                c03.setCellStyle(alignLeftStyle);
            }
            sheet.addMergedRegion(new CellRangeAddress(row++, rowIndex++, 0, 3));
            //第五行
            Row r4 = sheet.createRow(rowNum++);
            r4.setHeight((short) 500);
            Cell c04 = r4.createCell(0);
            c04.setCellValue(transferMoney.getBtitle());
            c04.setCellStyle(commonWrapStyle);
            for (int i = 1; i <=3; i++) {
                c04=r4.createCell(i);
                c04.setCellValue("");
                c04.setCellStyle(commonWrapStyle);
            }
            sheet.addMergedRegion(new CellRangeAddress(row++, rowIndex++, 0, 3));
        }
        for (int i = 0; i < headers.length; i++) {
            Row r5=sheet.createRow(rowNum++);
            r5.setHeight((short) 500);
            Cell c05=r5.createCell(0);
            Cell c06=r5.createCell(1);
            c05.setCellValue(headers[i]);
            c05.setCellStyle(alignLeftStyle);
            c06.setCellStyle(alignLeftStyle);
            for (Map<Integer, Object> rowMap : list) {
                Object obj=rowMap.get(i+1);
                if(obj==null) {
                    c06.setCellValue("");
                }else {
                    c06.setCellValue(rowMap.get(i+2)+"");
                }
            }
            for (int j = 2;j <=3; j++) {
                c06=r5.createCell(j);
                c06.setCellValue("");
                c06.setCellStyle(alignLeftStyle);
            }
            CellRangeAddress cellRangePlanNo5 =new CellRangeAddress(row++, rowIndex++, 1, 3);
            sheet.addMergedRegion(cellRangePlanNo5);
        }
        Row r3 = sheet.createRow(rowNum++);
        Cell c04=r3.createCell(0);
        Cell c05=r3.createCell(2);
        r3.setHeight((short) 2500);
        c04.setCellValue("管理人签字：");
        c04.setCellStyle(alignLeftStyle);
        c05.setCellStyle(alignLeftStyle);
        c05.setCellValue("托管人签字：");
        for (int i = 1; i <=3; i++) {
            if(i==1) {
                c04=r3.createCell(1);
                c04.setCellValue("");
                c04.setCellStyle(alignLeftStyle);
            }else if(i==3) {
                c05=r3.createCell(3);
                c05.setCellValue("");
                c05.setCellStyle(alignLeftStyle);
            }
        }
        int row1=row;
        int rowINdex1=rowIndex;
        CellRangeAddress cellRangePlanNo6 =new CellRangeAddress(row++,rowIndex++, 0, 1);
        sheet.addMergedRegion(cellRangePlanNo6);
        CellRangeAddress cellRangePlanNo7 =new CellRangeAddress(row1,rowINdex1++,2, 3);
        sheet.addMergedRegion(cellRangePlanNo7);
        if(transferMoney.getAuditor()==1) {
            Row r4 = sheet.createRow(rowNum++);
            Cell c06=r4.createCell(0);
            Cell c07=r4.createCell(2);
            r4.setHeight((short) 1000);
            c06.setCellValue("复核人：");
            c06.setCellStyle(alignLeftStyle);
            c07.setCellValue("复核人：");
            c07.setCellStyle(alignLeftStyle);
            for (int i = 1; i <=3; i++) {
                if(i==1) {
                    c06=r4.createCell(1);
                    c06.setCellValue("");
                    c06.setCellStyle(alignLeftStyle);
                }else if(i==3) {
                    c07=r4.createCell(3);
                    c07.setCellValue("");
                    c07.setCellStyle(alignLeftStyle);
                }
            }
            int row2=row;
            int rowINdex2=rowIndex;
            CellRangeAddress cellRangePlanNo8 =new CellRangeAddress(row++,rowIndex++, 0, 1);
            sheet.addMergedRegion(cellRangePlanNo8);
            CellRangeAddress cellRangePlanNo9 =new CellRangeAddress(row2,rowINdex2, 2, 3);
            sheet.addMergedRegion(cellRangePlanNo9);
        }
        Row r5 = sheet.createRow(rowNum++);
        Cell c08=r5.createCell(0);
        Cell c09=r5.createCell(2);
        r5.setHeight((short) 1000);
        c08.setCellValue("经办人：");
        c08.setCellStyle(alignLeftStyle);
        c09.setCellValue("经办人：");
        c09.setCellStyle(alignLeftStyle);
        for (int i = 1; i <=3; i++) {
            if(i==1) {
                c08=r5.createCell(1);
                c08.setCellValue("");
                c08.setCellStyle(alignLeftStyle);
            }else if(i==3) {
                c09=r5.createCell(3);
                c09.setCellValue("");
                c09.setCellStyle(alignLeftStyle);
            }
        }
        int row3=row;
        int rowINdex3=rowIndex;
        CellRangeAddress cellRangePlanNo10 =new CellRangeAddress(row++,rowIndex++, 0, 1);
        sheet.addMergedRegion(cellRangePlanNo10);
        CellRangeAddress cellRangePlanNo11 =new CellRangeAddress(row3,rowINdex3, 2, 3);
        sheet.addMergedRegion(cellRangePlanNo11);
    }

    //ExcelBeanUtil
    public static List<Map<Integer, Object>> dataList(final TransferMoney TransferMoney, Account inAccounts, Account outAccounts) throws Exception {
        List<Map<Integer, Object>> list = new ArrayList<>();
        if (TransferMoney != null && !TransferMoney.equals("")) {
            Map<Integer, Object> dataMap;
            String Money = ChinaNumber.change(TransferMoney.getMoney());
            dataMap = new HashMap<>();
            dataMap.put(0, TransferMoney.getTransferMoneyId());
            dataMap.put(1, TransferMoney.getCrossSectionDate());
            dataMap.put(2, TransferMoney.getAccountingDate());
            dataMap.put(3, inAccounts.getAccountName());
            dataMap.put(4, inAccounts.getBlankName());
            dataMap.put(5, inAccounts.getBlankCardCode());
            dataMap.put(6, TransferMoney.getMoney());
            dataMap.put(7, Money);
            dataMap.put(8, outAccounts.getAccountName());
            dataMap.put(9, outAccounts.getBlankName());
            dataMap.put(10, outAccounts.getBlankCardCode());
            dataMap.put(11, TransferMoney.getMoney());
            dataMap.put(12, Money);
            dataMap.put(13, "存款使用");
            dataMap.put(14, TransferMoney.getPurpose());
            list.add(dataMap);
        }

        return list;
    }
    //现金金额
    public static class ChinaNumber {
        private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
        private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
        private static final double MAX_VALUE = 9999999999999.99D;
        public static String change(double v) {
            if (v < 0 || v > MAX_VALUE){
                return "参数非法!";
            }
            long l = Math.round(v * 100);
            if (l == 0){
                return "零元整";
            }
            String strValue = l + "";
            // i用来控制数
            int i = 0;
            // j用来控制单位
            int j = UNIT.length() - strValue.length();
            String rs = "";
            boolean isZero = false;
            for (; i < strValue.length(); i++, j++) {
                char ch = strValue.charAt(i);
                if (ch == '0') {
                    isZero = true;
                    if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {
                        rs = rs + UNIT.charAt(j);
                        isZero = false;
                    }
                } else {
                    if (isZero) {
                        rs = rs + "零";
                        isZero = false;
                    }
                    rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
                }
            }
            if (!rs.endsWith("分")) {
                rs = rs + "整";
            }
            rs = rs.replaceAll("亿万", "亿");
            return rs;
        }


    }


    public static void export(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String((fileName).getBytes("utf-8"), "iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
