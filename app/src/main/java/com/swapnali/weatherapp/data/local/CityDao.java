package com.swapnali.weatherapp.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CityDao {
   @Insert
   void insertCity(City city);

   @Query("SELECT * FROM city_weather ORDER BY id DESC LIMIT 1")
   City getLastSearchedCity();
}
