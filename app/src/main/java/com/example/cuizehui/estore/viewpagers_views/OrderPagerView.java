package com.example.cuizehui.estore.viewpagers_views;

import android.view.LayoutInflater;
import android.view.View;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;

/**
 * Created by cuizehui on 17-9-14.
 */

public class OrderPagerView extends  BasePagerView {
    public OrderPagerView(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void initView() {
        super.initView();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View orderview=inflater.inflate(R.layout.main_viewpager_order,null);
        basePager_fl.addView(orderview);

    }

    @Override
    protected void initDate() {
        super.initDate();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
