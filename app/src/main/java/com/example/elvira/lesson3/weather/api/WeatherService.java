package com.example.elvira.lesson3.weather.api;

import com.example.elvira.lesson3.weather.models.Forecast;
import com.example.elvira.lesson3.weather.models.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bibi1 on 21.08.2017.
 */

public interface WeatherService {
    @GET("weather")
    Call<Weather> getWeather(@Query("q") String city, @Query("units") String u);

    @GET("forecast/daily")
    Call<Forecast> getForecast(@Query("q") String city, @Query("units") String u, @Query("cnt") int cnt);
}
