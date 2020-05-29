package com.android.covid.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.repo.CovidSummaryRepo;
import com.android.covid.home.repo.persistence.NovelCovidGlobalPersistent;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<NovelCovidDetail> data;

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private LiveData<Boolean> isLoading;

    public HomeViewModel(@NonNull Application application) {
        super(application);
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