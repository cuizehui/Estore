package com.example.cuizehui.estore.viewpagers_views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.R;

import butterknife.BindView;


/**
 * Created by cuizehui on 17-9-14.
 */

public class ShopCarView extends  BasePagerView {
    
    @BindView(R.id.shopcar_recyclerView)
    RecyclerView shopcar_recyclerView;

    public ShopCarView(MainActivity mainActivity) {
        super(mainActivity);
        initDate();
        initView();
        initEvent();
    }

    @Override
    protected void initView() {
        super.initView();
        LayoutInflater inflater =LayoutInflater.from(mainActivity);
        View orderview=inflater.inflate(R.layout.main_viewpager_shopcar,null);
        basePager_fl.addView(orderview);

    }

    @Override
    protected void initDate() {
        super.initDate();

        //拉取数据库 购物车商品信息

        //recycleviewAdapter 生成，在这里 动态生成 同类商品个数view

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
