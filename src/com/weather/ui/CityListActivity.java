package com.weather.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.weather.model.CityInfo;
import com.weather.model.WeatherConstant;
import com.weather.utils.FileUtils;
import com.weather.utils.JsonParser;
import com.weather.utils.ProvinceHeadNoUtils;
import com.weather.widget.SideBar;
import com.weather.widget.SideBar.OnTouchingLetterChangedListener;
import com.weather.R;
import com.weather.adapter.CityAdapter;

public class CityListActivity extends ActionBarActivity {
	private final static String TAG = "CityListActivity";
	private Context mContext = null;
	private ListView mCityList = null;
	private CityAdapter cityAdapter = null;
	private SideBar mSideBar = null;
	private View mEmptyView = null;
	private String [] indice = new String [] {"北京", "上海", "天津", "重庆", "黑龙江", 
				"吉林", "辽宁", "内蒙古", "河北", "山西", "陕西", "山东", "新疆", "西藏", 
				"青海", "甘肃", "宁夏", "河南", "江苏", "湖北", "浙江", "安徽", "福建", 
				"江西", "湖南", "贵州", "四川", "广东", "云南", "广西", "海南", "香港", 
				"澳门", "台湾"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		mContext = CityListActivity.this;
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.citiesList);
		actionBar.setDisplayShowHomeEnabled(false);
		
		cityAdapter = new CityAdapter(this);
		mEmptyView = findViewById(R.id.empty_view);
		
		mCityList = (ListView)findViewById(R.id.city_list);
		mCityList.setAdapter(cityAdapter);
		mCityList.setOnItemClickListener(mOnEachCityClickListener);
		mCityList.setEmptyView(mEmptyView);
		
		mSideBar = (SideBar)findViewById(R.id.letter_side_bar);
		mSideBar.setIndience(indice);
		mSideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			@Override
			public void OnTouchingLetterUp() {
				
			}
			@Override
			public void OnTouchingLetterChanged(String mChoose,int position) {
				int NO = ProvinceHeadNoUtils.getHeadCityNo(position);
				mCityList.setSelection(NO-1);
			}
		});
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
				intent.putExtra(WeatherConstant.CITY_NAME, city.getCityName());
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
