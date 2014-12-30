package com.weather.adapter;

import java.util.ArrayList;

import com.weather.model.CityInfo;
import com.weather.utils.ProvinceHeadNoUtils;
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
			holder.tvProvince = (TextView)view.findViewById(R.id.province_name);
			holder.line_up = (View)view.findViewById(R.id.line_up);
			holder.line_down = (View)view.findViewById(R.id.line_down);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		if( ProvinceHeadNoUtils.isHeadCity(position) ) {
			holder.tvProvince.setText(ProvinceHeadNoUtils.getProvinceName());
			holder.tvProvince.setVisibility(View.VISIBLE);
			holder.line_down.setVisibility(View.VISIBLE);
			holder.line_up.setVisibility(View.VISIBLE);
			ProvinceHeadNoUtils.next();
		} else {
			holder.tvProvince.setVisibility(View.GONE);
			holder.line_down.setVisibility(View.GONE);
			holder.line_up.setVisibility(View.GONE);
		}
		holder.tv.setText(mData.get(position).getCityName());
		return view;
	}

	public final class ViewHolder
	{
		public TextView tv;
		public TextView tvProvince;
		public View 	line_up;
		public View 	line_down;
	}
}
