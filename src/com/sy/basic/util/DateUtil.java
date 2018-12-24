package com.sy.basic.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
			"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private final static SimpleDateFormat sdfDayd = new SimpleDateFormat("dd");

	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat(
			"HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取DD格式
	 * 
	 * @return
	 */
	public static String getDayd() {
		return sdfDayd.format(new Date());
	}
	
	/**
	 * 获取DD格式最后一位
	 * 
	 * @return
	 */
	public static int getDayPost() {
		int day;
		if(sdfDayd.format(new Date()).equals("31")){
			day=31;
		}else{
			day=Integer.parseInt(sdfDayd.format(new Date()).substring(1));
		}
		return day;
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * 获取YYYYMMDDHHmmss格式
	 * 
	 * @return
	 */
	public static int getDayTime(String d) {
		int days = 0;
		try {
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			String fromDate = f.parse(d).toLocaleString();
			String toDate = DateUtil.getTime();
			long from = simpleFormat.parse(fromDate).getTime(); 
			long to = simpleFormat.parse(toDate).getTime(); 
			days = (int) ((to - from)/(1000*60 * 60));			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}
	
	public static boolean getTimeFlag(String d) {
//		try {
//			int limitDay = 1;
//			Calendar now = Calendar.getInstance();			
//	        if(Integer.parseInt(d.substring(0, 4))!=now.get(Calendar.YEAR)){
//	        	if(now.get(Calendar.YEAR)-Integer.parseInt(d.substring(0, 4))==1){
//	        		if(Integer.parseInt(d.substring(4, 6))==12&&(now.get(Calendar.MONTH) + 1)==1){
//		        		if((Math.abs(30-Integer.parseInt(d.substring(6, 8)))+now.get(Calendar.DAY_OF_MONTH))>limitDay){
//			        		return false;
//			        	}else{
//			        		return true;
//			        	}
//		        	}
//	        	}else{
//	        		return false;
//	        	}	        	
//	        }
//	        if(Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1)!=0&&Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1)!=-1){
//	        	return false;
//	        }
//	        if(Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1)==0){
//	        	if((now.get(Calendar.DAY_OF_MONTH)-Integer.parseInt(d.substring(6, 8)))>limitDay||Integer.parseInt(d.substring(6, 8))>now.get(Calendar.DAY_OF_MONTH)){
//	        		return false;
//	        	}
//	        }
//	        if(Math.abs(Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1))==1){	        	
//	        	if((Math.abs(30-Integer.parseInt(d.substring(6, 8)))+now.get(Calendar.DAY_OF_MONTH))>limitDay){
//	        		return false;
//	        	}
//	        }
//	        
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return true;
	}
	
	public static boolean getTimeFlags(String d) {
		try {
			int limitDay = 1;
			Calendar now = Calendar.getInstance();			
	        if(Integer.parseInt(d.substring(0, 4))!=now.get(Calendar.YEAR)){
	        	if(now.get(Calendar.YEAR)-Integer.parseInt(d.substring(0, 4))==1){
	        		if(Integer.parseInt(d.substring(4, 6))==12&&(now.get(Calendar.MONTH) + 1)==1){
		        		if((Math.abs(30-Integer.parseInt(d.substring(6, 8)))+now.get(Calendar.DAY_OF_MONTH))>limitDay){
			        		return false;
			        	}else{
			        		return true;
			        	}
		        	}
	        	}else{
	        		return false;
	        	}	        	
	        }
	        if(Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1)!=0&&Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1)!=-1){
	        	return false;
	        }
	        if(Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1)==0){
	        	if((now.get(Calendar.DAY_OF_MONTH)-Integer.parseInt(d.substring(6, 8)))>limitDay||Integer.parseInt(d.substring(6, 8))>now.get(Calendar.DAY_OF_MONTH)){
	        		return false;
	        	}
	        }
	        if(Math.abs(Integer.parseInt(d.substring(4, 6))-(now.get(Calendar.MONTH) + 1))==1){	        	
	        	if((Math.abs(30-Integer.parseInt(d.substring(6, 8)))+now.get(Calendar.DAY_OF_MONTH))>limitDay){
	        		return false;
	        	}
	        }
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 获取HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTimes() {
		return sdfTimes.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
					startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 获取某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}

	public static int getSpecifiedDayBefore(int d) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-d);
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String dayBefore = sdf.format(cal.getTime());
		if(dayBefore.equals("31")){
			dayBefore="31";
		}else{
			dayBefore=dayBefore.substring(1);
		}
		return Integer.parseInt(dayBefore);
	}

	public static void main(String[] args) {
		System.out.println(getDayd().substring(getDayd().length() - 1));
		System.out.println(getAfterDayWeek("3"));
	}

}
