package com.weather.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PullToRefreshListView extends LinearLayout implements OnScrollListener {
	private boolean DEBUG = true;
	private String TAG = "PULL_TO_REFRESH";
	private ListView mRefreshableView;
	private LinearLayout mHeaderLayout;
	private FrameLayout mRefreshViewWrapper;
	
	private int mTouchSlop;
	private State mCurrentState = State.RESET;
	
	public PullToRefreshListView(Context context) {
		super(context);
		init(context,null);
	}
	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}
	
	private void init(Context context,AttributeSet attrs) 
	{
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER);
		
		ViewConfiguration config = ViewConfiguration.get(context);
		mTouchSlop = config.getScaledTouchSlop();
		if(DEBUG)
			Log.v(TAG,"touchslop:" + mTouchSlop);
		//create ListView 
		mRefreshableView = createRefreshableView(context,attrs);
		addRefreshableView(context,mRefreshableView);
		
		
		//create pullToRefresh Header view
		mHeaderLayout = createLoadingLayout(context);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
								LinearLayout.LayoutParams.WRAP_CONTENT);
		addView(mHeaderLayout,0,lp);
		refreshLoadingViewsSize();
	}
	
	private void refreshLoadingViewsSize() {
	}
	private LinearLayout createLoadingLayout(Context context) {
		TextView t = new TextView(context);
		t.setText("aaaa");
		LinearLayout l = new LinearLayout(context);
		l.addView(t);
		l.setVisibility(View.INVISIBLE);
		return l;
	}
	private void addRefreshableView(Context context, ListView refreshableView) {
		mRefreshViewWrapper = new FrameLayout(context);
		mRefreshViewWrapper.addView(refreshableView,
								ViewGroup.LayoutParams.MATCH_PARENT,
								ViewGroup.LayoutParams.MATCH_PARENT);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
											LinearLayout.LayoutParams.MATCH_PARENT,
											LinearLayout.LayoutParams.MATCH_PARENT);						
		addView(mRefreshViewWrapper,-1,lp);	
	}
	
	private ListView createRefreshableView(Context context, AttributeSet attrs) {
		return new ListView(context,attrs);
	}
	
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
	
	}
	
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
	
	}
	
	public static enum State {
		RESET,
		PULL_TO_REFRESH,
		RELEASE_TO_REFRESH,
		REFRESHING,
		MANUAL_REFRESHING,
		OVERSCROLLING;
	}
}
