package com.example.elvira.lesson3.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bibi1 on 21.08.2017.
 */

public class Forecast {
    @SerializedName("list")
    List<ForecastByDay> list;

    public List<ForecastByDay> getList() {
        return list;
    }

}
