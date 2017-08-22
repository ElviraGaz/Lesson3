package com.example.elvira.lesson3.weather.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elvira.lesson3.R;
import com.example.elvira.lesson3.chuckNorris.JokeViewHolder;

import java.util.List;

/**
 * Created by bibi1 on 22.08.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {
    private List<ForecastByDay> forecastDay;

    public ForecastAdapter(List<ForecastByDay> forecastDay){
        this.forecastDay = forecastDay;
    }
    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.forecast_item, parent, false);
        return new ForecastViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.dayTextView.setText("День: "+ String.valueOf(forecastDay.get(position).getTemp().getDay()));
        holder.mornTextView.setText("Утро: " + String.valueOf(forecastDay.get(position).getTemp().getMorn()));
        holder.eveTextView.setText("Вечер: " + String.valueOf(forecastDay.get(position).getTemp().getEve()));
        holder.nightTextView.setText("Ночь: " + String.valueOf(forecastDay.get(position).getTemp().getNight()));
        holder.date.setText(forecastDay.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return forecastDay.size();
    }
}
