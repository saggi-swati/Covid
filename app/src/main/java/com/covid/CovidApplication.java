package com.covid;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class CovidApplication extends Application {

    OkHttpClient.Builder builder;

    private static volatile CovidApplication instance;
    private static Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        buildHttpClient();
        init();
    }

    private static CovidApplication get(Context context) {
        return instance = (CovidApplication) context.getApplicationContext();
    }

    public static CovidApplication create(Context context) {
        return get(context);
    }

    @NonNull
    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(instance.getMainLooper());
        }

        return mainHandler;
    }

    public void buildHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

    }

    private static void init() {
        // initialize modules like A/B test, Firebase etc.
    }
}
