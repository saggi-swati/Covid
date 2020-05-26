package com.android.covid.deepdive.web.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.android.covid.deepdive.data.CovidCountryDetail;
import com.android.covid.deepdive.data.CovidCountrySearch;
import com.android.covid.deepdive.web.CovidAllCountryService;
import com.android.covid.network.NetworkState;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CovidDataSource extends PageKeyedDataSource<Long, CovidCountryDetail> {

    private static final String TAG = CovidDataSource.class.getSimpleName();


    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<NetworkState> initialLoading;

    private static CovidAllCountryService api;


    CovidDataSource() {

        if (api == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CovidAllCountryService.COVID_BASE_HTTPS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(CovidAllCountryService.class);
        }

        networkState = new MutableLiveData<>();
        initialLoading = new MutableLiveData<>();
    }


    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, CovidCountryDetail> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        api.getAllCountryData(params.requestedLoadSize, 1, "total_cases", "desc")
                .enqueue(new Callback<CovidCountrySearch>() {
                    @Override
                    public void onResponse(@NotNull Call<CovidCountrySearch> call, @NotNull Response<CovidCountrySearch> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onResult(response.body().data.rows, null, 2L);
                            initialLoading.postValue(NetworkState.LOADED);
                            networkState.postValue(NetworkState.LOADED);

                        } else {
                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(@Nullable Call<CovidCountrySearch> call, @Nullable Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, CovidCountryDetail> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, CovidCountryDetail> callback) {

        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);

        api.getAllCountryData(params.requestedLoadSize, params.key, "total_cases", "desc").enqueue(new Callback<CovidCountrySearch>() {
            @Override
            public void onResponse(@NonNull Call<CovidCountrySearch> call, @NonNull Response<CovidCountrySearch> response) {
                if (response.isSuccessful() && response.body() != null) {
                    long nextKey = (params.key == response.body().data.paginationMetadata.totalRecords) ? params.key : params.key + 1;
                    callback.onResult(response.body().data.rows, nextKey);
                    networkState.postValue(NetworkState.LOADED);

                } else
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
            }

            @Override
            public void onFailure(@NotNull Call<CovidCountrySearch> call, @Nullable Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }
}
