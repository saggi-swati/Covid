package com.covid;

import android.app.Application;
import android.content.Context;


public class CovidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static CovidApplication get(Context context) {
        return (CovidApplication) context.getApplicationContext();
    }

    public static CovidApplication create(Context context) {
        return get(context);
    }
}
