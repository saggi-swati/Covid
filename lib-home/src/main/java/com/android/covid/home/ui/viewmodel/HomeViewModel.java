package com.android.covid.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.covid.home.data.CovidStats;
import com.android.covid.home.web.repo.CovidSummaryRepo;

public class HomeViewModel extends AndroidViewModel {

    private LiveData<CovidStats> data;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        fetchLatestCovidStats();
    }

    public LiveData<CovidStats> getCovidSummary() {
        return data;
    }

    public void fetchLatestCovidStats() {
        data = CovidSummaryRepo.getInstance().getCovidSummary();
    }


}
