package com.summer.framework.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summer.framework.R;
import com.summer.framework.base.tools.ProxyUtils;
import com.summer.framework.service.entity.PersonalBean;
import com.summer.framework.ui.base.BaseFragment;

public class PersonalFragment extends BaseFragment
{
	private TextView balanceTxt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return super.createView(inflater.inflate(R.layout.fragment_personal, container, false));
	}
	
	@Override
	protected void findWidgets()
	{
		balanceTxt = findView(R.id.tv_personal_balance);
	}
	
	@Override
	protected void initComponent()
	{
		balanceTxt.setText("读取中...");
	}
	
	@Override
	protected void asyncRetrive()
	{
		ProxyUtils.getHttpProxy().getUserInfo("UserID", this);
	}
	
	protected void refreshPersonal(PersonalBean perosnal)
	{
		balanceTxt.setText(perosnal.getUserBalance());
	}
}
