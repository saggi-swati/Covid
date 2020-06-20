package com.android.covid.covidnews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.covid.covidnews.injection.DaggerNewsComponent;
import com.android.covid.covidnews.model.Article;
import com.android.covid.covidnews.model.web.NewsDataFactory;
import com.android.covid.covidnews.model.web.NewsDataSource;
import com.android.covid.network.State;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class NewsViewModel extends ViewModel {

    private LiveData<State> networkState;
    private LiveData<PagedList<Article>> articleLiveData;

    @Inject
    public NewsDataFactory newsDataFactory;

    public NewsViewModel() {
        super();
        DaggerNewsComponent.builder().build().inject(this);
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);
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
