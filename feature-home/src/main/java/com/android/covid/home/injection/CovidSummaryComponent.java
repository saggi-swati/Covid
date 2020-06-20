package com.android.covid.home.injection;

import com.android.covid.home.model.repo.CovidSummaryRepo;
import com.android.covid.home.viewmodel.HomeViewModel;

import dagger.Component;

@Component(modules = {HomeModule.class})
public interface CovidSummaryComponent {

    @Component.Builder
    public interface Builder {

        public CovidSummaryComponent build();

    }

    public void inject(CovidSummaryRepo repo);

    public void inject(HomeViewModel viewModel);
}
