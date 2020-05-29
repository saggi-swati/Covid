package com.android.covid.home.repo.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.covid.home.HomeModule;
import com.android.covid.home.data.NovelCovidDetail;

public class NovelCovidGlobalPersistent {

    private static final String TOTAL_CASES = "total_cases";
    private static final String TOTAL_RECOVERED = "total_recovered";
    private static final String TOTAL_DECEASED = "total_deceased";
    private static final String TOTAL_SERIOUS = "total_serious";
    private static final String TOTAL_COUNTRIES = "total_countries";

    private static NovelCovidGlobalPersistent sInstance;
    private final SharedPreferences sharedPrefs;

    public static NovelCovidGlobalPersistent get() {
        if (sInstance == null)
            sInstance = new NovelCovidGlobalPersistent();
        return sInstance;
    }

    private NovelCovidGlobalPersistent() {
        sharedPrefs = HomeModule
                .get()
                .getDependencies()
                .getCovidApplication()
                .getSharedPreferences("novel_covid", Context.MODE_PRIVATE);
    }

    public void cacheCovidDetail(NovelCovidDetail novelCovidDetail) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putLong(TOTAL_CASES, novelCovidDetail.getTotalCases());
        editor.putLong(TOTAL_RECOVERED, novelCovidDetail.getTotalRecovered());
        editor.putLong(TOTAL_DECEASED, novelCovidDetail.getTotalDeceased());
        editor.putLong(TOTAL_SERIOUS, novelCovidDetail.getTotalSerious());
        editor.putInt(TOTAL_COUNTRIES, novelCovidDetail.getTotalAffectedCountries());

        editor.apply();
    }

    public NovelCovidDetail getCachedCovidDetail() {
        NovelCovidDetail covidDetail = new NovelCovidDetail();

        covidDetail.setTotalCases(sharedPrefs.getLong(TOTAL_CASES, 0));
        covidDetail.setTotalRecovered(sharedPrefs.getLong(TOTAL_RECOVERED, 0));
        covidDetail.setTotalDeceased(sharedPrefs.getLong(TOTAL_DECEASED, 0));
        covidDetail.setTotalSerious(sharedPrefs.getLong(TOTAL_SERIOUS, 0));
        covidDetail.setTotalAffectedCountries(sharedPrefs.getInt(TOTAL_COUNTRIES, 0));

        return covidDetail;
    }
}
