package com.weather.widget;

import com.weather.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SideBar extends View {
	private Context mContext;
	private Resources mResource;
	private int mWidth;
	private int mHeight;
	private Paint mPaint;
	private String [] indiences = new String[]{};
	private OnTouchingLetterChangedListener mOnTouchingLetterChangedListener;
	private String maxLengthString = "W"; 
	private int mChoose = -1;
	public SideBar(Context context) {
		super(context, null);
		mContext = context;
		init();
	}
	
	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs,0);
		mContext = context;
		init();
	}
	
	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		init();
	}

	private void init()
	{
		mPaint = new Paint();
		mPaint.setColor(getResources().getColor(R.color.ltblue));
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mPaint.setTextSize(getResources().getDimension(R.dimen.font_size_small));
		mPaint.setAntiAlias(true);
		
		mResource = mContext.getResources();
	}
	
	public void setIndience(String[] indice)
	{
		indiences = indice;
		for(String s : indiences) 
			if(maxLengthString.length() < s.length()){
				maxLengthString = s;
			}
		invalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//'W' and 'M' are the widest letter,so use 'W' 's width as the default width
		mWidth = (int)(mPaint.measureText(maxLengthString) + mResource.getDimension(R.dimen.padding_midle));
		mHeight =  MeasureSpec.getSize(heightMeasureSpec);
		
		int widthSpec = MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY);
		int heightSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
		super.onMeasure(widthSpec,heightSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int singleHeight = mHeight / indiences.length;
		float posX,posY;
		for(int i=0;i<indiences.length;i++) {
			posX = (mWidth- mPaint.measureText(indiences[i]))/2;
			posY = singleHeight * i + mResource.getDimension(R.dimen.padding_small);
			canvas.drawText(indiences[i],posX,posY,mPaint);
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float y = event.getY();
		int c = (int) (y / getHeight() * indiences.length);
		switch(action) {
		case MotionEvent.ACTION_UP:
			mChoose = -1;
			if( null == mOnTouchingLetterChangedListener )
				return true; 
			mOnTouchingLetterChangedListener.OnTouchingLetterUp();
			break;
		default: 
			if ( c < 0 || c > indiences.length )
				return true;
			if ( null == mOnTouchingLetterChangedListener )
				return true;
			mChoose = c;
			mOnTouchingLetterChangedListener.OnTouchingLetterChanged(indiences[mChoose],mChoose);
			break;
		}
		return true;
	}

	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener l)
	{
		this.mOnTouchingLetterChangedListener = l;
	}
	
	public interface OnTouchingLetterChangedListener 
	{
		public void OnTouchingLetterChanged(String mChoose,int postion);
		public void OnTouchingLetterUp();
	}
}
