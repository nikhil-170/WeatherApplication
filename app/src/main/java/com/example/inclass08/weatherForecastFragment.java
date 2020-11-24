package com.example.inclass08;


/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class weatherForecastFragment extends Fragment {

    RecyclerView recyclerView;
    final OkHttpClient client = new OkHttpClient();
    ArrayList<WeatherForecast> weatherForecasts = new ArrayList<>();
    static RecyclerViewAdapter weatherAdapter;
    LinearLayoutManager layoutManager;
    String cityName;
    TextView cityNameView;
    public weatherForecastFragment() {
        // Required empty public constructor
    }

    public weatherForecastFragment(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            cityName = getArguments().getString("weatherResponse");
        }
    }


    public weatherForecastFragment newIstance(String param1){
        weatherForecastFragment weatherForecastFragment = new weatherForecastFragment(param1);
        Bundle arguments = new Bundle();
        arguments.putString("weatherResponse",param1);
        weatherForecastFragment.setArguments(arguments);
        return weatherForecastFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        recyclerView =  view.findViewById(R.id.recyclerViewId);
        getActivity().setTitle(getResources().getString(R.string.WeatherForecast));
//        recyclerView.setVisibility(View.VISIBLE);
        cityNameView = view.findViewById(R.id.cityNameId);
        cityNameView.setText(cityName);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        weatherAdapter = new RecyclerViewAdapter(getContext(), weatherForecasts);
        recyclerView.setAdapter(weatherAdapter);
        getFiveDayWeather();

        return view;
    }



    public void getFiveDayWeather(){

        HttpUrl url = HttpUrl.parse("https://api.openweathermap.org/data/2.5/forecast?").newBuilder()
                .addQueryParameter("q",String.valueOf(cityName))
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
                    try {
                        String string = response.body().string();
                         JSONObject jsonObject = new JSONObject(string);
                         JSONArray jsonArray = jsonObject.getJSONArray("list");
                        Log.d("demo", "forweatherforecasts: " + string);
                        for (int i=0 ; i < jsonArray.length() ; i++ ) {
                            JSONObject weatherForecastObject = jsonArray.getJSONObject(i);
                            WeatherForecast weatherForecast = new WeatherForecast(weatherForecastObject);
                            weatherForecasts.add(weatherForecast);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                weatherAdapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}