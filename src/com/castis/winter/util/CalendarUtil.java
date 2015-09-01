package com.castis.winter.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Appl. 전체에 사용 되는 Calendar Util Class.</br> 용도에 따라 리팩토링 및 재정의 필요(ClientUI에서
 * 사용)</br>
 * 
 */
public class CalendarUtil {
    public String[] DAY_STRING = { "일", "월", "화", "수", "목", "금", "토" };

    private int year, month, date, day, hour, minute, second, startDay, lastDate = 0;

    private Calendar calendar;

    public CalendarUtil() {
	calendar = Calendar.getInstance();
	getCalendar();
    }

    public CalendarUtil(Date _date) {
	calendar = Calendar.getInstance();
	calendar.setTime(_date);
	getCalendar();
    }

    public void setTime(Date _date) {
	calendar.setTime(_date);
    }

    public Date getTime() {
	return calendar.getTime();
    }

    public long getTimeMillis() {
	return calendar.getTime().getTime();
    }

    public int getYear() {
	return this.year;
    } // 년도 구하기

    public int getMonth() {
	return this.month;
    } // 월 구하기

    public int getDate() {
	return this.date;
    } // 날짜 구하기

    /* 현재 시간을 14자리의 문자열로 구함 */
    public String getDate14() {
	String date_str = "";
	getCalendar();

	date_str += year;
	date_str += conv_str(month);
	date_str += conv_str(date);
	date_str += conv_str(hour);
	date_str += conv_str(minute);
	date_str += conv_str(second);

	return date_str;
    }

    public int getDay() {
	return this.day;
    } // 요일 숫자로 구하기( 1=일, 2=월,... 7=토요일)

    public String getDayString() {
	return DAY_STRING[day - 1];
    } // 요일을 글자로 구하기 (일,월,화,...토)

    public int getStartDay() {
	return this.startDay;
    } // 당월 1일의 요일 숫자 구하기

    public int getLastDate() {
	return this.lastDate;
    } // 당월 마지막 날짜 구하기

    // 오늘 정보로 되돌리려면 getCalendar( null )하면 됨.
    public void getCalendar(Calendar calendar) {
	if (calendar == null)
	    calendar = Calendar.getInstance();
	this.calendar = calendar;
	getCalendar();
    }

    public void getCalendar() {
	year = calendar.get(Calendar.YEAR); // 년 구하기
	month = calendar.get(Calendar.MONTH) + 1; // 월 구하기
	date = calendar.get(Calendar.DAY_OF_MONTH); // 일 구하기
	day = calendar.get(Calendar.DAY_OF_WEEK); // 요일 구하기
	hour = calendar.get(Calendar.HOUR_OF_DAY); // 시간 구하기
	minute = calendar.get(Calendar.MINUTE); // 분 구하기
	second = calendar.get(Calendar.SECOND); // 초 구하기
	startDay = getstartday(); // 당월 1일의 요일 구하기
	lastDate = getlastdate();// 당월의 마지막 날짜 구하기
    }

    public void getCalendar(int recvYear, int recvMonth, int recvDate) {
	calendar = new GregorianCalendar(recvYear, recvMonth - 1, recvDate);
	year = recvYear;
	month = recvMonth;
	date = recvDate;
	day = calendar.get(Calendar.DAY_OF_WEEK);
	startDay = getstartday();
	lastDate = getlastdate();
    }

    // 년,월까지만 입력시
    public void getCalendar(int recvYear, int recvMonth) {
	getCalendar(recvYear, recvMonth, 1);
    }

    // 당월 1일의 요일 구하기
    private int getstartday() {
	GregorianCalendar cal = new GregorianCalendar(year, month - 1, 1);
	return cal.get(Calendar.DAY_OF_WEEK);
    }

    // 당월의 마지막 날짜 구하기
    private int getlastdate() {
	int total = 0;
	switch (month) {
	case 2:
	    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
		total = 29;
	    } else {
		total = 28;
	    } // end if
	    break;
	case 4:
	case 6:
	case 9:
	case 11:
	    total = 30;
	    break;
	default:
	    total = 31;
	} // end switch
	return total;
    } // end getlastDate()

    // 작년
    public void previousYear() {
	calendar.add(Calendar.YEAR, -1);
	getCalendar();
    }

    // 내년
    public void nextYear() {
	calendar.add(Calendar.YEAR, 1);
	getCalendar();
    }

    public void moveYear(int go) {
	calendar.add(Calendar.YEAR, go);
	getCalendar();
    }

    // 지난달
    public void previousMonth() {
	calendar.add(Calendar.MONTH, -1);
	getCalendar();
    }

    // 다음달
    public void nextMonth() {
	calendar.add(Calendar.MONTH, 1);
	getCalendar();
    }

    public void moveMonth(int go) {
	calendar.add(Calendar.MONTH, go);
	getCalendar();
    }

    // 어제
    public void previousDay() {
	calendar.add(Calendar.DAY_OF_MONTH, -1);
	getCalendar();
    }

    // 내일
    public void nextDay() {
	calendar.add(Calendar.DAY_OF_MONTH, 1);
	getCalendar();
    }

    public void moveDay(int go) {
	calendar.add(Calendar.DAY_OF_MONTH, go);
	getCalendar();
    }

    // 다음주
    public void nextWeek() {
	calendar.add(Calendar.WEEK_OF_MONTH, 1);
	getCalendar();
    }

    // 지난주
    public void previousWeek() {
	calendar.add(Calendar.WEEK_OF_MONTH, -1);
	getCalendar();
    }

    public void moveWeek(int go) {
	calendar.add(Calendar.WEEK_OF_MONTH, go);
	getCalendar();
    }

    // 다음시간
    public void nextHour() {
	calendar.add(Calendar.HOUR_OF_DAY, 1);
	getCalendar();
    }

    // 이전시간
    public void previousHour() {
	calendar.add(Calendar.HOUR_OF_DAY, -1);
	getCalendar();
    }

    public void moveHour(int go) {
	calendar.add(Calendar.HOUR_OF_DAY, go);
	getCalendar();
    }

    public void moveMinute(int go) {
	calendar.add(Calendar.MINUTE, go);
	getCalendar();
    }

    public void moveSecond(int go) {
	calendar.add(Calendar.SECOND, go);
	getCalendar();
    }

    public void moveTime(Calendar date) {
	calendar.add(Calendar.YEAR, date.get(Calendar.YEAR));
	calendar.add(Calendar.MONTH, date.get(Calendar.MONTH)+1);
	calendar.add(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
	calendar.add(Calendar.HOUR_OF_DAY, date.get(Calendar.HOUR_OF_DAY));
	calendar.add(Calendar.MINUTE, date.get(Calendar.MINUTE));
	calendar.add(Calendar.SECOND, date.get(Calendar.SECOND));
	getCalendar();
    }

    public void moveTime(int year, int month, int day, int hour, int min, int sec) {
	calendar.add(Calendar.YEAR, year);
	calendar.add(Calendar.MONTH, month);
	calendar.add(Calendar.DAY_OF_MONTH, day);
	calendar.add(Calendar.HOUR_OF_DAY, hour);
	calendar.add(Calendar.MINUTE, min);
	calendar.add(Calendar.SECOND, sec);
	getCalendar();

    }

    public String conv_str(int a) {
	String r = "";
	if (a < 10)
	    r += "0" + a;
	else
	    r += a;
	return r;
    }
} // end CalendarBean

