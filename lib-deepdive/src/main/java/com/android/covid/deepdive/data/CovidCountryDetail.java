package com.android.covid.deepdive.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class CovidCountryDetail implements Parcelable {

    private static int increment = 0;
    private long id;

    public CovidCountryDetail() {
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

    protected CovidCountryDetail(Parcel in) {
        countryName = in.readString();
        abbreviation = in.readString();
        totalCases = in.readString();
        newCases = in.readString();
        totalDeaths = in.readString();
        newDeaths = in.readString();
        totalRecovered = in.readString();
        activeCases = in.readString();
        seriousCritical = in.readString();
        casesPerMillion = in.readString();
        countryFlag = in.readString();
    }

    public static final Creator<CovidCountryDetail> CREATOR = new Creator<CovidCountryDetail>() {
        @Override
        public CovidCountryDetail createFromParcel(Parcel in) {
            return new CovidCountryDetail(in);
        }

        @Override
        public CovidCountryDetail[] newArray(int size) {
            return new CovidCountryDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(countryName);
        dest.writeString(abbreviation);
        dest.writeString(totalCases);
        dest.writeString(newCases);
        dest.writeString(totalDeaths);
        dest.writeString(newDeaths);
        dest.writeString(totalRecovered);
        dest.writeString(activeCases);
        dest.writeString(seriousCritical);
        dest.writeString(casesPerMillion);
        dest.writeString(countryFlag);
    }

    public static DiffUtil.ItemCallback<CovidCountryDetail> DIFF_CALLBACK = new DiffUtil.ItemCallback<CovidCountryDetail>() {
        @Override
        public boolean areItemsTheSame(@NonNull CovidCountryDetail oldItem, @NonNull CovidCountryDetail newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CovidCountryDetail oldItem, @NonNull CovidCountryDetail newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        CovidCountryDetail article = (CovidCountryDetail) obj;
        return article.id == this.id;
    }
}
