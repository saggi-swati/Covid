package com.android.covid.deepdive.data;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class CovidCountryInfo {

    private static int increment = 0;
    private long id;

    public CovidCountryInfo() {
        id = ++increment;
    }

    @SerializedName("country")
    public String countryName;
    @SerializedName("country_abbreviation")
    public String abbreviation;
    @SerializedName("total_cases")
    public String totalCases;
    @SerializedName("new_cases")
    public String newCases;
    @SerializedName("total_deaths")
    public String totalDeaths;
    @SerializedName("new_deaths")
    public String newDeaths;
    @SerializedName("total_recovered")
    public String totalRecovered;
    @SerializedName("active_cases")
    public String activeCases;
    @SerializedName("serious_critical")
    public String seriousCritical;
    @SerializedName("cases_per_mill_pop")
    public String casesPerMillion;
    @SerializedName("flag")
    public String countryFlag;

    public static DiffUtil.ItemCallback<CovidCountryInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<CovidCountryInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull CovidCountryInfo oldItem, @NonNull CovidCountryInfo newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CovidCountryInfo oldItem, @NonNull CovidCountryInfo newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        CovidCountryInfo article = (CovidCountryInfo) obj;
        return article.id == this.id;
    }
}
