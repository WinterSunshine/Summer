package com.summer.framework.ui.listener;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.summer.framework.base.tools.IntentUtils;
import com.summer.framework.ui.activity.MainActivity;
import com.summer.framework.ui.activity.WelcomeActivity;

public class WelcomeAnimatorListener implements AnimationListener
{
	private WelcomeActivity welcomeActivity;
	
	public WelcomeAnimatorListener(WelcomeActivity welcomeActivity)
	{
		this.welcomeActivity = welcomeActivity;
	}
	
	@Override
	public void onAnimationStart(Animation animation)
	{
	}
	
	@Override
	public void onAnimationEnd(Animation animation)
	{
		IntentUtils.startAty(welcomeActivity, MainActivity.class);
	}
	
	@Override
	public void onAnimationRepeat(Animation animation)
	{
	}
}
