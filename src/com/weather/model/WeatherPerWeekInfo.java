package com.weather.model;

import java.util.ArrayList;


public class WeatherPerWeekInfo {
	ArrayList<WeatherInfo> weathers;
	String date;	
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<WeatherInfo> getWeathers()
	{
		return weathers;
	}
	
	public void setWeathers(ArrayList<WeatherInfo> weathers2)
	{
		this.weathers =  weathers2;
	}
	
}
