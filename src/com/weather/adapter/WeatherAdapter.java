package com.weather.adapter;

import java.util.ArrayList;

import com.weather.model.WeatherBriefInfo;
import com.weather.utils.UrlImageLoader;
import com.weather.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {
	private ArrayList<WeatherBriefInfo> mData;
	private Context mContext;
	private LayoutInflater inflater;
	 
	public WeatherAdapter(Context context){
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}
	
	public void setData(ArrayList<WeatherBriefInfo> weeklyWeather)
	{
		mData = weeklyWeather;
	}
	@Override
	public int getCount() {
		return null == mData ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return null == mData ? null : mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressLint("InflateParams")
	public View getView(int position, View view, ViewGroup root) {
		ViewHolder holder;
		if ( null == view ) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.item_weather_perweek, null);
			holder.tvDate = (TextView)view.findViewById(R.id.tv_date);
			holder.tvWeek = (TextView)view.findViewById(R.id.tv_week);
			holder.tvTemp = (TextView)view.findViewById(R.id.tv_temperature);
			holder.ivWeahter1 = (ImageView)view.findViewById(R.id.iv_weather_1);
			holder.ivWeather2 = (ImageView)view.findViewById(R.id.iv_weather_2);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		WeatherBriefInfo weather = mData.get(position);
		holder.tvTemp.setText(weather.getTemperature());
		holder.tvDate.setText(weather.getDate());
		holder.tvWeek.setText(weather.getWeek());
		UrlImageLoader.getInstance().loadUrlImage(weather.getWeatherPictureUrl1(),holder.ivWeahter1);
		//如果有两个天气状态的图片，都显示出来
		if(null != weather.getWeatherPictureUrl2()) {
			holder.ivWeather2.setVisibility(View.VISIBLE);
			UrlImageLoader.getInstance().loadUrlImage(weather.getWeatherPictureUrl2(),holder.ivWeather2);
		} else {
			holder.ivWeather2.setVisibility(View.GONE);
		}
		return view;
	}

	public final class ViewHolder 
	{
		public TextView tvDate;
		public TextView tvWeek;
		public TextView tvTemp;
		public ImageView ivWeahter1;
		public ImageView ivWeather2;
	}
}
