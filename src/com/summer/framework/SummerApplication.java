package com.summer.framework;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

import com.summer.framework.base.tools.AppCrashUtils;
import com.summer.framework.base.tools.PreferencesUtils;
import com.summer.framework.base.tools.ToastUtils;

public class SummerApplication extends Application
{
	private static Stack<Activity> atyStack = new Stack<Activity>();
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		ToastUtils.init(this);
		AppCrashUtils.init(this);
		PreferencesUtils.openFile(this);
	}
	
	public static void push(Activity aty)
	{
		atyStack.push(aty);
	}
	
	@Override
	public void onTerminate()
	{
		super.onTerminate();
		while (!atyStack.empty())
		{
			atyStack.pop().finish();
		}
		Process.killProcess(Process.myPid());
	}
	
	public static void closeSeries(Class<Activity> cls)
	{
		for (int pos = findPos(cls); pos < atyStack.size() - 1;)
		{
			atyStack.pop().finish();
		}
	}
	
	private static int findPos(Class<Activity> cls)
	{
		for (int pos = 0; pos < atyStack.size(); pos++)
		{
			if (atyStack.get(pos).getClass() == cls)
			{
				return pos;
			}
		}
		throw new IllegalStateException();
	}
	
	public static void pop(Activity aty)
	{
		atyStack.remove(aty);
	}
}
