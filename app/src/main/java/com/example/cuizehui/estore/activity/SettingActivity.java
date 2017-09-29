package com.example.cuizehui.estore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuizehui.estore.MyApplication;
import com.example.cuizehui.estore.R;
import com.example.cuizehui.estore.base.BaseActivity;
import com.example.cuizehui.estore.entity.RefrushMain;
import com.example.cuizehui.estore.entity.StringFlag;
import com.example.cuizehui.estore.interfaces.ApplicationComponent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingActivity extends BaseActivity {


    @BindView(R.id.person_ll)
    LinearLayout personLl;
    @BindView(R.id.logout_set)
    Button logoutBtn;
    @BindView(R.id.person_massege)
    TextView personMassege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
         initData();
        initView();
        initEvent();
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {

    }


    @Override
    protected void initEvent() {
        super.initEvent();
        personMassege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SettingActivity.this,PersonalInfoActivity.class);
                startActivity(intent,true);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();

        //退出登录
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefrushMain refrushMainui=new RefrushMain();
                MyApplication.getInstance(SettingActivity.this).setUser(null);
                //设置文字为点击登录
                //并刷新界面
                Toast.makeText(SettingActivity.this,"已经退出登录",Toast.LENGTH_SHORT).show();

                EventBus.getDefault().post(refrushMainui);


                StringFlag stringFlag=new StringFlag();
                stringFlag.setFlag("logout");
                EventBus.getDefault().post(stringFlag);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }



    }
