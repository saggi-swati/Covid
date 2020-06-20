package com.android.covid.covidnews.model.web;

import com.android.covid.covidnews.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidNewsService {

    @GET("/v2/everything")
    Call<News> fetchFeed(@Query("q") String q,
                         @Query("apiKey") String apiKey,
                         @Query("page") long page,
                         @Query("pageSize") int pageSize);
}
