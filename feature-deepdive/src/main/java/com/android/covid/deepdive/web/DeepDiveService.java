package com.android.covid.deepdive.web;

import com.android.covid.deepdive.data.CovidCountry;
import com.android.covid.deepdive.data.NovelCovid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeepDiveService {

   @GET("api/v1/cases/countries-search?")
    Call<CovidCountry> getAllCountryData(
            @Query("limit") int pageSize,
            @Query("page") long page,
            @Query("order") String orderBy,
            @Query("how") String order);

    @GET("/v2/countries?yesterday&sort=cases")
    Call<List<NovelCovid>> getAllCountryData();
}