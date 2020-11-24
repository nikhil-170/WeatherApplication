package com.example.inclass08;
/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrentWeatherFragment extends Fragment {

    private String city;
    private final OkHttpClient client = new OkHttpClient();
    Button checkforecast;
    TextView cityTextView, tempTextView, tempMaxTV, tempMinTV,descriptionTV , humidityTV, windSpeedTV, windDegreeTV, cloudinessTV;
    String weatherIcon, imageUrl;
    ImageView imageView;

    public CurrentWeatherFragment(String city) {
        this.city = city;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        getActivity().setTitle(getResources().getString(R.string.CurrentWeather));
        cityTextView = view.findViewById(R.id.cityTextView);
        tempTextView = view.findViewById(R.id.tempTextView);
        tempMaxTV = view.findViewById(R.id.tempMaxTV);
        tempMinTV = view.findViewById(R.id.tempMinTV);
        descriptionTV = view.findViewById(R.id.descriptionTV);
        humidityTV = view.findViewById(R.id.humidityTV);
        windSpeedTV = view.findViewById(R.id.windSpeedTV);
        windDegreeTV = view.findViewById(R.id.windDegreeTV);
        cloudinessTV = view.findViewById(R.id.cloudinessTV);
        checkforecast = view.findViewById(R.id.chechForecastId);
        imageView = view.findViewById(R.id.imageWeatherId);
        cityTextView.setText(city);

        Log.d("demo", "onCreateView: "+city);

        getWeather();
//        imageUrl = "http://openweathermap.org/img/wn/10d@2x.png";
//        Picasso.get().load(imageUrl).into(imageView);
        imageView.setVisibility(View.VISIBLE);
        checkforecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.callForecastFragment(city);
            }
        });

        return view;
    }

    private void getWeather() {
        HttpUrl url = HttpUrl.parse("https://api.openweathermap.org/data/2.5/weather").newBuilder()
                .addQueryParameter("q",city)
                .addQueryParameter("appid",getResources().getString(R.string.appID))
                .addQueryParameter("units","imperial")
                .addQueryParameter("mode","json")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()){

                    String body = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        final Weather weather = new Weather(jsonObject);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tempTextView.setText(weather.temp+" "+getResources().getString(R.string.F));
                                tempMaxTV.setText(weather.temp_max+" "+getResources().getString(R.string.F));
                                tempMinTV.setText(weather.temp_min+" "+getResources().getString(R.string.F));
                                descriptionTV.setText(weather.description+"");
                                humidityTV.setText(weather.humidity+" "+getResources().getString(R.string.percent));
                                windSpeedTV.setText(weather.speed+" "+getResources().getString(R.string.miles_per_hour));
                                windDegreeTV.setText(weather.deg+" "+getResources().getString(R.string.degree));
                                cloudinessTV.setText(weather.cloudiness+" "+getResources().getString(R.string.percent));
                                Log.d("demo", "weather.weatherIcon = "+weather.weatherIcon);
                                imageUrl = "https://openweathermap.org/img/wn/"+weather.weatherIcon+"@2x.png";
                                Picasso.get().load(imageUrl).into(imageView);
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    final String body = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),body, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener = (currentWeatherInterface) context;
    }

    currentWeatherInterface mlistener;

    public interface currentWeatherInterface{
        void callForecastFragment(String cityName);
    }
}