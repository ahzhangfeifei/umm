package com.umm.wfm.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static Date getOnlyDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 判断 date 是否在 startDate endDate 区间
	 * 
	 * @param date
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isInDate(Date date, Date startDate, Date endDate) {
		if (null == date || null == startDate || null == endDate) {
			return false;
		}

		// return date.after(startDate) && date.before(endDate);
		return date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime();
	}
	
	/**
	 * 
	 * @param date 判断时间是否在周日~周六之间   1：周日 ~  7：周六
	 * @param suitWeek
	 * @return
	 */
	public  static boolean isSuitWeek(Date date,String suitWeek){
		if(null == date || StringUtils.isBlank(suitWeek)){
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if(StringUtils.contains(suitWeek, String.valueOf(dayWeek))){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 获取当前日期是周几 1：周日 ~  7：周六
	 * @param date
	 * @return
	 */
	public static int getDayWeek(Date date){
		if(null == date){
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return  cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取日期的前几天
	 *  如： date 20151212， beforeNum 2 返回 20151210
	 * @param date
	 * @param beforeNum 天数
	 * @return Date
	 */
	public static Date getDayBefore(Date date,int beforeNum) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - beforeNum);
		return c.getTime();
	}

	/**
	 * 获取日期的后几天
	 * 如： date 20151010， afterNum 2 返回 20151012
	 * @param date
	 * @param afterNum 天数
	 * @return Date
	 */
	public static Date getDayAfter(Date date,int afterNum) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + afterNum);
		return c.getTime();
	}

	/**
	 * 获取某天开始时间
	 *  传入 2015-12-12  返回2015-12-12 00:00:00.000
	 * @param date
	 * @return
	 */
	public static Date getStartTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 获取某天开始时间
	 *  传入 2015-12-12  返回2015-12-12 23:59:59.999
	 * @param date
	 * @return
	 */
	public static Date getEndTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
}
