package com.android.covid.home.model.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.home.injection.DaggerCovidSummaryComponent;
import com.android.covid.home.model.NovelCovidDetail;
import com.android.covid.home.model.NovelCovidGlobalStats;
import com.android.covid.home.model.web.CovidGlobalService;
import com.android.covid.network.State;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidSummaryRepo {

    private static CovidSummaryRepo mInstance;
    private MutableLiveData<NovelCovidDetail> data;
    private MutableLiveData<State> isLoading;

    @Inject
    public CovidGlobalService api;

    public static CovidSummaryRepo getInstance() {
        if (mInstance == null) {
            mInstance = new CovidSummaryRepo();
        }
        return mInstance;
    }

    private CovidSummaryRepo() {
        isLoading = new MutableLiveData<>();
        data = new MutableLiveData<>();

        DaggerCovidSummaryComponent.builder().build().inject(this);
    }

    public MutableLiveData<State> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<NovelCovidDetail> getCovidSummary() {

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
