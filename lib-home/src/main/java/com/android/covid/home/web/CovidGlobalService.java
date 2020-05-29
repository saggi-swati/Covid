package com.android.covid.home.web;

import com.android.covid.home.data.CovidStats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidGlobalService {

    @GET("/api/v1/cases/general-stats")
    Call<CovidStats> getGlobalStats();
}