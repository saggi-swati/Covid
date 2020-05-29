package com.android.covid.covidnews.web;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.android.covid.covidnews.model.Article;
import com.android.covid.covidnews.model.News;
import com.android.covid.network.NetworkState;
import com.android.covid.retrofit.RetrofitFactory;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsDataSource extends PageKeyedDataSource<Long, Article> {


    private static final String API_KEY = "9d40258647d849a0a45a10dc2cc630b4";
    private static final String TAG = NewsDataSource.class.getSimpleName();
    private static final String NEWS_QUERY = "COVID";


    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<NetworkState> initialLoading;

    private static CovidNewsService api;


    NewsDataSource() {

        if (api == null) {

            api = RetrofitFactory.buildNewsService(CovidNewsService.class);
        }

        networkState = new MutableLiveData<>();
        initialLoading = new MutableLiveData<>();
    }


    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, Article> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        api.fetchFeed(NEWS_QUERY, API_KEY, 1, params.requestedLoadSize)

                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onResult(response.body().getArticles(), null, 2L);
                            initialLoading.postValue(NetworkState.LOADED);
                            networkState.postValue(NetworkState.LOADED);

                        } else {
                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(@Nullable Call<News> call, @Nullable Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, Article> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, Article> callback) {

        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);
        api.fetchFeed(NEWS_QUERY, API_KEY, params.key, params.requestedLoadSize).enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                if (response.isSuccessful() && response.body() != null) {
                    long nextKey = (params.key.equals(response.body().getTotalResults())) ? params.key : params.key + 1;
                    callback.onResult(response.body().getArticles(), nextKey);
                    networkState.postValue(NetworkState.LOADED);

                } else
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
            }

            @Override
            public void onFailure(@Nullable Call<News> call, @Nullable Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }
}
