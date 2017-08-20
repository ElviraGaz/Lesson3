package com.example.elvira.lesson3;

import android.app.Application;

import com.example.elvira.lesson3.chuckNorris.api.ApiFactory;
import com.facebook.stetho.Stetho;


public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiFactory.provideClient();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

}
