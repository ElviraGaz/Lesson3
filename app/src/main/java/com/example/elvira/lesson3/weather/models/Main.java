package com.example.elvira.lesson3.weather.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bibi1 on 21.08.2017.
 */

public class Main {
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @SerializedName("temp")
    String temp;
}
