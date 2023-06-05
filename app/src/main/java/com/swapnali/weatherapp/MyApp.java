package com.swapnali.weatherapp;

import android.app.Application;

import com.swapnali.weatherapp.data.local.CityDatabase;

public class MyApp extends Application {
   @Override
   public void onCreate() {
      super.onCreate();

      // Initialize Room database
      CityDatabase.initialize(this);
   }
}
