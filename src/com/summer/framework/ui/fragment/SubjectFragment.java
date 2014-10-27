package com.summer.framework.ui.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.framework.R;
import com.summer.framework.base.tools.ProxyUtils;
import com.summer.framework.service.entity.GameBean;
import com.summer.framework.service.entity.GameBean.GameData;
import com.summer.framework.ui.base.BaseFragment;

public class SubjectFragment extends BaseFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return super.createView(inflater.inflate(R.layout.fragment_subject, container, false));
	}
	
	@Override
	protected void findWidgets()
	{
	}
	
	@Override
	protected void initComponent()
	{
	}
	
	@Override
	protected void asyncRetrive()
	{
		ProxyUtils.getHttpProxy().getAllGameList(this);
	}
	
	protected void refreshGames(GameBean bean)
	{
		List<GameData> games = bean.getGames();
		System.out.println(games);
	}
}
