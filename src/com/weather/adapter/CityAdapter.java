package com.weather.adapter;

import java.util.ArrayList;

import com.weather.model.CityInfo;
import com.weather.R;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CityAdapter extends BaseAdapter {
//	private final static String TAG = "CityAdapter";
	private Context mContext;
	private ArrayList<CityInfo> mData;
	private LayoutInflater inflator;
	public CityAdapter(Context context)
	{
		mContext = context;
		inflator = LayoutInflater.from(mContext);
	}
	
	public void setData(ArrayList<CityInfo> data) {
		mData = data;
	}
	
	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData == null ? null : mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressLint("InflateParams")
	public View getView(int position, View view, ViewGroup root) {
		ViewHolder holder;
		if( null == view ) {
			holder = new ViewHolder();
			view = inflator.inflate(R.layout.item_city,null);
			holder.tv = (TextView) view.findViewById(R.id.city_name);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.tv.setText(mData.get(position).getCityName());
		return view;
	}

	public final class ViewHolder
	{
		public TextView tv;
	}
}
