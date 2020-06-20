package com.android.covid.home.model.web;

import com.android.covid.home.model.NovelCovidGlobalStats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidGlobalService {

    @GET("/free-api?global=stats")
    Call<NovelCovidGlobalStats> getCovidGlobalStats();
}