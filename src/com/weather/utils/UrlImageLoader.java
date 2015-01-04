package com.weather.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public class UrlImageLoader {
	private static UrlImageLoader mInstance;
	private static ExecutorService mExecutor;
	private UrlImageLoader()
	{
		if( null == mExecutor ) {
			mExecutor = Executors.newCachedThreadPool();
		}
	}
	
	public static synchronized UrlImageLoader getInstance(){
		if( null == mInstance )
			mInstance = new UrlImageLoader();
		return mInstance;
	}

	public void loadUrlImage(String urlStr,ImageView imView)
	{
		LoadUrlImageTask t = new LoadUrlImageTask(urlStr, imView);
		mExecutor.execute(t);
	}
	
	public void cancelAll()
	{
		//TODO cancel all the submitted tasks 
	}
	
	class LoadUrlImageTask implements Runnable{
		private String mUrl;
		private ImageView mImageView;
		private Bitmap mBitmap;
		private Handler mHandler;
		public LoadUrlImageTask(String urlStr,ImageView imView)
		{
			mUrl = urlStr;
			mImageView = imView;
			mHandler = new Handler(Looper.getMainLooper());
		}
		
		@Override
		public void run()
		{
			mBitmap = HttpUtils.readBitmapFromUrl(mUrl);
			if(null == mBitmap)
				return;
			if(null == mImageView)
				return;
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mImageView.setImageBitmap(mBitmap);
				}
			});
		}
	}
}
