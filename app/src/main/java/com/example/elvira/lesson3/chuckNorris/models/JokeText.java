package com.example.elvira.lesson3.chuckNorris.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bibi1 on 19.08.2017.
 */

public class JokeText {
    @SerializedName("joke")
    private String joke;

    public String getJoke() {
        return this.joke;
    }

}
