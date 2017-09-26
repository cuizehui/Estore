package com.example.cuizehui.estore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuizehui.estore.MainActivity;
import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.User;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;
import com.facebook.drawee.view.DraweeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人信息（收货地址）Activity
 */
public class PersonalInfoActivity extends BaseActivity {



    @BindView(R.id.user_nickname_tv)
    TextView userNicknameTv;
    @BindView(R.id.user_phone_tv)
    TextView userPhoneTv;
    @BindView(R.id.user_sex_tv)
    TextView userSexTv;
    @BindView(R.id.user_birth_tv)
    TextView userBirthTv;
   TextView userHomeTv;
    @BindView(R.id.user_personid_tv)
    TextView userPersonidTv;
    @BindView(R.id.receip_ll)
    LinearLayout receipLl;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();

    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }

    @Override
    protected void initData() {
        super.initData();
        user  = MyApplication.getInstance(this).getUser();

    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        receipLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PersonalInfoActivity.this,AdressMenagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
