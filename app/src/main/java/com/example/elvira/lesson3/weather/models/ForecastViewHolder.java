package com.example.elvira.lesson3.weather.models;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.example.elvira.lesson3.R;

/**
 * Created by bibi1 on 22.08.2017.
 */

public class ForecastViewHolder extends ViewHolder {
    public TextView dayTextView;
    public TextView mornTextView;
    public TextView eveTextView;
    public TextView nightTextView;
    public TextView date;

    public ForecastViewHolder(View itemView) {
        super(itemView);
        dayTextView = (TextView) itemView.findViewById(R.id.day);
        mornTextView = (TextView) itemView.findViewById(R.id.morn);
        eveTextView = (TextView) itemView.findViewById(R.id.eve);
        nightTextView = (TextView) itemView.findViewById(R.id.night);
        date = (TextView) itemView.findViewById(R.id.date);
    }
}
