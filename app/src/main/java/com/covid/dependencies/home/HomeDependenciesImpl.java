package com.covid.dependencies.home;

import android.app.Application;

import com.android.covid.home.ui.dependencies.HomeDependencies;
import com.covid.CovidApplication;

public class HomeDependenciesImpl implements HomeDependencies {

    @Override
    public Application getCovidApplication() {
        return CovidApplication.get();
    }

}
