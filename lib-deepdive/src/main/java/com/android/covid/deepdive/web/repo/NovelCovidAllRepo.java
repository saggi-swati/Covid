package com.android.covid.deepdive.web.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.android.covid.deepdive.data.NovelCovid;
import com.android.covid.deepdive.web.CovidAllCountryService;
import com.android.covid.retrofit.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovelCovidAllRepo {

    private CovidAllCountryService api;

    private static NovelCovidAllRepo mInstance;

    public static NovelCovidAllRepo getInstance() {
        if (mInstance == null) {
            mInstance = new NovelCovidAllRepo();
        }
        return mInstance;
    }

    private NovelCovidAllRepo() {

        if (api == null) {
            api = RetrofitFactory.buildDeepDiveService(CovidAllCountryService.class);
        }
    }

    public MutableLiveData<List<NovelCovid>> getNovelCovidAllCountryData() {
        final MutableLiveData<List<NovelCovid>> data = new MutableLiveData<>();

        api.getAllCountryData().enqueue(
                new Callback<List<NovelCovid>>() {

                    @Override
                    public void onResponse(@Nullable Call<List<NovelCovid>> call,
                                           @NonNull Response<List<NovelCovid>> response) {
                        if (response.isSuccessful() && response.body() != null)
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@Nullable Call<List<NovelCovid>> call, @Nullable Throwable t) {

                        if (t != null)
                            t.printStackTrace();
                    }
                });
        return data;
    }
}
