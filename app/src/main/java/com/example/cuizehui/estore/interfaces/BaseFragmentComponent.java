package com.example.cuizehui.estore.interfaces;

import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.fragment.BaseFragment;
import com.example.cuizehui.estore.module.BaseFragmentModule;

import dagger.Component;

/**
 * Created by cuizehui on 17-9-14.
 */
@Component(modules = BaseFragmentModule.class)
public interface BaseFragmentComponent {
          void inject(BaseFragment baseFragment);
}
