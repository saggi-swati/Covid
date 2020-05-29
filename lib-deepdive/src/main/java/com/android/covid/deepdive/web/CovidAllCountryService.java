package com.android.covid.deepdive.web;

import com.android.covid.deepdive.data.CovidCountry;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidAllCountryService {

    @GET("api/v1/cases/countries-search?")
    Call<CovidCountry> getAllCountryData(
            @Query("limit") int pageSize,
            @Query("page") long page,
            @Query("order") String orderBy,
            @Query("how") String order);
}