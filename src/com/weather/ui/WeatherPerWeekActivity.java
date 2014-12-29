package com.weather.ui;

import java.util.ArrayList;

import com.weather.model.WeatherBriefInfo;
import com.weather.model.WeatherConstant;
import com.weather.model.WeatherInfo;
import com.weather.model.WeatherPerWeekInfo;
import com.weather.utils.DataConverter;
import com.weather.utils.HttpUtils;
import com.weather.utils.JsonParser;
import com.weather.R;
import com.weather.adapter.WeatherAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WeatherPerWeekActivity extends Activity {
	private final static String TAG = "WeatherPerWeekActivity";
	private int mCityNo ;
	private WeatherAdapter mWeatherAdapter;
	private ListView mWeatherList;
	private ArrayList<WeatherInfo> mWeathers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_perweek);
		
		mCityNo = getIntent().getIntExtra(WeatherConstant.CITY_NO,0);
		mWeatherAdapter = new WeatherAdapter(WeatherPerWeekActivity.this);
		mWeatherList = (ListView)findViewById(R.id.perweek_weather_list);
		mWeatherList.setAdapter(mWeatherAdapter);
		mWeatherList.setOnItemClickListener(mOnEachDayClickListener);
		
		WeatherObtainTask task = new WeatherObtainTask();
		task.execute();
	}
	
	public OnItemClickListener mOnEachDayClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> listView, View view, int position,
				long Id) {
			Intent intent = new Intent(WeatherPerWeekActivity.this, WeatherDetailActivity.class);
			intent.putExtra(WeatherConstant.CITY_NO, mCityNo);
			intent.putExtra(WeatherConstant.ITEM_NO,position);
			intent.putExtra(WeatherConstant.WEATHER_INFO, mWeathers.get(position));
			startActivity(intent);
		}
	};
	
	class WeatherObtainTask extends AsyncTask<Void, Integer, ArrayList<WeatherBriefInfo>>
	{
		@Override
		protected ArrayList<WeatherBriefInfo> doInBackground(Void... arg0) {
			String url = WeatherConstant.WEATHER_PER_WEEK_URL_BASE + mCityNo + ".html";
			Log.v(TAG,"url:"+url);
			String content = HttpUtils.readJsonFromUrl(url);
			Log.v(TAG,content);
			if ( TextUtils.isEmpty(content) )
				return null;
			WeatherPerWeekInfo weatherAllInfo = JsonParser.weatherPerWeekParser(content);
			mWeathers = weatherAllInfo.getWeathers();
			return DataConverter.convertToBriefWeathers(weatherAllInfo);
		}

		@Override
		protected void onPostExecute(ArrayList<WeatherBriefInfo> result) {
			super.onPostExecute(result);
			if( null == result )
				return;
			mWeatherAdapter.setData(result);
			mWeatherAdapter.notifyDataSetChanged();
		}		
	}
}
