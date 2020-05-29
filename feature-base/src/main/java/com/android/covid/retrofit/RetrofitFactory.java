package com.android.covid.retrofit;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    @NonNull
    public static <T> T buildCountryListService(@NonNull Class<T> service) {
        return buildService(service, BaseConstants.COVID_COUNTRY_NAME_BASE_URL);
    }

    @NonNull
    public static <T> T buildGlobalStatsService(@NonNull Class<T> service) {
        return buildService(service, BaseConstants.COVID_HOME_GLOBAL_BASE_URL);
    }

    @NonNull
    public static <T> T buildDeepDiveService(@NonNull Class<T> service) {
        return buildService(service, BaseConstants.COVID_DEEP_DIVE_BASE_URL);
    }

    @NonNull
    public static <T> T buildNewsService(@NonNull Class<T> service) {
        return buildService(service, BaseConstants.COVID_NEWS_BASE_URL);
    }

    @NonNull
    private static <T> T buildService(@NonNull Class<T> service, @NonNull String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service);
    }
}

