package com.weather.ui;

import com.weather.model.WeatherConciseInfo;
import com.weather.model.WeatherConstant;
import com.weather.model.WeatherInfo;
import com.weather.utils.DataConverter;
import com.weather.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherDetailActivity extends Activity {
	private TextView mTest;
	private WeatherInfo mWeatherInfo;
	private WeatherConciseInfo mWeatherConciseInfo;
	private int mItemNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_detail);
		mTest = (TextView)findViewById(R.id.test);
		mWeatherInfo = (WeatherInfo) getIntent().getSerializableExtra(WeatherConstant.WEATHER_INFO);
		mItemNo = getIntent().getIntExtra(WeatherConstant.ITEM_NO, 0);
		mWeatherConciseInfo = DataConverter.convertToConciseInfo(mWeatherInfo, mItemNo);
		mTest.setText("日期:"+mWeatherConciseInfo.getDate());
		mTest.append("\n星期:"+mWeatherConciseInfo.getWeek());
		mTest.append("\n温度:"+mWeatherConciseInfo.getTemperature());
		mTest.append("\n风向:"+mWeatherConciseInfo.getWindDirection());
		mTest.append("\n风力:"+mWeatherConciseInfo.getWindStrength());
	}
}
