package com.jsst.cloud.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFunctions {
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String DATE_COMMON = "yyyyMMdd";

	public static final String End_FORMAT = "yyyyMMddhhmmss";

	private static SimpleDateFormat dateFormatTemp;

	/**
	 * 字符串类型时间转换成Date类型时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDateTime(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat dateFormatTemp = new SimpleDateFormat(DATETIME_FORMAT);
		try {
			return dateFormatTemp.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串类型时间转换成Date类型时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDate(String str) {
		if (StringUtil.isEmpty(str)) {
			return null;
		}

		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间比较，看end是否大于begin�? 如果时间为空则返回false�? 如果时间格式不正确则返回false
	 * 
	 * @param begin �?始时�?
	 * @param end 结束时间
	 * @return
	 */
	public static boolean dateTimeAfter(String begin, String end) {
		if (StringUtil.isEmpty(begin) || StringUtil.isEmpty(end)) {
			return false;
		}
		SimpleDateFormat dateFormatTemp = new SimpleDateFormat(DATETIME_FORMAT);
		try {
			return dateFormatTemp.parse(end).after(dateFormatTemp.parse(begin));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 时间比较，看end是否大于begin�? 如果时间为空则返回false�? 如果时间格式不正确则返回false
	 * 
	 * @param begin �?始时�?
	 * @param end 结束时间
	 * @return
	 */
	public static boolean dateAfter(String begin, String end) {
		if (StringUtil.isEmpty(begin) || StringUtil.isEmpty(end)) {
			return false;
		}
		SimpleDateFormat dateFormatTemp = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dateFormatTemp.parse(end).after(dateFormatTemp.parse(begin));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 时间比较，看end是否大于当前时间�? 如果时间为空则返回false�? 如果时间格式不正确则返回false
	 * 
	 * @param end 结束时间
	 * @return
	 */
	public static boolean dateAfter(String end) {
		if (StringUtil.isEmpty(end)) {
			return false;
		}
		SimpleDateFormat dateFormatTemp = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dateFormatTemp.parse(end).after(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @see 取得当前日期（格式为：yyyy-MM-dd�?
	 * @return String
	 */
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 * @see 取得当前时间（格式为：yyyy-MM-dd HH:mm:ss?
	 * @return String
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 * @see 取得当前时间（格式为：yyyMMddHHmmss?
	 * @return String
	 */
	public static String getEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(End_FORMAT);
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 * @see 将时间转化为字符�? (格式:yyy-MM-dd HH:mm:ss)
	 * @return String
	 */
	public static String toDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return (null != date) ? sdf.format(date) : " ";
	}

	/**
	 * 取得字符串日期（格式为：yyyy-MM-dd�?
	 * 
	 * @param calendar
	 * @return
	 */
	public static String toDateStr(GregorianCalendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 取得字符串日期（格式为：yyyy-MM-dd HH:mm:ss�?
	 * 
	 * @param calendar
	 * @return
	 */
	public static String toDateTimeStr(GregorianCalendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 将形如：yyyy-MM-dd HH:mm:ss的日期转换成GregorianCalendar类型
	 * 
	 * @param calendarStr
	 * @return
	 */
	public static GregorianCalendar toDateTimeCalendar(String calendarStr) {
		if (calendarStr == null) {
			return null;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
			Date date = dateFormat.parse(calendarStr);
			GregorianCalendar rv = new GregorianCalendar();
			rv.setTime(date);
			return rv;
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将形如：yyyy-MM-dd的日期转换成GregorianCalendar类型
	 * 
	 * @param calendarStr
	 * @return
	 */
	public static GregorianCalendar toDateCalendar(String calendarStr) {
		if (calendarStr == null) {
			return null;
		}
		try {
			SimpleDateFormat dateDayFormat = new SimpleDateFormat(DATE_FORMAT);
			Date date = dateDayFormat.parse(calendarStr);
			GregorianCalendar rv = new GregorianCalendar();
			rv.setTime(date);
			return rv;
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @method addDay: 根据天数和小时数以及分钟确定时间
	 * @param dateField
	 * @param days
	 * @param hour
	 * @return xxxxxxx
	 * @return GregorianCalendar xxxxxxxx
	 * @throws
	 */
	public static GregorianCalendar addTime(GregorianCalendar dateField, int days, int hours, int minute) {
		long dateTime = dateField.getTimeInMillis();
		if (days < 0)
			return dateField;
		long daysTime = days * 24 * 60 * 60 * 1000L;
		long hoursTime = hours * 60 * 60 * 1000L;
		long minuteTime = minute * 60 * 1000L;
		long newDateTime = dateTime + daysTime + hoursTime + minuteTime;
		GregorianCalendar newDate = new GregorianCalendar();
		newDate.setTimeInMillis(newDateTime);
		return newDate;
	}

	/**
	 * 小时的加�?
	 * 
	 * @param dateField GregorianCalendar 基础时间
	 * @param days int 要加的时�?
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar addDay(GregorianCalendar dateField, int days) {
		dateField.add(GregorianCalendar.DATE, days);
		return dateField;
	}

	/**
	 * 
	 * @method now: 返回当前时间
	 * @Date 2009/Sep 14, 2009
	 * @return
	 */
	public static GregorianCalendar getNow() {
		return new GregorianCalendar();
	}

	/**
	 * @fucntion 取得n天后的日期（格式为yyyy-MM-dd HH:mm:ss�?
	 * @param days
	 * @return
	 */
	public static String dateAfter(int days) {
		return toDateTimeStr(addDay(getNow(), days));
	}

	/***
	 * 比较当前时间是否在此区间
	 * 
	 * @param startValid 开始日期　yyyy-MM-dd
	 * @param endValid 结束日期　yyyy-MM-dd
	 * @return
	 */
	public static boolean isValidDate(String startValid, String endValid) {

		try {
			if (StringUtil.isEmpty(startValid) || StringUtil.isEmpty(endValid)) {
				return false;
			}
			Date startDate = toDate(startValid);
			Date endDate = toDate(endValid);
			long current = System.currentTimeMillis();

			if ((current - startDate.getTime() / 1000) >= 0 && (current - endDate.getTime() / 1000 <= 0)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static ThreadLocal threadlocal = new ThreadLocal() {

		protected synchronized Object initialValue() {
			return new SimpleDateFormat(DATE_COMMON);
		}
	};

	public static SimpleDateFormat getDateFormat() {

		return (SimpleDateFormat) threadlocal.get();
	}

	public static Date parse(String textdate) throws ParseException {
		return getDateFormat().parse(textdate);
	}

	public static void main(String[] args) {
		boolean result1 = DateFunctions.dateAfter("2013-03-12", getDate());
		System.out.println(result1);
		boolean result2 = DateFunctions.dateAfter("2013-03-28");
		System.out.println(result2);
		System.out.println(!result1 || !result2);
	}
}
