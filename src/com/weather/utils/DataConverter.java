package com.weather.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

import com.weather.model.WeatherBriefInfo;
import com.weather.model.WeatherConciseInfo;
import com.weather.model.WeatherConstant;
import com.weather.model.WeatherInfo;
import com.weather.model.WeatherPerWeekInfo;

public class DataConverter {
	private static Hashtable<String,String> weatherStateQuery;
	private static Hashtable<String,String> windDirectionQuery;
	private static Hashtable<String,String> windStrengthQuery;
	static {
		String[] status = { "00","晴","01","多云","02","阴","03","阵雨","04","雷阵雨","05","雷阵雨伴有冰雹","06","雨夹雪","07","小雨","08","中雨","09","大雨","10","暴雨",
							"11","大>暴雨","12","特大暴雨","13","阵雪","14","小雪","15","中雪","16","大雪","17","暴雪","18","雾","19","冻雨","20","沙尘暴",
							"21","小到中雨","22","中到大雨","23","大到暴雨","24","暴雨到大暴雨","25","大暴雨到特大暴雨","26","小到中雪","27","中到大雪","28","大到暴雪","29","浮尘","30","扬沙",
							"31","强沙尘暴","53","霾","99",""};
		 
		String[] windDirectionName = {"0","无持续风向","1","东北风","2","东风","3","东南风","4","南风","5","西南风","6","西风","7","西北风","8","北风","9","旋转风"};
		String[] windStrengthName = {"0","微风","1","3-4级","2","4-5级","3","5-6级","4","6-7级","5","7-8级","6","8-9级","7","9-10级","8","10-11级","9","11-12级"};
		weatherStateQuery = new Hashtable<String,String>();
		for( int i=0;i<status.length;i+=2 ) {
			if(weatherStateQuery.containsKey(status[i]))
				continue;
			weatherStateQuery.put(status[i], status[i+1]);
		}
		
		windDirectionQuery = new Hashtable<String,String>();
		for( int i=0;i<windDirectionName.length;i+=2 ) {
			if(windDirectionQuery.containsKey(windDirectionName[i]))
				continue;
			windDirectionQuery.put(windDirectionName[i],windDirectionName[i+1]);
		}
		windStrengthQuery = new Hashtable<String,String>();
		for( int i=0;i<windStrengthName.length;i+=2){
			if(windStrengthQuery.contains(windStrengthName[i]))
				continue;
			windStrengthQuery.put(windStrengthName[i], windStrengthName[i+1]);
		}
		
	}
	
	public static ArrayList<WeatherBriefInfo> convertToBriefWeathers(WeatherPerWeekInfo weatherAll)
	{
		ArrayList<WeatherBriefInfo> briefWeather = new ArrayList<WeatherBriefInfo>();
		ArrayList<WeatherInfo> weathers = weatherAll.getWeathers();
		DateUtils.reset2Today();
		for(WeatherInfo weather : weathers) {
			WeatherBriefInfo w = new WeatherBriefInfo();
			w.setTemperature((weather.getLowestTemp() + "℃ ~ " + weather.getHighestTemp())+"℃");
			w.setDate(DateUtils.getDate());
			w.setWeek(DateUtils.getWeek());
			DateUtils.nextDay();
			
			String Status1 = weatherStateQuery.get(weather.getStatus1());
			String Status2 = weatherStateQuery.get(weather.getStatus2());
			String weatherStr = Status2.equals(Status1) ? Status2 : Status1+"转"+Status2 ;
			w.setWeatherStatus(weatherStr);
			
			String pictureUrl1 = String.format(Locale.US, WeatherConstant.WEATHER_IMAGE_URL_BASE, weather.getStatus1());
			String pictureUrl2 = Status2.equals(Status1) ? null : 
									String.format(Locale.US, WeatherConstant.WEATHER_IMAGE_URL_BASE, weather.getStatus2());
			w.setWeatherPictureUrl1(pictureUrl1);
			w.setWeatherPictureUrl2(pictureUrl2);
			
			briefWeather.add(w);
		}
		return briefWeather;	
	}
	
	public static WeatherConciseInfo convertToConciseInfo(WeatherInfo rawWeather,int position)
	{
		WeatherConciseInfo result = new WeatherConciseInfo();
		result.setTemperature(rawWeather.getLowestTemp() + "℃ ~ " + rawWeather.getHighestTemp()+"℃");
		
		DateUtils.reset2Today();
		DateUtils.moveToNextNDay(position);
		result.setDate(DateUtils.getDate());
		result.setWeek(DateUtils.getWeek());
		
		String Status1 = weatherStateQuery.get(rawWeather.getStatus1());
		String Status2 = weatherStateQuery.get(rawWeather.getStatus2());
		String weatherStr = Status2.equals(Status1) ? Status2 : Status1+"转"+Status2 ;
		result.setWeatherStatus(weatherStr);
		
		String windDirect1 = windDirectionQuery.get(rawWeather.getWindDirection1());
		String windDirect2 = windDirectionQuery.get(rawWeather.getWindDirection2());
		String windDirect = windDirect1.equals(windDirect2) ? windDirect1 : windDirect1 + " 转 " + windDirect2;
		result.setWindDirection(windDirect);
		
		String windStrength1 = windStrengthQuery.get(rawWeather.getHighestWindStrength());
		String windStrength2 = windStrengthQuery.get(rawWeather.getLowestWindStrength());
		String windStrength = windStrength1.equals(windStrength2) ? windStrength1 : windStrength1 + " 或 " + windStrength2;
		result.setWindStrength(windStrength);
		return result;
	}
}
