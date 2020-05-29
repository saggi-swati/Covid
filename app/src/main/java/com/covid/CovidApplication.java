package com.covid;

import android.app.Application;

import com.android.covid.home.HomeModule;
import com.covid.dependencies.home.HomeDependenciesImpl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class CovidApplication extends Application {

    OkHttpClient.Builder builder;

    private static volatile CovidApplication sInstance;

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


    public void buildHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

    }

    private static void init() {
        HomeModule.init(new HomeDependenciesImpl());
    }
}
