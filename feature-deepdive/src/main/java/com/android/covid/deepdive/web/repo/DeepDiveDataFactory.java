package com.android.covid.deepdive.web.repo;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import org.jetbrains.annotations.NotNull;

public class DeepDiveDataFactory extends DataSource.Factory {

    private MutableLiveData<DeepDiveDataSource> mutableLiveData;

    public DeepDiveDataFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {
        DeepDiveDataSource feedDataSource = new DeepDiveDataSource();
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }


    public MutableLiveData<DeepDiveDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}