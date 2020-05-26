package com.android.covid.covidnews.web;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class FeedDataFactory extends DataSource.Factory {

    private MutableLiveData<FeedDataSource> mutableLiveData;
    private FeedDataSource feedDataSource;

    public FeedDataFactory() {
        this.mutableLiveData = new MutableLiveData<FeedDataSource>();
    }

    @Override
    public DataSource create() {
        feedDataSource = new FeedDataSource();
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }


    public MutableLiveData<FeedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
