package com.weather.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WeatherPerWeekInfo {
	@SerializedName("f1")
	ArrayList<WeatherInfo> weathers;
	
	@SerializedName("f0")
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
