package com.android.covid.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.model.CountryInfo;
import com.android.covid.web.CountryDataRepo;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private MutableLiveData<List<CountryInfo>> mCountryList;

    public CountryViewModel(Application app) {
        super(app);
    }

    private void fetchCountryList() {
        mCountryList = CountryDataRepo.getInstance().getCountryList();
    }

    public LiveData<List<CountryInfo>> getCountryList() {
        if (mCountryList == null || mCountryList.getValue() == null) {
            fetchCountryList();
        }
        return mCountryList;
    }

}