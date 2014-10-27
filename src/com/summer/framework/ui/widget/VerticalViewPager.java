package com.summer.framework.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class VerticalViewPager extends ViewGroup
{
	// 用于滑动的类
	private Scroller mScroller;
	// 用来跟踪触摸速度的类
	private VelocityTracker mVelocityTracker;
	// 当前的屏幕视图
	private int mCurScreen;
	// 默认的显示视图
	private int mDefaultScreen = 0;
	// 无事件的状态
	private static final int TOUCH_STATE_REST = 0;
	// 处于拖动的状态
	private static final int TOUCH_STATE_SCROLLING = 1;
	// 滑动的速度
	private static final int SNAP_VELOCITY = 600;
	private int mTouchState = TOUCH_STATE_REST;
	private int mTouchSlop;
	private float mLastMotionY;
	OnVerticalPageChangeListener mOnVerticalPageChangeListener;
	
	public VerticalViewPager(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	
	public VerticalViewPager(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mScroller = new Scroller(context);
		mCurScreen = mDefaultScreen;
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
	}
	
	public void setOnAppPageChangeListener(OnVerticalPageChangeListener onVerticalPageChangeListener)
	{
		this.mOnVerticalPageChangeListener = onVerticalPageChangeListener;
	}
	
	public interface OnVerticalPageChangeListener
	{
		public void onVerticalPageSelected(int position);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		int childheiht = 0;
		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			final View childView = getChildAt(i);
			if (childView.getVisibility() != View.GONE)
			{
				final int childWidth = childView.getMeasuredWidth();
				childView.layout(0, childheiht, childWidth, childView.getMeasuredHeight() + childheiht);
				childheiht += childView.getMeasuredHeight();
			}
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int height = MeasureSpec.getSize(heightMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY)
		{
			throw new IllegalStateException("ScrollLayout only can run at EXACTLY mode!");
		}
		final int count = getChildCount();
		for (int i = 0; i < count; i++)
		{
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
		scrollTo(0, mCurScreen * height);
	}
	
	private void snapToDestination()
	{
		final int screenHeight = getHeight();
		final int destScreen = (getScrollY() + screenHeight / 2) / screenHeight;
		snapToScreen(destScreen);
	}
	
	private void snapToScreen(int whichScreen)
	{
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		if (getScrollY() != (whichScreen * getHeight()))
		{
			final int delta = whichScreen * getHeight() - getScrollY();
			mScroller.startScroll(0, getScrollY(), 0, delta, Math.abs(delta) / 2);
			mCurScreen = whichScreen;
			invalidate();
			if (mOnVerticalPageChangeListener != null)
			{
				mOnVerticalPageChangeListener.onVerticalPageSelected(whichScreen);
			}
		}
	}
	
	public void setToScreen(int whichScreen)
	{
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		mCurScreen = whichScreen;
		scrollTo(0, whichScreen * getHeight());
		if (mOnVerticalPageChangeListener != null)
		{
			mOnVerticalPageChangeListener.onVerticalPageSelected(whichScreen);
		}
	}
	
	@Override
	public void computeScroll()
	{
		if (mScroller.computeScrollOffset())
		{
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (mVelocityTracker == null)
		{
			mVelocityTracker = VelocityTracker.obtain();
		}
		// 将当前的触摸事件传递给VelocityTracker对象
		mVelocityTracker.addMovement(event);
		// 得到触摸事件的类型
		final int action = event.getAction();
		final float y = event.getY();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				if (!mScroller.isFinished())
				{
					mScroller.abortAnimation();
				}
				mLastMotionY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				int deltay = (int) (mLastMotionY - y);
				mLastMotionY = y;
				scrollBy(0, deltay);
				break;
			case MotionEvent.ACTION_UP:
				final VelocityTracker velocityTracker = mVelocityTracker;
				// 计算当前的速度
				velocityTracker.computeCurrentVelocity(1000);
				// 获得当前的速度
				int velocityY = (int) velocityTracker.getYVelocity();
				if (velocityY > SNAP_VELOCITY && mCurScreen > 0)
				{
					snapToScreen(mCurScreen - 1);
				} else if (velocityY < -SNAP_VELOCITY && mCurScreen < getChildCount() - 1)
				{
					snapToScreen(mCurScreen + 1);
				} else
				{
					snapToDestination();
				}
				if (mVelocityTracker != null)
				{
					mVelocityTracker.recycle();
					mVelocityTracker = null;
				}
				mTouchState = TOUCH_STATE_REST;
				break;
			case MotionEvent.ACTION_CANCEL:
				mTouchState = TOUCH_STATE_REST;
				break;
		}
		return true;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST))
		{
			return true;
		}
		final float y = ev.getY();
		switch (action)
		{
			case MotionEvent.ACTION_MOVE:
				final int xDiff = (int) Math.abs(mLastMotionY - y);
				if (xDiff > mTouchSlop)
				{
					mTouchState = TOUCH_STATE_SCROLLING;
				}
				break;
			case MotionEvent.ACTION_DOWN:
				mLastMotionY = y;
				mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				mTouchState = TOUCH_STATE_REST;
				break;
		}
		return mTouchState != TOUCH_STATE_REST;
	}
}
