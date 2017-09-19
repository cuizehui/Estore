package com.example.cuizehui.estore.interfaces;

import android.app.Application;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.module.ApplicationModule;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cuizehui on 17-9-13.
 * * 注入器
 */
@Singleton
@Component (modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application getApplication();

}
