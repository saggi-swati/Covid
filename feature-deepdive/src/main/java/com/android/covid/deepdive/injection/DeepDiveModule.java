package com.android.covid.deepdive.injection;

import com.android.covid.deepdive.model.repo.DeepDiveRepo;
import com.android.covid.deepdive.model.web.DeepDiveService;
import com.android.covid.retrofit.RetrofitFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class DeepDiveModule {

    @Provides
    public DeepDiveService provideDeepDiveService() {
        return RetrofitFactory.buildDeepDiveService(DeepDiveService.class);
    }

    @Provides
    public DeepDiveRepo provideDeepDiveRepo(){
        return DeepDiveRepo.getInstance();
    }
}
