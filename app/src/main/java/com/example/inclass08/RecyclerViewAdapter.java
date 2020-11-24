package com.example.inclass08;

/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.weatherViewHolder>{
    Context context;
    ArrayList<WeatherForecast> weatherForecasts = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<WeatherForecast> weatherForecasts) {
        this.context = context;
        this.weatherForecasts = weatherForecasts;
    }

    @NonNull
    @Override
    public weatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        weatherViewHolder viewHolder = new weatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull weatherViewHolder holder, int position) {
        WeatherForecast weatherForecast = weatherForecasts.get(position);
        holder.description.setText(weatherForecast.weather.description);
        holder.temp_min.setText(context.getResources().getString(R.string.Min)+weatherForecast.weather.temp_min + context.getResources().getString(R.string.F));
        holder.temp_max.setText(context.getResources().getString(R.string.Max)+weatherForecast.weather.temp_max+ context.getResources().getString(R.string.F));
        holder.humidity.setText(context.getResources().getString(R.string.humiditycolon)+weatherForecast.weather.humidity+ context.getResources().getString(R.string.percent));
        holder.temp.setText(weatherForecast.weather.temp+context.getResources().getString(R.string.F));
        holder.dateTime.setText(weatherForecast.dateTime);

        String imageUrl = "https://openweathermap.org/img/wn/"+weatherForecasts.get(position).weather.weatherIcon+"@2x.png";
        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return weatherForecasts.size();
    }

    public class weatherViewHolder extends RecyclerView.ViewHolder{

        TextView dateTime, humidity, temp, temp_max, temp_min, description;
        ImageView imageView;
        public weatherViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setVisibility(View.VISIBLE);
            dateTime = itemView.findViewById(R.id.dateTimeId);
            humidity = itemView.findViewById(R.id.humidityCardViewID);
            temp = itemView.findViewById(R.id.tempCardViewId);
            temp_max = itemView.findViewById(R.id.tempMaxCardViewId);
            temp_min = itemView.findViewById(R.id.tempMinCardViewId);
            description = itemView.findViewById(R.id.descriptionCardViewID);
            imageView = itemView.findViewById(R.id.imageId);
        }
    }
}
