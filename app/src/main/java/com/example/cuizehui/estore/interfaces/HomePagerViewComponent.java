package com.example.cuizehui.estore.interfaces;

import com.example.cuizehui.estore.module.HomepagerViewModule;
import com.example.cuizehui.estore.module.MainActivityModule;
import com.example.cuizehui.estore.viewpagers_views.HomepagerView;

import dagger.Component;

/**
 * Created by cuizehui on 17-9-19.
 */
@Component(modules = HomepagerViewModule.class)
public interface HomePagerViewComponent {
    //注入方法
    void inject(HomepagerView homepagerView);

}
