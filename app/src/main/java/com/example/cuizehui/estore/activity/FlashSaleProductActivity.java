package com.example.cuizehui.estore.activity;

import android.os.Bundle;

import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

public class FlashSaleProductActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_sale_product);


    }

    @Override
    protected void initData() {
        super.initData();
        //获取到差值
    }

    @Override
    protected void initView() {
        super.initView();
        //计时器显示
    }

    @Override
    protected void initEvent() {
        super.initEvent();
          // 能否 购买  判断 跳转订单

    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {


    }
}
