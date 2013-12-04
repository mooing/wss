package com.mooing.wss.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 2011-8-10
 * @author zhangzijing
 */
public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final String FORMAT_DATE_PATTERN = "yyyy年MM月dd日";
    private static final String FORMAT_DATETIME_PATTERN = "yyyy年MM月dd日 HH:mm:ss";

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
     * 将java.util.Date转化为传入格式
     * 
     * @param date
     * @return
     */
    public static String formatDateToFormatDate(Date date,String format) {
    	SimpleDateFormat formatDate = new SimpleDateFormat(format);
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
     * 日期转换成字符串
     * @param date 日期
     * @param pattern 格式
     * @return String
     */
    public static String dateToString(Date date, String pattern) {
    	if (date == null)
			return null;
    	SimpleDateFormat sdf = null;
    	try{
    		sdf = new SimpleDateFormat(pattern);
    		return sdf.format(date);
    	}catch (Exception e) {
			logger.error("date转换成string出错:" + e.getMessage());
			 sdf = new SimpleDateFormat(FORMAT_DATETIME_PATTERN);
			 return sdf.format(date);
		}
		
	}
    /**
     * 字符串转换日期
     * @param strDate 字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date stringToDate(String strDate, String pattern){
		if (strDate == null || strDate.trim().length() <= 0)
			return null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(strDate);
		}catch (ParseException e){
			logger.error("字符串转换日期出错:" + e.getMessage());
			return null;
		}
	}
    
    /** 
     * 计算出两个日期之间相差的天数 
     * 建议date1 大于 date2 这样计算的值为正数 
     * @param date1 日期1 
     * @param date2 日期2 
     * @return date1 - date2 
     */  
    public static int dateInterval(long date1, long date2) {  
        if(date2 > date1){  
            date2 = date2 + date1;  
            date1 = date2 - date1;  
            date2 = date2 - date1;  
        }  
        /* 
         * Canlendar 该类是一个抽象类  
         * 提供了丰富的日历字段 
         *  
         * 本程序中使用到了 
         * Calendar.YEAR    日期中的年份 
         * Calendar.DAY_OF_YEAR     当前年中的天数 
         * getActualMaximum(Calendar.DAY_OF_YEAR) 返回今年是 365 天还是366天 
         */  
        Calendar calendar1 = Calendar.getInstance(); // 获得一个日历  
        calendar1.setTimeInMillis(date1); // 用给定的 long 值设置此 Calendar 的当前时间值。  
          
        Calendar calendar2 = Calendar.getInstance();  
        calendar2.setTimeInMillis(date2);  
        // 先判断是否同年  
        int y1 = calendar1.get(Calendar.YEAR);  
        int y2 = calendar2.get(Calendar.YEAR);  
          
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);  
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);  
        int maxDays = 0;  
        int day = 0;  
        if(y1 - y2 > 0){  
            day = numerical(maxDays, d1, d2, y1, y2, calendar2);  
        }else{  
            day = d1 - d2;  
        }  
        return day;  
    }  
    
    /** 
     * 日期间隔计算 
     * 计算公式(示例): 
     *      20121230 - 20071001 
     *      取出20121230这一年过了多少天 d1 = 365     取出20071001这一年过了多少天 d2 = 274 
     *      如果2007年这一年有366天就要让间隔的天数+1，因为2月份有29日。 
     * @param maxDays   用于记录一年中有365天还是366天 
     * @param d1    表示在这年中过了多少天 
     * @param d2    表示在这年中过了多少天 
     * @param y1    当前为2010年 
     * @param y2    当前为2012年 
     * @param calendar  根据日历对象来获取一年中有多少天 
     * @return  计算后日期间隔的天数 
     */  
    public static int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar){  
        int day = d1 - d2;  
        int betweenYears = y1 - y2;  
        List<Integer> d366 = new ArrayList<Integer>();  
          
        if(calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366){  
            System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));  
            day += 1;  
        }  
          
        for (int i = 0; i < betweenYears; i++) {  
            // 当年 + 1 设置下一年中有多少天  
            calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1);  
            maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);  
            // 第一个 366 天不用 + 1 将所有366记录，先不进行加入然后再少加一个  
            if(maxDays != 366){  
                day += maxDays;  
            }else{  
                d366.add(maxDays);  
            }  
            // 如果最后一个 maxDays 等于366 day - 1  
            if(i == betweenYears-1 && betweenYears > 1 && maxDays == 366){  
                day -= 1;  
            }  
        }  
          
        for(int i = 0; i < d366.size(); i++){  
            // 一个或一个以上的366天  
            if(d366.size() >= 1){  
                day += d366.get(i);  
            }  
//          else{  
//              day -= 1;  
//          }  
        }  
        return day;  
    }  
      
    /** 
     * 将日期字符串装换成日期 
     * @param strDate   日期支持年月日 示例:yyyyMMdd 
     * @return  1970年1月1日器日期的毫秒数 
     */  
    public static long getDateTime(String strDate) {  
        return getDateByFormat(strDate, "yyyyMMdd").getTime();  
    }  
    
    /** 
     * @param strDate   日期字符串 
     * @param format    日期格式 
     * @return      Date 
     */  
    public static Date getDateByFormat(String strDate, String format) {  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        try{  
            return (sdf.parse(strDate));  
        }catch (Exception e){  
            return null;  
        }  
    }  
	public static Date convertStringToDate(String strDate)  {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat("yyyy-MM-dd");


		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			return null;
		}

		return (date);
	}

    public static String getCurrentMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
	
	public static String getPreviousMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(cal.getTime());
	}
	
	public static String getMonthFirstDay(Date date) {     
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
	            .getActualMinimum(Calendar.DAY_OF_MONTH));     
	    
	    return dateToString(calendar.getTime(), "yyyy-MM-dd");
	    		    
	}
	public static String getMonthLastDay(Date date) {     
	    Calendar calendar = Calendar.getInstance();     
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
	            .getActualMaximum(Calendar.DAY_OF_MONTH));     
	    return dateToString(calendar.getTime(), "yyyy-MM-dd");    
	}  
	public static String getMonthFirstDay(Date date,String pattern) {     
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar     
				.getActualMinimum(Calendar.DAY_OF_MONTH));     
		
		return dateToString(calendar.getTime(), pattern);
		
	}
	public static String getMonthLastDay(Date date,String pattern) {     
		Calendar calendar = Calendar.getInstance();     
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar     
				.getActualMaximum(Calendar.DAY_OF_MONTH));     
		return dateToString(calendar.getTime(), pattern);    
	}  
	
	public static String getNextMonthFirstDay(Date date) {     
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(date);
	    calendar.add(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
	            .getActualMinimum(Calendar.DAY_OF_MONTH));     
	    
	    return dateToString(calendar.getTime(), "yyyy-MM-dd");
	    		    
	}
	
	/**
	 * 获得上一天
	 * @param date
	 * @return
	 */
	public static Date getYesterday(Date date){
		 Calendar calendar = Calendar.getInstance(); 
		 calendar.setTime(date);
		 calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	
	/**
	 * 获得下一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date){
		 Calendar calendar = Calendar.getInstance(); 
		 calendar.setTime(date);
		 calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static String getDay (String beginDate, int days) {
		Calendar calendar= Calendar.getInstance();
		try {			
			Date nowDay = stringToDate(beginDate, "yyyy-MM-dd");		
			calendar.setTime(nowDay); 
			calendar.add(java.util.Calendar.DAY_OF_YEAR, -days);
		} catch (Exception e){
			logger.error("getDay error:" + e.getMessage(), e);
			return null;
		}
   
	    return dateToString(calendar.getTime(), "yyyy-MM-dd");	
	}
	
	/**
	 * 计算两个时间之间的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int dateDiff(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null)
			return 0;
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginDate);
		// 设置时分秒毫秒数为0
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		// 设置时分秒毫秒数为0
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);

		long compare = (end.getTimeInMillis() - begin.getTimeInMillis()) / 1000;

		int days = (int) compare / 86400;
		return days;
	}
	
	/**
	 * 获得指定日期的下num个月,日期不减1
	 * 
	 * @param aDate
	 * @param num
	 * @return 2011-8-2 上午09:24:19
	 */
	public static Date getNextNumMonth(Date aDate, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(aDate);
		// boolean flag = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) ==
		// calendar
		// .get(Calendar.DAY_OF_MONTH);//判断当前日期是否是该月最后一天

		calendar.add(Calendar.MONTH, num);// 到下num个月

		// 判断当前日期如果不是最后一天并且flag为true的情况下，调整为该月最后一天
		// if (flag
		// && calendar.getActualMaximum(Calendar.DAY_OF_MONTH) > calendar
		// .get(Calendar.DAY_OF_MONTH)) {
		// calendar.set(Calendar.DAY_OF_MONTH, calendar
		// .getMaximum(Calendar.DAY_OF_MONTH));
		// }
		// 是否是1个月
		// calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}
	
	
	/**
	 * 取当天 日期
	 * 
	 * @param aDate
	 * @return
	 */
	public static int getToDay(Date aDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);
		return cal.get(Calendar.DATE);
	}
	
	public static void main(String[] args) {
		Date date = new Date(System.currentTimeMillis());
		System.out.println(dateToString(getYesterday(date), "yyyy-MM-dd HH:mm:ss"));
		
	}
	
}
