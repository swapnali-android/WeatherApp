package com.swapnali.weatherapp.data.repository;

import com.swapnali.weatherapp.data.remote.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
 @GET("weather")
 Call<WeatherResponse> getWeatherData(
         @Query("q") String city,
         @Query("appid") String apiKey);

 @GET("weather")
 Call <WeatherResponse> getWeatherDataForCurrentLocation(@Query("lat") Double lat,
                                                         @Query("lon") Double lon,
                                                         @Query("appid") String apiKey);
}
