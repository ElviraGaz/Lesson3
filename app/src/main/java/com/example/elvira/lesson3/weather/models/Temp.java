package com.example.elvira.lesson3.weather.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by bibi1 on 21.08.2017.
 */

public class Temp {

    @SerializedName("day")
    private float day;

    @SerializedName("morn")
    private double morn;

    @SerializedName("night")
    private double night;

    @SerializedName("eve")
    private double eve;


    public String getDay() {
        return formatTemp((int) day);
    }

    public String getMorn() {
        return formatTemp((int) morn);
    }

    public String getNight() {
        return formatTemp((int) night);
    }

    public String getEve() {
        return formatTemp((int) eve);
    }

    public float getFloatDay() {
        return day;
    }

    private String formatTemp(int temp) {
        String result = String.valueOf(temp) + (char) 0x00B0 + "C";
        if (temp == 0)
            return result;
        if (temp > 0)
            return result = "+" + result;
        else return result = "-" + result;
    }
}
