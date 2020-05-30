package com.android.covid.deepdive.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.covid.deepdive.data.CovidCountryInfo;
import com.android.covid.deepdive.web.repo.DeepDiveDataFactory;
import com.android.covid.deepdive.web.repo.DeepDiveDataSource;
import com.android.covid.network.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DeepDiveViewModelUnused extends ViewModel {

    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<CovidCountryInfo>> allCountryData;

    public DeepDiveViewModelUnused() {
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);

        DeepDiveDataFactory feedDataFactory = new DeepDiveDataFactory();
        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
                DeepDiveDataSource::getNetworkState);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        allCountryData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<CovidCountryInfo>> getAllCountryData() {
        return allCountryData;
    }
}
