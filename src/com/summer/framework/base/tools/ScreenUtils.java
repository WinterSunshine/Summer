package com.summer.framework.base.tools;

import android.content.Context;

public class ScreenUtils
{
	private ScreenUtils()
	{
	}
	
	public static float dpToPx(Context context, float dp)
	{
		if (context == null)
		{
			return -1;
		}
		return dp * context.getResources().getDisplayMetrics().density;
	}
	
	public static float pxToDp(Context context, float px)
	{
		if (context == null)
		{
			return -1;
		}
		return px / context.getResources().getDisplayMetrics().density;
	}
	
	public static float dpToPxInt(Context context, float dp)
	{
		return (int) (dpToPx(context, dp) + 0.5f);
	}
	
	public static float pxToDpCeilInt(Context context, float px)
	{
		return (int) (pxToDp(context, px) + 0.5f);
	}
}
