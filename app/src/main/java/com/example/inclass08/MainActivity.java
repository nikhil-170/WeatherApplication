package com.example.inclass08;
/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements CitiesListFragment.CitiesListListener, CurrentWeatherFragment.currentWeatherInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentView,new CitiesListFragment())
                .commit();

    }

    @Override
    public void sendDataToCurrentWeather(String cityName) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new CurrentWeatherFragment(cityName))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void callForecastFragment(String cityName) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentView, new weatherForecastFragment(cityName))
                    .addToBackStack(null).commit();
    }
}