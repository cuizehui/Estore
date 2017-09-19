package com.example.cuizehui.estore.viewpagers_views;

import android.view.LayoutInflater;
import android.view.View;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;

/**
 * Created by cuizehui on 17-9-14.
 */

public class ShopPagerView  extends  BasePagerView{
    public ShopPagerView(MainActivity mainActivity) {
        super(mainActivity);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initView() {
        super.initView();
        basePager_fl.removeAllViews();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View shopview=inflater.inflate(R.layout.main_viewpager_shop,null);
        basePager_fl.addView(shopview);

    }

    @Override
    protected void initDate() {
        super.initDate();
    }
}
