package com.android.covid.deepdive.data;

import com.google.gson.annotations.SerializedName;

public class PaginationMetadata {

    @SerializedName("currentPage")
    public int currentPage;

    @SerializedName("currentPageSize")
    public int currentPageSize;

    @SerializedName("totalPages")
    public int totalPages;

    @SerializedName("totalRecords")
    public int totalRecords;

}
