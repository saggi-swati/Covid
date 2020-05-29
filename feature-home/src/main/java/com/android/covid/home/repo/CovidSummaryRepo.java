package com.android.covid.home.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.home.data.NovelCovidDetail;
import com.android.covid.home.data.NovelCovidGlobalStats;
import com.android.covid.home.repo.persistence.NovelCovidGlobalPersistent;
import com.android.covid.home.repo.web.CovidGlobalService;
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
            api = RetrofitFactory.buildGlobalStatsService(CovidGlobalService.class);
        }
    }


    public MutableLiveData<NovelCovidDetail> getCovidSummary() {
        final MutableLiveData<NovelCovidDetail> data = new MutableLiveData<>();
        isLoading.setValue(true);
        api.getCovidGlobalStats().enqueue(
                new Callback<NovelCovidGlobalStats>() {

                    @Override
                    public void onResponse(@Nullable Call<NovelCovidGlobalStats> call,
                                           @NonNull Response<NovelCovidGlobalStats> response) {
                        isLoading.setValue(false);
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body().globalStats.get(0));
                            NovelCovidGlobalPersistent
                                    .get()
                                    .cacheCovidDetail(response.body().globalStats.get(0));
                        } else
                            data.setValue(NovelCovidGlobalPersistent.get().getCachedCovidDetail());
                    }

                    @Override
                    public void onFailure(@Nullable Call<NovelCovidGlobalStats> call, @Nullable Throwable t) {
                        isLoading.setValue(false);
                        data.setValue(NovelCovidGlobalPersistent.get().getCachedCovidDetail());
                        if (t != null)
                            t.printStackTrace();
                    }
                });
        return data;
    }

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
