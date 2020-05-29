package com.android.covid.home.data;

import com.google.gson.annotations.SerializedName;

public class NovelCovidDetail {

    @SerializedName("total_cases")
    private long totalCases;

    @SerializedName("total_recovered")
    private long totalRecovered;

    @SerializedName("total_unresolved")
    private long totalUnresolved;

    @SerializedName("total_deaths")
    private long totalDeceased;

    @SerializedName("total_new_cases_today")
    private long totalCasesToday;

    @SerializedName("total_new_deaths_today")
    private long totalDeceasedToday;

    @SerializedName("total_active_cases")
    private long totalActiveCases;

    @SerializedName("total_serious_cases")
    private long totalSerious;

    @SerializedName("total_affected_countries")
    private int totalAffectedCountries;

    public long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(long totalCases) {
        this.totalCases = totalCases;
    }

    public long getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public long getTotalUnresolved() {
        return totalUnresolved;
    }

    public void setTotalUnresolved(long totalUnresolved) {
        this.totalUnresolved = totalUnresolved;
    }

    public long getTotalDeceased() {
        return totalDeceased;
    }

    public void setTotalDeceased(long totalDeceased) {
        this.totalDeceased = totalDeceased;
    }

    public long getTotalCasesToday() {
        return totalCasesToday;
    }

    public void setTotalCasesToday(long totalCasesToday) {
        this.totalCasesToday = totalCasesToday;
    }

    public long getTotalDeceasedToday() {
        return totalDeceasedToday;
    }

    public void setTotalDeceasedToday(long totalDeceasedToday) {
        this.totalDeceasedToday = totalDeceasedToday;
    }

    public long getTotalActiveCases() {
        return totalActiveCases;
    }

    public void setTotalActiveCases(long totalActiveCases) {
        this.totalActiveCases = totalActiveCases;
    }

    public long getTotalSerious() {
        return totalSerious;
    }

    public void setTotalSerious(long totalSerious) {
        this.totalSerious = totalSerious;
    }

    public int getTotalAffectedCountries() {
        return totalAffectedCountries;
    }

    public void setTotalAffectedCountries(int totalAffectedCountries) {
        this.totalAffectedCountries = totalAffectedCountries;
    }
}
