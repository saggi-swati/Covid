package com.android.covid.home.web.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.home.data.CovidStats;
import com.android.covid.home.web.CovidGlobalService;
import com.android.covid.retrofit.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidSummaryRepo {

    private CovidGlobalService api;

    private static CovidSummaryRepo mInstance;


    public static CovidSummaryRepo getInstance() {
        if (mInstance == null) {
            mInstance = new CovidSummaryRepo();
        }
        return mInstance;
    }

    private CovidSummaryRepo() {

        if (api == null) {
            api = RetrofitFactory.buildCovidService(CovidGlobalService.class);
        }
    }

    public MutableLiveData<CovidStats> getCovidSummary() {
        final MutableLiveData<CovidStats> data = new MutableLiveData<>();

        api.getGlobalStats().enqueue(
                new Callback<CovidStats>() {

                    @Override
                    public void onResponse(@Nullable Call<CovidStats> call,
                                           @NonNull Response<CovidStats> response) {
                        if (response.isSuccessful() && response.body() != null)
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@Nullable Call<CovidStats> call, @Nullable Throwable t) {

                        if (t != null)
                            t.printStackTrace();
                    }
                });
        return data;
    }
}
