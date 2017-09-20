package com.example.cuizehui.estore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.databaseutil.UserDb;
import com.example.cuizehui.estore.entity.ShopDaTa;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductMessageActivity extends BaseActivity {

    @BindView(R.id.shopname)
    TextView shopname_textView;
    @BindView(R.id.tv_add_shopcar)
    TextView addshopcar_textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_message);
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {
        super.initData();
        ButterKnife.bind(this);
        ShopDaTa shopDaTa = (ShopDaTa) getIntent().getSerializableExtra("shopData");
        Log.d("shopdata name",""+shopDaTa.getProductName());
        if(shopname_textView!=null){
            shopname_textView.setText(shopDaTa.getProductName());
        }
        else {
            Log.d("textview","null");
        }

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        addshopcar_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //执行动画


                //database
                UserDb userDb=MyApplication.getInstance(ProductMessageActivity.this).getUserDatedb();
                //插入表 商品名 和商品个数 商家名（便于分类）
                // userDb.insertShopCarTable();

            }
        });
    }

}
