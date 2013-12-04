package com.mooing.wss.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理taglib
 * 
 * @author zhangzijing
 */
public class DateFunction {

    private static final String FORMAT_DATE_PATTERN = "yyyy年MM月dd日";
    private static final String FORMAT_DATETIME_PATTERN = "yyyy年MM月dd日 HH:mm:ss";
    private static final String FORMAT_HHMM_PATTERN = "HH:mm";

    /**
     * 将long型时间戳转化为日期(年月日)
     * 
     * @param timemillis
     * @return
     */
    public static String formatLongToDate(long timemillis) {
        if (timemillis == 0) {
            return "";
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(FORMAT_DATE_PATTERN);
        return formatDate.format(new Date(timemillis));
    }

    /**
     * 将long型时间戳转化为日期(年月日时分秒)
     * 
     * @param timemillis
     * @return
     */
    public static String formatLongToDateTime(long timemillis) {
        if (timemillis == 0) {
            return "";
        }
        SimpleDateFormat formDateTime = new SimpleDateFormat(FORMAT_DATETIME_PATTERN);
        return formDateTime.format(new Date(timemillis));
    }

    /**
     * 将java.util.Date转化为标准日期(年月日)
     * 
     * @param date
     * @return
     */
    public static String formatDateToSimpleDate(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat(FORMAT_DATE_PATTERN);
        return formatDate.format(date);
    }

    /**
     * 将java.util.Date转化为标准日期(年月日时分秒)
     * 
     * @param date
     * @return
     */
    public static String formatDateToSimpleDatetime(Date date) {
        SimpleDateFormat formDateTime = new SimpleDateFormat(FORMAT_DATETIME_PATTERN);
        return formDateTime.format(date);
    }

    /**
     * 将java.util.Date转化为HH:mm
     * 
     * @param date
     * @return
     */
    public static String formatDateToHHmm(Date date) {
        SimpleDateFormat formDateTime = new SimpleDateFormat(FORMAT_HHMM_PATTERN);
        return formDateTime.format(date);
    }

}
