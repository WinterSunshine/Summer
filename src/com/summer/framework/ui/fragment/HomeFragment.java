package com.summer.framework.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.framework.R;
import com.summer.framework.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return super.createView(inflater.inflate(R.layout.fragment_home, container, false));
	}
	
	@Override
	protected void findWidgets()
	{
	}
	
	@Override
	protected void initComponent()
	{
	}
}
