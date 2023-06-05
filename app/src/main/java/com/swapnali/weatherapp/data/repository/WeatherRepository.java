package com.swapnali.weatherapp.data.repository;

import com.swapnali.weatherapp.data.local.City;

import com.swapnali.weatherapp.data.local.CityDatabase;
import com.swapnali.weatherapp.data.remote.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Retrofit;

public class WeatherRepository {
   private final WeatherApi weatherApi;
   private final String apiKey;


   public WeatherRepository() {
      Retrofit retrofit = ApiClient.getRetrofitInstance();
      weatherApi = retrofit.create(WeatherApi.class);
      apiKey = "22f4611cf706016ce9ded4446d305b9a";

   }

   public void fetchWeatherData(String city, Callback<WeatherResponse> callback) {
      Call<WeatherResponse> call = weatherApi.getWeatherData(city, apiKey);
      call.enqueue(callback);
   }

   public void fetchWeatherDataForCurrentLocation(Double lat, Double lon,Callback<WeatherResponse> callback){
      Call<WeatherResponse> call = weatherApi.getWeatherDataForCurrentLocation(lat,lon, apiKey);
      call.enqueue(callback);
   }

   public void insertCity(WeatherResponse weatherResponse){
      City city =new City();

      city.setCityName(weatherResponse.getName());
      city.setWeatherDesc(weatherResponse.getWeather().get(0).getDescription());
      city.setWeatherIcon(weatherResponse.getWeather().get(0).getIcon());
      city.setTemp(weatherResponse.getMain().getTemp());
      city.setFeelsLike(weatherResponse.getMain().getFeelsLike());
      city.setHumidity(weatherResponse.getMain().getHumidity());
      city.setSunrise(weatherResponse.getSys().getSunrise());
      city.setSunset(weatherResponse.getSys().getSunset());
      city.setWindSpeed(weatherResponse.getWind().getSpeed());
      city.setPressure(weatherResponse.getMain().getPressure());

     CityDatabase.getInstance().cityDao().insertCity(city);

   }

   public City getLastSearchCity(){
     return CityDatabase.getInstance().cityDao().getLastSearchedCity();
   }

}
