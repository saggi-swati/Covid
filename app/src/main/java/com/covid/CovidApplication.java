package com.covid;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class CovidApplication extends Application {

    OkHttpClient.Builder builder;

    private static CovidApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        sInstance = this;
        buildHttpClient();
        init();
    }

    public static CovidApplication get() {
        return sInstance;
    }

    private static void init() {
        // Init modules or dependencies.
    }


    public void buildHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

    }
}
