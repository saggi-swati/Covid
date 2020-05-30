package com.android.covid.deepdive.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.web.repo.DeepDiveRepo;

import java.util.List;

public class DeepDiveViewModel extends ViewModel {

    private LiveData<List<NovelCovid>> data;

    public DeepDiveViewModel() {
        data = DeepDiveRepo.getInstance().getNovelCovidAllCountryData();
    }

    public LiveData<List<NovelCovid>> getNovelCovidAllCountryData() {
        return data;
    }

}

