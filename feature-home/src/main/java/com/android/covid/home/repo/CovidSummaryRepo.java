package com.android.covid.home.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.data.NovelCovidGlobalStats;
import com.android.covid.home.repo.web.CovidGlobalService;
import com.android.covid.network.State;
import com.android.covid.retrofit.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidSummaryRepo {

    private static CovidSummaryRepo mInstance;
    private MutableLiveData<State> isLoading;
    private CovidGlobalService api;

    public static CovidSummaryRepo getInstance() {
        if (mInstance == null) {
            mInstance = new CovidSummaryRepo();
        }
        return mInstance;
    }

    private CovidSummaryRepo() {
        if (api == null) {
            api = RetrofitFactory.buildGlobalStatsService(CovidGlobalService.class);
        }
        isLoading = new MutableLiveData<>();
    }

    public MutableLiveData<State> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<NovelCovidDetail> getCovidSummary() {
        final MutableLiveData<NovelCovidDetail> data = new MutableLiveData<>();
        isLoading.setValue(State.LOADING);

        api.getCovidGlobalStats().enqueue(
                new Callback<NovelCovidGlobalStats>() {

                    @Override
                    public void onResponse(@Nullable Call<NovelCovidGlobalStats> call,
                                           @NonNull Response<NovelCovidGlobalStats> response) {
                        isLoading.setValue(State.SUCCESS);
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body().globalStats.get(0));
                        } else
                            isLoading.setValue(new State(State.Status.FAILED, response.message()));

                    }

                    @Override
                    public void onFailure(@Nullable Call<NovelCovidGlobalStats> call, @Nullable Throwable t) {
                        isLoading.setValue(new State(State.Status.FAILED, t != null ? t.getMessage() : ""));
                        if (t != null)
                            t.printStackTrace();
                    }
                });

        return data;
    }
}
