package com.ssaw.GlobalManagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统时间工具类
 * @type: util
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/03
 */
public class DateTimeUtil {
    public static final String type1 = "yyyy";
    public static final String type2 = "MM";
    public static final String type3 = "dd";
    public static final String type4 = "yyyyMMdd";
    public static final String type5 = "hh";
    public static final String type6 = "mm";
    public static final String type7 = "ss";
    public static final String type8 = "HH";
    public static final String type9 = "hhmmss";
    public static final String type10 = "HHmmss";
    public static final String type11 = "hh:mm:ss";
    public static final String type12 = "HH:mm:ss";
    public static final String type13 = "yyyy/MM/dd";
    public static final String type14 = "yyyy/MM/dd hh:mm:ss";
    public static final String type15 = "yyyy/MM/dd HH:mm:ss";
    public static final String type16 = "yyyy年MM月dd日 hh时mm分ss秒";
    public static final String type17 = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 根据格式返回当前时间字符串
     * @param format 格式化格式
     * @return 格式化后的时间字符串
     */
    public static String getSystemDateTime(String format){
        //生成日期格式化格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //获取系统当前时间，格式化日期
        String strDateTime = simpleDateFormat.format(new Date());
        //返回格式化后的日期字符串
        return strDateTime;
    }

    public static void main(String[] args) {
        String systemDateTime = DateTimeUtil.getSystemDateTime(DateTimeUtil.type14);
        System.out.println(systemDateTime);
    }
}
