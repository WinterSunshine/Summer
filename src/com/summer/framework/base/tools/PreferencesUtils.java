package com.summer.framework.base.tools;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils
{
	private static final String CONFIG_FILE = "config";
	private static SharedPreferences mSp;
	
	private PreferencesUtils()
	{
	}
	
	public static void openFile(Context context)
	{
		mSp = context.getSharedPreferences(CONFIG_FILE, 0);
	}
	
	public static void clear()
	{
		mSp.edit().clear().commit();
	}
	
	public static boolean put(String key, boolean value)
	{
		return mSp.edit().putBoolean(key, value).commit();
	}
	
	public static boolean put(String key, int value)
	{
		return mSp.edit().putInt(key, value).commit();
	}
	
	public static boolean put(String key, long value)
	{
		return mSp.edit().putLong(key, value).commit();
	}
	
	public static boolean put(String key, float value)
	{
		return mSp.edit().putFloat(key, value).commit();
	}
	
	public static boolean put(String key, String value)
	{
		return mSp.edit().putString(key, value).commit();
	}
	
	public static boolean getBoolean(String key, boolean defaultValue)
	{
		return mSp.getBoolean(key, defaultValue);
	}
	
	public static int getInt(String key, int defaultValue)
	{
		return mSp.getInt(key, defaultValue);
	}
	
	public static long getLong(String key, long defaultValue)
	{
		return mSp.getLong(key, defaultValue);
	}
	
	public static float getFloat(String key, float defaultValue)
	{
		return mSp.getFloat(key, defaultValue);
	}
	
	public static String getString(String key, String defaultValue)
	{
		return mSp.getString(key, defaultValue);
	}
	
	public static Map<String, ?> getAll()
	{
		return mSp.getAll();
	}
	
	public static void remove(String key)
	{
		mSp.edit().remove(key).commit();
	}
}
