package com.summer.framework.ui.base;

import com.summer.framework.SummerApplication;
import com.summer.framework.base.tools.ReflectUtils;
import com.summer.framework.base.tools.ToastUtils;

import android.support.v4.app.FragmentActivity;
import android.view.View;

public abstract class BaseActivity extends FragmentActivity
{
	protected abstract void findWidgets();
	protected abstract void initComponent();
	
	protected void onCreateView(int layoutResID)
	{
		super.setContentView(layoutResID);
		findWidgets();
		initComponent();
		initListener();
		initHandler();
		excuteOther();
		asyncRetrive();
		pushAtyToStack();
	}
	
	/**
	 * 后退按钮响应事件
	 */
	public void onBackClick(View v)
	{
		finish();
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T findView(int id)
	{
		return (T) findViewById(id);
	}
	
	/**
	 * 初始化Listener，子类根据需要自行重写
	 */
	protected void initListener()
	{
		return;
	}
	
	/**
	 * 初始化Handler，子类根据需要自行重写
	 */
	protected void initHandler()
	{
		return;
	}
	
	/**
	 * 做一些其他的事情，如开启一个定时器或者线程、getIntentExtra、显示一个WebPage等等..
	 */
	protected void excuteOther()
	{
		return;
	}
	
	/**
	 * 异步查询网络数据，子类根据需要自行重写
	 */
	protected void asyncRetrive()
	{
		return;
	}
	
	/**
	 * 重试查询网络数据，子类根据需要自行重写
	 */
	protected void retryRetrive()
	{
		ReflectUtils.invokeMethod(this, "asyncRetrive");
	}
	
	/**
	 * 显示失败信息，默认显示吐司，子类有需要显示界面可自行重写
	 */
	protected void showErrorMessage(String errorMessage)
	{
		ToastUtils.custom(errorMessage);
	}
	
	/**
	 * 把当前Activity Push栈中
	 */
	private void pushAtyToStack()
	{
		SummerApplication.push(this);
	}
	
	/**
	 * 把当前Activity Pop出栈
	 */
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		SummerApplication.pop(this);
	}
}
