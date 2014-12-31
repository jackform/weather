package com.weather.model;

public class WeatherBriefInfo {
	private String week;
	private String date;
	private String weatherStatus;
	private String temperature;
	private String weatherPictureUrl1;
	private String weatherPictureUrl2;
	
	public String getWeatherPictureUrl1() {
		return weatherPictureUrl1;
	}
	public void setWeatherPictureUrl1(String weatherPictureUrl1) {
		this.weatherPictureUrl1 = weatherPictureUrl1;
	}
	public String getWeatherPictureUrl2() {
		return weatherPictureUrl2;
	}
	public void setWeatherPictureUrl2(String weatherPictureUrl2) {
		this.weatherPictureUrl2 = weatherPictureUrl2;
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
