package com.android.covid.home.web;

import com.android.covid.home.data.NovelCovidGlobalStats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidGlobalService {

    @GET("/free-api?global=stats")
    Call<NovelCovidGlobalStats> getCovidGlobalStats();
}