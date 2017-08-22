package com.example.elvira.lesson3.weather.models;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bibi1 on 21.08.2017.
 */

public class ForecastByDay {
    @SerializedName("dt")
    String dt;

    @SerializedName("temp")
    private Temp temp;

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public String getDate(){
        long dv = Long.valueOf(dt)*1000;
        Date df = new java.util.Date(dv);
        return new SimpleDateFormat("dd.MM.yyyy").format(df);
    }

}
