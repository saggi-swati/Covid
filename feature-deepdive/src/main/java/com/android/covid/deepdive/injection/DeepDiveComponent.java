package com.android.covid.deepdive.injection;

import com.android.covid.deepdive.model.repo.DeepDiveRepo;
import com.android.covid.deepdive.viewmodel.DeepDiveViewModel;

import dagger.Component;

@Component(modules = {DeepDiveModule.class})
public interface DeepDiveComponent {

    @Component.Builder
    public interface Builder {

        public DeepDiveComponent build();
    }

    public void inject(DeepDiveRepo repo);

    public void inject(DeepDiveViewModel viewModel);
}
