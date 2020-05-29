package com.android.covid.deepdive.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidCountryData {

    @SerializedName("paginationMeta")
    public CovidCountryPaginationMetadata covidCountryPaginationMetadata;
    @SerializedName("last_update")
    public String lastUpdate;
    @SerializedName("rows")
    public List<CovidCountryInfo> covidCountryInfos;
}
