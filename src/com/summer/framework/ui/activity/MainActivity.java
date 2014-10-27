package com.summer.framework.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.summer.framework.R;
import com.summer.framework.base.constant.Constant;
import com.summer.framework.base.tools.ToastUtils;
import com.summer.framework.ui.base.BaseActivity;
import com.summer.framework.ui.fragment.CategoryFragment;
import com.summer.framework.ui.fragment.HomeFragment;
import com.summer.framework.ui.fragment.PersonalFragment;
import com.summer.framework.ui.fragment.SubjectFragment;
import com.summer.framework.ui.widget.FragmentTabHost;

public class MainActivity extends BaseActivity
{
	private static final String HOME = "首页";
	private static final String SUBJECT = "专题";
	private static final String CATEGORY = "分类";
	private static final String PERSONAL = "个人";
	private long exitTime = 0;
	private FragmentTabHost mTabHost;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.onCreateView(R.layout.activity_main);
	}
	
	@Override
	protected void findWidgets()
	{
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
	}
	
	@Override
	protected void initComponent()
	{
		initTabHost();
	}
	
    private void initTabHost()
	{
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.addTab(mTabHost.newTabSpec(Constant.TAB_TAG_HOME).setIndicator(createIndicatorView(R.drawable.selector_maintab_nav_home, HOME)), HomeFragment.class, new Bundle());
		mTabHost.addTab(mTabHost.newTabSpec(Constant.TAB_TAG_SUBJECT).setIndicator(createIndicatorView(R.drawable.selector_maintab_nav_subject, SUBJECT)), SubjectFragment.class, new Bundle());
		mTabHost.addTab(mTabHost.newTabSpec(Constant.TAB_TAG_CATEGORY).setIndicator(createIndicatorView(R.drawable.selector_maintab_nav_category, CATEGORY)), CategoryFragment.class, new Bundle());
		mTabHost.addTab(mTabHost.newTabSpec(Constant.TAB_TAG_PERSONAL).setIndicator(createIndicatorView(R.drawable.selector_maintab_nav_personal, PERSONAL)), PersonalFragment.class, new Bundle());
	}
	
	public FragmentTabHost getTabHost()
	{
		return mTabHost;
	}
	
	@SuppressLint("InflateParams")
	private View createIndicatorView(int selectorRes, String indictorName)
	{
		TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_maintab_navigation, null);
		textView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(selectorRes), null, null);
		textView.setText(indictorName);
		return textView;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0)
			{
				if (TextUtils.equals(mTabHost.getCurrentTabTag(), Constant.TAB_TAG_HOME))
				{
					exitApp();
				} else
				{
					mTabHost.setCurrentTabByTag(Constant.TAB_TAG_HOME);
				}
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
	
	private void exitApp()
	{
		if ((System.currentTimeMillis() - exitTime) > 2000)
		{
			ToastUtils.custom("再按一次退出程序");
			exitTime = System.currentTimeMillis();
		} else
		{
			getApplication().onTerminate();
		}
	}
}
