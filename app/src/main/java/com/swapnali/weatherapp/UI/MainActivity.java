package com.swapnali.weatherapp.UI;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.swapnali.weatherapp.R;
import com.swapnali.weatherapp.data.remote.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    private TextView textCityName;
    private TextView textTemp;
    private TextView textWeatherDesc;
    private TextView textWindSpeed;
    private TextView textHumidity;
    private TextView textPressure;
    private TextView textSunrise;
    private TextView textSunset;
    private TextView textTempFeelsLike;
    private ProgressBar progressBar;
    private TextView textErrorMsg;
    private LinearLayoutCompat layoutWeatherInfo;

    private ImageView imageWeatherCondition;


    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        getWeatherForCurrentLocation();

        initializeView();

        EditText edtCity = findViewById(R.id.editCity);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClicked(edtCity.getText().toString());
            }
        });


        weatherViewModel.getWeatherData().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                updateWeatherUI(weatherResponse);
            }
        });


    }

    private void updateWeatherUI(WeatherResponse weatherResponse) {
        textCityName.setText(weatherResponse.getName());
        int temp = Math.round(weatherResponse.getMain().getTemp() - 273.15f);
        String tempStr = String.valueOf(temp);
        textTemp.setText(tempStr);
        textWeatherDesc.setText(weatherResponse.getWeather().get(0).getDescription());

        String imgUrl = "https://openweathermap.org/img/wn/" + weatherResponse.getWeather().get(0).getIcon() + "@2x.png";

        Glide.with(this)
                .asBitmap()
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageWeatherCondition);

        textSunrise.setText(covertUnixToHour(weatherResponse.getSys().getSunrise()));
        textSunset.setText(covertUnixToHour(weatherResponse.getSys().getSunset()));
        textWindSpeed.setText(String.valueOf(weatherResponse.getWind().getSpeed()));
        textPressure.setText(String.valueOf(weatherResponse.getMain().getPressure()));
        textHumidity.setText(String.valueOf(weatherResponse.getMain().getHumidity()));

        int tempFeelsLike = Math.round(weatherResponse.getMain().getFeelsLike() - 273.15f);
        textTempFeelsLike.setText( String.valueOf(tempFeelsLike));
    }

    public static String covertUnixToHour(int sunrise){
        Date date = new Date(sunrise* 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        return sdf.format(date);
    }
    private void initializeView() {
        textCityName = findViewById(R.id.textCity);
        textTemp = findViewById(R.id.textTemp);
        textWeatherDesc = findViewById(R.id.textWeatherDesc);
        textWindSpeed = findViewById(R.id.textWindSpeed);
        textHumidity = findViewById(R.id.textHumidity);
        textPressure =findViewById(R.id.textPressure);
        textTempFeelsLike = findViewById(R.id.textFeelsLike);
        textSunrise = findViewById(R.id.textSunrise);
        textSunset =findViewById(R.id.textSunset);
        textErrorMsg = findViewById(R.id.textErrorMsg);
        layoutWeatherInfo=findViewById(R.id.weatherInfo);
        progressBar=findViewById(R.id.progress);
        imageWeatherCondition=findViewById(R.id.imgWeatherCondition);

        progressBar.setVisibility(View.GONE);
        textErrorMsg.setVisibility(View.GONE);

    }


    private void handleButtonClicked(String city) {
        weatherViewModel.fetchWeatherDataByCity(city);
    }

    private void getWeatherForCurrentLocation() {
        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Double lat = location.getLatitude();
                Double lng = location.getLongitude();
                weatherViewModel.fetchWeatherDataForCurrentLocation(lat, lng);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                //not able to get location
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        String location_Provider = LocationManager.GPS_PROVIDER;
        mLocationManager.requestLocationUpdates(location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Location successfully obtained ", Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
            } else {
                //user denied the permission
                //if db get data from db
            }
        }


    }
}