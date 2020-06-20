package com.android.covid.deepdive.model.web;

import com.android.covid.deepdive.model.NovelCovid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeepDiveService {

    @GET("/v2/countries?yesterday&sort=cases")
    Call<List<NovelCovid>> getAllCountryData();

    @GET("/v2/countries/{countries}")
    Call<NovelCovid> getCountryData(@Path("countries") String country);
}