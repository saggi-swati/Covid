package com.android.covid.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("Country")
    public String countryName;

    @SerializedName("Slug")
    public String slugName;

    @SerializedName("ISO2")
    public String id;
}
