package com.android.covid.home;

import com.android.covid.home.ui.dependencies.HomeDependencies;

public class HomeModule {

    private static HomeModule instance;
    private HomeDependencies dependencies;

    private HomeModule(HomeDependencies dependencies) {
        this.dependencies = dependencies;
    }

    public static void init(HomeDependencies dependencies) {
        instance = new HomeModule(dependencies);
    }

    public HomeDependencies getDependencies() {
        return dependencies;
    }

    public static HomeModule get() {
        return instance;
    }
}
