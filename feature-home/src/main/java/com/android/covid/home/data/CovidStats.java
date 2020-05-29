package com.android.covid.home.data;

import com.google.gson.annotations.SerializedName;

public class CovidStats {
    @SerializedName("data")
    public CovidGlobalStats data;

    @SerializedName("status")
    public String status;
}
