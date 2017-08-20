package com.example.elvira.lesson3.beerFilter.api;

import com.example.elvira.lesson3.beerFilter.models.Beer;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author Timur Valiev
 */
public interface BeerService {

    @GET("beers")
    Call<List<Beer>> getBeer(@QueryMap Map<String,String> params);

}
