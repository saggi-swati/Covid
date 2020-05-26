package com.android.covid.deepdive.web.repo;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import org.jetbrains.annotations.NotNull;

public class CovidDataFactory extends DataSource.Factory {

    private MutableLiveData<CovidDataSource> mutableLiveData;

    public CovidDataFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {
        CovidDataSource feedDataSource = new CovidDataSource();
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }


    public MutableLiveData<CovidDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}