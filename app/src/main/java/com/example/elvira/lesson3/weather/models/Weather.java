package com.example.elvira.lesson3.weather.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bibi1 on 21.08.2017.
 */

    public class Weather {

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        @SerializedName("main")
        Main main;

    }

