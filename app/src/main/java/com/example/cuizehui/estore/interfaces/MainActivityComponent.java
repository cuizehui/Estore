package com.example.cuizehui.estore.interfaces;

import com.example.cuizehui.estore.MainActivity;

import com.example.cuizehui.estore.PreActivity;
import com.example.cuizehui.estore.module.MainActivityModule;

import dagger.Component;

/**
 * Created by cuizehui on 17-9-14.
 *
 */
@PreActivity
@Component (modules = MainActivityModule.class,dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
