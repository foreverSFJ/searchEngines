package searchEngines.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 对获取的代码内容进行细节处理
 * @author SFJ
 */
public final class DetailProcess {

	/**
	 * 获取书籍的状态
	 * @param status
	 * @return
	 */
	public static String getNovelStatus(String status) {
		if (status.contains("连载")) {
			return "连载中";
		} else if (status.contains("完结") || status.contains("完成") || status.contains("完本")|| status.contains("已完成")) {
			return "完结";
		} else {
			throw new RuntimeException ("不支持的书籍状态：" + status);
		}
	}
	
	/**
	 * 格式化日期字符串为日期对象,为年-月-日
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr, String pattern) throws ParseException {
		if ("MM-dd".equals(pattern)) {
			pattern = "yyyy-MM-dd";
			dateStr = getDateField(Calendar.YEAR) + "-" + dateStr;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	/**
	 * 格式化日期字符串为日期对象，为年-月-日-时-分-秒
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateEtc(String dateStr, String pattern) throws ParseException {
		if ("MM-dd".equals(pattern)) {
			pattern = "yyyy-MM-dd";
			dateStr = getDateField(Calendar.YEAR) + "-" + dateStr ;
		}else if ("yyyy-MM-dd".equals(pattern)) {
			dateStr = dateStr+ " " + getDateField(Calendar.HOUR_OF_DAY)+ ":" + getDateField(Calendar.MINUTE) + ":" + getDateField(Calendar.SECOND);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	
	/**
	 * 获取本时刻的字符量
	 * @param field
	 * @return
	 */
	public static String getDateField(int field) {
		Calendar cal = new GregorianCalendar();
		return cal.get(field) + "";
	}
	
	/**
	 * 获取系统当前时间字符串
	 * @return
	 */
	public static String getPerDate() {
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return date;
	}
}
