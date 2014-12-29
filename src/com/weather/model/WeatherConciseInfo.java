package com.weather.model;

public class WeatherConciseInfo {
	private String week;
	private String date;
	private String weatherStatus;
	private String temperature;
	private String windStrength;
	private String windDirection;
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
	public String getWindStrength() {
		return windStrength;
	}
	public void setWindStrength(String windStrength) {
		this.windStrength = windStrength;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
}
