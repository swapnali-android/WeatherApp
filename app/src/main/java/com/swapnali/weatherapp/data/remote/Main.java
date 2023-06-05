package com.swapnali.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	private Float temp;

	@SerializedName("temp_min")
	private Float tempMin;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private int pressure;

	@SerializedName("feels_like")
	private Float feelsLike;

	@SerializedName("temp_max")
	private Float tempMax;

	public Float getTemp(){
		return temp;
	}

	public Float getTempMin(){
		return tempMin;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public Float getFeelsLike(){
		return feelsLike;
	}

	public Float getTempMax(){
		return tempMax;
	}
}