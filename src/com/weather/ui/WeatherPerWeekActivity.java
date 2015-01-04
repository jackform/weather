package com.weather.ui;

import java.util.ArrayList;
import java.util.Locale;

import com.weather.model.WeatherBriefInfo;
import com.weather.model.WeatherConstant;
import com.weather.model.WeatherInfo;
import com.weather.model.WeatherPerWeekInfo;
import com.weather.utils.DataConverter;
import com.weather.utils.HttpUtils;
import com.weather.utils.JsonParser;
import com.weather.R;
import com.weather.adapter.WeatherAdapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WeatherPerWeekActivity extends ActionBarActivity {
	private final static String TAG = "WeatherPerWeekActivity";
	private int mCityNo;
	private String mCityName;
	private WeatherAdapter mWeatherAdapter;
	private ArrayList<WeatherInfo> mWeathers;
	private ListView mWeatherList;
	private View mEmptyView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_perweek);

		mCityNo = getIntent().getIntExtra(WeatherConstant.CITY_NO,0);
		mCityName = getIntent().getStringExtra(WeatherConstant.CITY_NAME);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(mCityName);
		actionBar.setDisplayShowHomeEnabled(false);
		
		mEmptyView = findViewById(R.id.empty_view);
		mWeatherAdapter = new WeatherAdapter(WeatherPerWeekActivity.this);
		
		mWeatherList = (ListView)findViewById(R.id.perweek_weather_list);
		mWeatherList.setAdapter(mWeatherAdapter);
		mWeatherList.setOnItemClickListener(mOnEachDayClickListener);
		mWeatherList.setEmptyView(mEmptyView);
		
		WeatherObtainTask task = new WeatherObtainTask();
		task.execute();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
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
			String url = String.format(Locale.US,WeatherConstant.WEATHER_PER_WEEK_URL_BASE,mCityNo);
			String content = HttpUtils.readJsonFromUrl(url);
			Log.v(TAG,"url:"+url);
			if ( TextUtils.isEmpty(content) )
				return null;
			Log.v(TAG,content);
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
