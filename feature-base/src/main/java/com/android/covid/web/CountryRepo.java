package com.android.covid.web;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.model.Country;
import com.android.covid.retrofit.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepo {

    private CountryService api;
    private static CountryRepo mInstance;

    public static CountryRepo getInstance() {

        if (mInstance == null) {
            mInstance = new CountryRepo();
        }

        return mInstance;
    }

    private CountryRepo() {
        if (api == null) {
            api = RetrofitFactory.buildCountryListService(CountryService.class);
        }
    }


    public MutableLiveData<List<Country>> getCountryList() {
        final MutableLiveData<List<Country>> mCountryData = new MutableLiveData<>();
        api.getCountryList().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, @NonNull Response<List<Country>> response) {

                if(response.isSuccessful() && response.body() != null)
                    mCountryData.setValue(response.body());
            }

            @Override
            public void onFailure(@Nullable Call<List<Country>> call, @Nullable Throwable t) {
                if(t != null ) {
                    t.printStackTrace();
                }
            }
        });

        return mCountryData;
    }
}
