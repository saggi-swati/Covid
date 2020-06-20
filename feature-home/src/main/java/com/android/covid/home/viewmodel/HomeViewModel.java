package com.android.covid.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.home.injection.CovidSummaryComponent;
import com.android.covid.home.injection.DaggerCovidSummaryComponent;
import com.android.covid.home.model.NovelCovidDetail;
import com.android.covid.home.model.repo.CovidSummaryRepo;
import com.android.covid.network.State;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    public HomeViewModel() {
        super();
        DaggerCovidSummaryComponent.builder().build().inject(this);
    }

    private MutableLiveData<NovelCovidDetail> data;

    @Inject
    public CovidSummaryRepo covidSummaryRepo;

    public LiveData<NovelCovidDetail> getCovidSummary() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        fetchLatestCovidStats();
        return data;
    }

    public void fetchLatestCovidStats() {
        data = covidSummaryRepo.getCovidSummary();
    }

    public LiveData<State> getIsLoading() {
        return covidSummaryRepo.getIsLoading();
    }

}