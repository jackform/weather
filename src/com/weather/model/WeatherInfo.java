package com.weather.model;

import java.io.Serializable;

public class WeatherInfo implements Serializable {
	private static final long serialVersionUID = -8986632802268098648L;
	private String status1;
	private String status2;
	private String highestTemp;
	private String lowestTemp;
	private String windDirection1;
	private String windDirection2;
	private String highestWindStrength;
	private String lowestWindStrength;
	private String timeSunRiseSet;

	public String getHighestWindStrength() {
		return highestWindStrength;
	}

	public void setHighestWindStrength(String highestWindStrength) {
		this.highestWindStrength = highestWindStrength;
	}

	public String getLowestWindStrength() {
		return lowestWindStrength;
	}

	public void setLowestWindStrength(String lowestWindStrength) {
		this.lowestWindStrength = lowestWindStrength;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getWindDirection1() {
		return windDirection1;
	}

	public void setWindDirection1(String windDirection1) {
		this.windDirection1 = windDirection1;
	}

	public String getWindDirection2() {
		return windDirection2;
	}

	public void setWindDirection2(String windDirection2) {
		this.windDirection2 = windDirection2;
	}

	public String getTimeSunRiseSet() {
		return timeSunRiseSet;
	}

	public void setTimeSunRiseSet(String timeSunRiseSet) {
		this.timeSunRiseSet = timeSunRiseSet;
	}

	public String getHighestTemp() {
		return highestTemp;
	}

	public void setHighestTemp(String highestTemp) {
		this.highestTemp = highestTemp;
	}

	public String getLowestTemp() {
		return lowestTemp;
	}

	public void setLowestTemp(String lowestTemp) {
		this.lowestTemp = lowestTemp;
	}
}
