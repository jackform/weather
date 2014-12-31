package com.weather.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.weather.model.CityInfo;
import com.weather.model.WeatherPerWeekInfo;
import com.weather.model.WeatherInfo;

public class JsonParser {
	
    public static ArrayList<CityInfo> CitiesParser(String jsonStr)
    {
    	ArrayList<CityInfo> result = new ArrayList<CityInfo>();
    	if(TextUtils.isEmpty(jsonStr))
    		return result;
    	try {
			JSONArray cities = new JSONArray(jsonStr);
			for(int i = 0;i<cities.length();i++) {
				JSONObject o = cities.getJSONObject(i);
				CityInfo   c = new CityInfo();
				c.setCityName(o.getString("cityName"));
				c.setCityNo(o.getInt("cityNo"));
				result.add(c);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
    }
    
	public static WeatherPerWeekInfo weatherPerWeekParser(String jsonStr)
	{
		WeatherPerWeekInfo result = new WeatherPerWeekInfo();

		try {
			JSONObject root = new JSONObject(jsonStr);
			JSONObject rootWeather = (JSONObject) root.get("f");
			JSONArray weathersJson = rootWeather.getJSONArray("f1");
			ArrayList<WeatherInfo> weathers = new ArrayList<WeatherInfo>();
			for( int i = 0 ;i < weathersJson.length() ;i++ ) {
				JSONObject	o = weathersJson.getJSONObject(i);
				WeatherInfo w = new WeatherInfo();
				w.setStatus1(o.getString("fa"));
				w.setStatus2(o.getString("fb"));
				w.setHighestTemp(o.getString("fc"));
				w.setLowestTemp(o.getString("fd"));
				w.setWindDirection1(o.getString("fe"));
				w.setWindDirection2(o.getString("ff"));
				w.setHighestWindStrength(o.getString("fg"));
				w.setLowestWindStrength(o.getString("fh"));
				w.setTimeSunRiseSet(o.getString("fi"));
				weathers.add(w);
			}
			String date = rootWeather.getString("f0");
			result.setDate(date);
			result.setWeathers(weathers);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
