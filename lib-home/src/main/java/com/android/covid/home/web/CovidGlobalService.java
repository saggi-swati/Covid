package com.android.covid.home.web;

import com.android.covid.home.data.CovidStats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidGlobalService {
    String COVID_BASE_HTTPS_URL = "https://corona-virus-stats.herokuapp.com/";

    @GET("/api/v1/cases/general-stats")
    Call<CovidStats> getGlobalStats();
}