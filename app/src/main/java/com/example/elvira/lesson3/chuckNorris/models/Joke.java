package com.example.elvira.lesson3.chuckNorris.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bibi1 on 19.08.2017.
 */

public class Joke {
    @SerializedName("value")
    private List<JokeText> value;



    public List<JokeText> getValue() {
        return value;
    }

}
