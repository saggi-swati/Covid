package com.android.covid.covidnews.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.covid.covidnews.model.Article;
import com.android.covid.covidnews.web.NewsDataFactory;
import com.android.covid.covidnews.web.NewsDataSource;
import com.android.covid.network.State;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsViewModel extends ViewModel {

    private LiveData<State> networkState;
    private LiveData<PagedList<Article>> articleLiveData;

    public NewsViewModel() {
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);

        NewsDataFactory newsDataFactory = new NewsDataFactory();
        networkState = Transformations.switchMap(newsDataFactory.getMutableLiveData(),
                NewsDataSource::getNetworkState);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        articleLiveData = (new LivePagedListBuilder(newsDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<State> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<Article>> getArticleLiveData() {
        return articleLiveData;
    }
}
