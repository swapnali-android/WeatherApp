package com.swapnali.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class Wind{
	@SerializedName("deg")
	private int deg;

	@SerializedName("speed")
	private Float speed;

	public int getDeg(){
		return deg;
	}

	public Float getSpeed(){
		return speed;
	}
}