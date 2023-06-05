package com.swapnali.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class Clouds{
	@SerializedName("all")
	private int all;

	public int getAll(){
		return all;
	}
}