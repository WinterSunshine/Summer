package com.summer.framework.base.tools;

import android.util.Log;

/**
 * Log工具，类似android.util.Log。 tag自动产生，格式:
 * ClassName.MethodName(LineNum:lineNumber),
 */
public class LogUtils
{
	private LogUtils()
	{
	}
	
	private static boolean allowD = true;
	private static boolean allowE = true;
	private static boolean allowI = true;
	private static boolean allowV = true;
	private static boolean allowW = true;
	
	private static String generateTag(StackTraceElement caller)
	{
		String tag = "%s.%s(Line:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		return String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
	}
	
	public static void d(String content)
	{
		if (!allowD)
			return;
		String tag = generateTag();
		Log.d(tag, content);
	}
	
	public static void d(String content, Throwable tr)
	{
		if (!allowD)
			return;
		String tag = generateTag();
		Log.d(tag, content, tr);
	}
	
	public static void e(String content)
	{
		if (!allowE)
			return;
		String tag = generateTag();
		Log.e(tag, content);
	}
	
	public static void e(String content, Throwable tr)
	{
		if (!allowE)
			return;
		String tag = generateTag();
		Log.e(tag, content, tr);
	}
	
	public static void i(String content)
	{
		if (!allowI)
			return;
		String tag = generateTag();
		Log.i(tag, content);
	}
	
	public static void i(String content, Throwable tr)
	{
		if (!allowI)
			return;
		String tag = generateTag();
		Log.i(tag, content, tr);
	}
	
	public static void v(String content)
	{
		if (!allowV)
			return;
		String tag = generateTag();
		Log.v(tag, content);
	}
	
	public static void v(String content, Throwable tr)
	{
		if (!allowV)
			return;
		String tag = generateTag();
		Log.v(tag, content, tr);
	}
	
	public static void w(String content)
	{
		if (!allowW)
			return;
		String tag = generateTag();
		Log.w(tag, content);
	}
	
	public static void w(String content, Throwable tr)
	{
		if (!allowW)
			return;
		String tag = generateTag();
		Log.w(tag, content, tr);
	}
	
	public static void w(Throwable tr)
	{
		if (!allowW)
			return;
		String tag = generateTag();
		Log.w(tag, tr);
	}
	
	private static String generateTag()
	{
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		return tag;
	}
	
	private static StackTraceElement getCallerStackTraceElement()
	{
		return Thread.currentThread().getStackTrace()[5];
	}
}
