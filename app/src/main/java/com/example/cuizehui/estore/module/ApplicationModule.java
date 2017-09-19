package com.example.cuizehui.estore.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuizehui on 17-9-13.
 * \
 */

@Module
public class ApplicationModule {


    private Application application;

    public ApplicationModule(Application application){
        this.application=application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }

}