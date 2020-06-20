package com.android.covid.deepdive.model;

import com.google.gson.annotations.SerializedName;

public class NovelCovid {
    @SerializedName("updated")
    public long lastUpdated;

    @SerializedName("country")
    public String countryName;

    @SerializedName("cases")
    public long totalCases;

    @SerializedName("todayCases")
    public int todayCases;

    @SerializedName("deaths")
    public long totalDeaths;

    @SerializedName("todayDeaths")
    public int todayDeaths;

    @SerializedName("recovered")
    public long totalRecovered;

    @SerializedName("todayRecovered")
    public int todayRecovered;

    @SerializedName("active")
    public long totalActiveCases;

    @SerializedName("critical")
    public double totalCritical;

    @SerializedName("casesPerOneMillion")
    public double casesPerMillion;

    @SerializedName("deathsPerOneMillion")
    public double deathPerMillion;

    @SerializedName("tests")
    public long totalTests;

    @SerializedName("testsPerOneMillion")
    public double testsPerMillion;

    @SerializedName("population")
    public long totalPopulation;

    @SerializedName("continent")
    public String continentName;

    @SerializedName("activePerOneMillion")
    public double activePerMillion;

    @SerializedName("recoveredPerOneMillion")
    public double recoveredPerOneMillion;

    @SerializedName("criticalPerOneMillion")
    public double criticalPerOneMillion;

    @SerializedName("countryInfo")
    public CovidCountryInfo countryInfo;

}
