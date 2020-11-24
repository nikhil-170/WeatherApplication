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

public class WeatherForecast {
        Weather weather;
        String dateTime;

    public WeatherForecast(JSONObject jsonObject) throws JSONException {
        weather = new Weather(jsonObject);
        this.dateTime = jsonObject.getString("dt_txt");

    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "weather=" + weather +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
