package com.android.covid.covidnews.model.web;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.covid.covidnews.injection.DaggerNewsComponent;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class NewsDataFactory extends DataSource.Factory {

    @Inject
    public NewsDataSource newsDataSource;

    private MutableLiveData<NewsDataSource> mutableLiveData;
    public NewsDataFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {
        DaggerNewsComponent.builder().build().inject(this);
        mutableLiveData.postValue(newsDataSource);
        return newsDataSource;
    }


    public MutableLiveData<NewsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
