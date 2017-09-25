package com.example.cuizehui.estore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.adapter.AdressMenagerRecyleviewAdapter;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.ShopAdress;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 地址管理Activity
 */
public class AdressMenagerActivity extends BaseActivity {
    @BindView(R.id.adressRecyclerview)
    RecyclerView recyclerView;

    ArrayList<ShopAdress> shopAdresses;

    AdressMenagerRecyleviewAdapter adressMenagerRecyleviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_menager);
        ButterKnife.bind(this);
        initData();
        initView();
        initData();

    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }

    @Override
    protected void initData() {
        super.initData();
        //数据库先拉一遍

        adressMenagerRecyleviewAdapter=new AdressMenagerRecyleviewAdapter(this,shopAdresses);

    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setAdapter(adressMenagerRecyleviewAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
