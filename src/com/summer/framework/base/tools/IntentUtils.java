package com.summer.framework.base.tools;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;

public class IntentUtils
{
	private IntentUtils()
	{
	}
	
	public static <T> void startAty(Context context, Class<?> cls)
	{
		Intent intent = new Intent(context, cls);
		context.startActivity(intent);
	}
	
	public static void startAtyWithSingleParam(Context context, Class<?> cls, String key, String value)
	{
		Intent intent = new Intent(context, cls);
		intent.putExtra(key, value);
		context.startActivity(intent);
	}
	
	public static void startAtyWithParams(Context context, Class<?> cls, List<ParamUtils.NameValue> extras)
	{
		Intent intent = new Intent(context, cls);
		for (ParamUtils.NameValue item : extras)
		{
			setValueToIntent(intent, item.name, item.value);
		}
		context.startActivity(intent);
	}
	
	public static void startAtyWithSerialObj(Context context, Class<?> cls, String key, Serializable obj)
	{
		Intent intent = new Intent(context, cls);
		intent.putExtra(key, obj);
		context.startActivity(intent);
	}
	
	public static void startAtyForResult(Activity aty, Class<?> cls, int requestCode)
	{
		Intent intent = new Intent(aty, cls);
		aty.startActivityForResult(intent, requestCode);
	}
	
	public static void startAtyForResult(Fragment fragment, Class<?> cls, int requestCode)
	{
		Intent intent = new Intent(fragment.getActivity(), cls);
		fragment.startActivityForResult(intent, requestCode);
	}
	
	public static void startAtyClearTop(Activity context, Class<?> cls)
	{
		Intent intent = new Intent(context, cls);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	public static void startDialNumberIntent(Activity context, String phoneNumber)
	{
		context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
	}
	
	public static void startSMSIntent(Activity context, String phoneNumber, String body)
	{
		context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)));
	}
	
	public static void startSettingIntent(Context context)
	{
		Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
		context.startActivity(intent);
	}
	
	private static void setValueToIntent(Intent intent, String key, Object val)
	{
		if (null == key || null == val)
			return;
		if (val instanceof Boolean)
			intent.putExtra(key, (Boolean) val);
		else if (val instanceof Boolean[])
			intent.putExtra(key, (Boolean[]) val);
		else if (val instanceof String)
			intent.putExtra(key, (String) val);
		else if (val instanceof String[])
			intent.putExtra(key, (String[]) val);
		else if (val instanceof Integer)
			intent.putExtra(key, (Integer) val);
		else if (val instanceof Integer[])
			intent.putExtra(key, (Integer[]) val);
		else if (val instanceof Long)
			intent.putExtra(key, (Long) val);
		else if (val instanceof Long[])
			intent.putExtra(key, (Long[]) val);
		else if (val instanceof Double)
			intent.putExtra(key, (Double) val);
		else if (val instanceof Double[])
			intent.putExtra(key, (Double[]) val);
		else if (val instanceof Float)
			intent.putExtra(key, (Float) val);
		else if (val instanceof Float[])
			intent.putExtra(key, (Float[]) val);
		else
		{
			throw new IllegalArgumentException("Not support data Type!");
		}
	}
}
