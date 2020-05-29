package com.android.covid.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.model.Country;
import com.android.covid.web.CountryRepo;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private MutableLiveData<List<Country>> mCountryList;

    public CountryViewModel(Application app) {
        super(app);
    }

    private void fetchCountryList() {
        mCountryList = CountryRepo.getInstance().getCountryList();
    }

    public LiveData<List<Country>> getCountryList() {
        if (mCountryList == null || mCountryList.getValue() == null) {
            fetchCountryList();
        }
        return mCountryList;
    }

}