package com.weather.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.weather.model.CityInfo;
import com.weather.model.WeatherConstant;
import com.weather.utils.FileUtils;
import com.weather.utils.JsonParser;
import com.weather.R;
import com.weather.adapter.CityAdapter;

public class CityListActivity extends Activity {
	private final static String TAG = "CityListActivity";
	private Context mContext = null;
	private ListView mCityList = null;
	private CityAdapter cityAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		mContext = CityListActivity.this;
		
		cityAdapter = new CityAdapter(this);
		mCityList = (ListView)findViewById(R.id.city_list);
		mCityList.setAdapter(cityAdapter);
		mCityList.setOnItemClickListener(mOnEachCityClickListener);
		//start a thread to obtain the cityList content 
		CitiesObtainTask task = new CitiesObtainTask();
		task.execute();
	}

	OnItemClickListener mOnEachCityClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long Id) {
				CityInfo city = (CityInfo) parent.getAdapter().getItem(position);
				if( null == city )
					return ;
				Intent intent = new Intent(CityListActivity.this,WeatherPerWeekActivity.class);
				intent.putExtra(WeatherConstant.CITY_NO, city.getCityNo());				
				startActivity(intent);
			}
	};

	class CitiesObtainTask extends AsyncTask<Void,Integer,ArrayList<CityInfo>>
	{
		@Override
		protected ArrayList<CityInfo> doInBackground(Void... arg0) {
			String lines = null;
			try {
				InputStream is = mContext.getAssets().open(WeatherConstant.CITY_LIST_FILE_NAME);
				lines = FileUtils.readFromStream(is);
				if( null != is )
					is.close();
			} catch(IOException e) {
				e.printStackTrace();
			} 
			Log.v(TAG,lines);
			return JsonParser.CitiesParser(lines);
		}

		@Override
		protected void onPostExecute(ArrayList<CityInfo> result) {
			super.onPostExecute(result);
			cityAdapter.setData(result);
			cityAdapter.notifyDataSetChanged();
		}
	}
}
