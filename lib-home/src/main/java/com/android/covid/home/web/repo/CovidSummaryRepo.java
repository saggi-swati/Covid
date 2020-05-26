package com.android.covid.home.web.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.home.data.CovidStats;
import com.android.covid.home.web.CovidGlobalService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidSummaryRepo {

    private CovidGlobalService gitHubService;

    private static CovidSummaryRepo mInstance;


    public static CovidSummaryRepo getInstance() {
        if (mInstance == null) {
            mInstance = new CovidSummaryRepo();
        }
        return mInstance;
    }

    private CovidSummaryRepo() {

        gitHubService = new Retrofit.Builder()
                .baseUrl(CovidGlobalService.COVID_BASE_HTTPS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidGlobalService.class);
    }

    public MutableLiveData<CovidStats> getCovidSummary() {
        final MutableLiveData<CovidStats> data = new MutableLiveData<>();

        gitHubService.getGlobalStats().enqueue(
                new Callback<CovidStats>() {

                    @Override
                    public void onResponse(@Nullable Call<CovidStats> call,
                                           @NonNull Response<CovidStats> response) {
                        if(response.isSuccessful() && response.body() != null)
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
