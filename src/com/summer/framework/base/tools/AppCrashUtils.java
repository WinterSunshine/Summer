package com.summer.framework.base.tools;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Application;
import android.os.Looper;

import com.summer.framework.base.constant.Constant;

public class AppCrashUtils implements UncaughtExceptionHandler
{
	private static AppCrashUtils mCrashHandler = new AppCrashUtils();
	private static Application mApplaction;
	
	private AppCrashUtils()
	{
	}
	
	public static void init(Application applaction)
	{
		mApplaction = applaction;
		Thread.setDefaultUncaughtExceptionHandler(mCrashHandler);
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		try
		{
			writeLog(ex);
			showToast(ex);
			ex.printStackTrace();
			mApplaction.onTerminate();
		} catch (Exception e)
		{
		}
	}
	
	private void writeLog(Throwable ex) throws Exception
	{
		FileUtils.WriteCrashLog(mApplaction.getFileStreamPath(Constant.LOG_FILE), ex);
	}
	
	private void showToast(final Throwable ex) throws InterruptedException
	{
		new Thread()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void run()
			{
				Looper.prepare();
				ToastUtils.show("很抱歉,程序出现异常,即将退出" + ex.getMessage());
				Looper.loop();
			}
		}.start();
		Thread.sleep(3000);
	}
}
