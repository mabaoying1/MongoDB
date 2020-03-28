package com.healthpay.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {
    public static int double2int(double d) throws Exception {
        String value = Double.toString(d);
        if (value.indexOf(".") > 0) {
            value = value.substring(0, value.indexOf("."));
        }
        return Integer.parseInt(value);
    }

    public static Integer long2int(Long l) {
        if (null == l)
            return null;
        String value = Long.toString(l);
        return Integer.parseInt(value);
    }

    public static Long int2Long(Integer i) {
        if (null == i)
            return null;

        String value = i.toString();
        return Long.parseLong(value);
    }

    public static String format(String pattern, double x) {
        DecimalFormat df = new DecimalFormat(pattern);

        return df.format(x);
    }

    public static String format(String pattern, java.math.BigDecimal x) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(x);
    }

    public static String format(String pattern, Date date) {
        if (null == date)
            return null;
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String datetime() {
        Date date = new Date();
        return format("yyyy.MM.dd HH:mm:ss", date);
    }

    public static String datetime(String format) {
        Date date = new Date();
        return format(format, date);
    }

    /**
     * 字符串到日期
     * @param sDate     -- 
     * @param format    -- 字符串对应的日期格式
     * @return
     * @throws Exception
     */
    public static Date str2date(String sDate, String format) throws Exception {
        if (null == sDate)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(sDate);
    }

    public static java.sql.Date str2sqldate(String sDate, String format) throws Exception {
        return new java.sql.Date(str2date(sDate, format).getTime());
    }

    /**
     * 格式化成显示金额
     * @param d
     * @return
     */
    public static String format2Price(double d) {
        return format("##,###,###,##0.00", d);
    }

    /**
     * 格式化成輸入金額
     * @param d
     * @return
     */
    public static String format2PriceInput(double d) {
        return format("##########0.00", d);
    }

    /**
     * 截断日期中的时间
     * @param d
     * @return
     * <br>-----------------------------------------------------<br>
     * @author	 flotage
     * @create 	 2012-12-26 下午04:29:42  
     * @note
     */
    public static Date trimTimeFromDate(Date d) {
        try{
            return str2date(format("yyyy.MM.dd", d), "yyyy.MM.dd");
        }catch(Exception e){
            return null;
        }
    }
}
