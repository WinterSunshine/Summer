package com.summer.framework.base.tools;

import java.text.DateFormat;
import java.util.Date;

public class TimeUtils
{
	private TimeUtils()
	{
	}
	
	public static long getCurrentTimeInLong()
	{
		return System.currentTimeMillis();
	}
	
	public static String getCurrentTimeInString()
	{
		return DateFormat.getDateTimeInstance().format(new Date());
	}
}
