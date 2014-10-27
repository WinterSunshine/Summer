package com.summer.framework.base.tools;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.summer.framework.R;

public class DialogUtils
{
	private static Dialog loading;
	private static Dialog error;
	
	private DialogUtils()
	{
	}
	
	private static Dialog createDialog(Context context, int styleRes)
	{
		return new Dialog(context, styleRes);
	}
	
	private static void initDialogStyle(int layoutRes)
	{
		loading.setContentView(layoutRes);
		loading.setCanceledOnTouchOutside(false);
	}
	
	private static boolean isFragment(Object context)
	{
		return context instanceof Fragment;
	}
	
	private static void initErrorDialogView(final Object context)
	{
		View view = View.inflate(error.getContext(), R.layout.dialog_error, null);
		error.setContentView(view);
		error.setCanceledOnTouchOutside(false);
		error.setCancelable(false);
		error.getWindow().setWindowAnimations(R.style.BottomToTopAnim);
		initErrorBtnListener(context, view);
	}
	
	private static void initErrorBtnListener(final Object context, View view)
	{
		Button retry = (Button) view.findViewById(R.id.retry);
		retry.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				ReflectUtils.invokeMethod(context, "retryRetriveData");
				error.dismiss();
			}
		});
		Button setNetwork = (Button) view.findViewById(R.id.set_network);
		setNetwork.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				IntentUtils.startSettingIntent(error.getContext());
			}
		});
		Button back = (Button) view.findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				dismissError();
			}
		});
	}
	
	public static void showLoading(Object context)
	{
		if (loading == null)
		{
			if (isFragment(context))
			{
				loading = createDialog(((Fragment) context).getActivity(), R.style.CommonView_Dialog_Loading);
			} else
			{
				loading = createDialog(((FragmentActivity) context), R.style.CommonView_Dialog_Loading);
			}
			initDialogStyle(R.layout.dialog_loading);
		}
		loading.show();
	}
	
	public static void showError(Object context)
	{
		if (isFragment(context))
		{
			error = createDialog(((Fragment) context).getActivity(), R.style.CommonView_Dialog_Error);
		} else
		{
			error = createDialog(((FragmentActivity) context), R.style.CommonView_Dialog_Error);
		}
		initErrorDialogView(context);
		error.show();
	}
	
	public static void dismissLoading()
	{
		loading.dismiss();
		loading = null;
	}
	
	public static void dismissError()
	{
		error.dismiss();
		error = null;
	}
}
