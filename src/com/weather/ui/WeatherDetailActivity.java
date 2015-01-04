package com.weather.ui;

import com.weather.model.WeatherConciseInfo;
import com.weather.model.WeatherConstant;
import com.weather.model.WeatherInfo;
import com.weather.utils.DataConverter;
import com.weather.R;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class WeatherDetailActivity extends ActionBarActivity {
	private TextView mTest;
	private WeatherInfo mWeatherInfo;
	private WeatherConciseInfo mWeatherConciseInfo;
	private int mItemNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_detail);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.perWeekWeather);
		actionBar.setDisplayShowHomeEnabled(false);
		
		mWeatherInfo = (WeatherInfo) getIntent().getSerializableExtra(WeatherConstant.WEATHER_INFO);
		mItemNo = getIntent().getIntExtra(WeatherConstant.ITEM_NO, 0);
		mWeatherConciseInfo = DataConverter.convertToConciseInfo(mWeatherInfo, mItemNo);
		mTest = (TextView) findViewById(R.id.test);
		mTest.setText("日期:"+mWeatherConciseInfo.getDate());
		mTest.append("\n\n\n星期:"+mWeatherConciseInfo.getWeek());
		mTest.append("\n\n\n天气:"+mWeatherConciseInfo.getWeatherStatus());
		mTest.append("\n\n\n温度:"+mWeatherConciseInfo.getTemperature());
		mTest.append("\n\n\n风向:"+mWeatherConciseInfo.getWindDirection());
		mTest.append("\n\n\n风力:"+mWeatherConciseInfo.getWindStrength());
	}
}
