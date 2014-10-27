package com.summer.framework.base.tools;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SystemUtils
{
	private SystemUtils()
	{
	}
	
	/**
	 * 获取Session
	 * 
	 * @return
	 */
	public static String getSession()
	{
		return PreferencesUtils.getString("session", "");
	}
	
	/**
	 * 是否是平板
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context)
	{
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
	
	public static void toggleSoftKeyboard(Context context)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	public static void showSoftKeyboard(Context context, View view)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}
	
	public static void hideSoftKeyboard(Context context, View view)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	/**
	 * 安装APK
	 * 
	 * @param context
	 */
	public static void installApk(Context context)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(context.getCacheDir(), "XXXXX.apk")),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
	/**
	 * 更改APK权限
	 * 
	 * @param context
	 */
	public static void chmodApk(Context context)
	{
		try
		{
			Runtime.getRuntime().exec("chmod 777 " + new File(context.getCacheDir(), "XXXXX.apk").toString());
		} catch (IOException e)
		{
			Log.e("", e.getMessage());
		}
	}
}
