package com.android.covid.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NovelCovidGlobalStats {

    @SerializedName("results")
    public List<NovelCovidDetail> globalStats;

    @SerializedName("stat")
    public String status;
}
