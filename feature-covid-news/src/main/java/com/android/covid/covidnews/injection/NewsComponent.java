package com.android.covid.covidnews.injection;

import com.android.covid.covidnews.model.web.NewsDataFactory;
import com.android.covid.covidnews.model.web.NewsDataSource;
import com.android.covid.covidnews.viewmodel.NewsViewModel;

import dagger.Component;

@Component(modules = {NewsModule.class})
public interface NewsComponent {

    @Component.Builder
    public interface Builder {

        public NewsComponent build();
    }

    public void inject(NewsDataSource dataSource);

    public void inject(NewsDataFactory dataFactory);

    public void inject(NewsViewModel viewModel);
}
