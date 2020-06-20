package com.android.covid.home.injection;

import com.android.covid.home.model.repo.CovidSummaryRepo;
import com.android.covid.home.model.web.CovidGlobalService;
import com.android.covid.retrofit.RetrofitFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    public CovidGlobalService provideCovidGlobalService() {
        return RetrofitFactory.buildGlobalStatsService(CovidGlobalService.class);
    }

    @Provides
    public CovidSummaryRepo provideCovidSummaryRepo() {
        return CovidSummaryRepo.getInstance();
    }
}
