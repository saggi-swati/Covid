package com.android.covid.deepdive.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.deepdive.injection.DaggerDeepDiveComponent;
import com.android.covid.deepdive.model.NovelCovid;
import com.android.covid.deepdive.model.repo.DeepDiveRepo;
import com.android.covid.network.State;

import java.util.List;

import javax.inject.Inject;

public class DeepDiveViewModel extends ViewModel {

    public DeepDiveViewModel() {
        DaggerDeepDiveComponent.builder().build().inject(this);
    }

    @Inject
    public DeepDiveRepo instance;

    public LiveData<State> getIsLoading() {

        return instance.getIsLoading();
    }

    public LiveData<List<NovelCovid>> getNovelCovidAllCountryData() {
        return instance.getNovelCovidAllCountryData();
    }

    public LiveData<NovelCovid> getSpecificCountryData(String currentCountry) {
        return instance.getCountryData(currentCountry);
    }

}

