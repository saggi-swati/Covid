package com.android.covid.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid.model.Country;
import com.android.covid.web.CountryRepo;

import java.util.List;

public class CountryViewModel extends ViewModel {

    private MutableLiveData<List<Country>> mCountryList;

    public CountryViewModel() {
        super();
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