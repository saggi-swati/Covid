package com.android.covid.deepdive.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidCountrySearchData {
    
    @SerializedName("paginationMeta")
    public PaginationMetadata paginationMetadata;
    @SerializedName("last_update")
    public String lastUpdate;
    @SerializedName("rows")
    public List<CovidCountryDetail> rows;
}
