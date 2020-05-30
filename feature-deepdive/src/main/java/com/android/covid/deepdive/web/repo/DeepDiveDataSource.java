package com.android.covid.deepdive.web.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.android.covid.deepdive.data.CovidCountryInfo;
import com.android.covid.deepdive.data.CovidCountry;
import com.android.covid.deepdive.web.DeepDiveService;
import com.android.covid.network.State;
import com.android.covid.retrofit.RetrofitFactory;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeepDiveDataSource extends PageKeyedDataSource<Long, CovidCountryInfo> {

    private static final String TAG = DeepDiveDataSource.class.getSimpleName();


    private MutableLiveData<State> networkState;
    private MutableLiveData<State> initialLoading;

    private static DeepDiveService api;


    DeepDiveDataSource() {

        if (api == null) {
            api = RetrofitFactory.buildDeepDiveService(DeepDiveService.class);
        }

        networkState = new MutableLiveData<>();
        initialLoading = new MutableLiveData<>();
    }


    public MutableLiveData<State> getNetworkState() {
        return networkState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, CovidCountryInfo> callback) {

        initialLoading.postValue(State.LOADING);
        networkState.postValue(State.LOADING);

        api.getAllCountryData(params.requestedLoadSize, 1, "total_cases", "desc")
                .enqueue(new Callback<CovidCountry>() {
                    @Override
                    public void onResponse(@NotNull Call<CovidCountry> call, @NotNull Response<CovidCountry> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onResult(response.body().data.covidCountryInfos, null, 2L);
                            initialLoading.postValue(State.SUCCESS);
                            networkState.postValue(State.SUCCESS);

                        } else {
                            initialLoading.postValue(new State(State.Status.FAILED, response.message()));
                            networkState.postValue(new State(State.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(@Nullable Call<CovidCountry> call, @Nullable Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new State(State.Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, CovidCountryInfo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, CovidCountryInfo> callback) {

        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(State.LOADING);

        api.getAllCountryData(params.requestedLoadSize, params.key, "total_cases", "desc").enqueue(new Callback<CovidCountry>() {
            @Override
            public void onResponse(@NonNull Call<CovidCountry> call, @NonNull Response<CovidCountry> response) {
                if (response.isSuccessful() && response.body() != null) {
                    long nextKey = (params.key == response.body().data.covidCountryPaginationMetadata.totalRecords) ? params.key : params.key + 1;
                    callback.onResult(response.body().data.covidCountryInfos, nextKey);
                    networkState.postValue(State.SUCCESS);

                } else
                    networkState.postValue(new State(State.Status.FAILED, response.message()));
            }

            @Override
            public void onFailure(@NotNull Call<CovidCountry> call, @Nullable Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                networkState.postValue(new State(State.Status.FAILED, errorMessage));
            }
        });
    }
}
