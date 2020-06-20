package com.covid.injection;

import android.app.Application;

import com.android.covid.home.injection.HomeModule;
import com.android.covid.home.model.repo.CovidSummaryRepo;
import com.covid.CovidApplication;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {HomeModule.class})
public interface AppComponent {

    @Component.Builder
    public interface Builder {

        @BindsInstance
        public Builder bindApplication(Application application);

        public AppComponent build();

    }

    public void inject(CovidApplication app);
}
