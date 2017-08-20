package com.example.elvira.lesson3.chuckNorris.api;

import android.support.annotation.NonNull;

import com.example.elvira.lesson3.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bibi1 on 19.08.2017.
 */

public final class ApiFactory {
    private static final String API_BASE_URL = "http://api.icndb.com";

    private static Retrofit sRetrofit;

    private static OkHttpClient sHttpClient;

    protected ApiFactory() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(sHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static void provideClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        sHttpClient = builder.build();
    }
}
