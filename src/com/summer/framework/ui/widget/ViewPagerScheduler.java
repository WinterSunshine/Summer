package com.summer.framework.ui.widget;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

/**
 * ViewPager定时轮播
 */
public class ViewPagerScheduler
{
	private ViewPager viewPager;
	private int count = 5;
	static final int MESSAGE = 520131474;
	@SuppressLint("HandlerLeak")
	private Handler scheduleTurnHandler = new Handler() {
		@Override
		public void handleMessage(Message msg)
		{
			int index = (viewPager.getCurrentItem() + 1) % count;
			viewPager.setCurrentItem(index, true);
		}
	};
	private Timer scheduleTimer;
	
	public ViewPagerScheduler(ViewPager viewPager)
	{
		this.viewPager = viewPager;
	}
	
	/**
	 * 更新ViewPager包含的数据数量
	 * 
	 * @param count 数据量
	 */
	public void updateCount(int count)
	{
		this.count = count;
	}
	
	/**
	 * 重新开启（首次开启）定时轮播。
	 * 
	 */
	public void restart()
	{
		scheduleTimer = new Timer();
		scheduleTimer.schedule(new TimerTask() {
			@Override
			public void run()
			{
				scheduleTurnHandler.sendEmptyMessage(MESSAGE);
			}
		}, 2000, 4000);
	}
	
	/**
	 * 停止。
	 */
	public void stop()
	{
		if (scheduleTimer != null)
			scheduleTimer.cancel();
	}
}
