package com.covid;

import android.app.Application;

import com.covid.injection.DaggerAppComponent;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class CovidApplication extends Application {

    OkHttpClient.Builder builder;

    private static CovidApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildHttpClient();
        init();
    }

    public static CovidApplication get() {
        return sInstance;
    }

    private void init() {
        // Init modules or dependencies.
        DaggerAppComponent.builder().bindApplication(this).build().inject(this);
    }


    public void buildHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

    }
}
