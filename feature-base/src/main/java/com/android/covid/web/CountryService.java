package com.android.covid.web;

import com.android.covid.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    @GET("/countries")
    Call<List<Country>> getCountryList();
}
