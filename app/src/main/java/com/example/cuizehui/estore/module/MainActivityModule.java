package com.example.cuizehui.estore.module;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.PreActivity;
import com.example.cuizehui.estore.adapter.MainViewPagerAdapter;
import com.example.cuizehui.estore.viewpagers_views.BasePagerView;
import com.example.cuizehui.estore.viewpagers_views.HomepagerView;
import com.example.cuizehui.estore.viewpagers_views.MinePagerView;
import com.example.cuizehui.estore.viewpagers_views.ShopCarView;
import com.example.cuizehui.estore.viewpagers_views.ShopPagerView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuizehui on 17-9-14.
 */
@Module

public class MainActivityModule {

    //mainActivity 引用的获取  ,方便fragment  获取
    private   MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {

        this.mainActivity=mainActivity;

    }

    @Provides
    @PreActivity

    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @PreActivity
    @Provides
    public ArrayList<BasePagerView> providePagerViews(){

        ArrayList<BasePagerView>   pagerViews=new ArrayList<>();
        pagerViews.add(new HomepagerView(mainActivity));
        pagerViews.add(new ShopPagerView(mainActivity));
        pagerViews.add(new ShopCarView(mainActivity));
        pagerViews.add(new MinePagerView(mainActivity));

        return  pagerViews;
    }

    //通过参数的方法 也能进入到上面的代码 获取到对象 ，另外这段代码可以省略和直接在在MainViewPagerAdapter的构造函数中添加注解的效果一至
    @PreActivity
    @Provides
    public MainViewPagerAdapter providemainViewPagerAdapter(ArrayList<BasePagerView> pagerViewslist){
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(pagerViewslist);
        return  mainViewPagerAdapter;
    }



}
