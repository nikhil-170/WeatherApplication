package com.example.inclass08;
/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    Double temp, temp_min, temp_max, pressure, humidity, speed, deg, cloudiness;
    String description, weatherIcon;

    public Weather(JSONObject jsonObject) throws JSONException{

        JSONObject mainJsonObject = jsonObject.getJSONObject("main");
        this.temp = mainJsonObject.getDouble("temp");
        this.temp_min = mainJsonObject.getDouble("temp_min");
        this.temp_max = mainJsonObject.getDouble("temp_max");
        this.pressure = mainJsonObject.getDouble("pressure");
        this.humidity = mainJsonObject.getDouble("humidity");


        JSONObject windJsonObject = jsonObject.getJSONObject("wind");
        this.speed = windJsonObject.getDouble("speed");
        this.deg = windJsonObject.getDouble("deg");

        JSONObject cloudJsonObject = jsonObject.getJSONObject("clouds");
        this.cloudiness = cloudJsonObject.getDouble("all");

        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        if(weatherArray.length()>0){
            this.description = weatherArray.getJSONObject(0).getString("description");
            this.weatherIcon = weatherArray.getJSONObject(0).getString("icon");
        }

    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", speed=" + speed +
                ", deg=" + deg +
                ", cloudiness=" + cloudiness +
                ", description='" + description + '\'' +
                ", weatherIcon='" + weatherIcon + '\'' +
                '}';
    }
}
