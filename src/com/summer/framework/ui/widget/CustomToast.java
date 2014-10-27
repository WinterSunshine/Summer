package com.summer.framework.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.summer.framework.R;
import com.summer.framework.base.tools.ScreenUtils;

@SuppressLint("InflateParams")
public class CustomToast extends Toast
{
	private TextView tipTxt;
	
	public CustomToast(Context context)
	{
		super(context);
		setGravity(Gravity.BOTTOM, 0, (int) ScreenUtils.dpToPx(context, 70));
		setDuration(Toast.LENGTH_SHORT);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.panel_custom_toast, null);
		tipTxt = (TextView) view.findViewById(R.id.tv_common_custom_toast);
		setView(view);
	}
	
	public void setShowMsg(CharSequence text)
	{
		tipTxt.setText(text);
	}
}
