package com.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

public class DateUtils {
	private static SimpleDateFormat sdf;
	private static Calendar mCalendar;
	private static Hashtable<Integer,String> weekDaysQuery;
	
	static {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		weekDaysQuery = new Hashtable<Integer,String>();
		for( int i=1;i<=weekDaysName.length;i++ ) {
			if( weekDaysQuery.containsKey(i) )
				continue;
			weekDaysQuery.put(i,weekDaysName[i-1]);
		}
		
		sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		mCalendar = Calendar.getInstance(); 
		mCalendar.setTime(new Date()); 
	}
	
	public static void reset2Today()
	{
		mCalendar.setTime(new Date()); 
	}
	
	public static String getDate()
	{
		return sdf.format(mCalendar.getTime());
	}
	
	public static String getWeek()
	{
		return weekDaysQuery.get(mCalendar.get(Calendar.DAY_OF_WEEK));
	}
	
	public static void nextDay()
	{
		mCalendar.add(Calendar.DATE, 1);
	}
	
	public static void moveToNextNDay(int n)
	{
		mCalendar.add(Calendar.DATE,n);
	}
}
