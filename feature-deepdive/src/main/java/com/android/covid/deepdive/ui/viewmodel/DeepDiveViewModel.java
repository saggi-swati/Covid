package com.android.covid.deepdive.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.web.repo.DeepDiveRepo;
import com.android.covid.network.State;

import java.util.List;

public class DeepDiveViewModel extends ViewModel {

    public LiveData<State> getIsLoading() {
        return DeepDiveRepo.getInstance().getIsLoading();
    }

    public LiveData<List<NovelCovid>> getNovelCovidAllCountryData() {
        return DeepDiveRepo.getInstance().getNovelCovidAllCountryData();
    }

}

