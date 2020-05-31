package com.android.covid.deepdive.web.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.web.DeepDiveService;
import com.android.covid.network.State;
import com.android.covid.retrofit.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeepDiveRepo {

    private DeepDiveService api;

    private static DeepDiveRepo mInstance;

    private MutableLiveData<State> isLoading;

    public static DeepDiveRepo getInstance() {
        if (mInstance == null) {
            mInstance = new DeepDiveRepo();
        }
        return mInstance;
    }

    private DeepDiveRepo() {

        if (api == null) {
            api = RetrofitFactory.buildDeepDiveService(DeepDiveService.class);
        }

        isLoading = new MutableLiveData<>();
    }

    public MutableLiveData<List<NovelCovid>> getNovelCovidAllCountryData() {
        isLoading.setValue(State.LOADING);
        final MutableLiveData<List<NovelCovid>> data = new MutableLiveData<>();

        api.getAllCountryData().enqueue(
                new Callback<List<NovelCovid>>() {

                    @Override
                    public void onResponse(@Nullable Call<List<NovelCovid>> call,
                                           @NonNull Response<List<NovelCovid>> response) {
                        isLoading.setValue(State.SUCCESS);
                        if (response.isSuccessful() && response.body() != null)
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@Nullable Call<List<NovelCovid>> call, @Nullable Throwable t) {
                        isLoading.setValue(new State(State.Status.FAILED, t != null ? t.getMessage() : ""));
                        if (t != null)
                            t.printStackTrace();
                    }
                });
        return data;
    }

    public MutableLiveData<NovelCovid> getCountryData(String country) {
        isLoading.setValue(State.LOADING);
        final MutableLiveData<NovelCovid> countryData = new MutableLiveData<>();

        api.getCountryData(country).enqueue(
                new Callback<NovelCovid>() {

                    @Override
                    public void onResponse(@Nullable Call<NovelCovid> call,
                                           @NonNull Response<NovelCovid> response) {
                        isLoading.setValue(State.SUCCESS);
                        if (response.isSuccessful() && response.body() != null)
                            countryData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@Nullable Call<NovelCovid> call, @Nullable Throwable t) {
                        isLoading.setValue(new State(State.Status.FAILED, t != null ? t.getMessage() : ""));
                        if (t != null)
                            t.printStackTrace();
                    }
                });
        return countryData;
    }

    public MutableLiveData<State> getIsLoading() {
        return isLoading;
    }
}
