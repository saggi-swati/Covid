package com.android.covid.home.repo.web;

import com.android.covid.home.data.CovidStats;
import com.android.covid.home.data.NovelCovidGlobalStats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidGlobalService {

    @GET("/api/v1/cases/general-stats")
    Call<CovidStats> getGlobalStats();

    @GET("/free-api?global=stats")
    Call<NovelCovidGlobalStats> getCovidGlobalStats();
}