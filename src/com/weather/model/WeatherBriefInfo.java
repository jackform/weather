package com.weather.model;

import android.graphics.Bitmap;

public class WeatherBriefInfo {
	private String week;
	private String date;
	private String weatherStatus;
	private String temperature;
	private Bitmap weatherPicture;
	
	
	public Bitmap getWeatherPicture() {
		return weatherPicture;
	}
	public void setWeatherPicture(Bitmap weatherPicture) {
		this.weatherPicture = weatherPicture;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeatherStatus() {
		return weatherStatus;
	}
	public void setWeatherStatus(String weatherStatus) {
		this.weatherStatus = weatherStatus;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	
}
