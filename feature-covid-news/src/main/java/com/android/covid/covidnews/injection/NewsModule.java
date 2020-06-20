package com.android.covid.covidnews.injection;

import com.android.covid.covidnews.model.web.CovidNewsService;
import com.android.covid.covidnews.model.web.NewsDataFactory;
import com.android.covid.covidnews.model.web.NewsDataSource;
import com.android.covid.retrofit.RetrofitFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    public CovidNewsService provideCovidNewsService() {
        return RetrofitFactory.buildNewsService(CovidNewsService.class);
    }

    @Provides
    public NewsDataSource providesNewsDataSource() {
        return new NewsDataSource();
    }

    @Provides
    public NewsDataFactory provideNewsDataFactory() {
        return new NewsDataFactory();
    }
}
