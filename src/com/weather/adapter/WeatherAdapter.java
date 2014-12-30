package com.weather.adapter;

import java.util.ArrayList;

import com.weather.model.WeatherBriefInfo;
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
	private Context mContext;
	private ArrayList<WeatherBriefInfo> mData;
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
			holder.tvDate = (TextView)view.findViewById(R.id.date);
			holder.tvTemp = (TextView)view.findViewById(R.id.temperature);
//			holder.tvWeather = (TextView)view.findViewById(R.id.weather);
			holder.iv_weahter = (ImageView)view.findViewById(R.id.iv_weather);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		WeatherBriefInfo weather = mData.get(position);
		holder.tvTemp.setText(weather.getTemperature());
		holder.tvDate.setText(weather.getDate()+"\n"+weather.getWeek());
//		holder.tvWeather.setText(weather.getWeatherStatus());
		holder.iv_weahter.setImageBitmap(weather.getWeatherPicture());
		return view;
	}

	public final class ViewHolder 
	{
		public TextView tvDate;
//		public TextView	tvWeather;
		public TextView tvTemp;
		public ImageView iv_weahter;
	}
}
