package com.example.elvira.lesson3.chuckNorris.api;

import com.example.elvira.lesson3.chuckNorris.models.Joke;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bibi1 on 19.08.2017.
 */

public interface ChuckNorrisService {

    @GET("jokes/random/{count}")
    Call<Joke> getJokes(@Path("count") Integer count, @Query("firstName") String firstName, @Query("lastName") String lastName, @Query("escape") String escape);
}
