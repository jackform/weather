package com.weather.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class UrlImageLoader {
	private static UrlImageLoader mInstance;
	private static ExecutorService mExecutor;
	private static LruCache<String, Bitmap> mCache;
	private UrlImageLoader()
	{
		if( null == mExecutor ) {
			mExecutor = Executors.newCachedThreadPool();
		}
	}
	
	public static synchronized UrlImageLoader getInstance(){
		if( null == mInstance )
			mInstance = new UrlImageLoader();
		if( null == mCache ) {
			int memCache = (int) (Runtime.getRuntime().maxMemory() / 16);
			mCache = new LruCache<String, Bitmap>(memCache){
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}
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
			if( null == mImageView)
				return;
			mBitmap = mCache.get(mUrl);
			if( null == mBitmap )  {
				mBitmap = HttpUtils.readBitmapFromUrl(mUrl);
				if( null != mBitmap )
					mCache.put(mUrl,mBitmap);
				else 
					return;
			}
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mImageView.setImageBitmap(mBitmap);
				}
			});
		}
	}
}
