package com.android.covid.home.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.repo.CovidSummaryRepo;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<NovelCovidDetail> data;

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private LiveData<Boolean> isLoading;

    public HomeViewModel() {
        isLoading = CovidSummaryRepo.getInstance().getIsLoading();

        data = new MutableLiveData<>();
        fetchLatestCovidStats();
    }

    public LiveData<NovelCovidDetail> getCovidSummary() {
        return data;
    }

    public void fetchLatestCovidStats() {
        data = CovidSummaryRepo.getInstance().getCovidSummary();
    }
}