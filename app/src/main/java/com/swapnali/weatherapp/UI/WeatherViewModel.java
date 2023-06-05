package com.swapnali.weatherapp.UI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swapnali.weatherapp.data.remote.WeatherResponse;
import com.swapnali.weatherapp.data.repository.WeatherRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
   private final MutableLiveData<WeatherResponse> weatherResponse;
   private final WeatherRepository weatherRepository;

   public WeatherViewModel() {
      weatherResponse = new MutableLiveData<>();
      weatherRepository = new WeatherRepository();
   }

   public LiveData<WeatherResponse> getWeatherData() {
      return weatherResponse;
   }


   public void fetchWeatherDataByCity(String city) {
      weatherRepository.fetchWeatherData(city, new Callback<WeatherResponse>() {
         @Override
         public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
            if (response.isSuccessful()) {
               WeatherResponse apiResponse = response.body();
               if (weatherResponse != null) {
                  weatherResponse.postValue(apiResponse);

                  weatherRepository.insertCity(apiResponse);
               }
            }
         }

         @Override
         public void onFailure(Call<WeatherResponse> call, Throwable t) {
            // Handle API call failure
         }
      });
   }

   public void fetchWeatherDataForCurrentLocation(Double lat, Double lon){
      weatherRepository.fetchWeatherDataForCurrentLocation(lat, lon, new Callback<WeatherResponse>() {
         @Override
         public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
            if (response.isSuccessful()) {
               WeatherResponse apiResponse = response.body();
               if (weatherResponse != null) {
                  weatherResponse.postValue(apiResponse);
               }
            }
         }

         @Override
         public void onFailure(Call<WeatherResponse> call, Throwable t) {
            // Handle API call failure
         }
      });
   }


}
