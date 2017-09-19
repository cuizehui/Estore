package com.example.cuizehui.estore.viewpagers_views;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.adapter.HomeViewpagerLVAdapter;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.interfaces.DaggerHomePagerViewComponent;
import com.example.cuizehui.estore.interfaces.HomePagerViewComponent;
import com.example.cuizehui.estore.module.HomepagerViewModule;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuizehui on 17-9-14.
 */

public class HomepagerView extends BasePagerView {

    public ListView home_listView;
    public TextView textView;

    @Inject   HomeViewpagerLVAdapter homeViewpagerLVAdapter;

    public HomepagerView(MainActivity mainActivity) {
        super(mainActivity);
    }


    @Override
    protected void initView() {
        super.initView();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        //绑定任意view
        View homeview=inflater.inflate(R.layout.main_viewpager_home,null);

        home_listView=  homeview.findViewById(R.id.home_pager_lv);

        home_listView.setAdapter(homeViewpagerLVAdapter);


        basePager_fl.addView(homeview);


    }

    @Override
    protected void initDate() {
        super.initDate();

        HomePagerViewComponent homePagerViewComponent= DaggerHomePagerViewComponent
                .builder()
                .homepagerViewModule(new HomepagerViewModule(mainActivity))
                .build();
        homePagerViewComponent.inject(this);



    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }
}
