package com.castis.winter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Appl. 전체에 사용 되는 Date Util Class.</br> 용도에 따라 리팩토링 및 재정의 필요(ClientUI,
 * ClientHAS에서 사용)</br> Static 멤버 변수들로 구성되어 직접 사용이 가능하다.
 */
public class DateUtil // yyyy/MM/ss HH:mm:ss
{
    public static final int TIMEFORAT = 1;

    public static final int DATETIMEFORMAT = 2;

    public static int getDateYear(String yyyymmddhh24miss) {
	return Integer.parseInt(yyyymmddhh24miss.substring(0, 4));
    }

    public static int getDateMonth(String yyyymmddhh24miss) {
	return Integer.parseInt(yyyymmddhh24miss.substring(5, 7));
    }

    public static int getDateDay(String yyyymmddhh24miss) {
	return Integer.parseInt(yyyymmddhh24miss.substring(8, 10));
    }

    public static int getDateHour(String yyyymmddhh24miss) {
	return Integer.parseInt(yyyymmddhh24miss.substring(11, 13));
    }

    public static int getDateMin(String yyyymmddhh24miss) {
	return Integer.parseInt(yyyymmddhh24miss.substring(14, 16));
    }

    public static int getDateSec(String yyyymmddhh24miss) {
	return Integer.parseInt(yyyymmddhh24miss.substring(17, 19));
    }

    public static boolean DifferenceSevenDay(long firstTime, long secondTime) {
	// 7일 이내면 true 아니면 false
	if (firstTime - secondTime > 604800000L)
	    return false;
	else
	    return true;
    }

    public static int getDateYear(Date _date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	return cal.get(Calendar.YEAR);
    }

    public static int getDateMonth(Date _date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	return cal.get(Calendar.MONTH) + 1;
    }

    public static int getDateDay(Date _date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	return cal.get(Calendar.DATE);
    }

    public static int getDateHour(Date _date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDateMin(Date _date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	return cal.get(Calendar.MINUTE);
    }

    public static int getDateSec(Date _date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	return cal.get(Calendar.SECOND);
    }

    public static int getTimeHour(String _date) {
	return Integer.parseInt(_date.substring(0, 2));
    }

    public static int getTimeMin(String _date) {
	return Integer.parseInt(_date.substring(3, 5));
    }

    public static int getTimeSec(String _date) {
	return Integer.parseInt(_date.substring(6, 8));
    }

    public static Date getCurrentDate() {
	Calendar now = Calendar.getInstance();
	return now.getTime();
    }

    public static Date getDate(String _date) {
	Calendar cal = Calendar.getInstance();
	cal.clear();

	cal.set(getDateYear(_date), getDateMonth(_date) - 1, getDateDay(_date), getDateHour(_date), getDateMin(_date), getDateSec(_date));
	return cal.getTime();
    }

    public static Date getDate(String _date, String _format) throws ParseException {
	SimpleDateFormat formatter = new SimpleDateFormat(_format);
	Date currentTime = formatter.parse(_date);
	return currentTime;
    }

    public static long getMilliSecond() {
	return getCurrentDate().getTime();
    }

    public static long getDateMilliSecond(String _date) {
	return getDate(_date).getTime();
    }

    public static long getDateMilliSecond(Date _date) {
	return _date.getTime();
    }

    public static long getDateMilliSecond(String _date, String _format) throws ParseException {
	return getDate(_date, _format).getTime();

    }

    public static int getTimeMilliSecond(String _time) {
	int millisec = 3600 * 1000 * DateUtil.getTimeHour(_time) // hour
		+ 60 * 1000 * DateUtil.getTimeMin(_time) // minute
		+ 1000 * DateUtil.getTimeSec(_time);

	return millisec;
    }

    public static String getOnlyTimeString() // yyyy MM dd HH mm ss
    {
	return getDateString(getCurrentDate(), "HH:mm:ss");
    }

    public static String getOnlyTimeString(Date _date) // yyyy mm dd hh mi ss
    {
	return getDateString(_date, "HH:mm:ss");
    }

    public static String getOnlyDateString() // yyyy mm dd hh mi ss
    {
	return getDateString(getCurrentDate(), "yyyy/MM/dd");
    }

    public static String getDateString() // yyyy mm dd hh mi ss
    {
	return getDateString(getCurrentDate(), "yyyy/MM/dd HH:mm:ss");
    }

    public static String getDateString(String _format) // yyyy mm dd hh mi ss
    {
	return getDateString(getCurrentDate(), _format);
    }

    public static String getDateString(Date _date) // yyyy mm dd hh mi ss
    {
	return getDateString(_date, "yyyy/MM/dd HH:mm:ss");
    }

    public static String getDateString(Date _date, String pattern) // yyyy mm dd
								   // hh mi ss
    {
	SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));
	return formatter.format(_date);
    }

    public static String getTimeString(long millisec) {
	int playTotalsec = (int) (millisec / 1000);
	int playhour = playTotalsec / 3600;
	int playmin = (playTotalsec % 3600) / 60;
	int playsec = playTotalsec % 60;

	String playhourStr = playhour < 10 ? "0" + playhour : "" + playhour;
	String playminStr = playmin < 10 ? "0" + playmin : "" + playmin;
	String playsecStr = playsec < 10 ? "0" + playsec : "" + playsec;

	return (playhourStr + ":" + playminStr + ":" + playsecStr);

    }

    public static long getTimeLong(String timeStr) {
	String hourStr = timeStr.substring(0, 2);
	String minStr = timeStr.substring(3, 5);
	String secStr = timeStr.substring(6);

	long total = Long.parseLong(hourStr) * 3600000 + Long.parseLong(minStr) * 60000 + Long.parseLong(secStr) * 1000;

	return total;
    }

    public static String getAddDateString(String _yyyymmdd, int _amount, String _format) // yyyymmdd
    {
	Calendar cal = Calendar.getInstance();
	cal.clear();

	cal.set(Integer.parseInt(_yyyymmdd.substring(0, 4)), Integer.parseInt(_yyyymmdd.substring(4, 6)) - 1, Integer.parseInt(_yyyymmdd.substring(6, 8)));

	return getAddDateString(cal.getTime(), _amount, _format);
    }

    public static String getAddDateString(Date _date, int _amount, String _format) // yyyy
										   // mm
										   // dd
										   // hh
										   // mi
										   // ss
    {
	Calendar cal = Calendar.getInstance();
	cal.setTime(_date);
	cal.add(Calendar.DATE, _amount);

	return getDateString(cal.getTime(), _format);
    }

    public static boolean isLicenseEndDateTenDay(String licenseEnd) {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, 10);
	if (cal.getTime().getTime() > getDate(licenseEnd).getTime())
	    return true;
	else
	    return false;
    }

    public static boolean isExpirationTimeDateNDays(String licenseEnd, int day) {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, day);
	if (cal.getTime().getTime() > getDate(licenseEnd).getTime())
	    return true;
	else
	    return false;
    }

    public static String getAvailableDate() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentDate());
	cal.add(Calendar.DAY_OF_MONTH, 1);

	return getDateString(cal.getTime(), "MM.dd HH:mm");
    }

    public static String getDayString(String date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getDate(date));
	int day = cal.get(Calendar.DATE);
	return String.valueOf(day);
    }

    public static String getHourString(String date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getDate(date));
	int hour = (cal.get(Calendar.DATE) * 24) + cal.get(Calendar.HOUR);
	return String.valueOf(hour);
    }

    public static String getOnlyDateString(Date _date) {
	// yyyy mm dd hh mi ss
	return getDateString(_date, "yyyy.MM.dd");
    }

    public static boolean is365days(String date) {
	return (date.substring(8, 11).equals("365"));
    }

    public static int getHour(String date) {
	int hour;
	if (is365days(date))
	    hour = 365 * 24;
	else
	    hour = (getDateDay(date) * 24) + getDateHour(date);
	return hour;
    }

    public static int getDay(String date) {
	int day = getDateDay(date);// + getDateHour(date);
	return day;
    }

    public static boolean isValidDate(String format, String date) {
	boolean isValid = true;
	SimpleDateFormat formatter = new SimpleDateFormat(format);
	formatter.setLenient(false);
	try {
	    formatter.format(getDate(date));
	} catch (Exception e) {
	    isValid = false;
	} finally {
	    formatter = null;
	}
	return isValid;
    }

}
