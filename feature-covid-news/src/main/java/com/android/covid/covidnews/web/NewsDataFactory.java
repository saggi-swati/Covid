package com.android.covid.covidnews.web;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class NewsDataFactory extends DataSource.Factory {

    private MutableLiveData<NewsDataSource> mutableLiveData;

    public NewsDataFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        NewsDataSource newsDataSource = new NewsDataSource();
        mutableLiveData.postValue(newsDataSource);
        return newsDataSource;
    }


    public MutableLiveData<NewsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
