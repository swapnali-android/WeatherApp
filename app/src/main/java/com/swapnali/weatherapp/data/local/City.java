package com.swapnali.weatherapp.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_weather")
public class City {
   @PrimaryKey(autoGenerate = true)
   private int id;
   private String cityName;
   private String weatherDesc;
   private String weatherIcon;
   private Float temp;
   private Float feelsLike;
   private String minTemp;
   private String maxTemp;
   private int pressure;
   private int humidity;
   private Float windSpeed;
   private int sunrise;
   private int sunset;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getCityName() {
      return cityName;
   }

   public void setCityName(String cityName) {
      this.cityName = cityName;
   }

   public String getWeatherDesc() {
      return weatherDesc;
   }

   public void setWeatherDesc(String weatherDesc) {
      this.weatherDesc = weatherDesc;
   }

   public String getWeatherIcon() {
      return weatherIcon;
   }

   public void setWeatherIcon(String weatherIcon) {
      this.weatherIcon = weatherIcon;
   }

   public Float getTemp() {
      return temp;
   }

   public void setTemp(Float temp) {
      this.temp = temp;
   }

   public Float getFeelsLike() {
      return feelsLike;
   }

   public void setFeelsLike(Float feelsLike) {
      this.feelsLike = feelsLike;
   }

   public String getMinTemp() {
      return minTemp;
   }

   public void setMinTemp(String minTemp) {
      this.minTemp = minTemp;
   }

   public String getMaxTemp() {
      return maxTemp;
   }

   public void setMaxTemp(String maxTemp) {
      this.maxTemp = maxTemp;
   }

   public int getPressure() {
      return pressure;
   }

   public void setPressure(int pressure) {
      this.pressure = pressure;
   }

   public int getHumidity() {
      return humidity;
   }

   public void setHumidity(int humidity) {
      this.humidity = humidity;
   }

   public Float getWindSpeed() {
      return windSpeed;
   }

   public void setWindSpeed(Float windSpeed) {
      this.windSpeed = windSpeed;
   }

   public int getSunrise() {
      return sunrise;
   }

   public void setSunrise(int sunrise) {
      this.sunrise = sunrise;
   }

   public int getSunset() {
      return sunset;
   }

   public void setSunset(int sunset) {
      this.sunset = sunset;
   }
}
