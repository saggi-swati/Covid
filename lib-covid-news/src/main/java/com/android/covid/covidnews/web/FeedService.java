package com.android.covid.covidnews.web;

import com.android.covid.covidnews.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedService {

    String BASE_NEWS_HTTPS_URL = "https://newsapi.org/";

    @GET("/v2/everything")
    Call<News> fetchFeed(@Query("q") String q,
                         @Query("apiKey") String apiKey,
                         @Query("page") long page,
                         @Query("pageSize") int pageSize);
}
