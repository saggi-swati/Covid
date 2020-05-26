package com.android.covid.web;

import com.android.covid.model.CountryInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryDataService {

    String BASE_COUNTRY_URL = "https://api.covid19api.com";

    @GET("/countries")
    Call<List<CountryInfo>> getCountryList();
}
