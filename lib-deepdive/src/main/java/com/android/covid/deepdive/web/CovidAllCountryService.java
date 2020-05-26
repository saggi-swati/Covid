package com.android.covid.deepdive.web;

import com.android.covid.deepdive.data.CovidCountrySearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidAllCountryService {
    String COVID_BASE_HTTPS_URL = "https://corona-virus-stats.herokuapp.com/";

    @GET("api/v1/cases/countries-search?")
    Call<CovidCountrySearch> getAllCountryData(
            @Query("limit") int pageSize,
            @Query("page") long page,
            @Query("order") String orderBy,
            @Query("how") String order);
}