package com.android.covid.deepdive.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.web.repo.NovelCovidAllRepo;

import java.util.List;

public class NovelCovidViewModel extends AndroidViewModel {

    private LiveData<List<NovelCovid>> data;

    public NovelCovidViewModel(@NonNull Application application) {
        super(application);
        data = NovelCovidAllRepo.getInstance().getNovelCovidAllCountryData();
    }

    public LiveData<List<NovelCovid>> getNovelCovidAllCountryData() {
        return data;
    }

}

