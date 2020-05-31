package com.android.covid.home.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.web.repo.CovidSummaryRepo;
import com.android.covid.network.State;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<NovelCovidDetail> data;

    public LiveData<NovelCovidDetail> getCovidSummary() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        data = CovidSummaryRepo.getInstance().getCovidSummary();
        return data;
    }

    public void fetchLatestCovidStats() {
        data = CovidSummaryRepo.getInstance().getCovidSummary();
    }

    public LiveData<State> getIsLoading() {
        return CovidSummaryRepo.getInstance().getIsLoading();
    }

}