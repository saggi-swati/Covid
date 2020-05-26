package com.android.covid.web;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.model.CountryInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryDataRepo {

    private CountryDataService api;
    private static CountryDataRepo mInstance;

    public static CountryDataRepo getInstance() {

        if (mInstance == null) {
            mInstance = new CountryDataRepo();
        }

        return mInstance;
    }

    private CountryDataRepo() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(CountryDataService.BASE_COUNTRY_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CountryDataService.class);

        }
    }


    public MutableLiveData<List<CountryInfo>> getCountryList() {
        final MutableLiveData<List<CountryInfo>> mCountryData = new MutableLiveData<>();
        api.getCountryList().enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(Call<List<CountryInfo>> call, @NonNull Response<List<CountryInfo>> response) {

                if(response.isSuccessful() && response.body() != null)
                    mCountryData.setValue(response.body());
            }

            @Override
            public void onFailure(@Nullable Call<List<CountryInfo>> call, @Nullable Throwable t) {
                if(t != null ) {
                    t.printStackTrace();
                }
            }
        });

        return mCountryData;
    }
}
